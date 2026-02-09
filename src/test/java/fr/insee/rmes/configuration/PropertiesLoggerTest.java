package fr.insee.rmes.configuration;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class PropertiesLoggerTest {

    @Test
    void shouldCheckForDuplicatesWhenConsideringStaticElements(){
        List<String> actualStaticElements = List.of(PropertiesLogger.PROPERTY_KEY_FOR_PREFIXES,
                PropertiesLogger.PROPERTY_KEY_FOR_MORE_HIDDEN,
                PropertiesLogger.PROPERTY_KEY_FOR_SOURCES_IGNORED,
                PropertiesLogger.PROPERTY_KEY_FOR_SOURCES_SELECT);
        Set<String> expectedStaticElements = new HashSet<>(actualStaticElements);
        assertEquals(expectedStaticElements.size(),actualStaticElements.size());
    }


}