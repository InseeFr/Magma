package fr.insee.rmes.model.datasets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class SpatialTemporalTest {

    @ParameterizedTest
    @ValueSource(strings = { "element1", "element2","element3" })
    void shouldCheckSpatialTemporalAttributeValue(String string){
        SpatialTemporal spatialTemporal  = new SpatialTemporal(string);
        assertEquals(string,spatialTemporal.getSpatialTemporal());
    }
}