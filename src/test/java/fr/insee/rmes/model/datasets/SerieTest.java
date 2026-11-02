package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class SerieTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    Serie firstSerie = new Serie("uri", "id", "labelSerieLg1", "labelSerieLg2");
    Serie secondSerie = new Serie();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUriValue(String mockedString){
        secondSerie.setUri(mockedString);
        assertEquals(mockedString,secondSerie.getUri());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckIdValue(String mockedString){
        firstSerie.setId(mockedString);
        assertEquals(mockedString,firstSerie.getId());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLabelSerieLg1Value(String mockedString){
        firstSerie.setLabelSerieLg1(mockedString);
        assertEquals(mockedString,firstSerie.getLabelSerieLg1());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLabelSerieLg2Value(String mockedString){
        firstSerie.setLabelSerieLg2(mockedString);
        assertEquals(mockedString,firstSerie.getLabelSerieLg2());
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstSerie.setAdditionalProperties(additionalProperties);
        assertEquals(additionalProperties,firstSerie.getAdditionalProperties());
    }

}