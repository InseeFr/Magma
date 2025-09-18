package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoCantonEndpoints;
import fr.insee.rmes.metadata.model.Canton;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.model.TypeEnumAscendantsCanton;
import org.junit.Assert;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class GeoCantonQueriesTest extends TestcontainerTest{

    @Autowired
    GeoCantonEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///        geo/canton/{code}/ascendants          ///
    /////////////////////////////////////////////////////////////////////

//    geo/canton/0101/ascendants?date=2025-09-04 renvoie 2 ascendants
    @Test
    void should_return_1_region_and_1_departement_when_CantonCodeAscendants_code0101_date20250904_typeNull(){
        var response  = endpoints.getcogcanasc("0101", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(2, result.size());
        Assert.assertEquals("01", resultItem1.getCode());
        Assert.assertEquals("http://id.insee.fr/geo/departement/84680e6f-2e99-44c9-a9ba-2e96a2ae48b7", resultItem1.getUri());
        Assert.assertEquals(TerritoireTousAttributs.TypeEnum.DEPARTEMENT, resultItem1.getType());
        Assert.assertEquals(LocalDate.of(1967,12,31), resultItem1.getDateCreation());
        Assert.assertEquals("Ain", resultItem1.getIntituleSansArticle());
        Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._5_ARTICLE_L_CHARNIERE_DE_L_, resultItem1.getTypeArticle());
        Assert.assertEquals("Ain", resultItem1.getIntitule());

        Assert.assertEquals(TerritoireTousAttributs.TypeEnum.REGION, result.get(1).getType());
    }

    //    geo/canton/0101/ascendants?date=2025-09-04&type=Departement renvoie 2 ascendants
    @Test
    void should_return_1_departement_when_CantonCodeAscendants_code0101_date20250904_typeDepartement(){
        var response  = endpoints.getcogcanasc("0101", LocalDate.of(2025, 9, 4), TypeEnumAscendantsCanton.DEPARTEMENT);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        Assert.assertEquals("01", resultItem1.getCode());
        Assert.assertEquals("http://id.insee.fr/geo/departement/84680e6f-2e99-44c9-a9ba-2e96a2ae48b7", resultItem1.getUri());
        Assert.assertEquals(TerritoireTousAttributs.TypeEnum.DEPARTEMENT, resultItem1.getType());
        Assert.assertEquals(LocalDate.of(1967,12,31), resultItem1.getDateCreation());
        Assert.assertEquals("Ain", resultItem1.getIntituleSansArticle());
        Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._5_ARTICLE_L_CHARNIERE_DE_L_, resultItem1.getTypeArticle());
        Assert.assertEquals("Ain", resultItem1.getIntitule());
    }

    /////////////////////////////////////////////////////////////////////
    ///        geo/canton/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/canton/0101?date=2025-09-04
    @Test
    void should_return_CantonCode_0101_when_code22_date20250904() {
        var response  = endpoints.getcogcan("0101", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        Assert.assertEquals(Canton.TypeEnum.CANTON, result.getType());
        Assert.assertEquals(LocalDate.of(2016, 1, 1), result.getDateCreation());
        Assert.assertEquals("Ambérieu-en-Bugey", result.getIntituleSansArticle());
        Assert.assertEquals(Canton.TypeArticleEnum._1_CHARNIERE_D_, result.getTypeArticle());
        Assert.assertEquals("Ambérieu-en-Bugey", result.getIntitule());
        Assert.assertEquals("01004", result.getChefLieu());
    }


//    geo/canton/01011?date=2025-09-04 renvoie 404
    @Test
    //le canton 0100 n'existe pas
    void should_return_404_when_CantonCode_code0102_date20250904() throws Exception{
        mockMvc.perform(get("/geo/canton/0100")
                        .param("date", "2025-09-01"))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///        geo/cantons                         ///
    ////////////////////////////////////////////////////////////////////

//    geo/cantons?date=2025-09-04//
    @Test
    void should_return_2054_cantons_when_Cantons_date20250904(){
        var response  = endpoints.getcogcanliste ("2025-09-04");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(2054, result.size());
        Assert.assertEquals("0101", resultItem1.getCode());
        Assert.assertEquals("http://id.insee.fr/geo/canton/f96a2438-478f-4ebb-b659-434305dff18f",resultItem1.getUri());
        Assert.assertEquals(Canton.TypeEnum.CANTON, resultItem1.getType());
        Assert.assertEquals(LocalDate.of(2016, 1, 1), resultItem1.getDateCreation());
        Assert.assertEquals("01004", resultItem1.getChefLieu());
        Assert.assertEquals("Ambérieu-en-Bugey", resultItem1.getIntitule());
        Assert.assertEquals("Ambérieu-en-Bugey", resultItem1.getIntituleSansArticle());
        Assert.assertEquals(Canton.TypeArticleEnum._1_CHARNIERE_D_, resultItem1.getTypeArticle());
    }

//    geo/cantons?date=*
void should_return_101_cantons_when_Cantons_dateEtoile(){
    var response  = endpoints.getcogcanliste ("2025-09-04");
    var result = response.getBody();
    var resultItem1= result.getFirst();
    assertEquals(101, result.size());
    Assert.assertEquals("0101", resultItem1.getCode());
    Assert.assertEquals("http://id.insee.fr/geo/canton/f96a2438-478f-4ebb-b659-434305dff18f",resultItem1.getUri());
    Assert.assertEquals(Canton.TypeEnum.CANTON, resultItem1.getType());
    Assert.assertEquals(LocalDate.of(2016, 1, 1), resultItem1.getDateCreation());
    Assert.assertEquals("01004", resultItem1.getChefLieu());
    Assert.assertEquals("Ambérieu-en-Bugey", resultItem1.getIntitule());
    Assert.assertEquals("Ambérieu-en-Bugey", resultItem1.getIntituleSansArticle());
    Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._1_CHARNIERE_D_, resultItem1.getTypeArticle());
}

    ////////////////////////////////////////////////////////////////////
    ///        geo/canton/{code}/precedents         ///
    ////////////////////////////////////////////////////////////////////

//    geo/canton/69385/precedents renvoie 1 canton
    @Test
    void should_return_1_canton_when_CantonCodePrecedents_code0103_date20250904() {
        var response  = endpoints.getcogcanprec ("0103", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        var resultItem1 = result.getFirst();
        assertEquals(1, result.size());
        Assert.assertEquals("0103", resultItem1.getCode());
        Assert.assertEquals("http://id.insee.fr/geo/canton/849dca85-8ef3-4a00-bc06-3165aeff1dbd",resultItem1.getUri());
        Assert.assertEquals(TerritoireTousAttributs.TypeEnum.CANTON, resultItem1.getType());
        Assert.assertEquals(LocalDate.of(2016, 1, 1), resultItem1.getDateCreation());
        Assert.assertEquals(LocalDate.of(2020, 3, 7), resultItem1.getDateSuppression());
        Assert.assertEquals("Bellegarde-sur-Valserine", resultItem1.getIntitule());
        Assert.assertEquals("Bellegarde-sur-Valserine", resultItem1.getIntituleSansArticle());
        Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, resultItem1.getTypeArticle());
    }

//    geo/canton/0101/precedents?date=1945-01-01  renvoie 404
    @Test
    void should_return_404_when_CantonCodePrecedents_code0101_date19450101() throws Exception{
        mockMvc.perform(get("/geo/canton/0101/precedents")
                        .param("date", "1945-01-01"))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///        geo/canton/{code}/projetes           ///
    ////////////////////////////////////////////////////////////////////

//    geo/canton/69385/projetes?date=1960-01-01&dateProjection=2011-12-31: renvoie les cantons de Lyon 5e et Lyon 9e

    ////////////////////////////////////////////////////////////////////
    ///        geo/canton/{code}/suivants           ///
    ////////////////////////////////////////////////////////////////////

//    geo/canton/69385/suivants?date=1960-01-01 renvoie 2 cantons
}
