package fr.insee.rmes.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    public static final String STARTER_SECURITY_ENABLED = "fr.insee.rmes.security.enabled";

    @Value("${fr.insee.rmes.role.administrateur}")
    private String administrateurRole;

    @Value("${fr.insee.rmes.role.gestionnaire.dataset}")
    private String gestionnaireDataset;

    private static final String ROLE_PREFIX = "ROLE_";

    @Autowired InseeSecurityTokenProperties inseeSecurityTokenProperties;

    @Value("#{'${fr.insee.rmes.security.whitelist-matchers}'.split(',')}")
    private String[] whiteList;


    @Bean
    @ConditionalOnProperty(name = STARTER_SECURITY_ENABLED, havingValue = "true")
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Webservice/RestAPI : disable CSRF & no sesion
        http
                .csrf(csrf -> csrf.disable())
                //disable sessions (stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        for (var pattern : whiteList) {
            http.authorizeHttpRequests(authorize ->
                    authorize
                            .requestMatchers(AntPathRequestMatcher.antMatcher(pattern)).permitAll()
            );
        }
        http.authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(whiteList).permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/dataset/{id}/observationNumber")).hasAnyRole(administrateurRole,gestionnaireDataset)
                                .anyRequest().authenticated()
                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
        return http.build();
    }

    @Bean
    @ConditionalOnProperty(name = STARTER_SECURITY_ENABLED, havingValue = "false")
    public SecurityFilterChain filterChain_noSecurity(HttpSecurity http) throws Exception {
        //Allow frames to be able tu use the H2 web console
        http.headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin()
                )
        );

        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
        return http.build();
    }
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setPrincipalClaimName(inseeSecurityTokenProperties.oidcClaimUsername());
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        return jwtAuthenticationConverter;
    }

    Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
        return source -> {

            String[] claimPath = inseeSecurityTokenProperties.oidcClaimRole().split("\\.");
            Map<String, Object> claims = source.getClaims();
            try {

                for (int i = 0; i < claimPath.length - 1; i++) {
                    claims = (Map<String, Object>) claims.get(claimPath[i]);
                }

                List<String> roles = (List<String>) claims.getOrDefault(claimPath[claimPath.length - 1], List.of());
                //if we need to add customs roles to every connected user we could define this variable (static or from properties)
                //roles.addAll(defaultRolesForUsers);
                return Collections.unmodifiableCollection(roles.stream().map(s -> new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return ROLE_PREFIX + s;
                    }

                    @Override
                    public String toString() {
                        return getAuthority();
                    }
                }).toList());
            } catch (ClassCastException e) {
                return List.of();
            }
        };
    }
}
