package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ThemeTest {

    String uri = "uri";
    String labelThemeLg1= "labelThemeLg1";
    String labelThemeLg2= "labelThemeLg2";
    String themeTaxonomy="themeTaxonomy";
    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    Theme firstTheme = new Theme(uri,labelThemeLg1,labelThemeLg2,themeTaxonomy);
    Theme secondTheme = new Theme();

    @ParameterizedTest
    @ValueSource(strings = { "mockedUri1", "mockedUri2", "mockedUri3" })
    void shouldCheckUri(String mockedString){
        firstTheme.setUri(mockedString);
        assertEquals(mockedString,firstTheme.getUri());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedLabelThemeLg1", "mockedLabelThemeLg2", "mockedLabelThemeLg3" })
    void shouldCheckLabelThemeLg1(String mockedString){
        secondTheme.setLabelThemeLg1(mockedString);
        assertEquals(mockedString,secondTheme.getLabelThemeLg1());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedLabelThemeLg1", "mockedLabelThemeLg2", "mockedLabelThemeLg3" })
    void shouldCheckLabelThemeLg2(String mockedString){
        secondTheme.setLabelThemeLg2(mockedString);
        assertEquals(mockedString,secondTheme.getLabelThemeLg2());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedThemeTaxonomyLg1", "mockedThemeTaxonomy2", "mockedThemeTaxonomy3" })
    void shouldCheckLabelThemeTaxonomy(String mockedString){
        secondTheme.setThemeTaxonomy(mockedString);
        assertEquals(mockedString,secondTheme.getThemeTaxonomy());
    }

    @Test
    void shouldCheckAdditionalProperties(){
        secondTheme.setAdditionalProperties(additionalProperties);
        assertEquals(additionalProperties,secondTheme.getAdditionalProperties());
    }


}