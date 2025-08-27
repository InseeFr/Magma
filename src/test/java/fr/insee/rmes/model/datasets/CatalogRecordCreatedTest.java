package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CatalogRecordCreatedTest {

    @Test
    void shouldInitializeCatalogRecordCreated(){
        String value = "mockedValue";
        CatalogRecordCreated catalogRecordCreated = new CatalogRecordCreated(value);
        assertEquals(value,catalogRecordCreated.getValue());
    }


}