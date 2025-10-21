package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.GeoArrondissementEndpoints;
import fr.insee.rmes.magma.diffusion.model.Arrondissement;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsArrondissement;
import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsArrondissement;
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


public class GeoArrondissementQueriesTest extends TestcontainerTest{

    @Autowired
    GeoArrondissementEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////
    ///        geo/arrondissement/{code}/ascendants       ///
    /////////////////////////////////////////////////////////


    //    geo/arrondissement/674?date=2025-09-04
    @Test
    void should_return_2_ascendants_when_ArrondissementCodeAscendants_code674_date20250904_typeNull() {
        var response = endpoints.getcogarrasc("674", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        assertNotNull(result);
        assertEquals(2, result.size());

        // Premier ascendant : Département
        var resultItem1 = result.getFirst();
        assertEquals("67", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/departement/e62b35df-f168-4dfa-b60f-ef6cdb3279a0", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.DEPARTEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(1943, 1, 1), resultItem1.getDateCreation());
        assertEquals("Bas-Rhin", resultItem1.getIntituleSansArticle());
        assertEquals("Bas-Rhin", resultItem1.getIntitule());
        assertEquals("67482", resultItem1.getChefLieu());

        // Deuxième ascendant : Région
        var resultItem2 = result.get(1);
        assertEquals("44", resultItem2.getCode());
        assertEquals("http://id.insee.fr/geo/region/8957f2ad-765c-4083-87f9-8d34fb702cf5", resultItem2.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.REGION, resultItem2.getType());
        assertEquals(LocalDate.of(2016, 12, 31), resultItem2.getDateCreation());
        assertEquals("Grand Est", resultItem2.getIntituleSansArticle());
        assertEquals("Grand Est", resultItem2.getIntitule());
        assertEquals("67482", resultItem2.getChefLieu());
    }

    //    geo/arrondissement/674?date=2025-09-04&type=Region
    @Test
    void should_return_1_region_when_ArrondissementCodeAscendants_code674_date20250904_typeRegion() {
        var response = endpoints.getcogarrasc("674", LocalDate.of(2025, 9, 4), TypeEnumAscendantsArrondissement.REGION);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1 = result.getFirst();
        assertEquals(1, result.size());
        assertEquals("44", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/region/8957f2ad-765c-4083-87f9-8d34fb702cf5", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.REGION, resultItem1.getType());
        assertEquals(LocalDate.of(2016, 12, 31), resultItem1.getDateCreation());
        assertEquals("Grand Est", resultItem1.getIntituleSansArticle());
        assertEquals("Grand Est", resultItem1.getIntitule());
        assertEquals("67482", resultItem1.getChefLieu());
    }

    /////////////////////////////////////////////////////////
    ///        geo/arrondissement/{code}                  ///
    /////////////////////////////////////////////////////////

//    geo/arrondissement/674?date=2025-09-04
    @Test
    void should_return_ArrondissementCode_674_when_code674_date20250904() {
        var response = endpoints.getcogarr("674", LocalDate.of(2025, 9, 4));
        var result = response.getBody();

        assertNotNull(result);
        assertEquals("674", result.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissement/7f59df93-132b-400f-9aa6-b3c6be1018eb", result.getUri());
        assertEquals(Arrondissement.TypeEnum.ARRONDISSEMENT, result.getType());
        assertEquals(LocalDate.of(2018, 12, 31), result.getDateCreation());
        assertEquals("Saverne", result.getIntituleSansArticle());
        assertEquals("Saverne", result.getIntitule());
        assertEquals("67437", result.getChefLieu());
    }

    /////////////////////////////////////////////////////////
    ///        geo/arrondissement/{code}/descendants      ///
    /////////////////////////////////////////////////////////
//    geo/arrondissement/674/descendants?type=CommuneDeleguee
    @Test
    void should_return_14_CommuneDeleguee_when_ArrondissementCodeDescendants_code674_typeCommuneDeleguee() {
        var response = endpoints.getcogarrdes("674", null, TypeEnumDescendantsArrondissement.COMMUNE_DELEGUEE);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1 = result.getFirst();
        assertEquals(14, result.size());
        assertEquals("67004", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/communeDeleguee/ec8ebfef-57c2-4e97-9d5c-f07b2e46fa36", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE_DELEGUEE, resultItem1.getType());
        assertEquals(LocalDate.of(2016, 1, 1), resultItem1.getDateCreation());
        assertEquals("Allenwiller", resultItem1.getIntituleSansArticle());
        assertEquals("Allenwiller", resultItem1.getIntitule());
    }

    //    geo/arrondissement/674/descendants?date=2025-09-04
    @Test
    void should_return_195_territoires_when_ArrondissementCodeDescendants_code674_date20250904_typeNull() {
        var response = endpoints.getcogarrdes("674", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1 = result.getFirst();
        assertEquals(195, result.size());
        assertEquals("67002", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/commune/83e823b1-a485-4441-b003-5ad23e47de5f", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType());
        assertEquals(LocalDate.of(1943, 1, 1), resultItem1.getDateCreation());
        assertEquals("Adamswiller", resultItem1.getIntituleSansArticle());
        assertEquals("Adamswiller", resultItem1.getIntitule());
    }

    /////////////////////////////////////////////////////////
    ///        geo/arrondissements                           ///
    /////////////////////////////////////////////////////////
//    geo/arrondissements?date=2025-09-04
    @Test
    void should_return_333_arrondissements_when_Arrondissements_date20250904() {
        var response = endpoints.getcogarrliste("2025-09-04");
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1 = result.getFirst();
        assertEquals(333, result.size()); // À adapter selon le nombre réel attendu
        assertEquals("011", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissement/cc3aee67-96dc-4e9a-ae4e-26860a90e0d5", resultItem1.getUri());
        assertEquals(Arrondissement.TypeEnum.ARRONDISSEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(2017, 1, 1), resultItem1.getDateCreation());
        assertEquals("Belley", resultItem1.getIntituleSansArticle());
        assertEquals("01034", resultItem1.getChefLieu());
        assertEquals("Belley", resultItem1.getIntitule());

    }

    //    geo/arrondissements?date=*
    @Test
    void should_return_748_arrondissements_when_Arrondissements_dateEtoile() {
        var response = endpoints.getcogarrliste("*");
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1 = result.getFirst();
        assertEquals(748, result.size()); // Remplacez 350 par le nombre réel attendu
        assertEquals("011", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissement/d693d3ca-5851-4c40-a19e-dba1d750bfcf", resultItem1.getUri());
        assertEquals(Arrondissement.TypeEnum.ARRONDISSEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(2016, 1, 1), resultItem1.getDateCreation());
        assertEquals(LocalDate.of(2017, 1, 1), resultItem1.getDateSuppression());
        assertEquals("Belley", resultItem1.getIntituleSansArticle());
        assertEquals("01034", resultItem1.getChefLieu());
        assertEquals("Belley", resultItem1.getIntitule());
    }

    /////////////////////////////////////////////////////////
    ///        geo/arrondissement/{code}/precedents       ///
    /////////////////////////////////////////////////////////
//    geo/arrondissement/674/precedents?date=2025-09-04
    @Test
    void should_return_2_arrondissements_when_ArrondissementCodePrecedents_code674_date20250904() {
        var response = endpoints.getcogarrprec("674", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1 = result.getFirst();
        assertEquals(2, result.size());
        assertEquals("672", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissement/f1b88b67-f15d-49ad-9bb2-ce221f249f04", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(2015, 1, 1), resultItem1.getDateCreation());
        assertEquals(LocalDate.of(2018, 12, 31), resultItem1.getDateSuppression());
        assertEquals("Haguenau-Wissembourg", resultItem1.getIntituleSansArticle());
        assertEquals("Haguenau-Wissembourg", resultItem1.getIntitule());
        assertEquals("67180", resultItem1.getChefLieu());

        // Vérification du deuxième élément
        var resultItem2 = result.get(1);
        assertEquals("674", resultItem2.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissement/f2cc4091-b4f0-485b-bf31-eb4afe8055a4", resultItem2.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, resultItem2.getType());
        assertEquals(LocalDate.of(2015, 1, 1), resultItem2.getDateCreation());
        assertEquals(LocalDate.of(2018, 12, 31), resultItem2.getDateSuppression());
        assertEquals("Saverne", resultItem2.getIntituleSansArticle());
        assertEquals("Saverne", resultItem2.getIntitule());
        assertEquals("67437", resultItem2.getChefLieu());
    }

    //    geo/arrondissement/674/precedents?date=1950-01-01
    @Test
    //le département 21 n'a pas de départments précédents
    void should_return_404_when_ArrondissementCodePrecedents_code674_date19500101() throws Exception{
        mockMvc.perform(get("/geo/departement/21/precedents")
                        .param("date", "1950-01-01"))
                .andExpect(status().isNotFound());
    }


    /////////////////////////////////////////////////////////
    ///        geo/arrondissement/{code}/projetes         ///
    /////////////////////////////////////////////////////////

    //    geo/arrondissement/674/projetes?date=2025-09-01
    @Test
    void should_return_400_when_DepartementCodeProjetes_dateProjectionNull() throws Exception{
        mockMvc.perform(get("/geo/arrondissement/674/projetes")
                        .param("date", "2025-09-01"))
                .andExpect(status().isBadRequest());
    }

    //    geo/arrondissement/674/projetes?date=2025-09-01&dateProjection=
    @Test
    public void should_return_400_when_DepartementCodeProjetes_dateProjectionEmpty() throws Exception {
        mockMvc.perform(get("/geo/arrondissement/674/projetes")
                        .param("dateProjection", "")  // Valeur vide
                        .param("date", "2025-09-01"))
                .andExpect(status().isBadRequest());
    }

    //    geo/arrondissement/674/projetes?date=2025-09-04&dateProjection=1994-07-30
    @Test
    void should_return_6_arrondissements_when_ArrondissementCodeProjetes_code674_date20250904_dateProjection19940730() {
        var response = endpoints.getcogarrproj("674", LocalDate.of(1994, 7, 30), LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1 = result.getFirst();
        assertEquals(6, result.size());
        assertEquals("672", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissement/3e815542-7173-4d26-9e94-ac663bba89f7", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(1993, 1, 1), resultItem1.getDateCreation());
        assertEquals(LocalDate.of(2015, 1, 1), resultItem1.getDateSuppression());
        assertEquals("Haguenau", resultItem1.getIntituleSansArticle());
        assertEquals("Haguenau", resultItem1.getIntitule());
        assertEquals("67180", resultItem1.getChefLieu());

        // Vérification du deuxième élément
        var resultItem2 = result.get(1);
        assertEquals("673", resultItem2.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissement/0e34aa15-b3c7-4574-ad89-e1d6d9bd9812", resultItem2.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, resultItem2.getType());
        assertEquals(LocalDate.of(1993, 1, 1), resultItem2.getDateCreation());
        assertEquals(LocalDate.of(2015, 1, 1), resultItem2.getDateSuppression());
        assertEquals("Molsheim", resultItem2.getIntituleSansArticle());
        assertEquals("Molsheim", resultItem2.getIntitule());
        assertEquals("67300", resultItem2.getChefLieu());
    }

    /////////////////////////////////////////////////////////
    ///        geo/arrondissement/{code}/suivants        ///
    /////////////////////////////////////////////////////////


    //    geo/arrondissement/042/suivants?date=2025-09-04
    @Test
    void should_return_404_when_ArrondissementCodeSuivants_code674_date20250904() throws Exception{
        mockMvc.perform(get("/geo/arrondissement/674/suivants")
                        .param("date", "2025-09-04"))
                .andExpect(status().isNotFound());
    }

    //    geo/arrondissement/042/suivants?date=2016-01-01
    @Test
    void should_return_3_arrondissements_when_ArrondissementCodeSuivants_code042_date20160101() {
        var response = endpoints.getcogarrsuiv("042", LocalDate.of(2016, 1, 1));
        var result = response.getBody();

        // Vérification du nombre d'arrondissements suivants
        assertNotNull(result);
        assertEquals(3, result.size());

        // Vérification du premier élément
        var resultItem1 = result.getFirst();
        assertEquals("042", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissement/1825eafe-2204-4849-b543-715f87615bb8", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(2017, 1, 1), resultItem1.getDateCreation());
        assertEquals("Castellane", resultItem1.getIntituleSansArticle());
        assertEquals("Castellane", resultItem1.getIntitule());
        assertEquals("04039", resultItem1.getChefLieu());

        // Vérification du deuxième élément
        var resultItem2 = result.get(1);
        assertEquals("043", resultItem2.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissement/904dae47-ccda-4877-9b6c-9c0566178db6", resultItem2.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, resultItem2.getType());
        assertEquals(LocalDate.of(2017, 1, 1), resultItem2.getDateCreation());
        assertEquals("Digne-les-Bains", resultItem2.getIntituleSansArticle());
        assertEquals("Digne-les-Bains", resultItem2.getIntitule());
        assertEquals("04070", resultItem2.getChefLieu());

        // Vérification du troisième élément
        var resultItem3 = result.get(2);
        assertEquals("044", resultItem3.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissement/51dcbc6e-5c97-4472-b597-68aee9904dc9", resultItem3.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, resultItem3.getType());
        assertEquals(LocalDate.of(2017, 1, 1), resultItem3.getDateCreation());
        assertEquals("Forcalquier", resultItem3.getIntituleSansArticle());
        assertEquals("Forcalquier", resultItem3.getIntitule());
        assertEquals("04088", resultItem3.getChefLieu());
    }

}

