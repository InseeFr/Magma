package fr.insee.rmes.modelSwagger.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class FrequenceTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");
    List<Label> label = List.of(new Label(),new Label());
    
    Frequence firstFrequence = new Frequence();
    Frequence secondFrequence = new Frequence("mockedId",label,"mockedUri");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstFrequence.setId(mockedString);
        secondFrequence.withId(mockedString);
        assertTrue(Objects.equals(firstFrequence.getId(),secondFrequence.getId()) &&
                Objects.equals(firstFrequence.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstFrequence.setUri(mockedString);
        secondFrequence.withUri(mockedString);
        assertTrue(Objects.equals(firstFrequence.getUri(),secondFrequence.getUri()) &&
                Objects.equals(firstFrequence.getUri(), mockedString));
    }

    @Test
    void shouldCheckLabel(){
        firstFrequence.setLabel(label);
        secondFrequence.withLabel(label);
        assertTrue(Objects.equals(firstFrequence.getLabel(),secondFrequence.getLabel()) &&
                Objects.equals(firstFrequence.getLabel(),label));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstFrequence.setAdditionalProperty("name","value");
        secondFrequence.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstFrequence.getAdditionalProperties(), secondFrequence.getAdditionalProperties()) &&
                !Objects.equals(firstFrequence.getAdditionalProperties(),additionalProperties));
    }





}