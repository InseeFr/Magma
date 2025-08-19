package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PAVTest {

    @Test
    void shouldCheckValues(){
        String actual= List.of(PAV.NAMESPACE,
                PAV.PREFIX,
                PAV.NS,
                PAV.VERSION,
                PAV.LASTREFRESHEDON
                ).toString();
        String expected = List.of(
                "http://purl.org/pav/",
                "pav",
                "pav :: http://purl.org/pav/",
                "http://purl.org/pav/version",
                "http://purl.org/pav/lastRefreshedOn").toString();
        assertEquals(expected,actual);
    }

}