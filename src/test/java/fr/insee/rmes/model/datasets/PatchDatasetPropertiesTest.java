package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PatchDatasetPropertiesTest {

    @Test
    void shouldCheckClassOfEnumContainsAtLeastOneValue(){
        PatchDatasetProperties[] numberOfValues = PatchDatasetProperties.values();
        assertTrue(numberOfValues.length>0);
    }

}