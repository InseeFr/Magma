package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DisseminationStatusTest {

    @Test
    void shouldReturnToString(){
        String mockedString="mockedString";
        DisseminationStatus disseminationStatus = new DisseminationStatus(mockedString);
        assertEquals(mockedString,disseminationStatus.toString());
    }
}