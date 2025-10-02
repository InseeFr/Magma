package fr.insee.rmes.modelSwagger.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class ComponentByIdModelSwaggerTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");
    List<Label> labels = List.of(new Label(),new Label());
    ListeCode listeCode = new ListeCode();

    ComponentByIdModelSwagger firstComponent = new ComponentByIdModelSwagger(
            "dateMiseAJour",
            "dateCreation",
            new Concept(),
            "statutValidation",
            labels,
            "type",
            "uri",
            "representation",
            "version",
            "notation",
            "id",
            listeCode);

    ComponentByIdModelSwagger secondComponent = new ComponentByIdModelSwagger();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckDateMiseAJour(String mockedString){
        firstComponent.setDateMiseAJour(mockedString);
        secondComponent.withDateMiseAJour(mockedString);
        assertTrue(Objects.equals(firstComponent.getDateMiseAJour(),secondComponent.getDateMiseAJour()) &&
                Objects.equals(firstComponent.getDateMiseAJour(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckDateCreation(String mockedString){
        firstComponent.setDateCreation(mockedString);
        secondComponent.withDateCreation(mockedString);
        assertTrue(Objects.equals(firstComponent.getDateCreation(),secondComponent.getDateCreation()) &&
                Objects.equals(firstComponent.getDateCreation(), mockedString));
    }

    @Test
    void shouldCheckConcept(){
        Concept concept = new Concept();
        firstComponent.setConcept(concept);
        secondComponent.withConcept(concept);
        assertTrue(Objects.equals(firstComponent.getConcept(),secondComponent.getConcept()) &&
                Objects.equals(firstComponent.getConcept(), concept));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckValidationStatus(String mockedString){
        firstComponent.setStatutValidation(mockedString);
        secondComponent.withStatutValidation(mockedString);
        assertTrue(Objects.equals(firstComponent.getStatutValidation(),secondComponent.getStatutValidation()) &&
                Objects.equals(firstComponent.getStatutValidation(), mockedString));
    }

    @Test
    void shouldCheckLabels(){
        firstComponent.setLabel(labels);
        secondComponent.withLabel(labels);
        assertTrue(Objects.equals(firstComponent.getLabel(),secondComponent.getLabel()) &&
                Objects.equals(firstComponent.getLabel(), labels));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckType(String mockedString){
        firstComponent.setType(mockedString);
        secondComponent.withType(mockedString);
        assertTrue(Objects.equals(firstComponent.getType(),secondComponent.getType()) &&
                Objects.equals(firstComponent.getType(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstComponent.setUri(mockedString);
        secondComponent.withUri(mockedString);
        assertTrue(Objects.equals(firstComponent.getUri(),secondComponent.getUri()) &&
                Objects.equals(firstComponent.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckRepresentation(String mockedString){
        firstComponent.setRepresentation(mockedString);
        secondComponent.withRepresentation(mockedString);
        assertTrue(Objects.equals(firstComponent.getRepresentation(),secondComponent.getRepresentation()) &&
                Objects.equals(firstComponent.getRepresentation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckVersion(String mockedString){
        firstComponent.setVersion(mockedString);
        secondComponent.withVersion(mockedString);
        assertTrue(Objects.equals(firstComponent.getVersion(),secondComponent.getVersion()) &&
                Objects.equals(firstComponent.getVersion(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckNotation(String mockedString){
        firstComponent.setNotation(mockedString);
        secondComponent.withNotation(mockedString);
        assertTrue(Objects.equals(firstComponent.getNotation(),secondComponent.getNotation()) &&
                Objects.equals(firstComponent.getNotation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstComponent.setId(mockedString);
        secondComponent.withId(mockedString);
        assertTrue(Objects.equals(firstComponent.getId(),secondComponent.getId()) &&
                Objects.equals(firstComponent.getId(), mockedString));
    }

    @Test
    void shouldCheckListeCode(){
        firstComponent.setListeCode(listeCode);
        secondComponent.withListeCode(listeCode);
        assertTrue(Objects.equals(firstComponent.getListeCode(),secondComponent.getListeCode()) &&
                Objects.equals(firstComponent.getListeCode(), listeCode));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstComponent.setAdditionalProperty("name","value");
        secondComponent.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstComponent.getAdditionalProperties(),secondComponent.getAdditionalProperties()) &&
                !Objects.equals(firstComponent.getAdditionalProperties(),additionalProperties));
    }

}