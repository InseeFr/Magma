package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class SerieTest {

    String uri= "mockedUri";
    String id= "mockedId";
    String labelSerieLg1= "mockedLabelSerieLg1";
    String labelSerieLg2= "mockedLabelSerieLg2";
    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    Serie firstSerie = new Serie(uri,id,labelSerieLg1,labelSerieLg2);
    Serie secondSerie = new Serie();

    @ParameterizedTest
    @ValueSource(strings = { "mockedUri1", "mockedUri2", "mockedUri3" })
    void shouldCheckUri(String mockedString){
        firstSerie.setUri(mockedString);
        assertEquals(mockedString,firstSerie.getUri());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedId1", "mockedId2", "mockedId3" })
    void shouldCheckId(String mockedString){
        firstSerie.setId(mockedString);
        assertEquals(mockedString,firstSerie.getId());
    }

    @ParameterizedTest
    @ValueSource(strings = { "LabelSerieLg1", "LabelSerieLg2", "mockedLabelSerieLg3" })
    void shouldCheckLabelSerieLg1(String mockedString){
        firstSerie.setLabelSerieLg1(mockedString);
        assertEquals(mockedString,firstSerie.getLabelSerieLg1());
    }

    @ParameterizedTest
    @ValueSource(strings = { "LabelSerieLg1", "LabelSerieLg2", "mockedLabelSerieLg3" })
    void shouldCheckLabelSerieLg2(String mockedString){
        firstSerie.setLabelSerieLg2(mockedString);
        assertEquals(mockedString,firstSerie.getLabelSerieLg2());
    }

    @Test
    void shouldCheckAdditionalProperties(){
        secondSerie.setAdditionalProperties(additionalProperties);
        assertEquals(additionalProperties,secondSerie.getAdditionalProperties());
    }


}