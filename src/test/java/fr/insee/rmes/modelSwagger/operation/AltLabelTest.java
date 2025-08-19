package fr.insee.rmes.modelSwagger.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class AltLabelTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    AltLabel firstAltLabel = new AltLabel();
    AltLabel secondAltLabel = new AltLabel("mockedLang","mockedContenu");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLang(String mockedString){
        firstAltLabel.setLangue(mockedString);
        secondAltLabel.withLangue(mockedString);
        assertTrue(Objects.equals(firstAltLabel.getLangue(),secondAltLabel.getLangue()) &&
                Objects.equals(firstAltLabel.getLangue(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckContenu(String mockedString){
        firstAltLabel.setContenu(mockedString);
        secondAltLabel.withContenu(mockedString);
        assertTrue(Objects.equals(firstAltLabel.getContenu(),secondAltLabel.getContenu()) &&
                Objects.equals(firstAltLabel.getContenu(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstAltLabel.setAdditionalProperty("name","value");
        secondAltLabel.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstAltLabel.getAdditionalProperties(), secondAltLabel.getAdditionalProperties()) &&
                !Objects.equals(firstAltLabel.getAdditionalProperties(),additionalProperties));
    }

}