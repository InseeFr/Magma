package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DisseminationStatusTest {

    @Test
    void shouldReturnValueForDisseminationStatus(){
        String string = "mockedString";
        DisseminationStatus disseminationStatus = new DisseminationStatus(string);
        assertEquals(string,disseminationStatus.toString());
    }
  
}