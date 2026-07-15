package fr.insee.rmes.magma.security.configuration;

import fr.insee.rmes.magma.security.User;
import fr.insee.rmes.magma.security.UserDecoder;
import fr.insee.rmes.magma.utils.RmesException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

import static fr.insee.rmes.magma.security.configuration.SecurityConfigWithAccessControl.STARTER_SECURITY_DISABLED;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@Configuration
@Profile("!" + STARTER_SECURITY_DISABLED)
@EnableConfigurationProperties(value = InseeSecurityTokenProperties.class)
public class UserDecoderConfiguration {

    private final InseeSecurityTokenProperties inseeSecurityTokenProperties;

    public UserDecoderConfiguration(InseeSecurityTokenProperties inseeSecurityTokenProperties) {
        this.inseeSecurityTokenProperties = inseeSecurityTokenProperties;
    }

    @Bean
    public UserDecoder userDecoder() {
        return principal -> "anonymousUser".equals(principal) ?
                empty() :
                of(buildUserFromToken(((Jwt) principal).getClaims()));
    }

    protected User buildUserFromToken(Map<String, Object> claims) throws RmesException {
        if (claims.isEmpty()) {
            throw new RmesException(HttpStatus.UNAUTHORIZED, "Must be authentified", "empty claims for JWT");
        }
        var id = (String) claims.get(inseeSecurityTokenProperties.oidcClaimUsername());
        var email = (String) claims.get(inseeSecurityTokenProperties.email());
        return new User(id, email);
    }

}
