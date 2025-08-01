package fr.insee.rmes.model.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class SerieByIdTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    SerieById firstSerieById = new SerieById(
            "type",
            "familyId",
            "periodicityId",
            "periodicityLabelLg2",
            "periodicityLabelLg1",
            "series",
            "typeLabelLg1",
            "typeLabelLg2",
            "periodicity",
            "typeId",
            "id",
            "seriesLabelLg1",
            "seriesAltLabelLg1",
            "nbOperation",
            "family",
            "familyLabelLg1",
            "seriesAltLabelLg2",
            "familyLabelLg2",
            "seriesLabelLg2");

    SerieById secondSerieById = new SerieById();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckType(String mockedString){
        firstSerieById.setType(mockedString);
        secondSerieById.withType(mockedString);
        assertTrue(Objects.equals(firstSerieById.getType(),secondSerieById.getType()) &&
                Objects.equals(firstSerieById.getType(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckFamilyId(String mockedString){
        firstSerieById.setFamilyId(mockedString);
        secondSerieById.withFamilyId(mockedString);
        assertTrue(Objects.equals(firstSerieById.getFamilyId(),secondSerieById.getFamilyId()) &&
                Objects.equals(firstSerieById.getFamilyId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckPeriodicityId(String mockedString){
        firstSerieById.setPeriodicityId(mockedString);
        secondSerieById.withPeriodicityId(mockedString);
        assertTrue(Objects.equals(firstSerieById.getPeriodicityId(),secondSerieById.getPeriodicityId()) &&
                Objects.equals(firstSerieById.getPeriodicityId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckPeriodicityLabelLg2(String mockedString){
        firstSerieById.setPeriodicityLabelLg2(mockedString);
        secondSerieById.withPeriodicityLabelLg2(mockedString);
        assertTrue(Objects.equals(firstSerieById.getPeriodicityLabelLg2(),secondSerieById.getPeriodicityLabelLg2()) &&
                Objects.equals(firstSerieById.getPeriodicityLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckPeriodicityLabelLg1(String mockedString){
        firstSerieById.setPeriodicityLabelLg1(mockedString);
        secondSerieById.withPeriodicityLabelLg1(mockedString);
        assertTrue(Objects.equals(firstSerieById.getPeriodicityLabelLg1(),secondSerieById.getPeriodicityLabelLg1()) &&
                Objects.equals(firstSerieById.getPeriodicityLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckSeries(String mockedString){
        firstSerieById.setSeries(mockedString);
        secondSerieById.withSeries(mockedString);
        assertTrue(Objects.equals(firstSerieById.getSeries(),secondSerieById.getSeries()) &&
                Objects.equals(firstSerieById.getSeries(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckTypeLabelLg1(String mockedString){
        firstSerieById.setTypeLabelLg1(mockedString);
        secondSerieById.withTypeLabelLg1(mockedString);
        assertTrue(Objects.equals(firstSerieById.getTypeLabelLg1(),secondSerieById.getTypeLabelLg1()) &&
                Objects.equals(firstSerieById.getTypeLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckTypeLabelLg2(String mockedString){
        firstSerieById.setTypeLabelLg2(mockedString);
        secondSerieById.withTypeLabelLg2(mockedString);
        assertTrue(Objects.equals(firstSerieById.getTypeLabelLg2(),secondSerieById.getTypeLabelLg2()) &&
                Objects.equals(firstSerieById.getTypeLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckPeriodicity(String mockedString){
        firstSerieById.setPeriodicity(mockedString);
        secondSerieById.withPeriodicity(mockedString);
        assertTrue(Objects.equals(firstSerieById.getPeriodicity(),secondSerieById.getPeriodicity()) &&
                Objects.equals(firstSerieById.getPeriodicity(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckTypeId(String mockedString){
        firstSerieById.setTypeId(mockedString);
        secondSerieById.withTypeId(mockedString);
        assertTrue(Objects.equals(firstSerieById.getTypeId(),secondSerieById.getTypeId()) &&
                Objects.equals(firstSerieById.getTypeId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstSerieById.setId(mockedString);
        secondSerieById.withId(mockedString);
        assertTrue(Objects.equals(firstSerieById.getId(),secondSerieById.getId()) &&
                Objects.equals(firstSerieById.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckSeriesLabelLg1(String mockedString){
        firstSerieById.setSeriesLabelLg1(mockedString);
        secondSerieById.withSeriesLabelLg1(mockedString);
        assertTrue(Objects.equals(firstSerieById.getSeriesLabelLg1(),secondSerieById.getSeriesLabelLg1()) &&
                Objects.equals(firstSerieById.getSeriesLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckSeriesAltLabelLg1(String mockedString){
        firstSerieById.setSeriesAltLabelLg1(mockedString);
        secondSerieById.withSeriesAltLabelLg1(mockedString);
        assertTrue(Objects.equals(firstSerieById.getSeriesAltLabelLg1(),secondSerieById.getSeriesAltLabelLg1()) &&
                Objects.equals(firstSerieById.getSeriesAltLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckNbOperations(String mockedString){
        firstSerieById.setNbOperation(mockedString);
        secondSerieById.withNbOperation(mockedString);
        assertTrue(Objects.equals(firstSerieById.getNbOperation(),secondSerieById.getNbOperation()) &&
                Objects.equals(firstSerieById.getNbOperation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckFamily(String mockedString){
        firstSerieById.setFamily(mockedString);
        secondSerieById.withFamily(mockedString);
        assertTrue(Objects.equals(firstSerieById.getFamily(),secondSerieById.getFamily()) &&
                Objects.equals(firstSerieById.getFamily(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckFamilyLabelLg1(String mockedString){
        firstSerieById.setFamilyLabelLg1(mockedString);
        secondSerieById.withFamilyLabelLg1(mockedString);
        assertTrue(Objects.equals(firstSerieById.getFamilyLabelLg1(),secondSerieById.getFamilyLabelLg1()) &&
                Objects.equals(firstSerieById.getFamilyLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckSeriesAltLabelLg2(String mockedString){
        firstSerieById.setSeriesAltLabelLg2(mockedString);
        secondSerieById.withSeriesAltLabelLg2(mockedString);
        assertTrue(Objects.equals(firstSerieById.getSeriesAltLabelLg2(),secondSerieById.getSeriesAltLabelLg2()) &&
                Objects.equals(firstSerieById.getSeriesAltLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckFamilyLabelLg2(String mockedString){
        firstSerieById.setFamilyLabelLg2(mockedString);
        secondSerieById.withFamilyLabelLg2(mockedString);
        assertTrue(Objects.equals(firstSerieById.getFamilyLabelLg2(),secondSerieById.getFamilyLabelLg2()) &&
                Objects.equals(firstSerieById.getFamilyLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckSeriesLabelLg2(String mockedString){
        firstSerieById.setSeriesLabelLg2(mockedString);
        secondSerieById.withSeriesLabelLg2(mockedString);
        assertTrue(Objects.equals(firstSerieById.getSeriesLabelLg2(),secondSerieById.getSeriesLabelLg2()) &&
                Objects.equals(firstSerieById.getSeriesLabelLg2(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstSerieById.setAdditionalProperty("name","value");
        secondSerieById.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstSerieById.getAdditionalProperties(),secondSerieById.getAdditionalProperties()) &&
                !Objects.equals(firstSerieById.getAdditionalProperties(),additionalProperties));
    }

}