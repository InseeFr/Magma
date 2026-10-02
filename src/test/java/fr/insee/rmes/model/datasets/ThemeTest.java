package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ThemeTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    Theme firstTheme =  new Theme("uri", "labelThemeLg1","labelThemeLg2","themeTaxonomy");
    Theme secondTheme = new Theme();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUriValue(String mockedString){
        secondTheme.setUri(mockedString);
        assertEquals(mockedString,secondTheme.getUri());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLabelThemeLg1Value(String mockedString){
        firstTheme.setLabelThemeLg1(mockedString);
        assertEquals(mockedString,firstTheme.getLabelThemeLg1());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLabelThemeLg2Value(String mockedString){
        firstTheme.setLabelThemeLg2(mockedString);
        assertEquals(mockedString,firstTheme.getLabelThemeLg2());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckThemeTaxonomyValue(String mockedString){
        firstTheme.setThemeTaxonomy(mockedString);
        assertEquals(mockedString,firstTheme.getThemeTaxonomy());
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstTheme.setAdditionalProperties(additionalProperties);
        assertEquals(additionalProperties,firstTheme.getAdditionalProperties());
    }

}