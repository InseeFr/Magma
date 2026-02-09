package fr.insee.rmes.model.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class SerieModelTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");


    SerieModel firstSerieModel = new SerieModel(
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
                    "seriesLabelLg2",
                    "proprietaire"
            );

    SerieModel secondSerieModel = new SerieModel();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckType(String mockedString){
        firstSerieModel.setType(mockedString);
        secondSerieModel.withType(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getType(),secondSerieModel.getType()) &&
                Objects.equals(firstSerieModel.getType(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckFamilyId(String mockedString){
        firstSerieModel.setFamilyId(mockedString);
        secondSerieModel.withFamilyId(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getFamilyId(),secondSerieModel.getFamilyId()) &&
                Objects.equals(firstSerieModel.getFamilyId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckPeriodicityId(String mockedString){
        firstSerieModel.setPeriodicityId(mockedString);
        secondSerieModel.withPeriodicityId(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getPeriodicityId(),secondSerieModel.getPeriodicityId()) &&
                Objects.equals(firstSerieModel.getPeriodicityId(), mockedString));
    }


    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckPeriodicityLabelLg1(String mockedString){
        firstSerieModel.setPeriodicityLabelLg1(mockedString);
        secondSerieModel.withPeriodicityLabelLg1(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getPeriodicityLabelLg1(),secondSerieModel.getPeriodicityLabelLg1()) &&
                Objects.equals(firstSerieModel.getPeriodicityLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckPeriodicityLabelLg2(String mockedString){
        firstSerieModel.setPeriodicityLabelLg2(mockedString);
        secondSerieModel.withPeriodicityLabelLg2(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getPeriodicityLabelLg2(),secondSerieModel.getPeriodicityLabelLg2()) &&
                Objects.equals(firstSerieModel.getPeriodicityLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckSeries(String mockedString){
        firstSerieModel.setSeries(mockedString);
        secondSerieModel.withSeries(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getSeries(),secondSerieModel.getSeries()) &&
                Objects.equals(firstSerieModel.getSeries(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckTypeLabelLg1(String mockedString){
        firstSerieModel.setTypeLabelLg1(mockedString);
        secondSerieModel.withTypeLabelLg1(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getTypeLabelLg1(),secondSerieModel.getTypeLabelLg1()) &&
                Objects.equals(firstSerieModel.getTypeLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckTypeLabelLg2(String mockedString){
        firstSerieModel.setTypeLabelLg2(mockedString);
        secondSerieModel.withTypeLabelLg2(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getTypeLabelLg2(),secondSerieModel.getTypeLabelLg2()) &&
                Objects.equals(firstSerieModel.getTypeLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckPeriodicity(String mockedString){
        firstSerieModel.setPeriodicity(mockedString);
        secondSerieModel.withPeriodicity(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getPeriodicity(),secondSerieModel.getPeriodicity()) &&
                Objects.equals(firstSerieModel.getPeriodicity(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckTypeId(String mockedString){
        firstSerieModel.setTypeId(mockedString);
        secondSerieModel.withTypeId(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getTypeId(),secondSerieModel.getTypeId()) &&
                Objects.equals(firstSerieModel.getTypeId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstSerieModel.setId(mockedString);
        secondSerieModel.withId(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getId(),secondSerieModel.getId()) &&
                Objects.equals(firstSerieModel.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckSeriesLabelLg1(String mockedString){
        firstSerieModel.setSeriesLabelLg1(mockedString);
        secondSerieModel.withSeriesLabelLg1(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getSeriesLabelLg1(),secondSerieModel.getSeriesLabelLg1()) &&
                Objects.equals(firstSerieModel.getSeriesLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckSeriesAltLabelLg1(String mockedString){
        firstSerieModel.setSeriesAltLabelLg1(mockedString);
        secondSerieModel.withSeriesAltLabelLg1(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getSeriesAltLabelLg1(),secondSerieModel.getSeriesAltLabelLg1()) &&
                Objects.equals(firstSerieModel.getSeriesAltLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckNbOperations(String mockedString){
        firstSerieModel.setNbOperation(mockedString);
        secondSerieModel.withNbOperation(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getNbOperation(),secondSerieModel.getNbOperation()) &&
                Objects.equals(firstSerieModel.getNbOperation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckFamily(String mockedString){
        firstSerieModel.setFamily(mockedString);
        secondSerieModel.withFamily(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getFamily(),secondSerieModel.getFamily()) &&
                Objects.equals(firstSerieModel.getFamily(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckFamilyLabelLg1(String mockedString){
        firstSerieModel.setFamilyLabelLg1(mockedString);
        secondSerieModel.withFamilyLabelLg1(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getFamilyLabelLg1(),secondSerieModel.getFamilyLabelLg1()) &&
                Objects.equals(firstSerieModel.getFamilyLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckSeriesAltLabelLg2(String mockedString){
        firstSerieModel.setSeriesAltLabelLg2(mockedString);
        secondSerieModel.withSeriesAltLabelLg2(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getSeriesAltLabelLg2(),secondSerieModel.getSeriesAltLabelLg2()) &&
                Objects.equals(firstSerieModel.getSeriesAltLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckFamilyLabelLg2(String mockedString){
        firstSerieModel.setFamilyLabelLg2(mockedString);
        secondSerieModel.withFamilyLabelLg2(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getFamilyLabelLg2(),secondSerieModel.getFamilyLabelLg2()) &&
                Objects.equals(firstSerieModel.getFamilyLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckSeriesLabelLg2(String mockedString){
        firstSerieModel.setSeriesLabelLg2(mockedString);
        secondSerieModel.withSeriesLabelLg2(mockedString);
        assertTrue(Objects.equals(firstSerieModel.getSeriesLabelLg2(),secondSerieModel.getSeriesLabelLg2()) &&
                Objects.equals(firstSerieModel.getSeriesLabelLg2(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstSerieModel.setAdditionalProperty("name","value");
        secondSerieModel.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstSerieModel.getAdditionalProperties(),secondSerieModel.getAdditionalProperties()) &&
                !Objects.equals(firstSerieModel.getAdditionalProperties(),additionalProperties));
    }




}