package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GEOTest {

    @Test
    void shouldCheckValues(){
        String actual= List.of(GEO.NAMESPACE,
                GEO.PREFIX,
                GEO.NS,
                GEO.FEATURE).toString();
        String expected = List.of(
                "http://www.opengis.net/ont/geosparql#",
                "geo",
                "geo :: http://www.opengis.net/ont/geosparql#",
                "http://www.opengis.net/ont/geosparql#Feature").toString();
        assertEquals(expected,actual);
    }

}