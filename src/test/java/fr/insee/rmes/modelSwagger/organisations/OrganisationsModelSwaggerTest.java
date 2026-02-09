package fr.insee.rmes.modelSwagger.organisations;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrganisationsModelSwaggerTest {

    List<Label> labelOrganisation = List.of(new Label(),new Label());
    String organisationId = "mockedOrganisationId";
    String uri = "mockedUri";

    OrganisationsModelSwagger  firstOrganisationModelSwagger = new OrganisationsModelSwagger(organisationId,labelOrganisation);
    OrganisationsModelSwagger  secondOrganisationModelSwagger = new OrganisationsModelSwagger(organisationId,uri,labelOrganisation);

    @Test
    void shouldCheckAltLabelOrganisation(){
        List<Label> mockedLabelOrganisation = List.of(new Label("mockedLang","mockedContenu"),new Label("Lang","Contenu"));
        firstOrganisationModelSwagger.setAltlabelOrganisation(mockedLabelOrganisation);
        assertEquals(mockedLabelOrganisation,firstOrganisationModelSwagger.getAltlabelOrganisation());
    }

    @Test
    void shouldCheckLabelOrganisation(){
        assertEquals(labelOrganisation,secondOrganisationModelSwagger.getLabelOrganisation());
    }

    @Test
    void shouldCheckUri(){
        assertEquals(uri,secondOrganisationModelSwagger.getUri());
    }

    @Test
    void shouldCheckAbreviation(){
        String abreviation = "mockedAbreviation";
        firstOrganisationModelSwagger.setAbreviation(abreviation);
        assertEquals(abreviation,firstOrganisationModelSwagger.getAbreviation());
    }

    @Test
    void shouldCheckUniteDe(){
        String uniteDe = "mockedUniteDe";
        firstOrganisationModelSwagger.setUniteDe(uniteDe);
        assertEquals(uniteDe,firstOrganisationModelSwagger.getUniteDe());
    }

}