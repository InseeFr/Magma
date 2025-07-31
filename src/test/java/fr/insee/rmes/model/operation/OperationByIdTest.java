package fr.insee.rmes.model.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class OperationByIdTest {

    String serie = "serie";
    String id="id";
    String operationLabelLg1="operationLabelLg1";
    String operationLabelLg2="operationLabelLg2";
    String seriesLabelLg1="seriesLabelLg1";
    String uri="uri";
    String seriesId="seriesId";
    String seriesLabelLg2="seriesLabelLg2";
    String proprietaire="proprietaire";
    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    OperationById firstOperationById = new OperationById(serie,id,operationLabelLg1,operationLabelLg2,seriesLabelLg1,uri,seriesId,seriesLabelLg2,proprietaire);
    OperationById secondOperationById = new OperationById();

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckSeries(String mockedString){
        firstOperationById.setSeries(mockedString);
        secondOperationById.withSeries(mockedString);
        assertTrue(Objects.equals(firstOperationById.getSeries(), secondOperationById.getSeries()) &&
                Objects.equals(firstOperationById.getSeries(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedId1", "mockedId2", "mockedId3" })
    void shouldCheckId(String mockedString){
        firstOperationById.setId(mockedString);
        secondOperationById.withOperationId(mockedString);
        assertTrue(Objects.equals(firstOperationById.getId(), secondOperationById.getId()) &&
                Objects.equals(firstOperationById.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedLabelLg1A", "mockedLabelLg1B", "mockedLabelLg1C" })
    void shouldCheckLabelLg1(String mockedString){
        firstOperationById.setOperationLabelLg1(mockedString);
        secondOperationById.withOperationLabelLg1(mockedString);
        assertTrue(Objects.equals(firstOperationById.getOperationLabelLg1(), secondOperationById.getOperationLabelLg1()) &&
                Objects.equals(firstOperationById.getOperationLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedLabelLg2A", "mockedLabelLg2B", "mockedLabelLg2C" })
    void shouldCheckLabelLg2(String mockedString){
        firstOperationById.setOperationLabelLg2(mockedString);
        secondOperationById.withOperationLabelLg2(mockedString);
        assertTrue(Objects.equals(firstOperationById.getOperationLabelLg2(), secondOperationById.getOperationLabelLg2()) &&
                Objects.equals(firstOperationById.getOperationLabelLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeriesLabelLg1A", "mockedSeriesLabelLg1B", "mockedSeriesLabelLg1C" })
    void shouldCheckSeriesLabelLg1(String mockedString){
        firstOperationById.setSeriesLabelLg1(mockedString);
        secondOperationById.withSeriesLabelLg1(mockedString);
        assertTrue(Objects.equals(firstOperationById.getSeriesLabelLg1(), secondOperationById.getSeriesLabelLg1()) &&
                Objects.equals(firstOperationById.getSeriesLabelLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedUri1", "mockedUri2", "mockedUri3" })
    void shouldCheckUri(String mockedString){
        firstOperationById.setUri(mockedString);
        secondOperationById.withUri(mockedString);
        assertTrue(Objects.equals(firstOperationById.getUri(), secondOperationById.getUri()) &&
                Objects.equals(firstOperationById.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeriesId1", "mockedSeriesId2", "mockedSeriesId3" })
    void shouldCheckSeriesId(String mockedString){
        firstOperationById.setSeriesId(mockedString);
        secondOperationById.withSeriesId(mockedString);
        assertTrue(Objects.equals(firstOperationById.getSeriesId(), secondOperationById.getSeriesId()) &&
                Objects.equals(firstOperationById.getSeriesId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeriesLabelLg2A", "mockedSeriesLabelLg2B", "mockedSeriesLabelLg2C" })
    void shouldCheckSeriesLabelLg2(String mockedString){
        firstOperationById.setSeriesLabelLg2(mockedString);
        secondOperationById.withSeriesLabelLg2(mockedString);
        assertTrue(Objects.equals(firstOperationById.getSeriesLabelLg2(), secondOperationById.getSeriesLabelLg2()) &&
                Objects.equals(firstOperationById.getSeriesLabelLg2(), mockedString));
    }


    @ParameterizedTest
    @ValueSource(strings = { "mockedProprietaire1", "mockedProprietaire2", "mockedProprietaire3" })
    void shouldCheckProprietaire(String mockedString){
        firstOperationById.setProprietaire(mockedString);
        assertEquals(mockedString,firstOperationById.getProprietaire());
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstOperationById.setAdditionalProperty("name","value");
        secondOperationById.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstOperationById.getAdditionalProperties(), secondOperationById.getAdditionalProperties()) &&
                !Objects.equals(firstOperationById.getAdditionalProperties(),additionalProperties));
    }

}