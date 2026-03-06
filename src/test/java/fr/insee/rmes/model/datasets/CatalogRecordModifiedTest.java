package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CatalogRecordModifiedTest {

    @Test
    void shouldInitializeCatalogRecordModified(){
        String value = "mockedValue";
        CatalogRecordModified catalogRecordModified = new CatalogRecordModified(value);
        assertEquals(value,catalogRecordModified.getValue());
    }

}