package fr.insee.rmes.modelSwagger.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class ConceptTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");
    Concept firstConcept = new Concept("id", "uri");
    Concept secondConcept = new Concept();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstConcept.setId(mockedString);
        secondConcept.withId(mockedString);
        assertTrue(Objects.equals(firstConcept.getId(),secondConcept.getId()) &&
                Objects.equals(firstConcept.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstConcept.setUri(mockedString);
        secondConcept.withUri(mockedString);
        assertTrue(Objects.equals(firstConcept.getUri(),secondConcept.getUri()) &&
                Objects.equals(firstConcept.getUri(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstConcept.setAdditionalProperty("name","value");
        secondConcept.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstConcept.getAdditionalProperties(),secondConcept.getAdditionalProperties()) &&
                !Objects.equals(firstConcept.getAdditionalProperties(),additionalProperties));
    }


}