package fr.insee.rmes.modelSwagger.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class SerieByIdModelSwaggerTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2", "key2", "value2");
    List<AltLabel> altLabel = List.of(new AltLabel(), new AltLabel());
    List<Label> label = List.of(new Label(), new Label());
    Frequence frequence = new Frequence();
    Type type = new Type();
    Famille famille = new Famille();
    List<String> proprietaires = List.of("proprietaire");

    SerieByIdModelSwagger firstSerie = new SerieByIdModelSwagger();
    SerieByIdModelSwagger secondSerie = new SerieByIdModelSwagger(
            altLabel,
            label,
            type,
            "mockedSeries",
            "mockedId",
            frequence,
            "mockedNbOperation",
            famille);

    SerieByIdModelSwagger thirdSerie = new SerieByIdModelSwagger(
            altLabel,
            label,
            type,
            "series",
            "id",
            frequence,
            "nbOperation",
            famille,
            "proprietaire");


    @Test
    void shouldCheckLabel(){
        firstSerie.setLabel(label);
        secondSerie.withLabel(label);
        assertTrue(Objects.equals(firstSerie.getLabel(),secondSerie.getLabel()) &&
                Objects.equals(firstSerie.getLabel(), label));
    }

    @Test
    void shouldCheckAltLabel(){
        firstSerie.setAltLabel(altLabel);
        secondSerie.withAltLabel(altLabel);
        assertTrue(Objects.equals(firstSerie.getAltLabel(),secondSerie.getAltLabel()) &&
                Objects.equals(firstSerie.getAltLabel(), altLabel));
    }

    @Test
    void shouldCheckType(){
        thirdSerie.setType(type);
        secondSerie.withType(type);
        assertTrue(Objects.equals(secondSerie.getType(),thirdSerie.getType()) &&
                Objects.equals(secondSerie.getType(), type));
    }

    @Test
    void shouldCheckProprietaire(){
        firstSerie.setProprietaire(proprietaires);
        assertTrue(Objects.equals(thirdSerie.getProprietaire(),firstSerie.getProprietaire()) &&
                Objects.equals(thirdSerie.getProprietaire(), proprietaires));
    }


    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckSeries(String mockedString){
        firstSerie.setSeries(mockedString);
        secondSerie.withSeries(mockedString);
        assertTrue(Objects.equals(firstSerie.getSeries(),secondSerie.getSeries()) &&
                Objects.equals(firstSerie.getSeries(), mockedString));
    }


    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstSerie.setId(mockedString);
        secondSerie.withId(mockedString);
        assertTrue(Objects.equals(firstSerie.getId(),secondSerie.getId()) &&
                Objects.equals(firstSerie.getId(), mockedString));
    }

    @Test
    void shouldCheckFrequence(){
        thirdSerie.setFrequence(frequence);
        secondSerie.withFrequence(frequence);
        assertTrue(Objects.equals(secondSerie.getFrequence(),thirdSerie.getFrequence()) &&
                Objects.equals(secondSerie.getFrequence(),frequence));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckNbOperations(String mockedString){
        firstSerie.setNbOperation(mockedString);
        secondSerie.withNbOperation(mockedString);
        assertTrue(Objects.equals(firstSerie.getNbOperation(),secondSerie.getNbOperation()) &&
                Objects.equals(firstSerie.getNbOperation(), mockedString));
    }

    @Test
    void shouldCheckFamille(){
        thirdSerie.setFamille(famille);
        secondSerie.withFamille(famille);
        assertTrue(Objects.equals(secondSerie.getFamille(),thirdSerie.getFamille()) &&
                Objects.equals(secondSerie.getFamille(),famille));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstSerie.setAdditionalProperty("name","value");
        secondSerie.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstSerie.getAdditionalProperties(), secondSerie.getAdditionalProperties()) &&
                !Objects.equals(firstSerie.getAdditionalProperties(),additionalProperties));
    }



}