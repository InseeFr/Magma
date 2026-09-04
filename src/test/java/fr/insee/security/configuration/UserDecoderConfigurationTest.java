package fr.insee.security.configuration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserDecoderConfigurationTest {

    @Test
    void shouldJwtAuthenticationConverter(){
        InseeSecurityTokenProperties property = new InseeSecurityTokenProperties("oidcClaimRole","oidcClaimUsername","oidcRolesInClaimRole","email");
        UserDecoderConfiguration token = new UserDecoderConfiguration(property);
        String signature = token.userDecoder().toString();
        String expected = "fr.insee.security.configuration.UserDecoderConfiguration";
        assertTrue(signature.contains(expected));
    }

}