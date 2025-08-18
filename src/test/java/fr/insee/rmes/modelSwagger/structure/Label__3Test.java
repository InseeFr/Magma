package fr.insee.rmes.modelSwagger.structure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class Label__3Test {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    Label__3 firstLabel__3 = new Label__3();
    Label__3 secondLabel__3 = new Label__3("mockedLangue","mockedContenu");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLangue(String mockedString){
        firstLabel__3.setLangue(mockedString);
        secondLabel__3.withLangue(mockedString);
        assertTrue(Objects.equals(firstLabel__3.getLangue(),secondLabel__3.getLangue()) &&
                Objects.equals(firstLabel__3.getLangue(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckContenu(String mockedString){
        firstLabel__3.setContenu(mockedString);
        secondLabel__3.withContenu(mockedString);
        assertTrue(Objects.equals(firstLabel__3.getContenu(),secondLabel__3.getContenu()) &&
                Objects.equals(firstLabel__3.getContenu(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstLabel__3.setAdditionalProperty("name","value");
        secondLabel__3.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstLabel__3.getAdditionalProperties(),secondLabel__3.getAdditionalProperties()) &&
                !Objects.equals(firstLabel__3.getAdditionalProperties(),additionalProperties));
    }
    
}