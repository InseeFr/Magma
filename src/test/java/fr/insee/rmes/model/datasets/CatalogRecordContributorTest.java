package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CatalogRecordContributorTest {

    @Test
    void shouldInitializeCatalogRecordContributor(){
        String value = "mockedValue";
        CatalogRecordContributor catalogRecordContributor = new CatalogRecordContributor(value);
        assertEquals(value,catalogRecordContributor.getValue());
    }

}