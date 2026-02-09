package fr.insee.rmes.model.datasets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class DataSetTest {

    String statutValidation="mockedStatutValidation";
    String dateCreation="mockedDateCreation";
    String names="mockedNames";
    String dateMiseAJour="mockedDateMiseAJour";
    String operationStat="mockedOperationStat";
    String titreLg1="mockedTitreLg1";
    String id="mockedId";
    String titreLg2="mockedTitreLg2";
    String uri="mockedUri";
    String serie = "mockedSerie";
    String idDataset1 = "idDataset1";
    String uriDataset1 = "uriDataset1";
    String titreFrDataset1="titreFrDataset1";
    String titreEnDataset1="titreEnDataset1";
    String s= "s";
    String publie = "publie";
    String s1= "s1";

    DataSet firstDataSet = new DataSet(dateCreation, names, dateMiseAJour, serie, titreLg1, id, titreLg2, uri);
    DataSet secondDataSet = new DataSet(statutValidation, dateCreation, names, dateMiseAJour, operationStat,titreLg1,id,titreLg2,uri);
    DataSet thirdDataSet = new  DataSet(idDataset1,uriDataset1,titreFrDataset1, titreEnDataset1, s, publie,s1);

    @ParameterizedTest
    @ValueSource(strings = { "mockedDateCreation1", "mockedDateCreation2", "mockedDateCreation3" })
    void shouldCheckDateCreation(String mockedString){
        firstDataSet.setDateCreation(mockedString);
        secondDataSet.withDateCreation(mockedString);
        assertTrue(Objects.equals(firstDataSet.getDateCreation(), secondDataSet.getDateCreation()) &&
                Objects.equals(firstDataSet.getDateCreation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedName1", "mockedName2", "mockedName3" })
    void shouldCheckName(String mockedString){
        firstDataSet.setNames(mockedString);
        thirdDataSet.withNames(mockedString);
        assertTrue(Objects.equals(firstDataSet.getNames(), thirdDataSet.getNames()) &&
                Objects.equals(firstDataSet.getNames(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedDateMiseAJour1", "mockedDateMiseAJour2", "mockedDateMiseAJour3" })
    void shouldCheckDateMiseAJour(String mockedString){
        firstDataSet.setDateMiseAJour(mockedString);
        thirdDataSet.withDateMiseAJour(mockedString);
        assertTrue(Objects.equals(firstDataSet.getDateMiseAJour(), thirdDataSet.getDateMiseAJour()) &&
                Objects.equals(firstDataSet.getDateMiseAJour(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSerie1", "mockedSerie2", "mockedSerie3" })
    void shouldCheckSerie(String mockedString){
        firstDataSet.setSerie(mockedString);
        thirdDataSet.withSerie(mockedString);
        assertTrue(Objects.equals(firstDataSet.getSerie(), thirdDataSet.getSerie()) &&
                Objects.equals(firstDataSet.getSerie(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedTitreLg1", "mockedTitreLg2", "mockedTitreLg3" })
    void shouldCheckTitreLg1(String mockedString){
        firstDataSet.setTitreLg1(mockedString);
        thirdDataSet.withTitreLg1(mockedString);
        assertTrue(Objects.equals(firstDataSet.getTitreLg1(), thirdDataSet.getTitreLg1()) &&
                Objects.equals(firstDataSet.getTitreLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedId1", "mockedId2", "mockedId3" })
    void shouldCheckId(String mockedString){
        firstDataSet.setId(mockedString);
        thirdDataSet.withId(mockedString);
        assertTrue(Objects.equals(firstDataSet.getId(), thirdDataSet.getId()) &&
                Objects.equals(firstDataSet.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedTitreLg1", "mockedTitreLg2", "mockedTitreLg3" })
    void shouldCheckTitreLg2(String mockedString){
        firstDataSet.setTitreLg2(mockedString);
        thirdDataSet.withTitreLg2(mockedString);
        assertTrue(Objects.equals(firstDataSet.getTitreLg2(), thirdDataSet.getTitreLg2()) &&
                Objects.equals(firstDataSet.getTitreLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedTitreLg1", "mockedTitreLg2", "mockedTitreLg3" })
    void shouldCheckUri(String mockedString){
        firstDataSet.setUri(mockedString);
        thirdDataSet.withUri(mockedString);
        assertTrue(Objects.equals(firstDataSet.getUri(), thirdDataSet.getUri()) &&
                Objects.equals(firstDataSet.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedTitreLg1", "mockedTitreLg2", "mockedTitreLg3" })
    void shouldCheckStatutValidation(String mockedString){
        firstDataSet.setStatutValidation(mockedString);
        assertEquals(firstDataSet.getStatutValidation(), mockedString);
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedVersion1/mockedValue1", "mockedVersion2/mockedValue2", "mockedVersion3/mockedValue3" })
    void shouldCheckAdditionalProperties(String mockedString){
        String name = mockedString.split("/")[0];
        String value = mockedString.split("/")[1];
        firstDataSet.setAdditionalProperty(name,value);
        secondDataSet.withAdditionalProperty(name,value);
        assertTrue(("{" + name + "=" + value + "}").equals(firstDataSet.getAdditionalProperties().toString())&&
                ("{" + name + "=" + value + "}").equals(secondDataSet.getAdditionalProperties().toString()));
    }



}