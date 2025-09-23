package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoRegionEndpoints;
import fr.insee.rmes.metadata.model.Region;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.model.TypeEnumDescendantsRegion;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class GeoRegionQueriesTest extends TestcontainerTest{


    @Autowired
    GeoRegionEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///                geo/region/{code}                              ///
    /////////////////////////////////////////////////////////////////////

//    geo/region/06?date=2025-09-04
    @Test
    void should_return_region06_when_regionCode06_date20250904() {
        var response  = endpoints.getcogreg("06", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertAll(
                () -> assertEquals("06", result.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/region/0e9f9adc-742d-4ab7-90bd-30e5aaf7b2ab", result.getUri()),
                () -> assertEquals(Region.TypeEnum.REGION, result.getType()),
                () -> assertEquals(LocalDate.of(2011,3,31), result.getDateCreation()),
                () -> assertEquals("Mayotte", result.getIntituleSansArticle()),
                () -> assertEquals(Region.TypeArticleEnum._0_CHARNIERE_DE_, result.getTypeArticle()),
                () -> assertEquals("97611", result.getChefLieu()),
                () -> assertEquals("Mayotte", result.getIntitule())
        );
    }


    /////////////////////////////////////////////////////////////////////
    ///                geo/region/{code}/descendants                  ///
    /////////////////////////////////////////////////////////////////////

//   geo/region/06/descendants?date=2025-09-04 : renvoie 30 territoires
    @Test
    void should_return_167_territoires_when_RegionCodeDescendants_code06_date20250904_typeNull_filtreNomNull(){
        var response  = endpoints.getcogregdes("06", LocalDate.of(2025, 9, 4), null, null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(167, result.size()),
                () -> assertEquals("97601", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/canton/03e01e65-f723-4998-b9e7-2068df3b053a", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.CANTON, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Bandraboua", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, resultItem1.getTypeArticle()),
                () -> assertEquals("97602", resultItem1.getChefLieu()),
                () -> assertEquals("Bandraboua", resultItem1.getIntitule())
        );
    }

//   geo/region/06/descendants?date=2025-09-04?type=CantonOuVille
    @Test
    void should_return_1_cantonOuVIlle_when_RegionCodeDescendants_code06_date20250904_typeCantonOuVille_filtreNomDembeni(){
        var response  = endpoints.getcogregdes("06", LocalDate.of(2025, 9, 4), TypeEnumDescendantsRegion.CANTON_OU_VILLE, "Dembeni");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("97603", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/cantonOuVille/ba7925cb-e000-499c-948c-9c7ef47d5f9e", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.CANTON_OU_VILLE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Dembeni", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, resultItem1.getTypeArticle()),
                () -> assertEquals("Dembeni", resultItem1.getIntitule())
        );
    }

    /////////////////////////////////////////////////////////////////////
    ///                geo/regions                                    ///
    /////////////////////////////////////////////////////////////////////

//   geo/regions?date=2025-09-04 : renvoie 30 territoires
    @Test
    void should_return_18_territoires_when_Region_date20250904_typeNull_filtreNomNull(){
        var response  = endpoints.getcogregliste("2025-09-04");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(18, result.size()),
                () -> assertEquals("01", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/region/598b3ed6-a7ea-44f8-a130-7a42e3630a8a", resultItem1.getUri()),
                () -> assertEquals(Region.TypeEnum.REGION, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2007,2,23), resultItem1.getDateCreation()),
                () -> assertEquals("Guadeloupe", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(Region.TypeArticleEnum._3_ARTICLE_LA_CHARNIERE_DE_LA_, resultItem1.getTypeArticle()),
                () -> assertEquals("97105", resultItem1.getChefLieu()),
                () -> assertEquals("Guadeloupe", resultItem1.getIntitule())
        );
    }

//  geo/region?date=*
    @Test
    void should_return_45_regions_when_Regions_dateEtoile(){
        var response  = endpoints.getcogregliste("*");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(45, result.size()),
                () -> assertEquals("01", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/region/c5e2a8e5-2a3b-4ff1-8f01-b6fc5710ceb9", resultItem1.getUri()),
                () -> assertEquals(Region.TypeEnum.REGION, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1982,3,2), resultItem1.getDateCreation()),
                () -> assertEquals(LocalDate.of(2007,2,23), resultItem1.getDateSuppression()),
                () -> assertEquals("Guadeloupe", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(Region.TypeArticleEnum._3_ARTICLE_LA_CHARNIERE_DE_LA_, resultItem1.getTypeArticle()),
                () -> assertEquals("97105", resultItem1.getChefLieu()),
                () -> assertEquals("Guadeloupe", resultItem1.getIntitule())
        );
    }

    /////////////////////////////////////////////////////////
    ///        geo/region/{code}/precedents               ///
    /////////////////////////////////////////////////////////

//  geo/region/44?date=2025-09-04
    @Test
    void should_return_2_regions_when_RegionsCodePrecedents_date20250904(){
        var response  = endpoints.getcogregprec("44", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("32", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/region/70086d81-9af2-4aeb-8734-502658d6a93f", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.REGION, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2016,9,29), resultItem1.getDateCreation()),
                () -> assertEquals(LocalDate.of(2016,12,31), resultItem1.getDateSuppression()),
                () -> assertEquals("Hauts-de-France", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._4_ARTICLE_LES_CHARNIERE_DES_, resultItem1.getTypeArticle()),
                () -> assertEquals("59350", resultItem1.getChefLieu()),
                () -> assertEquals("Hauts-de-France", resultItem1.getIntitule())
        );
    }

    //    geo/region/44/precedents?date=201-01-01
    @Test
    void should_return_404_when_RegionCodePrecedents_code44_date20100101() throws Exception{
        mockMvc.perform(get("/geo/region/44/precedents")
                        .param("date", "2010-01-01"))
                .andExpect(status().isNotFound());
    }

    /////////////////////////////////////////////////////////
    ///        geo/region/{code}/projetes               ///
    /////////////////////////////////////////////////////////


    //    geo/region/44/projetes?date=2025-09-01
    @Test
    void should_return_400_when_RegionCodeProjetes_dateProjectionNull() throws Exception{
        mockMvc.perform(get("/geo/region/44/projetes")
                        .param("date", "2025-09-01"))
                .andExpect(status().isBadRequest());
    }

    //    geo/region/44/projetes?date=2025-09-01&dateProjection=
    @Test
    public void should_return_400_when_RegionCodeProjetes_dateProjectionEmpty() throws Exception {
        mockMvc.perform(get("/geo/region/44/projetes")
                        .param("dateProjection", "")  // Valeur vide
                        .param("date", "2025-09-01"))
                .andExpect(status().isBadRequest());
    }

//  geo/region/44?date=2025-09-04&dateProjection=2000-01-01
    @Test
    void should_return_5_regions_when_RegionsCodeProjetes_date20250904_dateProjection20100101(){
        var response  = endpoints.getcogregproj("44", LocalDate.of(2000, 1, 1), LocalDate.of(2025,9,4));
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(5, result.size()),
                () -> assertEquals("21", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/region/15916a60-04eb-4951-abd4-1bfd0b54f2e9", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.REGION, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1982,3,2), resultItem1.getDateCreation()),
                () -> assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateSuppression()),
                () -> assertEquals("Champagne-Ardenne", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, resultItem1.getTypeArticle()),
                () -> assertEquals("Champagne-Ardenne", resultItem1.getIntitule())
        );
    }


    /////////////////////////////////////////////////////////
    ///        geo/region/{code}/suivants            ///
    /////////////////////////////////////////////////////////

    //    geo/region/41/suivants?date=2020-01-01
    @Test
    void should_return_404_when_RegionCodeSuivants_code41_date20200101() throws Exception{
        mockMvc.perform(get("/geo/region/41/suivants")
                        .param("date", "2020-01-01"))
                .andExpect(status().isNotFound());
    }

    //    geo/region/41/suivants?date=2000-01-01
    @Test
    void should_return_1_region_when_RegionCodeSuivants_code41_date20000101(){
        var response  = endpoints.getcogregsuiv("41", LocalDate.of(2000,1,1));
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertEquals("44", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/region/5295742f-1bd8-4062-b9d2-3a32ab1d8fbe", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.REGION, resultItem1.getType());
        assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateCreation());
        assertEquals(LocalDate.of(2016,9,29), resultItem1.getDateSuppression());
        assertEquals("Alsace-Champagne-Ardenne-Lorraine", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._1_CHARNIERE_D_, resultItem1.getTypeArticle());
        assertEquals("67482", resultItem1.getChefLieu());
        assertEquals("Alsace-Champagne-Ardenne-Lorraine", resultItem1.getIntitule());
    }


}
