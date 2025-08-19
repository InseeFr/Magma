package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SDMX_MMTest {

    @Test
    void shouldCheckValues(){
        String actual= List.of(SDMX_MM.NAMESPACE,
                SDMX_MM.PREFIX,
                SDMX_MM.NS,
                SDMX_MM.REPORTED_ATTRIBUTE,
                SDMX_MM.METADATA_REPORT,
                SDMX_MM.METADATA_REPORT_PREDICATE,
                SDMX_MM.TARGET
        ).toString();

        String expected = List.of(
                "http://www.w3.org/ns/sdmx-mm#",
                "sdmx-mm",
                "sdmx-mm :: http://www.w3.org/ns/sdmx-mm#",
                "http://www.w3.org/ns/sdmx-mm#ReportedAttribute",
                "http://www.w3.org/ns/sdmx-mm#MetadataReport",
                "http://www.w3.org/ns/sdmx-mm#metadataReport",
                "http://www.w3.org/ns/sdmx-mm#target"
                ).toString();

        assertEquals(expected,actual);
    }
}