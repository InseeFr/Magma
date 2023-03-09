package fr.insee.rmes.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

        // Démonstration avec un rôle protégeant l'accès à un des endpoints
        @Value("${fr.insee.sndil.starter.role.administrateur}")
        private String administrateurRole;

        @Value("${fr.insee.sndil.starter.role.user}")
        private String defaultRolesForUsers;

        //Par défaut, spring sécurity prefixe les rôles avec cette chaine
        private String ROLE_PREFIX = "ROLE_";

        @Autowired InseeSecurityTokenProperties inseeSecurityTokenProperties;

        //Liste d'URL sur lesquels on n'applique pas de sécurité (swagger; actuator...)
        @Value("#{'${fr.insee.sndil.starter.security.whitelist-matchers}'.split(',')}")
        private String[] whiteList;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http.csrf().disable()
                    .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(whiteList).permitAll()
                            .requestMatchers("/starter/healthcheck").permitAll()
                            .requestMatchers("/starter/healthcheckadmin").hasRole(administrateurRole)
                                .requestMatchers("/structures").hasRole((defaultRolesForUsers))
                                .requestMatchers("/structure/**").hasRole((defaultRolesForUsers))
                                .requestMatchers("/operations/**").hasRole((defaultRolesForUsers))
                                .requestMatchers("/concept/**").hasRole((defaultRolesForUsers))
                                .requestMatchers("/concepts").hasRole((defaultRolesForUsers))
                                .requestMatchers("/listesCodes").hasRole((defaultRolesForUsers))
                                .requestMatchers("/listeCode/**").hasRole((defaultRolesForUsers))
                                .requestMatchers("/composants").hasRole((defaultRolesForUsers))
                                .requestMatchers("/composant/**").hasRole((defaultRolesForUsers))
                                .requestMatchers("/datasets/**").hasRole((defaultRolesForUsers))
                    )
                    .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                    .oauth2ResourceServer(oauth2 -> oauth2.jwt().jwtAuthenticationConverter(jwtAuthenticationConverter()));
                return http.build();
        }
        @Bean
        JwtAuthenticationConverter jwtAuthenticationConverter() {
                JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
                jwtAuthenticationConverter.setPrincipalClaimName(inseeSecurityTokenProperties.getOidcClaimUsername());
                jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
                return jwtAuthenticationConverter;
        }

        Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
                return new Converter<Jwt, Collection<GrantedAuthority>>() {
                        @Override @SuppressWarnings({ "unchecked" }) public Collection<GrantedAuthority> convert(Jwt source) {

                                String[] claimPath = inseeSecurityTokenProperties.getOidcClaimRole().split("\\.");
                                Map<String, Object> claims = source.getClaims();
                                try {

                                        for (int i = 0; i < claimPath.length - 1; i++) {
                                                claims = (Map<String, Object>) claims.get(claimPath[i]);
                                        }

                                        List<String> roles = (List<String>) claims.getOrDefault(claimPath[claimPath.length - 1], new ArrayList<>());
                                        //if we need to add customs roles to every connected user we could define this variable (static or from properties)
                                        roles.addAll(Collections.singleton(defaultRolesForUsers));
                                        return roles.stream().map(s -> new GrantedAuthority() {
                                                @Override public String getAuthority() {
                                                        return ROLE_PREFIX + s;
                                                }

                                                @Override public String toString() {
                                                        return getAuthority();
                                                }
                                        }).collect(Collectors.toList());
                                }
                                catch (ClassCastException e) {
                                        // role path not correctly found, assume that no role for this user
                                        return new ArrayList<>();
                                }
                        }
                };
        }
}


