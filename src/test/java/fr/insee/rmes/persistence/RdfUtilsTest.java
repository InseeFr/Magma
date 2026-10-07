package fr.insee.rmes.persistence;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RdfUtilsTest {

    @Test
    void shouldReturnNotNullValueFactory(){
        assertNotNull(RdfUtils.factory.toString());
    }

}