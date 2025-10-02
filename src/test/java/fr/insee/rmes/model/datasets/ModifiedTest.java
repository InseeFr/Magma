package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModifiedTest {

    @Test
    void shouldReturnToString(){
        String mockedString="mockedString";
        Modified modified = new Modified(mockedString);
        assertEquals(mockedString,modified.toString());
    }

}