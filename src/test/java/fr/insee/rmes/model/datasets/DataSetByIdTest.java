package fr.insee.rmes.model.datasets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class DataSetByIdTest {

    String operationStat = "mockedOperationStat";
    String dateCreation = "mockedDateCreation";
    String names= "mockedNames";
    String dateModification= "mockedDateModification";
    String titreLg1= "mockedTitreLg1";
    String id= "mockedId";
    String titreLg2= "mockedTitreLg2";
    String uri= "mockedUri";

    DataSetById firstDataSetById = new DataSetById(operationStat,dateCreation,names,dateModification,titreLg1,id,titreLg2,uri);
    DataSetById secondDataSetById = new DataSetById();

    @ParameterizedTest
    @ValueSource(strings = { "mockedOperationStat1", "mockedOperationStat2", "mockedOperationStat3" })
    void shouldCheckOperationStat(String mockedString){
        firstDataSetById.setOperationStat(mockedString);
        secondDataSetById.withOperationStat(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getOperationStat(), secondDataSetById.getOperationStat()) &&
                Objects.equals(firstDataSetById.getOperationStat(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedDateCreation1", "mockedDateCreation2", "mockedDateCreation3" })
    void shouldCheckDateCreation(String mockedString){
        firstDataSetById.setDateCreation(mockedString);
        secondDataSetById.withDateCreation(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getDateCreation(), secondDataSetById.getDateCreation()) &&
                Objects.equals(firstDataSetById.getDateCreation(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedNames1", "mockedNames2", "mockedNames3" })
    void shouldCheckNames(String mockedString){
        firstDataSetById.setNames(mockedString);
        secondDataSetById.withNames(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getNames(), secondDataSetById.getNames()) &&
                Objects.equals(firstDataSetById.getNames(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedDateModification1", "mockedDateModification2", "mockedDateModification3" })
    void shouldCheckDateModification(String mockedString){
        firstDataSetById.setDateModification(mockedString);
        secondDataSetById.withDateModification(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getDateModification(), secondDataSetById.getDateModification()) &&
                Objects.equals(firstDataSetById.getDateModification(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedTitreLg1", "mockedTitreLg2", "mockedTitreLg3" })
    void shouldCheckTitreLg1(String mockedString){
        firstDataSetById.setTitreLg1(mockedString);
        secondDataSetById.withTitreLg1(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getTitreLg1(), secondDataSetById.getTitreLg1()) &&
                Objects.equals(firstDataSetById.getTitreLg1(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedId1", "mockedId2", "mockedId3" })
    void shouldCheckId(String mockedString){
        firstDataSetById.setId(mockedString);
        secondDataSetById.withId(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getId(), secondDataSetById.getId()) &&
                Objects.equals(firstDataSetById.getId(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedTitreLg1", "mockedTitreLg2", "mockedTitreLg3" })
    void shouldCheckTitreLg2(String mockedString){
        firstDataSetById.setTitreLg2(mockedString);
        secondDataSetById.withTitreLg2(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getTitreLg2(), secondDataSetById.getTitreLg2()) &&
                Objects.equals(firstDataSetById.getTitreLg2(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedUri1", "mockedUri2", "mockedUri3" })
    void shouldCheckUri(String mockedString){
        firstDataSetById.setUri(mockedString);
        secondDataSetById.withUri(mockedString);
        assertTrue(Objects.equals(firstDataSetById.getUri(), secondDataSetById.getUri()) &&
                Objects.equals(firstDataSetById.getUri(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedVersion1/mockedValue1", "mockedVersion2/mockedValue2", "mockedVersion3/mockedValue3" })
    void shouldCheckAdditionalProperties(String mockedString){
        String nameExample = mockedString.split("/")[0];
        String value = mockedString.split("/")[1];
        firstDataSetById.setAdditionalProperty(nameExample,value);
        secondDataSetById.withAdditionalProperty(nameExample,value);
        assertTrue(("{" + nameExample + "=" + value + "}").equals(firstDataSetById.getAdditionalProperties().toString())&&
                ("{" + nameExample + "=" + value + "}").equals(secondDataSetById.getAdditionalProperties().toString()));
    }

}