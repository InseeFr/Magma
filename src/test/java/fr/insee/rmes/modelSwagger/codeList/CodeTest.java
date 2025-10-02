package fr.insee.rmes.modelSwagger.codeList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class CodeTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");
    List<Label> labels = List.of(new Label(),new Label());

    Code firstCode = new Code("code", labels, "uri");
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
    void shouldCheckLabel(){
        firstCode.setLabel(labels);
        secondCode.withLabel(labels);
        assertEquals(firstCode.getLabel(),secondCode.getLabel());
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
        assertTrue(Objects.equals(firstCode.getAdditionalProperties(),firstCode.getAdditionalProperties()) &&
                !Objects.equals(firstCode.getAdditionalProperties(),additionalProperties));
    }



}