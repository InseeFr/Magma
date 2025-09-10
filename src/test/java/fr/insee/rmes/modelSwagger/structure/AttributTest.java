package fr.insee.rmes.modelSwagger.structure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class AttributTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");
    
    Attribut firstAttribut = new Attribut("ordre",
            "notation",
            new ListCode("id","uri"),
            "attachement",
            "obligatoire" ,
            List.of(new Label__2("langue","contenu")),
            "uri",
            "representation");
    
    Attribut secondAttribut = new Attribut();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckOrdre(String mockedString){
        firstAttribut.setOrdre(mockedString);
        secondAttribut.withOrdre(mockedString);
        assertTrue(Objects.equals(firstAttribut.getOrdre(),secondAttribut.getOrdre()) &&
                Objects.equals(firstAttribut.getOrdre(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckNotation(String mockedString){
        firstAttribut.setNotation(mockedString);
        secondAttribut.withNotation(mockedString);
        assertTrue(Objects.equals(firstAttribut.getNotation(),secondAttribut.getNotation()) &&
                Objects.equals(firstAttribut.getNotation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckListCode(){
        ListCode myListCode = new ListCode();
        firstAttribut.setListCode(myListCode);
        secondAttribut.withListCode(myListCode);
        assertTrue(Objects.equals(firstAttribut.getListCode(),secondAttribut.getListCode()) &&
                Objects.equals(firstAttribut.getListCode(), myListCode));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckAttachement(String mockedString){
        firstAttribut.setAttachement(mockedString);
        secondAttribut.withAttachement(mockedString);
        assertTrue(Objects.equals(firstAttribut.getAttachement(),secondAttribut.getAttachement()) &&
                Objects.equals(firstAttribut.getAttachement(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckObligatoire(String mockedString){
        firstAttribut.setObligatoire(mockedString);
        secondAttribut.withObligatoire(mockedString);
        assertTrue(Objects.equals(firstAttribut.getObligatoire(),secondAttribut.getObligatoire()) &&
                Objects.equals(firstAttribut.getObligatoire(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstAttribut.setUri(mockedString);
        secondAttribut.withUri(mockedString);
        assertTrue(Objects.equals(firstAttribut.getUri(),secondAttribut.getUri()) &&
                Objects.equals(firstAttribut.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckRepresentation(String mockedString){
        firstAttribut.setRepresentation(mockedString);
        secondAttribut.withRepresentation(mockedString);
        assertTrue(Objects.equals(firstAttribut.getRepresentation(),secondAttribut.getRepresentation()) &&
                Objects.equals(firstAttribut.getRepresentation(), mockedString));
    }

    @Test
    void shouldCheckLabel(){
        List<Label__2> myList = List.of(new Label__2("langue","contenu"), new Label__2("mockedLangue","mockedContenu"));
        secondAttribut.setLabel(myList);
        assertEquals(myList,secondAttribut.getLabel());
    }


    @Test
    void shouldCheckAdditionalProperties(){
        firstAttribut.setAdditionalProperty("name","value");
        secondAttribut.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstAttribut.getAdditionalProperties(),secondAttribut.getAdditionalProperties()) &&
                !Objects.equals(firstAttribut.getAdditionalProperties(),additionalProperties));
    }

}