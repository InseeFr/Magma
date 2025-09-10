package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PROVTest {

    @Test
    void shouldCheckValues(){
        String actual= List.of(PROV.NAMESPACE,
                PROV.PREFIX,
                PROV.NS,
                PROV.WAS_GENERATED_BY
        ).toString();

        String expected = List.of(
                "http://www.w3.org/ns/prov#",
                "prov",
                "prov :: http://www.w3.org/ns/prov#",
                "http://www.w3.org/ns/prov#wasGeneratedBy").toString();

        assertEquals(expected,actual);
    }

}