package fr.insee.rmes.model.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class OperationBySerieIdTest {

    String series = "series";
    String typeLabelLg1="typeLabelLg1";
    String typeLabelLg2="typeLabelLg2";
    String operationId="operationId";
    String operationLabelLg1="operationLabelLg1";
    String operationLabelLg2="operationLabelLg2";
    String operationAltLabelLg2="operationAltLabelLg2";
    String seriesLabelLg1="seriesLabelLg1";
    String operationAltLabelLg1="operationAltLabelLg1";
    String uri="uri";
    String seriesId="seriesId";
    String seriesLabelLg2="seriesLabelLg2";
    String periodicityId="periodicityId";
    String periodicity="periodicity";
    String periodicityLabelLg1="periodicityLabelLg1";
    String periodicityLabelLg2="periodicityLabelLg2";

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    OperationBySerieId firstOperationBySerieId= new OperationBySerieId(series,typeLabelLg1,typeLabelLg2,operationId,operationLabelLg1,operationLabelLg2,operationAltLabelLg2,seriesLabelLg1,operationAltLabelLg1,uri,seriesId,seriesLabelLg2,periodicityId,periodicity,periodicityLabelLg1,periodicityLabelLg2);
    OperationBySerieId secondOperationBySerieId= new OperationBySerieId();

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckSeries(String mockedString){
        firstOperationBySerieId.setSeries(mockedString);
        secondOperationBySerieId.withSeries(mockedString);
        assertTrue(Objects.equals(firstOperationBySerieId.getSeries(), secondOperationBySerieId.getSeries()) &&
                Objects.equals(firstOperationBySerieId.getSeries(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedTypeLabelLg1A", "mockedTypeLabelLg1B", "mockedTypeLabelLg1C" })
    void shouldCheckLabelLg1(String mockedString){
        firstOperationBySerieId.setTypeLabelLg1(mockedString);
        secondOperationBySerieId.withTypeLabelLg1(mockedString);
        assertTrue(Objects.equals(firstOperationBySerieId.getTypeLabelLg1(), secondOperationBySerieId.getTypeLabelLg1()) &&
                Objects.equals(firstOperationBySerieId.getTypeLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedTypeLabelLg2A", "mockedTypeLabelLg2B", "mockedTypeLabelLg2C" })
    void shouldCheckLabelLg2(String mockedString){
        firstOperationBySerieId.setTypeLabelLg2(mockedString);
        secondOperationBySerieId.withTypeLabelLg2(mockedString);
        assertTrue(Objects.equals(firstOperationBySerieId.getTypeLabelLg2(), secondOperationBySerieId.getTypeLabelLg2()) &&
                Objects.equals(firstOperationBySerieId.getTypeLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedId1", "mockedId2", "mockedId3" })
    void shouldCheckOperationId(String mockedString){
        firstOperationBySerieId.setOperationId(mockedString);
        secondOperationBySerieId.withOperationId(mockedString);
        assertTrue(Objects.equals(firstOperationBySerieId.getOperationId(), secondOperationBySerieId.getOperationId()) &&
                Objects.equals(firstOperationBySerieId.getOperationId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedLabelLg1A", "mockedLabelLg1B", "mockedLabelLg1C" })
    void shouldCheckOperationLabelLg1(String mockedString){
        firstOperationBySerieId.setOperationLabelLg1(mockedString);
        secondOperationBySerieId.withOperationLabelLg1(mockedString);
        assertTrue(Objects.equals(firstOperationBySerieId.getOperationLabelLg1(), secondOperationBySerieId.getOperationLabelLg1()) &&
                Objects.equals(firstOperationBySerieId.getOperationLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedLabelLg2A", "mockedLabelLg2B", "mockedLabelLg2C" })
    void shouldCheckOperationLabelLg2(String mockedString){
        firstOperationBySerieId.setOperationLabelLg2(mockedString);
        secondOperationBySerieId.withOperationLabelLg2(mockedString);
        assertTrue(Objects.equals(firstOperationBySerieId.getOperationLabelLg2(), secondOperationBySerieId.getOperationLabelLg2()) &&
                Objects.equals(firstOperationBySerieId.getOperationLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedLabelLg2A", "mockedLabelLg2B", "mockedLabelLg2C" })
    void shouldCheckOperationAltLabelLg2(String mockedString){
        firstOperationBySerieId.setOperationAltLabelLg2(mockedString);
        secondOperationBySerieId.withOperationAltLabelLg2(mockedString);
        assertTrue(Objects.equals(firstOperationBySerieId.getOperationAltLabelLg2(), secondOperationBySerieId.getOperationAltLabelLg2()) &&
                Objects.equals(firstOperationBySerieId.getOperationAltLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeriesLabelLg1A", "mockedSeriesLabelLg1B", "mockedSeriesLabelLg1C" })
    void shouldCheckSeriesLabelLg1(String mockedString){
        firstOperationBySerieId.setSeriesLabelLg1(mockedString);
        secondOperationBySerieId.withSeriesLabelLg1(mockedString);
        assertTrue(Objects.equals(firstOperationBySerieId.getSeriesLabelLg1(), secondOperationBySerieId.getSeriesLabelLg1()) &&
                Objects.equals(firstOperationBySerieId.getSeriesLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedLabelLg1A", "mockedLabelLg1B", "mockedLabelLg1C" })
    void shouldCheckOperationAltLabelLg1(String mockedString){
        firstOperationBySerieId.setOperationAltLabelLg1(mockedString);
        secondOperationBySerieId.withOperationAltLabelLg1(mockedString);
        assertTrue(Objects.equals(firstOperationBySerieId.getOperationAltLabelLg1(), secondOperationBySerieId.getOperationAltLabelLg1()) &&
                Objects.equals(firstOperationBySerieId.getOperationAltLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedUri1", "mockedUri2", "mockedUri3" })
    void shouldCheckUri(String mockedString){
        firstOperationBySerieId.setUri(mockedString);
        secondOperationBySerieId.withUri(mockedString);
        assertTrue(Objects.equals(firstOperationBySerieId.getUri(), secondOperationBySerieId.getUri()) &&
                Objects.equals(firstOperationBySerieId.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedId1", "mockedId2", "mockedId3" })
    void shouldCheckSeriesId(String mockedString){
        firstOperationBySerieId.setSeriesId(mockedString);
        secondOperationBySerieId.withSeriesId(mockedString);
        assertTrue(Objects.equals(firstOperationBySerieId.getSeriesId(), secondOperationBySerieId.getSeriesId()) &&
                Objects.equals(firstOperationBySerieId.getSeriesId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeriesLabelLg2A", "mockedSeriesLabelLg2B", "mockedSeriesLabelLg2C" })
    void shouldCheckSeriesLabelLg2(String mockedString){
        firstOperationBySerieId.setSeriesLabelLg2(mockedString);
        secondOperationBySerieId.withSeriesLabelLg2(mockedString);
        assertTrue(Objects.equals(firstOperationBySerieId.getSeriesLabelLg2(), secondOperationBySerieId.getSeriesLabelLg2()) &&
                Objects.equals(firstOperationBySerieId.getSeriesLabelLg2(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstOperationBySerieId.setAdditionalProperty("name","value");
        secondOperationBySerieId.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstOperationBySerieId.getAdditionalProperties(), secondOperationBySerieId.getAdditionalProperties()) &&
                !Objects.equals(firstOperationBySerieId.getAdditionalProperties(),additionalProperties));
    }

}