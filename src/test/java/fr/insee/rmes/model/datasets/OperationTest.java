package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    Operation firstOperation = new Operation("uri","id","labelOperationLg1","labelOperationLg2");
    Operation secondOperation = new Operation();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUriValue(String mockedString){
        secondOperation.setUri(mockedString);
        assertEquals(mockedString,secondOperation.getUri());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckIdValue(String mockedString){
        firstOperation.setId(mockedString);
        assertEquals(mockedString,firstOperation.getId());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLabelSerieLg1Value(String mockedString){
        firstOperation.setlabelOperationLg1(mockedString);
        assertEquals(mockedString,firstOperation.getlabelOperationLg1());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLabelSerieLg2Value(String mockedString){
        firstOperation.setlabelOperationLg2(mockedString);
        assertEquals(mockedString,firstOperation.getlabelOperationLg2());
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstOperation.setAdditionalProperties(additionalProperties);
        assertEquals(additionalProperties,firstOperation.getAdditionalProperties());
    }


}