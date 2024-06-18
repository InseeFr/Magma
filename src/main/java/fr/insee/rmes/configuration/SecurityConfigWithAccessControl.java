package fr.insee.rmes.configuration;

import fr.insee.security.configuration.TokenConverterConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;

import static fr.insee.rmes.configuration.SecurityConfigWithAccessControl.STARTER_SECURITY_DISABLED;
import static java.util.function.Predicate.not;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Slf4j
@Profile("!"+STARTER_SECURITY_DISABLED)
@Import(TokenConverterConfiguration.class)
public class SecurityConfigWithAccessControl {

    public static final String STARTER_SECURITY_DISABLED = "security.disabled";

    private final String administrateurRole;

    private final String gestionnaireDataset;


    private final String[] whiteList;

    public SecurityConfigWithAccessControl(@Value("${fr.insee.rmes.role.administrateur}") String administrateurRole,
                                           @Value("${fr.insee.rmes.role.gestionnaire.dataset}") String gestionnaireDataset,
                                           @Value("#{'${fr.insee.rmes.security.whitelist-matchers}'.split(',')}") String[] whiteList) {
        this.administrateurRole = administrateurRole;
        this.gestionnaireDataset = gestionnaireDataset;
        this.whiteList = whiteList;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Webservice/RestAPI : disable CSRF & no session
        http
                .csrf(AbstractHttpConfigurer::disable)
                //disable sessions (stateless)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(
                                        Arrays.stream(whiteList)
                                                .filter(not(String::isEmpty))
                                                .map(AntPathRequestMatcher::antMatcher)
                                                .toArray(RequestMatcher[]::new)
                                ).permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.PATCH, "/dataset/**")).hasAnyRole(administrateurRole, gestionnaireDataset)
                                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(withDefaults()));
        return http.build();
    }

}
