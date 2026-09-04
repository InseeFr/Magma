package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class DataSetTest {

    String statutValidation ="mockedStatutValidation";
    String dateCreation= "mockedDateCreation";
    String names= "mockedNames";
    String dateMiseAJour= "mockedDateMiseAJour";
    String operationStat= "mockedOperationStat";
    String titreLg1= "mockedTitreLg1";
    String id= "mockedId";
    String titreLg2= "mockedTitreLg2";
    String uri= "uri";
    String serie = "mockedSerie";

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    DataSet firstDataSet = new DataSet();
    DataSet secondDataSet= new DataSet(dateCreation,names, dateMiseAJour, serie, titreLg1, id, titreLg2, uri);
    DataSet thirdDataSet= new DataSet(dateCreation,names, dateMiseAJour, serie, titreLg1, id, titreLg2, uri);
    DataSet fourthDataSet = new DataSet(statutValidation,dateCreation,names, dateMiseAJour, operationStat, titreLg1, id, titreLg2,uri);
    DataSet fivethDataSet = new DataSet("idDataset1","uriDataset1", "titreFrDataset1", "titreEnDataset1","s","publié", "s1");

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckDateCreation(String mockedString){
        firstDataSet.setDateCreation(mockedString);
        secondDataSet.withDateCreation(mockedString);
        assertTrue(Objects.equals(firstDataSet.getDateCreation(), secondDataSet.getDateCreation()) &&
                Objects.equals(firstDataSet.getDateCreation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckDateMiseAJour(String mockedString){
        firstDataSet.setDateMiseAJour(mockedString);
        secondDataSet.withDateMiseAJour(mockedString);
        assertTrue(Objects.equals(firstDataSet.getDateMiseAJour(), secondDataSet.getDateMiseAJour()) &&
                Objects.equals(firstDataSet.getDateMiseAJour(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckNames(String mockedString){
        thirdDataSet.setNames(mockedString);
        fourthDataSet.withNames(mockedString);
        assertTrue(Objects.equals(thirdDataSet.getNames(), fourthDataSet.getNames()) &&
                Objects.equals(thirdDataSet.getNames(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckSeries(String mockedString){
        fivethDataSet.setSerie(mockedString);
        thirdDataSet.withSerie(mockedString);
        assertTrue(Objects.equals(fivethDataSet.getSerie(), thirdDataSet.getSerie()) &&
                Objects.equals(fivethDataSet.getSerie(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckTitreLg1(String mockedString){
        firstDataSet.setTitreLg1(mockedString);
        secondDataSet.withTitreLg1(mockedString);
        assertTrue(Objects.equals(firstDataSet.getTitreLg1(), secondDataSet.getTitreLg1()) &&
                Objects.equals(firstDataSet.getTitreLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckTitreLg2(String mockedString){
        firstDataSet.setTitreLg2(mockedString);
        secondDataSet.withTitreLg2(mockedString);
        assertTrue(Objects.equals(firstDataSet.getTitreLg2(), secondDataSet.getTitreLg2()) &&
                Objects.equals(firstDataSet.getTitreLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckId(String mockedString){
        firstDataSet.setId(mockedString);
        secondDataSet.withId(mockedString);
        assertTrue(Objects.equals(firstDataSet.getId(), secondDataSet.getId()) &&
                Objects.equals(firstDataSet.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckUri(String mockedString){
        firstDataSet.setUri(mockedString);
        secondDataSet.withUri(mockedString);
        assertTrue(Objects.equals(firstDataSet.getUri(), secondDataSet.getUri()) &&
                Objects.equals(firstDataSet.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckStatutValidation(String mockedString){
        firstDataSet.setStatutValidation(mockedString);
        assertEquals(firstDataSet.getStatutValidation(), mockedString);
    }

    @Test
    void shouldCheckOperation(){
        assertEquals(operationStat, fourthDataSet.getOperationStat());
    }

    @Test
    void shouldCheckAdditionalProperties(){
        firstDataSet.setAdditionalProperty("name","value");
        secondDataSet.withAdditionalProperty("name","value");
        assertTrue(Objects.equals(firstDataSet.getAdditionalProperties(), secondDataSet.getAdditionalProperties()) &&
                !Objects.equals(firstDataSet.getAdditionalProperties(),additionalProperties));
    }

}