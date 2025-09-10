package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IdTest {

    @Test
    void shouldReturnToString(){
        String mockedString="mockedString";
        Id id = new Id(mockedString);
        assertEquals(mockedString,id.toString());
    }

}