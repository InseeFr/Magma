package fr.insee.rmes.modelSwagger.concept;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class LabelConceptTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");
    LabelConcept firstLabelConcepts = new LabelConcept("mockedLang","mockedContenu");
    LabelConcept secondLabelConcepts = new LabelConcept();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLang(String mockedString){
        firstLabelConcepts.setLangue(mockedString);
        secondLabelConcepts.withLangue(mockedString);
        assertTrue(Objects.equals(firstLabelConcepts.getLangue(),secondLabelConcepts.getLangue()) &&
                Objects.equals(firstLabelConcepts.getLangue(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckContenu(String mockedString){
        firstLabelConcepts.setContenu(mockedString);
        secondLabelConcepts.withContenu(mockedString);
        assertTrue(Objects.equals(firstLabelConcepts.getContenu(),secondLabelConcepts.getContenu()) &&
                Objects.equals(firstLabelConcepts.getContenu(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstLabelConcepts.setAdditionalProperty("name","value");
        secondLabelConcepts.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstLabelConcepts.getAdditionalProperties(),secondLabelConcepts.getAdditionalProperties()) &&
                !Objects.equals(firstLabelConcepts.getAdditionalProperties(),additionalProperties));
    }

}