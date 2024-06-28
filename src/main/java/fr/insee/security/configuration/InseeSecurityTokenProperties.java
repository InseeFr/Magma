package fr.insee.security.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "fr.insee.rmes.security.token"
)
public record InseeSecurityTokenProperties(String oidcClaimRole, String oidcClaimUsername, String oidcRolesInClaimRole, String email) {

}
