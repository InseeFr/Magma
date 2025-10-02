package fr.insee.rmes.modelSwagger.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class CodeTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");
    List<Label__1> labels = List.of(new Label__1(),new Label__1());

    Code firstCode = new Code("mockedCode", labels, "mockedUri");
    Code secondCode = new Code();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckCode(String mockedString){
        firstCode.setCode(mockedString);
        secondCode.withCode(mockedString);
        assertTrue(Objects.equals(firstCode.getCode(),secondCode.getCode()) &&
                Objects.equals(firstCode.getCode(), mockedString));
    }

    @Test
    void shouldCheckLabels(){
        firstCode.setLabel(labels);
        secondCode.withLabel(labels);
        assertTrue(Objects.equals(firstCode.getLabel(),secondCode.getLabel()) &&
                Objects.equals(firstCode.getLabel(), labels));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstCode.setUri(mockedString);
        secondCode.withUri(mockedString);
        assertTrue(Objects.equals(firstCode.getUri(),secondCode.getUri()) &&
                Objects.equals(firstCode.getUri(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstCode.setAdditionalProperty("name","value");
        secondCode.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstCode.getAdditionalProperties(),secondCode.getAdditionalProperties()) &&
                !Objects.equals(firstCode.getAdditionalProperties(),additionalProperties));
    }

}