package fr.insee.rmes.modelSwagger.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class TypeTest {


    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");
    List<Label> label = List.of(new Label(),new Label());

    Type firstType = new Type();
    Type secondType = new Type("mockedId",label,"mockedUri");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstType.setId(mockedString);
        secondType.withId(mockedString);
        assertTrue(Objects.equals(firstType.getId(),secondType.getId()) &&
                Objects.equals(firstType.getId(), mockedString));
    }

    @Test
    void shouldCheckLabel(){
        firstType.setLabel(label);
        secondType.withLabel(label);
        assertTrue(Objects.equals(firstType.getLabel(),secondType.getLabel()) &&
                Objects.equals(firstType.getLabel(),label));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstType.setUri(mockedString);
        secondType.withUri(mockedString);
        assertTrue(Objects.equals(firstType.getUri(),secondType.getUri()) &&
                Objects.equals(firstType.getUri(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstType.setAdditionalProperty("name","value");
        secondType.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstType.getAdditionalProperties(), secondType.getAdditionalProperties()) &&
                !Objects.equals(firstType.getAdditionalProperties(),additionalProperties));
    }
  
}