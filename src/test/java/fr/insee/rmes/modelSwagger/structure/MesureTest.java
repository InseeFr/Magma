package fr.insee.rmes.modelSwagger.structure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class MesureTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    Mesure firstMesure = new Mesure();
    Mesure secondMesure = new Mesure("ordre","notation", List.of(new Label()),"uri","representation");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckOrdre(String mockedString){
        firstMesure.setOrdre(mockedString);
        secondMesure.withOrdre(mockedString);
        assertTrue(Objects.equals(firstMesure.getOrdre(),secondMesure.getOrdre()) &&
                Objects.equals(firstMesure.getOrdre(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckNotation(String mockedString){
        firstMesure.setNotation(mockedString);
        secondMesure.withNotation(mockedString);
        assertTrue(Objects.equals(firstMesure.getNotation(),secondMesure.getNotation()) &&
                Objects.equals(firstMesure.getNotation(), mockedString));
    }

    @Test
    void shouldCheckLabel(){
        List<Label> myList = List.of(new Label("langue","contenu"), new Label("mockedLangue","mockedContenu"));
        secondMesure.setLabel(myList);
        assertEquals(myList,secondMesure.getLabel());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstMesure.setUri(mockedString);
        secondMesure.withUri(mockedString);
        assertTrue(Objects.equals(firstMesure.getUri(),secondMesure.getUri()) &&
                Objects.equals(firstMesure.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckRepresentation(String mockedString){
        firstMesure.setRepresentation(mockedString);
        secondMesure.withRepresentation(mockedString);
        assertTrue(Objects.equals(firstMesure.getRepresentation(),secondMesure.getRepresentation()) &&
                Objects.equals(firstMesure.getRepresentation(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstMesure.setAdditionalProperty("name","value");
        secondMesure.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstMesure.getAdditionalProperties(),secondMesure.getAdditionalProperties()) &&
                !Objects.equals(firstMesure.getAdditionalProperties(),additionalProperties));
    }


}