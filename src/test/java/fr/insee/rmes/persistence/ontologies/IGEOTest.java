package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class IGEOTest {

    @Test
    void shouldCheckValues(){
        var actual= List.of(IGEO.NAMESPACE,
                IGEO.PREFIX,
                IGEO.NS,
                IGEO.NOM,
                IGEO.CODE_INSEE).toString();

        String expected = List.of(
                "http://rdf.insee.fr/def/geo#",
                "igeo",
                "igeo :: http://rdf.insee.fr/def/geo#",
                "http://rdf.insee.fr/def/geo#nom",
                "http://rdf.insee.fr/def/geo#codeINSEE").toString();

        assertEquals(expected,actual);
    }

}