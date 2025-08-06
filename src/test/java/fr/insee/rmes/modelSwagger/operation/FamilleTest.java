package fr.insee.rmes.modelSwagger.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class FamilleTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");
    List<Label> label = List.of(new Label(),new Label());

    Famille firstFamille = new Famille();
    Famille secondFamille = new Famille("mockedId",label,"mockedUri");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstFamille.setId(mockedString);
        secondFamille.withId(mockedString);
        assertTrue(Objects.equals(firstFamille.getId(),secondFamille.getId()) &&
                Objects.equals(firstFamille.getId(), mockedString));
    }

    @Test
    void shouldCheckLabel(){
        firstFamille.setLabel(label);
        secondFamille.withLabel(label);
        assertTrue(Objects.equals(firstFamille.getLabel(),secondFamille.getLabel()) &&
                Objects.equals(firstFamille.getLabel(),label));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        firstFamille.setUri(mockedString);
        secondFamille.withUri(mockedString);
        assertTrue(Objects.equals(firstFamille.getUri(),secondFamille.getUri()) &&
                Objects.equals(firstFamille.getUri(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstFamille.setAdditionalProperty("name","value");
        secondFamille.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstFamille.getAdditionalProperties(), secondFamille.getAdditionalProperties()) &&
                !Objects.equals(firstFamille.getAdditionalProperties(),additionalProperties));
    }

}