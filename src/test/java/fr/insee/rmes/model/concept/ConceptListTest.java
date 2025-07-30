package fr.insee.rmes.model.concept;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class ConceptListTest {

    String dateMiseAJour = "mockedDateMiseAJour";
    String statutValidation = "mockedStatutValidation";
    String id= "mockedId";

    ConceptList firstConceptList = new ConceptList(dateMiseAJour,statutValidation, id) ;
    ConceptList secondConceptList = new ConceptList();

    @ParameterizedTest
    @ValueSource(strings = { "mockedDateMiseAJour1", "mockedDateMiseAJour2", "mockedDateMiseAJour3" })
    void shouldCheckDateMiseAJour(String mockedString){
        firstConceptList.setDateMiseAJour(mockedString);
        secondConceptList.withDateMiseAJour(mockedString);
        assertTrue(Objects.equals(firstConceptList.getDateMiseAJour(), secondConceptList.getDateMiseAJour()) &&
                        Objects.equals(mockedString, firstConceptList.getDateMiseAJour())
                );
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedStatutValidation1", "mockedStatutValidation2", "mockedStatutValidation3" })
    void shouldCheckStatutValidation(String mockedString){
        firstConceptList.setStatutValidation(mockedString);
        secondConceptList.withStatutValidation(mockedString);
        assertTrue(Objects.equals(firstConceptList.getStatutValidation(), secondConceptList.getStatutValidation()) &&
                Objects.equals(mockedString, firstConceptList.getStatutValidation())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedId1", "mockedId2", "mockedId3" })
    void shouldCheckId(String mockedString){
        firstConceptList.setId(mockedString);
        secondConceptList.withId(mockedString);
        assertTrue(Objects.equals(firstConceptList.getId(), secondConceptList.getId()) &&
                Objects.equals(mockedString, firstConceptList.getId())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedName1/mockedValue1", "mockedName2/mockedValue2", "mockedName3/mockedValue3" })
    void shouldCheckAdditionalProperties(String mockedString){
        String name = mockedString.split("/")[0];
        String value = mockedString.split("/")[1];
        firstConceptList.setAdditionalProperty(name,value);
        secondConceptList.withAdditionalProperty(name,value);
        assertTrue(("{" + name + "=" + value + "}").equals(firstConceptList.getAdditionalProperties().toString())&&
                ("{" + name + "=" + value + "}").equals(secondConceptList.getAdditionalProperties().toString()));
    }

}