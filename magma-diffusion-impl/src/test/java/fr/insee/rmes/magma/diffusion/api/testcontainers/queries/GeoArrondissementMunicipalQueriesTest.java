package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;


import fr.insee.rmes.magma.diffusion.api.GeoArrondissementMunipalEndpoints;
import fr.insee.rmes.magma.diffusion.model.ArrondissementMunicipal;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
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


public class GeoArrondissementMunicipalQueriesTest extends TestcontainerTest {

    @Autowired
    GeoArrondissementMunipalEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementMunicipal/{code}/ascendants          ///
    /////////////////////////////////////////////////////////////////////

//    geo/arrondissementMunicipal/13202/ascendants renvoie 11 ascendants
    @Test
    void should_return_11_territoires_when_ArrondissementMunicipalCodeAscendants_code13202_date20250904_typeNull(){
        var response  = endpoints.getcogarrmuasc("13202", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        assertNotNull(result);
        assertEquals(11, result.size());

        // Vérifie le premier élément (AireDAttractionDesVilles2020)
        var resultItem1 = result.getFirst();
        assertEquals("003", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/aireDAttractionDesVilles2020/132d17f0-048f-4e01-91f6-88c383c7a2b9", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.AIRE_D_ATTRACTION_DES_VILLES2020, resultItem1.getType());
        assertEquals(LocalDate.of(2020, 1, 1), resultItem1.getDateCreation());
        assertEquals("Marseille - Aix-en-Provence", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Marseille - Aix-en-Provence", resultItem1.getIntitule());

        // Vérifie le deuxième élément (Arrondissement)
        assertEquals("133", result.get(1).getCode());
        assertEquals("http://id.insee.fr/geo/arrondissement/5af92f83-0b53-4143-91a0-e5206ce9d5f6", result.get(1).getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, result.get(1).getType());
        assertEquals(LocalDate.of(2017, 3, 1), result.get(1).getDateCreation());
        assertEquals("Marseille", result.get(1).getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, result.get(1).getTypeArticle());
        assertEquals("Marseille", result.get(1).getIntitule());
    }

    /////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementMunicipal/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/arrondissementMunicipal/69385?date=2025-09-04
    @Test
    void should_return_ArrondissementMunicipal_When_code69385_date20250904() {
        var response = endpoints.getcogarrmu("69385", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertNotNull(result);
        assertEquals("69385", result.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/cd9f4663-684c-455d-b62e-39e51c6fad99", result.getUri());
        assertEquals(ArrondissementMunicipal.TypeEnum.ARRONDISSEMENT_MUNICIPAL, result.getType());
        assertEquals(LocalDate.of(1964, 8, 12), result.getDateCreation());
        assertEquals("Lyon 5e Arrondissement", result.getIntituleSansArticle());
        assertEquals(ArrondissementMunicipal.TypeArticleEnum._0, result.getTypeArticle());
        assertEquals("Lyon 5e Arrondissement", result.getIntitule());
    }

//    geo/arrondissementMunicipal/69380?date=2025-09-04 renvoie 404
    @Test
    //l'arrondissement municipal 69380 n'existe pas
    void should_return_404_when_ArrondissementMunicipalCode_code69380_date20250904() throws Exception {
            mockMvc.perform(get("/geo/arrondissementMunicipal/69380")
                            .param("date", "2025-09-01"))
                    .andExpect(status().isNotFound());
    }


    ////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementsMunicipaux                         ///
    ////////////////////////////////////////////////////////////////////

//    geo/arrondissementsMunicipaux?date=2025-09-04//
    @Test
    void should_return_45_arrondissementsMunicipaux_when_ArrondissementsMunicipaux_date20250904(){
        var response  = endpoints.getcogarrmuliste ("2025-09-04");
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(45, result.size());
        assertEquals("13201", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/d2ae811d-f0b8-4bac-972d-01dabe292665", resultItem1.getUri());
        assertEquals(ArrondissementMunicipal.TypeEnum.ARRONDISSEMENT_MUNICIPAL, resultItem1.getType());
        assertEquals(LocalDate.of(1946, 10, 18), resultItem1.getDateCreation());
        assertEquals("Marseille 1er Arrondissement", resultItem1.getIntituleSansArticle());
        assertEquals(ArrondissementMunicipal.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Marseille 1er Arrondissement", resultItem1.getIntitule());

        var resultItem2= result.get(1);
        assertEquals("13202", resultItem2.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/00b1eb8a-1163-4108-a64f-aee2e6d88e0a", resultItem2.getUri());
        assertEquals(ArrondissementMunicipal.TypeEnum.ARRONDISSEMENT_MUNICIPAL, resultItem2.getType());
        assertEquals(LocalDate.of(1946, 10, 18), resultItem2.getDateCreation());
        assertEquals("Marseille 2e Arrondissement", resultItem2.getIntituleSansArticle());
        assertEquals(ArrondissementMunicipal.TypeArticleEnum._0, resultItem2.getTypeArticle());
        assertEquals("Marseille 2e Arrondissement", resultItem2.getIntitule());
    }

//    geo/arrondissementsMunicipaux?date=*
@Test
void should_return_45_arrondissementsMunicipaux_when_ArrondissementsMunicipaux_dateEtoile(){
    var response  = endpoints.getcogarrmuliste ("2025-09-04");
    var result = response.getBody();
    assertNotNull(result);
    var resultItem1= result.getFirst();
    assertEquals(45, result.size());
    assertEquals("13201", resultItem1.getCode());
    assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/d2ae811d-f0b8-4bac-972d-01dabe292665", resultItem1.getUri());
    assertEquals(ArrondissementMunicipal.TypeEnum.ARRONDISSEMENT_MUNICIPAL, resultItem1.getType());
    assertEquals(LocalDate.of(1946, 10, 18), resultItem1.getDateCreation());
    assertEquals("Marseille 1er Arrondissement", resultItem1.getIntituleSansArticle());
    assertEquals(ArrondissementMunicipal.TypeArticleEnum._0, resultItem1.getTypeArticle());
    assertEquals("Marseille 1er Arrondissement", resultItem1.getIntitule());

    var resultItem2= result.get(1);
    assertEquals("13202", resultItem2.getCode());
    assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/00b1eb8a-1163-4108-a64f-aee2e6d88e0a", resultItem2.getUri());
    assertEquals(ArrondissementMunicipal.TypeEnum.ARRONDISSEMENT_MUNICIPAL, resultItem2.getType());
    assertEquals(LocalDate.of(1946, 10, 18), resultItem2.getDateCreation());
    assertEquals("Marseille 2e Arrondissement", resultItem2.getIntituleSansArticle());
    assertEquals(ArrondissementMunicipal.TypeArticleEnum._0, resultItem2.getTypeArticle());
    assertEquals("Marseille 2e Arrondissement", resultItem2.getIntitule());
}

    ////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementMunicipal/{code}/precedents         ///
    ////////////////////////////////////////////////////////////////////

//    geo/arrondissementMunicipal/69385/precedents renvoie l’arrondissement municipal de Lyon 5e
    @Test
    void should_return_1_arrondissementMunicipal_when_ArrondissementMunicipalCodePrecedents_code69385_date20250904(){
        var response  = endpoints.getcogarrmuprec ("69385", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertEquals("69385", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/67a81278-24c3-4aec-a2e8-fe914fe05fc2", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT_MUNICIPAL, resultItem1.getType());
        assertEquals(LocalDate.of(1943, 1, 1), resultItem1.getDateCreation());
        assertEquals(LocalDate.of(1964, 8, 12), resultItem1.getDateSuppression());
        assertEquals("Lyon 5e Arrondissement", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Lyon 5e Arrondissement", resultItem1.getIntitule());
    }

//    geo/arrondissementMunicipal/69385/precedents?date=1945-01-01  renvoie 404
    @Test
    void should_return_404_when_ArrondissementMunicipalCodePrecedents_code69385_date19450101() throws Exception{
        mockMvc.perform(get("/geo/arrondissementMunicipal/69385/precedents")
                        .param("date", "1945-01-01"))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementMunicipal/{code}/projetes           ///
    ////////////////////////////////////////////////////////////////////

//    geo/arrondissementMunicipal/69385/projetes?date=1960-01-01&dateProjection=2011-12-31: renvoie les arrondissements de Lyon 5e et Lyon 9e
    @Test
    void should_return_2_arrondissementsMunicipaux_when_ArrondissementMunicipalCodeProjetes_code69385_date19600101_dateProjection20111231(){
        var response  = endpoints.getcogarrmuproj ("69385", LocalDate.of(2011,12,31), LocalDate.of(1950,1,1));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(2, result.size());
        assertEquals("69385", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/cd9f4663-684c-455d-b62e-39e51c6fad99", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT_MUNICIPAL, resultItem1.getType());
        assertEquals(LocalDate.of(1964, 8, 12), resultItem1.getDateCreation());
        assertEquals("Lyon 5e Arrondissement", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Lyon 5e Arrondissement", resultItem1.getIntitule());

        var resultItem2= result.get(1);
        assertEquals("69389", resultItem2.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/c14e7076-ffb4-490b-96c2-341e832d5f60", resultItem2.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT_MUNICIPAL, resultItem2.getType());
        assertEquals(LocalDate.of(1964, 8, 12), resultItem2.getDateCreation());
        assertEquals("Lyon 9e Arrondissement", resultItem2.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem2.getTypeArticle());
        assertEquals("Lyon 9e Arrondissement", resultItem2.getIntitule());
    }

    ////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementMunicipal/{code}/suivants           ///
    ////////////////////////////////////////////////////////////////////

//    geo/arrondissementMunicipal/69385/suivants?date=1960-01-01 renvoie 2 arrondissements municipaux
    @Test
    void should_return_2_arrondissementsMunicipaux_when_ArrondissementMunicipalCodeSuivants_code69385_date19600101(){
        var response  = endpoints.getcogarrmusuiv ("69385", LocalDate.of(1960,1,1));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(2, result.size());
        assertEquals("69385", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/cd9f4663-684c-455d-b62e-39e51c6fad99", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT_MUNICIPAL, resultItem1.getType());
        assertEquals(LocalDate.of(1964, 8, 12), resultItem1.getDateCreation());
        assertEquals("Lyon 5e Arrondissement", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Lyon 5e Arrondissement", resultItem1.getIntitule());

        var resultItem2= result.get(1);
        assertEquals("69389", resultItem2.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/c14e7076-ffb4-490b-96c2-341e832d5f60", resultItem2.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT_MUNICIPAL, resultItem2.getType());
        assertEquals(LocalDate.of(1964, 8, 12), resultItem2.getDateCreation());
        assertEquals("Lyon 9e Arrondissement", resultItem2.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem2.getTypeArticle());
        assertEquals("Lyon 9e Arrondissement", resultItem2.getIntitule());
    }
}
