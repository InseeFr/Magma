package fr.insee.security.configuration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TokenConverterConfigurationTest {

    @Test
    void shouldGrantedAuthorityDefault(){
        String actual = TokenConverterConfiguration.grantedAuthorityDefaults().toString();
        String expected = "org.springframework.security.config.core.GrantedAuthorityDefaults@";
        assertTrue(actual.contains(expected));
    }

    @Test
    void shouldJwtAuthenticationConverter(){
        InseeSecurityTokenProperties property = new InseeSecurityTokenProperties("oidcClaimRole","oidcClaimUsername","oidcRolesInClaimRole","email");
        TokenConverterConfiguration token = new TokenConverterConfiguration(property);
        assertNotNull(token.jwtAuthenticationConverter());
    }

}