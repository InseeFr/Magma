package fr.insee.rmes.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidationStatusTest {

    @Test
    void shouldCheckClassOfEnumContainsAtLeastOneValue(){
        ValidationStatus[] numberOfValues = ValidationStatus.values();
        assertTrue(numberOfValues.length>0);
    }

    @Test
    void shouldCheckReturnTheSameValue(){
        assertEquals(ValidationStatus.MODIFIED.getValue(),ValidationStatus.MODIFIED.toString());
    }
}