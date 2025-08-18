package fr.insee.rmes.modelSwagger.structure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class Label__1Test {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    Label__1 firstLabel__1 = new Label__1();
    Label__1 secondLabel__1 = new Label__1("mockedLangue","mockedContenu");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLangue(String mockedString){
        firstLabel__1.setLangue(mockedString);
        secondLabel__1.withLangue(mockedString);
        assertTrue(Objects.equals(firstLabel__1.getLangue(),secondLabel__1.getLangue()) &&
                Objects.equals(firstLabel__1.getLangue(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckContenu(String mockedString){
        firstLabel__1.setContenu(mockedString);
        secondLabel__1.withContenu(mockedString);
        assertTrue(Objects.equals(firstLabel__1.getContenu(),secondLabel__1.getContenu()) &&
                Objects.equals(firstLabel__1.getContenu(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstLabel__1.setAdditionalProperty("name","value");
        secondLabel__1.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstLabel__1.getAdditionalProperties(),secondLabel__1.getAdditionalProperties()) &&
                !Objects.equals(firstLabel__1.getAdditionalProperties(),additionalProperties));
    }


}