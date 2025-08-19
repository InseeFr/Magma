package fr.insee.rmes.modelSwagger.structure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class StructureByIdModelSwaggerTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    StructureByIdModelSwagger firstStructure = new StructureByIdModelSwagger();
    StructureByIdModelSwagger secondStructure = new StructureByIdModelSwagger(
            "dateMiseAJour",
            "notation",
            "dateCreation",
            "statutValidation",
            List.of(new Mesure(),new Mesure()),
            "id",
            List.of(new Label__1(),new Label__1()),
            "uri",
            "version",
            List.of(new Attribut(),new Attribut()),
            List.of(new Dimension(),new Dimension())
    );

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckDateMiseAJour(String mockedString){
        firstStructure.setDateMiseAJour(mockedString);
        secondStructure.withDateMiseAJour(mockedString);
        assertTrue(Objects.equals(firstStructure.getDateMiseAJour(),secondStructure.getDateMiseAJour()) &&
                Objects.equals(firstStructure.getDateMiseAJour(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckNotation(String mockedString){
        firstStructure.setNotation(mockedString);
        secondStructure.withNotation(mockedString);
        assertTrue(Objects.equals(firstStructure.getNotation(),secondStructure.getNotation()) &&
                Objects.equals(firstStructure.getNotation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckDateCreation(String mockedString){
        firstStructure.setDateCreation(mockedString);
        secondStructure.withDateCreation(mockedString);
        assertTrue(Objects.equals(firstStructure.getDateCreation(),secondStructure.getDateCreation()) &&
                Objects.equals(firstStructure.getDateCreation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckStatutValidation(String mockedString){
        firstStructure.setStatutValidation(mockedString);
        secondStructure.withStatutValidation(mockedString);
        assertTrue(Objects.equals(firstStructure.getStatutValidation(),secondStructure.getStatutValidation()) &&
                Objects.equals(firstStructure.getStatutValidation(), mockedString));
    }

    @Test
    void shouldCheckMesures(){
        List<Mesure> myList = List.of(new Mesure());
        secondStructure.setMesures(myList);
        assertEquals(myList,secondStructure.getMesures());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstStructure.setId(mockedString);
        secondStructure.withId(mockedString);
        assertTrue(Objects.equals(firstStructure.getId(),secondStructure.getId()) &&
                Objects.equals(firstStructure.getId(), mockedString));
    }

    @Test
    void shouldCheckLabel(){
        List<Label__1> myList = List.of(new Label__1("langue","contenu"), new Label__1("mockedLangue","mockedContenu"));
        secondStructure.setLabel(myList);
        assertEquals(myList,secondStructure.getLabel());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstStructure.setUri(mockedString);
        secondStructure.withUri(mockedString);
        assertTrue(Objects.equals(firstStructure.getUri(),secondStructure.getUri()) &&
                Objects.equals(firstStructure.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckVersion(String mockedString){
        firstStructure.setVersion(mockedString);
        secondStructure.withVersion(mockedString);
        assertTrue(Objects.equals(firstStructure.getVersion(),secondStructure.getVersion()) &&
                Objects.equals(firstStructure.getVersion(), mockedString));
    }

    @Test
    void shouldCheckAttributs(){
        List<Attribut> myList = List.of(new Attribut());
        secondStructure.setAttributs(myList);
        assertEquals(myList,secondStructure.getAttributs());
    }

    @Test
    void shouldCheckDimensions(){
        List<Dimension> myList = List.of(new Dimension());
        secondStructure.setDimensions(myList);
        assertEquals(myList,secondStructure.getDimensions());
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstStructure.setAdditionalProperty("name","value");
        secondStructure.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstStructure.getAdditionalProperties(),secondStructure.getAdditionalProperties()) &&
                !Objects.equals(firstStructure.getAdditionalProperties(),additionalProperties));
    }

}