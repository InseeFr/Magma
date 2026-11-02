package fr.insee.rmes.configuration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InseeSpringdocPropertiesTest {

    @Test
    void shouldInitializeConstructor(){
        InseeSpringdocProperties inseeSpringdocProperties = new InseeSpringdocProperties();
        assertNotNull(inseeSpringdocProperties);
    }


}