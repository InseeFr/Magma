package fr.insee.rmes.modelSwagger.concept;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class AllConceptModelSwaggerTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    AllConceptModelSwagger firstModel = new AllConceptModelSwagger("dateMiseAJour", "statutValidation", "id");
    AllConceptModelSwagger secondModel = new AllConceptModelSwagger();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckDateMiseAJour(String mockedString){
        firstModel.setDateMiseAJour(mockedString);
        secondModel.withDateMiseAJour(mockedString);
        assertTrue(Objects.equals(firstModel.getDateMiseAJour(),secondModel.getDateMiseAJour()) &&
                Objects.equals(firstModel.getDateMiseAJour(), mockedString));
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
    void shouldCheckId(String mockedString){
        firstModel.setId(mockedString);
        secondModel.withId(mockedString);
        assertTrue(Objects.equals(firstModel.getId(),secondModel.getId()) &&
                Objects.equals(firstModel.getId(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstModel.setAdditionalProperty("name","value");
        secondModel.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstModel.getAdditionalProperties(),secondModel.getAdditionalProperties()) &&
                !Objects.equals(firstModel.getAdditionalProperties(),additionalProperties));
    }

}