package fr.insee.rmes.configuration;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Customization to get Keycloak Role and get preffered_username as principal
        JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        jwtAuthenticationConverter.setPrincipalClaimName("preferred_username");
        return jwtAuthenticationConverter;
    }

    Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
        return new Converter<Jwt, Collection<GrantedAuthority>>() {
            @Override
            @SuppressWarnings({"unchecked"})
            public Collection<GrantedAuthority> convert(Jwt source) {
                return ((Map<String, List<String>>) source.getClaim("realm_access")).get("roles").stream()
                        .map(s -> new GrantedAuthority() {
                            @Override
                            public String getAuthority() {
                                return "ROLE_" + s;
                            }

                            @Override
                            public String toString() {
                                return getAuthority();
                            }
                        }).collect(Collectors.toList());
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable csrf because of API mode
        http.csrf().disable();
        // allow jwt bearer authentication
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> {
            jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter());
        }));
        // configuration pour Swagger
        http.authorizeRequests(
                        authz -> authz.antMatchers(HttpMethod.OPTIONS).permitAll()
                                .antMatchers("/", "/swagger-ui-magma.html","/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .antMatchers("/user").permitAll()
                                .antMatchers("/admin").permitAll()
                                .antMatchers("/operations/series").authenticated()
                                .antMatchers("/operations/serie/{id}/operations").authenticated()
                                .antMatchers("/operations/serie/{id}/").authenticated()
                                .antMatchers("/operations/operation/{id}").authenticated()
                                .antMatchers("/listesCodes").authenticated()
                                .antMatchers("/listeCode/{id}").authenticated()
                                .antMatchers("/structures").authenticated()
                                .antMatchers("/structure/{id}").authenticated()
                                .antMatchers("/composants").authenticated()
                                .antMatchers("/composant/{id}").authenticated()
                                .antMatchers("/concepts").authenticated()
                                .anyRequest().denyAll());
        // autorisation d'afficher des frames dans l'appli pour afficher la console h2
        // (risque de clickjacking)
        http.headers().frameOptions().sameOrigin();
    }
}

