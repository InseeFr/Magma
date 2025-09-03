package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoDepartementEndpoints;
import fr.insee.rmes.metadata.model.TypeEnumDescendantsDepartement;
import org.junit.Assert;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Tag("integration")
class GeoDepartementQueriesTest extends TestcontainerTest{

    @Autowired
    GeoDepartementEndpoints endpoints;

    @Test
    void should_return_Paris_intitule() {
        var d  = endpoints.getcogdep("75", LocalDate.now());
        Assert.assertEquals("Paris", d.getBody().getIntitule());
    }

    @Test
    void should_return_P_intitule() {
        var response  = endpoints.getcogdep("75", LocalDate.now());
        Assert.assertEquals("Paris", response.getBody().getIntitule());
    }

    @Test
    void should_return_1_commune_when_departement_45_descendants_type_Commune_filtreNom_Orleans(){
        var response  = endpoints.getcogdepdesc("45", LocalDate.now(), TypeEnumDescendantsDepartement.COMMUNE,"Orleans");
        var toto=response.getBody();
        assertEquals(1, response.getBody().size());
        assertFalse(response.getBody().isEmpty(), "La liste ne doit pas être vide");
        Assert.assertEquals("Orléans", response.getBody().get(0).getIntitule());
    }
}



