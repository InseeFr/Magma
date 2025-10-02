package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EVOCTest {

    @Test
    void shouldCheckValues(){
        String actual= List.of(EVOC.NAMESPACE,
                EVOC.PREFIX,
                EVOC.NS,
                EVOC.NOTE_LITERAL).toString();
        String expected = List.of(
                "http://eurovoc.europa.eu/schema#",
                "evoc",
                "evoc :: http://eurovoc.europa.eu/schema#",
                "http://eurovoc.europa.eu/schema#noteLiteral").toString();
        assertEquals(expected,actual);
    }

}