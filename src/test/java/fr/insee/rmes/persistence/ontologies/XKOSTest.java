package fr.insee.rmes.persistence.ontologies;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class XKOSTest {

    @Test
    void shouldCheckValues(){

    String actual= List.of(XKOS.NS,
            XKOS.CLASSIFICATION_LEVEL,
            XKOS.CONCEPT_ASSOCIATION,
            XKOS.CORRESPONDENCE,
            XKOS.EXPLANATORY_NOTE,
            XKOS.CASE_LAW,
            XKOS.BELONGS_TO,
            XKOS.MAX_LENGTH,
            XKOS.CORE_CONTENT_NOTE,
            XKOS.ADDITIONAL_CONTENT_NOTE,
            XKOS.EXCLUSION_NOTE,
            XKOS.ORGANISED_BY ,
            XKOS.PLAIN_TEXT,
            XKOS.MADE_OF ,
            XKOS.SOURCE_CONCEPT ,
            XKOS.TARGET_CONCEPT,
            XKOS.COMPARES).toString();

        String expected = List.of(
                "xkos :: http://rdf-vocabulary.ddialliance.org/xkos#",
                "http://rdf-vocabulary.ddialliance.org/xkos#ClassificationLevel",
                "http://rdf-vocabulary.ddialliance.org/xkos#ConceptAssociation",
                "http://rdf-vocabulary.ddialliance.org/xkos#Correspondence",
                "http://rdf-vocabulary.ddialliance.org/xkos#ExplanatoryNote",
                "http://rdf-vocabulary.ddialliance.org/xkos#caseLaw",
                "http://rdf-vocabulary.ddialliance.org/xkos#belongsTo",
                "http://rdf-vocabulary.ddialliance.org/xkos#maxLength",
                "http://rdf-vocabulary.ddialliance.org/xkos#coreContentNote",
                "http://rdf-vocabulary.ddialliance.org/xkos#additionalContentNote",
                "http://rdf-vocabulary.ddialliance.org/xkos#exclusionNote",
                "http://rdf-vocabulary.ddialliance.org/xkos#organisedBy",
                "http://rdf-vocabulary.ddialliance.org/xkos#plainText",
                "http://rdf-vocabulary.ddialliance.org/xkos#madeOf",
                "http://rdf-vocabulary.ddialliance.org/xkos#sourceConcept",
                "http://rdf-vocabulary.ddialliance.org/xkos#targetConcept",
                "http://rdf-vocabulary.ddialliance.org/xkos#compares").toString();

        assertEquals(expected,actual);
    }

}