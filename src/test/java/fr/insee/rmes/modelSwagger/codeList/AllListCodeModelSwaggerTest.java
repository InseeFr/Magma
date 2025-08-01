package fr.insee.rmes.modelSwagger.codeList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class AllListCodeModelSwaggerTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    AllListCodeModelSwagger firstModel = new AllListCodeModelSwagger(
            "ordre",
            "id",
            "version",
            "statutValidation",
            "dateMiseAJour"
    );

    AllListCodeModelSwagger secondModel = new AllListCodeModelSwagger();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckOrdre(String mockedString){
        firstModel.setOrdre(mockedString);
        secondModel.withOrdre(mockedString);
        assertTrue(Objects.equals(firstModel.getOrdre(),secondModel.getOrdre()) &&
                Objects.equals(firstModel.getOrdre(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstModel.setId(mockedString);
        secondModel.withId(mockedString);
        assertTrue(Objects.equals(firstModel.getId(),secondModel.getId()) &&
                Objects.equals(firstModel.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckVersion(String mockedString){
        firstModel.setVersion(mockedString);
        secondModel.withVersion(mockedString);
        assertTrue(Objects.equals(firstModel.getVersion(),secondModel.getVersion()) &&
                Objects.equals(firstModel.getVersion(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckStatutValidation(String mockedString){
        firstModel.setStatutValidation(mockedString);
        secondModel.withStatutValidation(mockedString);
        assertTrue(Objects.equals(firstModel.getStatutValidation(),secondModel.getStatutValidation()) &&
                Objects.equals(firstModel.getStatutValidation(), mockedString));
    }


    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckDateMiseAJour(String mockedString){
        firstModel.setDateMiseAJour(mockedString);
        secondModel.withDateMiseAJour(mockedString);
        assertTrue(Objects.equals(firstModel.getDateMiseAJour(),secondModel.getDateMiseAJour()) &&
                Objects.equals(firstModel.getDateMiseAJour(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstModel.setAdditionalProperty("name","value");
        secondModel.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstModel.getAdditionalProperties(),secondModel.getAdditionalProperties()) &&
                !Objects.equals(firstModel.getAdditionalProperties(),additionalProperties));
    }

}