package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.GeoDepartementEndpoints;
import fr.insee.rmes.magma.diffusion.model.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")


class GeoDepartementQueriesTest extends TestcontainerTest{

    @Autowired
    GeoDepartementEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////
    ///        geo/departement/{code}/ascendants          ///
    /////////////////////////////////////////////////////////

    //    geo/departement/22/ascendants?date=2025-09-04
    @Test
    void should_return_1_region_when_DepartementCodeAscendants_code22_date20250904_typeNull(){
        var response  = endpoints.getcogdepasc("22", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());

        assertEquals("53", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/region/6c83500c-454c-4d69-aec5-b988fb6f6f1c", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.REGION, resultItem1.getType());
        assertEquals(LocalDate.of(2019,1,1), resultItem1.getDateCreation());
        assertEquals("Bretagne", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Bretagne", resultItem1.getIntitule());

    }

    //    geo/departement/22/ascendants?date=2025-09-04&type=Region
    @Test
    void should_return_1_region_when_DepartementCodeAscendants_code22_date20250904_typeRegion(){
        var response  = endpoints.getcogdepasc("22", LocalDate.of(2025, 9, 4), TypeEnumAscendantsDepartement.REGION);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());

        assertEquals("53", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/region/6c83500c-454c-4d69-aec5-b988fb6f6f1c", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.REGION, resultItem1.getType());
        assertEquals(LocalDate.of(2019,1,1), resultItem1.getDateCreation());
        assertEquals("Bretagne", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Bretagne", resultItem1.getIntitule());
    }


    /////////////////////////////////////////////////////////
    ///        geo/departement/{code}                     ///
    /////////////////////////////////////////////////////////

    //    geo/departement/22?date=2025-09-04
    @Test
    void should_return_DepartementCode_22_when_code22_date20250904() {
        var response  = endpoints.getcogdep("22", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertNotNull(result);
        assertEquals("22", result.getCode());
        assertEquals("http://id.insee.fr/geo/departement/f07f6a49-9dce-4f2d-a99e-5d61eedf2827", result.getUri());
        assertEquals(Departement.TypeEnum.DEPARTEMENT, result.getType());
        assertEquals(LocalDate.of(1990,3,8), result.getDateCreation());
        assertEquals("Côtes-d'Armor", result.getIntituleSansArticle());
        assertEquals(Departement.TypeArticleEnum._4, result.getTypeArticle());
        assertEquals("22278", result.getChefLieu());
        assertEquals("Côtes-d'Armor", result.getIntitule());

    }


    /////////////////////////////////////////////////////////
    ///        geo/departement/{code}/descendants         ///
    /////////////////////////////////////////////////////////

    //    geo/departement/22/descendants?date=2025-09-04&type=Commune&filtreNom=Orleans
    @Test
    void should_return_1_commune_when_DepartementCodeDescendants_code45_date20250904_typeCommune_filtreNomOrleans(){
        var response  = endpoints.getcogdepdesc("45", LocalDate.of(2025, 9, 4), TypeEnumDescendantsDepartement.COMMUNE,"Orleans");
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertEquals("45234", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/commune/4180d005-bb9a-4271-ae5c-dc6b46b874da", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType());
        assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation());
        assertEquals("Orléans", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._1, resultItem1.getTypeArticle());
        assertEquals("Orléans", resultItem1.getIntitule());

    }

    //    geo/departement/22/descendants?date=2025-09-04
    @Test
    void should_return_523_territoires_when_DepartementCodeDescendants_code45_date20250904_typeNull_filtreNomNull(){
        var response  = endpoints.getcogdepdesc("45", LocalDate.of(2025, 9, 4), null,null);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(523, result.size());
        assertEquals("451", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissement/a9f9ff71-7658-4ef0-98b0-f048c8831be1", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(2017,1,1), resultItem1.getDateCreation());
        assertEquals("Montargis", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Montargis", resultItem1.getIntitule());

    }


    /////////////////////////////////////////////////////////
    ///        geo/departements                           ///
    /////////////////////////////////////////////////////////

    //    geo/departements?date=2025-09-04
    @Test
    void should_return_101_departements_when_Departements_date20250904(){
        var response  = endpoints.getcogdepts ("2025-09-04");
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(101, result.size());
        assertEquals("01", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/departement/84680e6f-2e99-44c9-a9ba-2e96a2ae48b7", resultItem1.getUri());
        assertEquals(TerritoireBaseChefLieu.TypeEnum.DEPARTEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(1967,12,31), resultItem1.getDateCreation());
        assertEquals("Ain", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireBaseChefLieu.TypeArticleEnum._5, resultItem1.getTypeArticle());
        assertEquals("01053", resultItem1.getChefLieu());
        assertEquals("Ain", resultItem1.getIntitule());

    }

    //    geo/departements?date=*
    @Test
    void should_return_154_departements_when_Departements_dateEtoile(){
        var response  = endpoints.getcogdepts ("*");
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(154, result.size());
        assertEquals("01", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/departement/69043f56-a413-47c8-804e-9d9ac0c0b67c", resultItem1.getUri());
        assertEquals(TerritoireBaseChefLieu.TypeEnum.DEPARTEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation());
        assertEquals(LocalDate.of(1967,12,31), resultItem1.getDateSuppression());
        assertEquals("Ain", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireBaseChefLieu.TypeArticleEnum._5, resultItem1.getTypeArticle());
        assertEquals("01053", resultItem1.getChefLieu());
        assertEquals("Ain", resultItem1.getIntitule());
    }

    /////////////////////////////////////////////////////////
    ///        geo/departement/{code}/precedents          ///
    /////////////////////////////////////////////////////////

    //    geo/departement/22/precedents?date=2025-09-04
    @Test
    void should_return_1_departement_when_DepartementCodePrecedents_code22_date20250904(){
        var response  = endpoints.getcogdepprec ("22", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertEquals("22", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/departement/95af7065-d100-4c4f-afd5-764edfe9ae9b", resultItem1.getUri());
        assertEquals(TerritoireBaseChefLieu.TypeEnum.DEPARTEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation());
        assertEquals(LocalDate.of(1990,3,8), resultItem1.getDateSuppression());
        assertEquals("Côtes-du-Nord", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireBaseChefLieu.TypeArticleEnum._4, resultItem1.getTypeArticle());
        assertEquals("22278", resultItem1.getChefLieu());
        assertEquals("Côtes-du-Nord", resultItem1.getIntitule());
    }

    //    geo/departement/21/precedents?date=2025-09-04
    @Test
        //le département 21 n'a pas de départments précédents
    void should_return_404_when_DepartementCodePrecedents_code21_date20250904() throws Exception{
        mockMvc.perform(get("/geo/departement/21/precedents")
                .param("date", "2025-09-01"))
                .andExpect(status().isNotFound());
    }

    /////////////////////////////////////////////////////////
    ///        geo/departement/{code}/projetes            ///
    /////////////////////////////////////////////////////////

    //    geo/departement/21/projetes?date=2025-09-01
    @Test
    void should_return_400_when_DepartementCodeProjetes_dateProjectionNull() throws Exception{
        mockMvc.perform(get("/geo/departement/22/projetes")
                        .param("date", "2025-09-01"))
                .andExpect(status().isBadRequest());
    }

    //    geo/departement/21/projetes?date=2025-09-01&dateProjection=
    @Test
    public void should_return_400_when_DepartementCodeProjetes_dateProjectionEmpty() throws Exception {
        mockMvc.perform(get("/geo/departement/22/projetes")
                        .param("dateProjection", "")  // Valeur vide
                        .param("date", "2025-09-01"))
                .andExpect(status().isBadRequest());
    }

//    geo/departement/21/projetes?date=2025-09-04&dateProjection=1950-01-01
    @Test
    void should_return_1_departement_when_DepartementCodeProjetes_code22_date20250904_dateProjection19500101(){
        var response  = endpoints.getcogdepproj ("22", LocalDate.of(1950,1,1),LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertEquals("22", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/departement/95af7065-d100-4c4f-afd5-764edfe9ae9b", resultItem1.getUri());
        assertEquals(TerritoireBaseChefLieu.TypeEnum.DEPARTEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation());
        assertEquals(LocalDate.of(1990,3,8), resultItem1.getDateSuppression());
        assertEquals("Côtes-du-Nord", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireBaseChefLieu.TypeArticleEnum._4, resultItem1.getTypeArticle());
        assertEquals("22278", resultItem1.getChefLieu());
        assertEquals("Côtes-du-Nord", resultItem1.getIntitule());
    }

    /////////////////////////////////////////////////////////
    ///        geo/departement/{code}/suivants            ///
    /////////////////////////////////////////////////////////

    //    geo/departement/22/suivants?date=2025-09-04
    @Test
    void should_return_404_when_DepartementCodeSuivants_code22_date20250904() throws Exception{
        mockMvc.perform(get("/geo/departement/22/suivants")
                        .param("date", "2025-09-04"))
                .andExpect(status().isNotFound());
    }

    //    geo/departement/22/suivants?date=1950-01-01
    @Test
    void should_return_1_departement_when_DepartementCodeSuivants_code22_date19500101(){
        var response  = endpoints.getcogdepsuiv ("22", LocalDate.of(1950,1,1));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertEquals("22", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/departement/f07f6a49-9dce-4f2d-a99e-5d61eedf2827", resultItem1.getUri());
        assertEquals(TerritoireBaseChefLieu.TypeEnum.DEPARTEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(1990,3,8), resultItem1.getDateCreation());
        assertEquals("Côtes-d'Armor", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireBaseChefLieu.TypeArticleEnum._4, resultItem1.getTypeArticle());
        assertEquals("22278", resultItem1.getChefLieu());
        assertEquals("Côtes-d'Armor", resultItem1.getIntitule());
    }


}



