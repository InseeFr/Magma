package fr.insee.rmes.configuration;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(
        prefix = "fr.insee.rmes.security.token"
)
@ConditionalOnWebApplication
@Data
public class InseeSecurityTokenProperties {

    //Chemin pour récupérer la liste des rôles dans le jwt (token)
    private String oidcClaimRole;
    //Chemin pour récupérer le username dans le jwt (token)
    private String oidcClaimUsername;

}
