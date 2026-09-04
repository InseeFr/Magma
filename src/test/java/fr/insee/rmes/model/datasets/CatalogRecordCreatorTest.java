package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CatalogRecordCreatorTest {

    @Test
    void shouldInitializeCatalogRecordCreator(){
        String value = "mockedValue";
        CatalogRecordCreator catalogRecordCreator = new CatalogRecordCreator(value);
        assertEquals(value,catalogRecordCreator.getValue());
    }

}