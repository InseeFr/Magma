package fr.insee.rmes.modelSwagger.structure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class AllStructureModelSwaggerTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    AllStructureModelSwagger firstSwagger = new AllStructureModelSwagger();
    AllStructureModelSwagger secondSwagger = new AllStructureModelSwagger("dateMiseAjour","statutValdiation","id");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckDateMiseAJour(String mockedString){
        firstSwagger.setDateMiseAJour(mockedString);
        secondSwagger.withDateMiseAJour(mockedString);
        assertTrue(Objects.equals(firstSwagger.getDateMiseAJour(),secondSwagger.getDateMiseAJour()) &&
                Objects.equals(firstSwagger.getDateMiseAJour(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckStatutValidation(String mockedString){
        firstSwagger.setStatutValidation(mockedString);
        secondSwagger.withStatutValidation(mockedString);
        assertTrue(Objects.equals(firstSwagger.getStatutValidation(),secondSwagger.getStatutValidation()) &&
                Objects.equals(firstSwagger.getStatutValidation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstSwagger.setId(mockedString);
        secondSwagger.withId(mockedString);
        assertTrue(Objects.equals(firstSwagger.getId(),secondSwagger.getId()) &&
                Objects.equals(firstSwagger.getId(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstSwagger.setAdditionalProperty("name","value");
        secondSwagger.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstSwagger.getAdditionalProperties(),secondSwagger.getAdditionalProperties()) &&
                !Objects.equals(firstSwagger.getAdditionalProperties(),additionalProperties));
    }


}