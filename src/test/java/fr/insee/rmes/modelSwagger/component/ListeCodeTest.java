package fr.insee.rmes.modelSwagger.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class ListeCodeTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    List<Code> codes = List.of(new Code(),new Code());
    ListeCode firstListCode = new ListeCode(codes,"mockedId","mockedUri");
    ListeCode secondListCode = new ListeCode();

    @Test
    void shouldCheckId(){
        firstListCode.setCodes(codes);
        secondListCode.withCodes(codes);
        assertTrue(Objects.equals(firstListCode.getCodes(),secondListCode.getCodes()) &&
                Objects.equals(firstListCode.getCodes(), codes));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstListCode.setId(mockedString);
        secondListCode.withId(mockedString);
        assertTrue(Objects.equals(firstListCode.getId(),secondListCode.getId()) &&
                Objects.equals(firstListCode.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstListCode.setUri(mockedString);
        secondListCode.withUri(mockedString);
        assertTrue(Objects.equals(firstListCode.getUri(),secondListCode.getUri()) &&
                Objects.equals(firstListCode.getUri(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstListCode.setAdditionalProperty("name","value");
        secondListCode.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstListCode.getAdditionalProperties(),secondListCode.getAdditionalProperties()) &&
                !Objects.equals(firstListCode.getAdditionalProperties(),additionalProperties));
    }

}