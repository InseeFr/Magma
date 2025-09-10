package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class QBTest {

    @Test
    void shouldCheckValues(){
        String actual= List.of(QB.NAMESPACE,
                QB.PREFIX,
                QB.NS,
                QB.DATA_STRUCTURE_DEFINITION,
                QB.COMPONENT,
                QB.COMPONENT_SPECIFICATION,
                QB.COMPONENT_ATTACHMENT,
                QB.COMPONENT_REQUIRED,
                QB.MEASURE,
                QB.MEASURE_PROPERTY,
                QB.ATTRIBUTE,
                QB.ATTRIBUTE_PROPERTY,
                QB.DIMENSION,
                QB.DIMENSION_PROPERTY,
                QB.CODE_LIST,
                QB.CODED_PROPERTY,
                QB.CONCEPT,
                QB.ORDER).toString();

        String expected = List.of(
                "http://purl.org/linked-data/cube#",
                "qb",
                "qb :: http://purl.org/linked-data/cube#",
                "http://purl.org/linked-data/cube#DataStructureDefinition",
                "http://purl.org/linked-data/cube#component",
                "http://purl.org/linked-data/cube#ComponentSpecification",
                "http://purl.org/linked-data/cube#componentAttachment",
                "http://purl.org/linked-data/cube#componentRequired",
                "http://purl.org/linked-data/cube#measure",
                "http://purl.org/linked-data/cube#MeasureProperty",
                "http://purl.org/linked-data/cube#attribute",
                "http://purl.org/linked-data/cube#AttributeProperty",
                "http://purl.org/linked-data/cube#dimension",
                "http://purl.org/linked-data/cube#DimensionProperty",
                "http://purl.org/linked-data/cube#codeList",
                "http://purl.org/linked-data/cube#CodedProperty",
                "http://purl.org/linked-data/cube#concept",
                "http://purl.org/linked-data/cube#order").toString();

        assertEquals(expected,actual);
    }

    @Test
    void shouldCheckGetUriForComponent(){
        boolean measure = "http://purl.org/linked-data/cube#MeasureProperty".equals(QB.getURIForComponent()[0]);
        boolean attribute = "http://purl.org/linked-data/cube#AttributeProperty".equals(QB.getURIForComponent()[1]);
        boolean dimensions = "http://purl.org/linked-data/cube#DimensionProperty".equals(QB.getURIForComponent()[2]);
        assertTrue(measure &&  attribute && dimensions);
    }
}