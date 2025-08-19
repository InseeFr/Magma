package fr.insee.rmes.modelSwagger.structure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class DimensionTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    Dimension firstDimension = new Dimension();

    Dimension secondDimension = new Dimension(
            "ordre",
            "notation",
            new ListCode__1(),
            List.of( new Label__3()),
            "uri",
            "representation"
    );

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckOrdre(String mockedString){
        firstDimension.setOrdre(mockedString);
        secondDimension.withOrdre(mockedString);
        assertTrue(Objects.equals(firstDimension .getOrdre(),secondDimension.getOrdre()) &&
                Objects.equals(firstDimension.getOrdre(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckNotation(String mockedString){
        firstDimension.setNotation(mockedString);
        secondDimension.withNotation(mockedString);
        assertTrue(Objects.equals(firstDimension.getNotation(),secondDimension.getNotation()) &&
                Objects.equals(firstDimension.getNotation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstDimension.setUri(mockedString);
        secondDimension.withUri(mockedString);
        assertTrue(Objects.equals(firstDimension.getUri(),secondDimension.getUri()) &&
                Objects.equals(firstDimension.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckRepresentation(String mockedString){
        firstDimension.setRepresentation(mockedString);
        secondDimension.withRepresentation(mockedString);
        assertTrue(Objects.equals(firstDimension.getRepresentation(),secondDimension.getRepresentation()) &&
                Objects.equals(firstDimension.getRepresentation(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstDimension.setAdditionalProperty("name","value");
        secondDimension.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstDimension.getAdditionalProperties(),secondDimension.getAdditionalProperties()) &&
                !Objects.equals(firstDimension.getAdditionalProperties(),additionalProperties));
    }

    @Test
    void shouldCheckListCode(){
        ListCode__1 listCode = new ListCode__1("id","uri");
        firstDimension.setListCode(listCode);
        secondDimension.withListCode(listCode);
        assertTrue(Objects.equals(firstDimension.getListCode(),secondDimension.getListCode()) &&
                Objects.equals(firstDimension.getListCode(), listCode));
    }

    @Test
    void shouldCheckLabel(){
        List<Label__3> listLabel = List.of(new Label__3("langue","contenu"));
        firstDimension.setLabel(listLabel);
        secondDimension.withLabel(listLabel);
        assertTrue(Objects.equals(firstDimension.getLabel(),secondDimension.getLabel()) &&
                Objects.equals(firstDimension.getLabel(), listLabel));
    }

}