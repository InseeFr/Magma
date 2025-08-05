package fr.insee.rmes.modelSwagger.concept;

import fr.insee.rmes.model.concept.ConceptDefCourte;
import fr.insee.rmes.model.concept.ConceptSDMX;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class ConceptByIdModelSwaggerTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");
    List<LabelConcept> labelConcept = List.of(new LabelConcept(),new LabelConcept());
    List<ConceptDefCourte> definitionCourte = List.of( new ConceptDefCourte(),new ConceptDefCourte());
    ConceptSDMX[]  conceptsSDMXArray = {new ConceptSDMX(),new ConceptSDMX()};


    ConceptByIdModelSwagger firstConcept = new ConceptByIdModelSwagger();

    ConceptByIdModelSwagger secondConcept = new ConceptByIdModelSwagger(
            "mockedDateCreation",
            "mockedDateMiseAJour",
            "mockedStatutValdiation",
            "mockedId",
            labelConcept,
            "mockedDateFinValidite",
            "mockedUri",
            "mockedVersion",
            definitionCourte);

    ConceptByIdModelSwagger thirdConcept = new ConceptByIdModelSwagger(
            "mockedDateCreation",
            "mockedDateMiseAJour",
            "mockedStatutValdiation",
            "mockedId",
            "mockedDateFinValidite",
            "mockedUri",
            "mockedVersion");


    ConceptByIdModelSwagger fourthConcept = new ConceptByIdModelSwagger(
            "mockedDateCreation",
            "mockedDateMiseAJour",
            "mockedStatutValdiation",
            "mockedId",
            labelConcept,
            "mockedDateFinValidite",
            "mockedUri",
            "mockedVersion",
            conceptsSDMXArray,
            definitionCourte);


    ConceptByIdModelSwagger fifthConcept = new ConceptByIdModelSwagger(
            "mockedDateCreation",
            "mockedDateMiseAJour",
            "mockedStatutValdiation",
            "mockedId",
            "mockedDateFinValidite",
            "mockedUri",
            "mockedVersion",
            conceptsSDMXArray);


    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckDateCreation(String mockedString){
        firstConcept.setDateCreation(mockedString);
        secondConcept.withDateCreation(mockedString);
        assertTrue(Objects.equals(firstConcept.getDateCreation(),secondConcept.getDateCreation()) &&
                Objects.equals(firstConcept.getDateCreation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckDateMiseAJour(String mockedString){
        firstConcept.setDateMiseAJour(mockedString);
        secondConcept.withDateMiseAJour(mockedString);
        assertTrue(Objects.equals(firstConcept.getDateMiseAJour(),secondConcept.getDateMiseAJour()) &&
                Objects.equals(firstConcept.getDateMiseAJour(), mockedString));
    }

    @Test
    void shouldCheckLabelConcept(){
        fifthConcept.setLabelConcept(labelConcept);
        assertEquals(fifthConcept.getLabelConcept(),labelConcept);
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckStatutValidation(String mockedString){
        fourthConcept.setStatutValidation(mockedString);
        thirdConcept.withStatutValidation(mockedString);
        assertTrue(Objects.equals(fourthConcept.getStatutValidation(),thirdConcept.getStatutValidation()) &&
                Objects.equals(fourthConcept.getStatutValidation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        thirdConcept.setId(mockedString);
        secondConcept.withId(mockedString);
        assertTrue(Objects.equals(thirdConcept.getId(),secondConcept.getId()) &&
                Objects.equals(secondConcept.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckDateFinValidite(String mockedString){
        firstConcept.setDateFinValidite(mockedString);
        secondConcept.withDateFinValidite(mockedString);
        assertTrue(Objects.equals(firstConcept.getDateFinValidite(),secondConcept.getDateFinValidite()) &&
                Objects.equals(firstConcept.getDateFinValidite(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstConcept.setUri(mockedString);
        secondConcept.withUri(mockedString);
        assertTrue(Objects.equals(firstConcept.getUri(),secondConcept.getUri()) &&
                Objects.equals(firstConcept.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckVersion(String mockedString){
        firstConcept.setVersion(mockedString);
        secondConcept.withVersion(mockedString);
        assertTrue(Objects.equals(firstConcept.getVersion(),secondConcept.getVersion()) &&
                Objects.equals(firstConcept.getVersion(), mockedString));
    }

    @Test
    void shouldCheckName(){
        secondConcept.setName("mockedName");
        assertEquals(secondConcept.getName(),"mockedName");
    }

    @Test
    void shouldCheckDefinitionCourte(){
        thirdConcept.setDefinitionCourte(definitionCourte);
        assertEquals(thirdConcept.getDefinitionCourte(),definitionCourte);
    }

    @Test
    void shouldCheckConceptSDMX(){
        fourthConcept.setConceptsSDMX(conceptsSDMXArray);
        assertEquals(fourthConcept.getConceptsSDMX(),conceptsSDMXArray);
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstConcept.setAdditionalProperty("name","value");
        secondConcept.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstConcept.getAdditionalProperties(),secondConcept.getAdditionalProperties()) &&
                !Objects.equals(firstConcept.getAdditionalProperties(),additionalProperties));
    }

}