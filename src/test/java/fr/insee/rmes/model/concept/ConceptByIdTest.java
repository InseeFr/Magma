package fr.insee.rmes.model.concept;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class ConceptByIdTest {

    String dateCreation = "mockedDateCreation";
    String dateMiseAJour = "mockedDateMiseAJour";
    String prefLabelLg1= "mockedPrefLabelLg1";
    String prefLabelLg2= "mockedPrefLabelLg2";
    String statutValidation= "mockedStatutValidation";
    String id= "mockedId";
    String dateFinValidite= "mockedDateFinValidite";
    String uri= "mockedUri";
    String version= "mockedVersion";
    String name= "mockedName";

    ConceptById firstConceptById = new ConceptById(dateCreation,dateMiseAJour, prefLabelLg1, prefLabelLg2,statutValidation, id,dateFinValidite, uri,version, name) ;
    ConceptById secondConceptById = new ConceptById();


    @ParameterizedTest
    @ValueSource(strings = { "mockedDateCreation1", "mockedDateCreation2", "mockedDateCreation3" })
    void shouldCheckDateCreation(String mockedString){
        firstConceptById.setDateCreation(mockedString);
        secondConceptById.withDateCreation(mockedString);
        assertTrue(Objects.equals(firstConceptById.getDateCreation(), secondConceptById.getDateCreation()) &&
                Objects.equals(firstConceptById.getDateCreation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedDateMiseAJour1", "mockedDateMiseAJour2", "mockedDateMiseAJour3" })
    void shouldCheckDateMiseAJour(String mockedString){
        firstConceptById.setDateMiseAJour(mockedString);
        secondConceptById.withDateMiseAjour(mockedString);
        assertTrue(Objects.equals(firstConceptById.getDateMiseAJour(), secondConceptById.getDateMiseAJour()) &&
                Objects.equals(firstConceptById.getDateMiseAJour(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedPrefLabelLg1", "mockedPrefLabelLg2", "mockedPrefLabelLg3" })
    void shouldCheckPrefLabelLg1(String mockedString){
        firstConceptById.setPrefLabelLg1(mockedString);
        secondConceptById.withPrefLabelLg1(mockedString);
        assertTrue(Objects.equals(firstConceptById.getPrefLabelLg1(), secondConceptById.getPrefLabelLg1()) &&
                Objects.equals(firstConceptById.getPrefLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedPrefLabelLg1", "mockedPrefLabelLg2", "mockedPrefLabelLg3" })
    void shouldCheckPrefLabelLg2(String mockedString){
        firstConceptById.setPrefLabelLg2(mockedString);
        secondConceptById.withPrefLabelLg2(mockedString);
        assertTrue(Objects.equals(firstConceptById.getPrefLabelLg2(), secondConceptById.getPrefLabelLg2()) &&
                Objects.equals(firstConceptById.getPrefLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedStatutValidation1", "mockedStatutValidation2", "mockedStatutValidation3" })
    void shouldCheckStatutValidation(String mockedString){
        firstConceptById.setStatutValidation(mockedString);
        secondConceptById.withStatutValidation(mockedString);
        assertTrue(Objects.equals(firstConceptById.getStatutValidation(), secondConceptById.getStatutValidation())
        && Objects.equals(firstConceptById.getStatutValidation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedId1", "mockedId2", "mockedId3" })
    void shouldCheckId(String mockedString){
        firstConceptById.setId(mockedString);
        secondConceptById.withId(mockedString);
        assertTrue(Objects.equals(firstConceptById.getId(), secondConceptById.getId()) &&
                        Objects.equals(firstConceptById.getId(), mockedString)
                );
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedDateFinValidite1", "mockedDateFinValidite2", "mockedDateFinValidite3" })
    void shouldCheckDateFinValidite(String mockedString){
        firstConceptById.setDateFinValidite(mockedString);
        secondConceptById.withDateFinValidite(mockedString);
        assertTrue(Objects.equals(firstConceptById.getDateFinValidite(), secondConceptById.getDateFinValidite()) &&
                Objects.equals(firstConceptById.getDateFinValidite(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedUri1", "mockedUri2", "mockedUri3" })
    void shouldCheckUri(String mockedString){
        firstConceptById.setUri(mockedString);
        secondConceptById.withUri(mockedString);
        assertTrue(Objects.equals(firstConceptById.getUri(), secondConceptById.getUri()) &&
                Objects.equals(firstConceptById.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedVersion1", "mockedVersion2", "mockedVersion3" })
    void shouldCheckVersion(String mockedString){
        firstConceptById.setVersion(mockedString);
        secondConceptById.withVersion(mockedString);
        assertTrue(Objects.equals(firstConceptById.getVersion(), secondConceptById.getVersion()) &&
                        Objects.equals(firstConceptById.getVersion(), mockedString)
                );
    }

    @Test
    void shouldCheckName(){
        firstConceptById.setName("mockedName");
        assertEquals("mockedName",firstConceptById.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedVersion1/mockedValue1", "mockedVersion2/mockedValue2", "mockedVersion3/mockedValue3" })
    void shouldCheckAdditionalProperties(String mockedString){
        String name = mockedString.split("/")[0];
        String value = mockedString.split("/")[1];
        firstConceptById.setAdditionalProperty(name,value);
        secondConceptById.withAdditionalProperty(name,value);
        assertTrue(("{" + name + "=" + value + "}").equals(firstConceptById.getAdditionalProperties().toString())&&
                ("{" + name + "=" + value + "}").equals(secondConceptById.getAdditionalProperties().toString()));
    }

}