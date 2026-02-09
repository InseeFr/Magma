package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DCTERMSTest {

    @Test
    void shouldCheckValues(){
        String actual= List.of(DCTERMS.NAMESPACE,
                DCTERMS.PREFIX,
                DCTERMS.NS,
                DCTERMS.HAS_PART).toString();
        String expected = List.of(
                "http://purl.org/dc/terms/",
                "dcterms",
                "dcterms :: http://purl.org/dc/terms/",
                "http://purl.org/dc/terms/hasPart").toString();
        assertEquals(expected,actual);
    }

}