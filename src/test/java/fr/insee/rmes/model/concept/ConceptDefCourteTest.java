package fr.insee.rmes.model.concept;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ConceptDefCourteTest {

    @ParameterizedTest
    @ValueSource(strings = { "mockedValue1", "mockedValue2", "mockedValue3" })
    void shouldCheckContenuWhenConceptDefCourte(String mockedString){
        ConceptDefCourte conceptDefCourte = new ConceptDefCourte();
        conceptDefCourte.setContenu(mockedString);
        assertEquals(mockedString,conceptDefCourte.getContenu());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedValue1", "mockedValue2", "mockedValue3" })
    void shouldCheckLangWhenConceptDefCourte(String mockedString){
        ConceptDefCourte conceptDefCourte = new ConceptDefCourte("mockedContenu","mockedLang");
        conceptDefCourte.setLangue(mockedString);
        assertEquals(mockedString,conceptDefCourte.getLangue());
    }

}