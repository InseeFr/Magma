package fr.insee.rmes.modelSwagger.structure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class ListCode__1Test {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");
    ListCode__1 firstListCode__1= new ListCode__1();
    ListCode__1 secondListCode__1 = new ListCode__1("mockedId","mockedIri");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstListCode__1.setId(mockedString);
        secondListCode__1.withId(mockedString);
        assertTrue(Objects.equals(firstListCode__1.getId(),secondListCode__1.getId()) &&
                Objects.equals(firstListCode__1.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstListCode__1.setUri(mockedString);
        secondListCode__1.withUri(mockedString);
        assertTrue(Objects.equals(firstListCode__1.getUri(),secondListCode__1.getUri()) &&
                Objects.equals(firstListCode__1.getUri(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstListCode__1.setAdditionalProperty("name","value");
        secondListCode__1.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstListCode__1.getAdditionalProperties(),secondListCode__1.getAdditionalProperties()) &&
                !Objects.equals(firstListCode__1.getAdditionalProperties(),additionalProperties));
    }
    
}