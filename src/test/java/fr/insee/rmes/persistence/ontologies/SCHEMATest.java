package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SCHEMATest {

    @Test
    void shouldCheckValues(){
        String actual= List.of(SCHEMA.NAMESPACE,
                SCHEMA.PREFIX,
                SCHEMA.NS,
                SCHEMA.URL
        ).toString();

        String expected = List.of(
                "http://schema.org/",
                "schema",
                "schema :: http://schema.org/",
                "http://schema.org/url").toString();

        assertEquals(expected,actual);
    }


}