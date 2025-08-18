package fr.insee.rmes.modelSwagger.structure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class Label__2Test {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    Label__2 firstLabel__2 = new Label__2();
    Label__2 secondLabel__2 = new Label__2("mockedLangue","mockedContenu");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLangue(String mockedString){
        firstLabel__2.setLangue(mockedString);
        secondLabel__2.withLangue(mockedString);
        assertTrue(Objects.equals(firstLabel__2.getLangue(),secondLabel__2.getLangue()) &&
                Objects.equals(firstLabel__2.getLangue(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckContenu(String mockedString){
        firstLabel__2.setContenu(mockedString);
        secondLabel__2.withContenu(mockedString);
        assertTrue(Objects.equals(firstLabel__2.getContenu(),secondLabel__2.getContenu()) &&
                Objects.equals(firstLabel__2.getContenu(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstLabel__2.setAdditionalProperty("name","value");
        secondLabel__2.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstLabel__2.getAdditionalProperties(),secondLabel__2.getAdditionalProperties()) &&
                !Objects.equals(firstLabel__2.getAdditionalProperties(),additionalProperties));
    }

}