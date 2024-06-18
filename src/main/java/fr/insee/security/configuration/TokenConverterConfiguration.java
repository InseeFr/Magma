package fr.insee.security.configuration;

import fr.insee.security.internal.InseeJwtGrantedAuthoritiesConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableConfigurationProperties(value = InseeSecurityTokenProperties.class)
public class TokenConverterConfiguration {

    public static final String DEFAULT_ROLE_PREFIX = "" ;
    private final InseeSecurityTokenProperties inseeSecurityTokenProperties;

    public TokenConverterConfiguration(InseeSecurityTokenProperties inseeSecurityTokenProperties) {
        this.inseeSecurityTokenProperties = inseeSecurityTokenProperties;
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setPrincipalClaimName(inseeSecurityTokenProperties.oidcClaimUsername());
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new InseeJwtGrantedAuthoritiesConverter(inseeSecurityTokenProperties.oidcClaimRole(), inseeSecurityTokenProperties.oidcRolesInClaimRole()));
        return jwtAuthenticationConverter;
    }

    @Bean
    static GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(DEFAULT_ROLE_PREFIX);
    }

}
