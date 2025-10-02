package fr.insee.rmes.model.organisation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class OrganisationModelTest {

    OrganisationModel firstOrganisationModel = new OrganisationModel(
            "Id",
            "uri",
            "abreviation",
            "prefLabelLg2",
            "prefLabelLg1",
            "altLabelLg2",
            "altLabelLg1",
            "uniteDe",
            "sousTelleDe"
    );

    OrganisationModel secondOrganisationModel = new OrganisationModel();

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckId(String mockedString){
        firstOrganisationModel.setId(mockedString);
        assertEquals(firstOrganisationModel.getId(),mockedString);
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUri(String mockedString){
        secondOrganisationModel.setUri(mockedString);
        assertEquals(secondOrganisationModel.getUri(),mockedString);
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckAbreviation(String mockedString){
        secondOrganisationModel.setAbreviation(mockedString);
        assertEquals(secondOrganisationModel.getAbreviation(), mockedString);
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUniteDe(String mockedString){
        secondOrganisationModel.setUniteDe(mockedString);
        assertEquals(secondOrganisationModel.getUniteDe(), mockedString);
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckSousTelleDe(String mockedString){
        secondOrganisationModel.setSousTelleDe(mockedString);
        assertEquals(secondOrganisationModel.getSousTelleDe(), mockedString);
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckPrefLabelLg2(String mockedString){
        secondOrganisationModel.setPrefLabelLg2(mockedString);
        assertEquals(secondOrganisationModel.getPrefLabelLg2(), mockedString);
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckPrefLabelLg1(String mockedString){
        secondOrganisationModel.setPrefLabelLg1(mockedString);
        assertEquals(secondOrganisationModel.getPrefLabelLg1(), mockedString);
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckAltLabelLg1(String mockedString){
        secondOrganisationModel.setAltLabelLg1(mockedString);
        assertEquals(secondOrganisationModel.getAltLabelLg1(), mockedString);
    }


    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckAltLabelLg2(String mockedString){
        secondOrganisationModel.setAltLabelLg2(mockedString);
        assertEquals(secondOrganisationModel.getAltLabelLg2(), mockedString);
    }

}