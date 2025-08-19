package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DCMITYPETest {

    @Test
    void shouldCheckValues(){
        String actual= List.of(DCMITYPE.NAMESPACE,
                DCMITYPE.PREFIX,
                DCMITYPE.NS,
                DCMITYPE.TEXT).toString();
        String expected = List.of(
                "http://purl.org/dc/dcmitype/",
                "dcmitype",
                "dcmitype :: http://purl.org/dc/dcmitype/",
                "http://purl.org/dc/dcmitype/Text").toString();
        assertEquals(expected,actual);
    }
}