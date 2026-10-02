package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class DataSetByIdTest {
    
    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    String operationStat= "mockedOperationStat";
    String dateCreation ="mockedDateCreation";
    String names= "mockedNames";
    String dateModification= "mockedDateModification";
    String titreLg1= "mockedTitreLg1";
    String id= "mockedId";
    String titreLg2= "mockedTitreLg2";
    String uri= "uri";

    DataSetById firstDataSetById = new DataSetById();
    DataSetById secondDataSetById= new DataSetById(operationStat, dateCreation, names, dateModification, titreLg1, id, titreLg2, uri);

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckDateCreation(String mockedString){
        firstDataSetById.setDateCreation(mockedString);
        secondDataSetById.withDateCreation(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getDateCreation(), secondDataSetById.getDateCreation()) &&
                Objects.equals(firstDataSetById.getDateCreation(), mockedString));
    }


    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckNames(String mockedString){
        firstDataSetById.setNames(mockedString);
        secondDataSetById.withNames(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getNames(), secondDataSetById.getNames()) &&
                Objects.equals(firstDataSetById.getNames(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckTitreLg1(String mockedString){
        firstDataSetById.setTitreLg1(mockedString);
        secondDataSetById.withTitreLg1(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getTitreLg1(), secondDataSetById.getTitreLg1()) &&
                Objects.equals(firstDataSetById.getTitreLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckTitreLg2(String mockedString){
        firstDataSetById.setTitreLg2(mockedString);
        secondDataSetById.withTitreLg2(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getTitreLg2(), secondDataSetById.getTitreLg2()) &&
                Objects.equals(firstDataSetById.getTitreLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckId(String mockedString){
        firstDataSetById.setId(mockedString);
        secondDataSetById.withId(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getId(), secondDataSetById.getId()) &&
                Objects.equals(firstDataSetById.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckUri(String mockedString){
        firstDataSetById.setUri(mockedString);
        secondDataSetById.withUri(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getUri(), secondDataSetById.getUri()) &&
                Objects.equals(firstDataSetById.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckDateModification(String mockedString){
        firstDataSetById.setDateModification(mockedString);
        secondDataSetById.withDateModification(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getDateModification(), secondDataSetById.getDateModification()) &&
                Objects.equals(firstDataSetById.getDateModification(), mockedString));
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstDataSetById.setAdditionalProperty("name","value");
        secondDataSetById.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstDataSetById.getAdditionalProperties(), secondDataSetById.getAdditionalProperties()) &&
                !Objects.equals(firstDataSetById.getAdditionalProperties(),additionalProperties));
    }




}