package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoCantonOuVilleEndpoints;
import fr.insee.rmes.metadata.model.*;
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


public class GeoCantonOuVilleQueriesTest extends TestcontainerTest {
    @Autowired
    GeoCantonOuVilleEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///        geo/cantonOuVille/{code}/ascendants          ///
    /////////////////////////////////////////////////////////////////////

//    geo/cantonOuVille/0101/ascendants?date=2025-09-04 renvoie 2 ascendants
    @Test
    void should_return_1_region_1_departement_when_CantonOuVilleCodeAscendants_code0101_date20250904_typeNull(){
        var response  = endpoints.getcogcanvilasc("0101", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(2, result.size());
        assertEquals("01", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/departement/84680e6f-2e99-44c9-a9ba-2e96a2ae48b7", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.DEPARTEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(1967,12,31), resultItem1.getDateCreation());
        assertEquals("Ain", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._5_ARTICLE_L_CHARNIERE_DE_L_, resultItem1.getTypeArticle());
        assertEquals("01053", resultItem1.getChefLieu());
        assertEquals("Ain", resultItem1.getIntitule());
    }

//    geo/cantonOuVille/0101/ascendants?date=2025-09-04&type=Region
    @Test
    void should_return_1_region_when_CantonOuVilleCodeAscendants_code0101_date20250904_typeRegion(){
        var response  = endpoints.getcogcanvilasc("0101", LocalDate.of(2025, 9, 4), TypeEnumAscendantsCantonOuVille.REGION);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertEquals("84", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/region/c12b23e7-d2e7-4443-ac4b-de8de5ce22f2", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.REGION, resultItem1.getType());
        assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateCreation());
        assertEquals("Auvergne-Rhône-Alpes", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._1_CHARNIERE_D_, resultItem1.getTypeArticle());
        assertEquals("69123", resultItem1.getChefLieu());
        assertEquals("Auvergne-Rhône-Alpes", resultItem1.getIntitule());
    }

    /////////////////////////////////////////////////////////////////////
    ///        geo/cantonOuVille/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/cantonOuVille/0101?date=2025-09-04
    @Test
    void should_return_CantonOuVille_When_code0101_date20250904() throws Exception {
        var response = endpoints.getcogcanvil("0101", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertEquals("0101", result.getCode());
        assertEquals("http://id.insee.fr/geo/cantonOuVille/5e75ead7-7564-4480-83b0-7e16a7d8acf7", result.getUri());
        assertEquals(CantonOuVille.TypeEnum.CANTON_OU_VILLE, result.getType());
        assertEquals(LocalDate.of(2016, 1, 1), result.getDateCreation());
        assertEquals("Ambérieu-en-Bugey", result.getIntituleSansArticle());
        assertEquals(CantonOuVille.TypeArticleEnum._1_CHARNIERE_D_, result.getTypeArticle());
        assertEquals("Ambérieu-en-Bugey", result.getIntitule());
    }

//    geo/cantonOuVille/0100?date=2025-09-04 renvoie 404
@Test
void should_return_404_when_CantonOuVilleCode_code0100_date20250904() throws Exception{
    mockMvc.perform(get("/geo/cantonOuVille/0100")
                    .param("date", "2025-09-04"))
            .andExpect(status().isNotFound());
}

    /////////////////////////////////////////////////////////////////////
    ///        geo/cantonOuVille/{code}/descendants          ///
    /////////////////////////////////////////////////////////////////////

    @Test
    void should_return_1_iris_when_CantonOuVilleCodeDescendants_code0101_date20250904_typeIris_filtreNomPerouses(){
        var response  = endpoints.getcogcanvildes("0101", LocalDate.of(2025, 9, 4), TypeEnumDescendantsCantonOuVille.IRIS,"Perouses");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertEquals("010040101", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/iris/b8c772de-9551-4f13-81c5-eca5bb0f2f7d", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.IRIS, resultItem1.getType());
        assertEquals(LocalDate.of(2008,1,1), resultItem1.getDateCreation());
        assertEquals("Pérouses-Triangle d'Activités", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._4_ARTICLE_LES_CHARNIERE_DES_, resultItem1.getTypeArticle());
        assertEquals("Les Pérouses-Triangle d'Activités", resultItem1.getIntitule());
    }

    @Test
    void should_return_22_territoires_when_CantonOuVilleCodeDescendants_code0101_date20250904_typeNull_filtreNomNull(){
        var response  = endpoints.getcogcanvildes("0101", LocalDate.of(2025, 9, 4), null,null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(22, result.size());
        assertEquals("01002", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/commune/43018c68-c278-433a-b285-3531e8d5347e", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType());
        assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation());
        assertEquals("Abergement-de-Varey", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._5_ARTICLE_L_CHARNIERE_DE_L_, resultItem1.getTypeArticle());
        assertEquals("L'Abergement-de-Varey", resultItem1.getIntitule());
    }

    ////////////////////////////////////////////////////////////////////
    ///        geo/cantonsEtVilles                         ///
    ////////////////////////////////////////////////////////////////////

//    geo/cantonsEtVilles?date=2025-09-04//
    @Test
    void should_return_2042_cantonsEtVilles_when_cantonsEtVilles_date20250904(){
        var response  = endpoints.getcogcanvilliste ("2025-09-04");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(2042, result.size());
        assertEquals("0101", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/cantonOuVille/5e75ead7-7564-4480-83b0-7e16a7d8acf7", resultItem1.getUri());
        assertEquals(CantonOuVille.TypeEnum.CANTON_OU_VILLE, resultItem1.getType());
        assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateCreation());
        assertEquals("Ambérieu-en-Bugey", resultItem1.getIntituleSansArticle());
        assertEquals(CantonOuVille.TypeArticleEnum._1_CHARNIERE_D_, resultItem1.getTypeArticle());
        assertEquals("Ambérieu-en-Bugey", resultItem1.getIntitule());
    }

//    geo/cantonsEtVilles?date=*
@Test
void should_return_2042_cantonsEtVilles_when_cantonsEtVilles_dateEtoile(){
    var response  = endpoints.getcogcanvilliste ("2025-09-04");
    var result = response.getBody();
    var resultItem1= result.getFirst();
    assertEquals(2042, result.size());
    assertEquals("0101", resultItem1.getCode());
    assertEquals("http://id.insee.fr/geo/cantonOuVille/5e75ead7-7564-4480-83b0-7e16a7d8acf7", resultItem1.getUri());
    assertEquals(CantonOuVille.TypeEnum.CANTON_OU_VILLE, resultItem1.getType());
    assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateCreation());
    assertEquals("Ambérieu-en-Bugey", resultItem1.getIntituleSansArticle());
    assertEquals(CantonOuVille.TypeArticleEnum._1_CHARNIERE_D_, resultItem1.getTypeArticle());
    assertEquals("Ambérieu-en-Bugey", resultItem1.getIntitule());
}

    ////////////////////////////////////////////////////////////////////
    ///        geo/cantonOuVille/{code}/precedents         ///
    ////////////////////////////////////////////////////////////////////

//    geo/cantonOuVille/0104/precedents?date=2025-09-04
    @Test
    void should_return_3_cantonsOuVilles_when_CantonsOuVillesCodePrecedents_code0104_date20250904(){
        var response  = endpoints.getcogcanvilprec ("0104", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(3, result.size());
        assertEquals("0104", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/cantonOuVille/383181fb-59ba-425c-81c1-dfdf1b51cf8c", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.CANTON_OU_VILLE, resultItem1.getType());
        assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateCreation());
        assertEquals(LocalDate.of(2020,3,7), resultItem1.getDateSuppression());
        assertEquals("Belley", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, resultItem1.getTypeArticle());
        assertEquals("Belley", resultItem1.getIntitule());
    }

//    geo/cantonOuVille/0104/precedents?date=1950-01-01  renvoie 404
    @Test
    void should_return_404_when_CantonOuVilleCodePrecedents_code0104_date19450101() throws Exception{
        mockMvc.perform(get("/geo/cantonOuVille/0104/precedents")
                        .param("date", "1945-01-01"))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///        geo/cantonOuVille/{code}/projetes           ///
    ////////////////////////////////////////////////////////////////////

//    geo/cantonOuVille/0104/projetes?date=2025-09-04&dateProjection=2016-01-01 renvoie 3 cantonsOuVilles
    @Test
    void should_return_3_cantonsOuVilles_when_CantonsOuVillesCodeProjetes_code0104_date20250904_dateProjection20160101(){
        var response  = endpoints.getcogcanvilproj ("0104", LocalDate.of(2016,1,1),LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(3, result.size());
        assertEquals("0104", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/cantonOuVille/383181fb-59ba-425c-81c1-dfdf1b51cf8c", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.CANTON_OU_VILLE, resultItem1.getType());
        assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateCreation());
        assertEquals(LocalDate.of(2020,3,7), resultItem1.getDateSuppression());
        assertEquals("Belley", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, resultItem1.getTypeArticle());
        assertEquals("Belley", resultItem1.getIntitule());
    }

    //    geo/cantonOuVille/0104/projetes?date=2025-09-01
    @Test
    void should_return_400_when_CantonOuVilleCodeProjetes_dateProjectionNull() throws Exception{
        mockMvc.perform(get("/geo/cantonOuVille/0104/projetes")
                        .param("date", "2025-09-01"))
                .andExpect(status().isBadRequest());
    }

    //    geo/cantonOuVille/0104/projetes?date=2025-09-01&dateProjection=
    @Test
    public void should_return_400_when_CantonOuVilleCodeProjetes_dateProjectionEmpty() throws Exception {
        mockMvc.perform(get("/geo/cantonOuVille/0104/projetes")
                        .param("dateProjection", "")  // Valeur vide
                        .param("date", "2025-09-01"))
                .andExpect(status().isBadRequest());
    }

    ////////////////////////////////////////////////////////////////////
    ///        geo/cantonOuVille/{code}/suivants           ///
    ////////////////////////////////////////////////////////////////////

//    geo/cantonOuVille/0103/suivants?date=1960-01-01 renvoie 1 cantonOuVille
    @Test
    void should_return_1_cantonOuVille_when_CantonOuVIlleCodeSuivants_code0103_date20160101(){
        var response  = endpoints.getcogcanvilsuiv ("0103", LocalDate.of(2016,1,1));
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertEquals("0103", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/cantonOuVille/cb4d9856-a39d-4283-a9ab-d91396ebd705", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.CANTON_OU_VILLE, resultItem1.getType());
        assertEquals(LocalDate.of(2020,3,7), resultItem1.getDateCreation());
        assertEquals("Valserhône", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, resultItem1.getTypeArticle());
        assertEquals("Valserhône", resultItem1.getIntitule());
    }

    //    geo/cantonOuVille/0103/suivants?date=2025-09-04
    @Test
    void should_return_404_when_CantonOuVIlleCodeSuivants_code0103_date20250904() throws Exception{
        mockMvc.perform(get("/geo/cantonOuVille/0103/suivants")
                        .param("date", "2025-09-04"))
                .andExpect(status().isNotFound());
    }

}
