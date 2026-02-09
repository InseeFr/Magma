package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class INSEETest {

    @Test
    void shouldCheckValues(){
        String actual= List.of(INSEE.NAMESPACE,
                INSEE.PREFIX,
                INSEE.NS,
                INSEE.DISSEMINATIONSTATUS,
                INSEE.ADDITIONALMATERIAL,
                INSEE.LEGALMATERIAL,
                INSEE.VALIDFROM,
                INSEE.VALIDUNTIL,
                INSEE.SIMILARITY_NOTE,
                INSEE.DIFFERENCE_NOTE,
                INSEE.FAMILY,
                INSEE.OPERATION,
                INSEE.INDICATOR,
                INSEE.IS_VALIDATED,
                INSEE.DOCUMENT,
                INSEE.CONCEPT_VERSION,
                INSEE.CODELIST,
                INSEE.VALIDATION_STATE,
                INSEE.IDENTIFIANT_METIER,
                INSEE.DATA_COLLECTOR,
                INSEE.SERIES,
                INSEE.STRUCTURE_CONCEPT,
                INSEE.DATA_COLLECTOR).toString();

        String expected = List.of(
                "http://rdf.insee.fr/def/base#",
                "insee",
                "insee :: http://rdf.insee.fr/def/base#",
                "http://rdf.insee.fr/def/base#disseminationStatus",
                "http://rdf.insee.fr/def/base#additionalMaterial",
                "http://rdf.insee.fr/def/base#legalMaterial",
                "http://rdf.insee.fr/def/base#validFrom",
                "http://rdf.insee.fr/def/base#validUntil",
                "http://rdf.insee.fr/def/base#similarityNote",
                "http://rdf.insee.fr/def/base#differenceNote",
                "http://rdf.insee.fr/def/base#StatisticalOperationFamily",
                "http://rdf.insee.fr/def/base#StatisticalOperation",
                "http://rdf.insee.fr/def/base#StatisticalIndicator",
                "http://rdf.insee.fr/def/base#isValidated",
                "http://rdf.insee.fr/def/base#document",
                "http://rdf.insee.fr/def/base#conceptVersion",
                "http://rdf.insee.fr/def/base#codeList",
                "http://rdf.insee.fr/def/base#validationState",
                "http://rdf.insee.fr/def/base#identifiantMetier",
                "http://rdf.insee.fr/def/base#dataCollector",
                "http://rdf.insee.fr/def/base#StatisticalOperationSeries",
                "http://id.insee.fr/concepts/definition/",
                "http://rdf.insee.fr/def/base#dataCollector").toString();
        assertEquals(expected,actual);
    }
}