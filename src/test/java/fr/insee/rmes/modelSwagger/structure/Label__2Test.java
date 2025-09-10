package fr.insee.rmes.modelSwagger.structure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class Label__2Test {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    Label__2 firstLabel = new Label__2();
    Label__2 secondLabel = new Label__2("mockedLangue","mockedContenu");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLangue(String mockedString){
        firstLabel.setLangue(mockedString);
        secondLabel.withLangue(mockedString);
        assertTrue(Objects.equals(firstLabel.getLangue(),secondLabel.getLangue()) &&
                Objects.equals(firstLabel.getLangue(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckContenu(String mockedString){
        firstLabel.setContenu(mockedString);
        secondLabel.withContenu(mockedString);
        assertTrue(Objects.equals(firstLabel.getContenu(),secondLabel.getContenu()) &&
                Objects.equals(firstLabel.getContenu(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstLabel.setAdditionalProperty("name","value");
        secondLabel.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstLabel.getAdditionalProperties(),secondLabel.getAdditionalProperties()) &&
                !Objects.equals(firstLabel.getAdditionalProperties(),additionalProperties));
    }

}