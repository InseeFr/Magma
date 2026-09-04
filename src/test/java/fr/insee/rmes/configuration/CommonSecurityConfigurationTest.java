package fr.insee.rmes.configuration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommonSecurityConfigurationTest {

    @Test
    void shouldInitializeConstructor(){
        CommonSecurityConfiguration commonSecurityConfiguration = new CommonSecurityConfiguration();
        assertNotNull(commonSecurityConfiguration);
    }

}