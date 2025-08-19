package fr.insee.rmes.modelSwagger.codeList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class ListCodeByIdModelSwaggerTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    List<Code> listCodes = List.of(new Code(),new Code());
    List<Label__1> listLabels= List.of(new Label__1(),new Label__1());

    ListCodeByIdModelSwagger firstListCode = new ListCodeByIdModelSwagger(
            listCodes,
            "id",
            listLabels,
            "uri",
            "version",
            "dateMiseAJour"
    );

    ListCodeByIdModelSwagger secondListCode = new ListCodeByIdModelSwagger();

    @Test
    void shouldCheckCode(){
        firstListCode.setCodes(listCodes);
        secondListCode.withCodes(listCodes);
        assertTrue(Objects.equals(firstListCode.getCodes(),secondListCode.getCodes()) &&
                Objects.equals(firstListCode.getCodes(), listCodes));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstListCode.setId(mockedString);
        secondListCode.withId(mockedString);
        assertTrue(Objects.equals(firstListCode.getId(),secondListCode.getId()) &&
                Objects.equals(firstListCode.getId(), mockedString));
    }

    @Test
    void shouldCheckLabel(){
        firstListCode.setLabel(listLabels);
        secondListCode.withLabel(listLabels);
        assertTrue(Objects.equals(firstListCode.getLabel(),secondListCode.getLabel()) &&
                Objects.equals(firstListCode.getLabel(), listLabels));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstListCode.setUri(mockedString);
        secondListCode.withUri(mockedString);
        assertTrue(Objects.equals(firstListCode.getUri(),secondListCode.getUri()) &&
                Objects.equals(firstListCode.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckVersion(String mockedString){
        firstListCode.setVersion(mockedString);
        secondListCode.withVersion(mockedString);
        assertTrue(Objects.equals(firstListCode.getVersion(),secondListCode.getVersion()) &&
                Objects.equals(firstListCode.getVersion(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckDateMiseAJour(String mockedString){
        secondListCode.setDateMiseAJour(mockedString);
        assertEquals(secondListCode.getDateMiseAJour(),mockedString);
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstListCode.setAdditionalProperty("name","value");
        secondListCode.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstListCode.getAdditionalProperties(),secondListCode.getAdditionalProperties()) &&
                !Objects.equals(firstListCode.getAdditionalProperties(),additionalProperties));
    }

}