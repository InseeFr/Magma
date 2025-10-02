package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ORGTest {

    @Test
    void shouldCheckValues(){

        String actual= List.of(ORG.NAMESPACE,
                ORG.PREFIX,
                ORG.NS,
                ORG.UNIT_OF,
                ORG.HAS_UNIT,
                ORG.REPORTS_TO,
                ORG.LINKED_TO,
                ORG.ORGANIZATION,
                ORG.ORGANIZATION_UNIT).toString();

        String expected = List.of(
                "http://www.w3.org/ns/org#",
                "org",
                "org :: http://www.w3.org/ns/org#",
                "http://www.w3.org/ns/org#unitOf",
                "http://www.w3.org/ns/org#hasUnit",
                "http://www.w3.org/ns/org#reportsTo",
                "http://www.w3.org/ns/org#linkedTo",
                "http://www.w3.org/ns/org#Organization",
                "http://www.w3.org/ns/org#OrganizationUnit").toString();

        assertEquals(expected,actual);
    }

}