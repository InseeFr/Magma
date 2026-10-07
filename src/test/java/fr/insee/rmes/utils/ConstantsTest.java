package fr.insee.rmes.utils;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

    @Test
    void shouldConfirmTheExistenceOfDuplicatesInStaticConstants(){

        List<String> elements = List.of(
                Constants.CODELIST,
                Constants.CODELISTS_QUERIES_PATH,
                Constants.COMPONENTS_QUERIES_PATH,
                Constants.CONCEPT,
                Constants.CONCEPTS_QUERIES_PATH,
                Constants.COLLECTION,
                Constants.DATASETS_QUERIES_PATH,
                Constants.DOCUMENT,
                Constants.FAMILY,
                Constants.ID,
                Constants.LABEL,
                Constants.NOTATION,
                Constants.POGUES_QUERIES_PATH,
                Constants.PREF_LABEL_LG1,
                Constants.PREF_LABEL_LG2,
                Constants.ORGANISATIONS_QUERIES_PATH,
                Constants.STRUCTURES_QUERIES_PATH,
                Constants.STATUT_VALIDATION,
                Constants.TYPE_OF_OBJECT,
                Constants.UNDEFINED,
                Constants.URI,
                Constants.URL,
                Constants.VALUE,
                Constants.PARENTS);

        HashSet<String> values = new HashSet<>(elements);

        assertNotEquals(values.size(),elements.size());
    }
  
}