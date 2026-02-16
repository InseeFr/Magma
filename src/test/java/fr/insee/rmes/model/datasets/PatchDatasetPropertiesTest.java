package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PatchDatasetPropertiesTest {

    @Test
    void shouldCheckForDuplicates(){
        List<PatchDatasetProperties> values = Arrays.asList(PatchDatasetProperties.values());
        HashSet<PatchDatasetProperties> set = new HashSet<>(values);
        assertEquals(set.size(),values.size());
    }

}