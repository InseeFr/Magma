package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    String uri= "mockedUri";
    String id= "mockedId";
    String labelOperationLg1="mockedLabelOperationLg1";
    String labelOperationLg2="mockedLabelOperationLg2";
    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    Operation firstOperation = new Operation(uri,id,labelOperationLg1,labelOperationLg2);
    Operation secondOperation = new Operation();

    @ParameterizedTest
    @ValueSource(strings = { "mockedUri1", "mockedUri2", "mockedUri3" })
    void shouldCheckUri(String mockedString){
        firstOperation.setUri(mockedString);
        assertEquals(mockedString,firstOperation.getUri());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedId1", "mockedId2", "mockedId3" })
    void shouldCheckId(String mockedString){
        secondOperation.setId(mockedString);
        assertEquals(mockedString,secondOperation.getId());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedOperationLg1", "mockedOperationLg2", "mockedOperationLg3" })
    void shouldCheckOperationLg1(String mockedString){
        firstOperation.setlabelOperationLg1(mockedString);
        assertEquals(mockedString,firstOperation.getlabelOperationLg1());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedOperationLg1", "mockedOperationLg2", "mockedOperationLg3" })
    void shouldCheckOperationLg2(String mockedString){
        firstOperation.setlabelOperationLg2(mockedString);
        assertEquals(mockedString,firstOperation.getlabelOperationLg2());
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstOperation.setAdditionalProperties(additionalProperties);
        assertEquals(additionalProperties,firstOperation.getAdditionalProperties());
    }

}