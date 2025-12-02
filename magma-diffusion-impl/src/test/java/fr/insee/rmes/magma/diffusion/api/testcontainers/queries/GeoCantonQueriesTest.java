package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.GeoCantonEndpoints;
import fr.insee.rmes.magma.diffusion.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
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
    ///                  geo/canton/{code}/ascendants                 ///
    /////////////////////////////////////////////////////////////////////

//    geo/canton/0101/ascendants?date=2025-09-04 renvoie 2 ascendants
    @Test
    void should_return_1_region_and_1_departement_when_CantonCodeAscendants_code0101_date20250904_typeNull(){
        var response  = endpoints.getcogcanasc("0101", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(2, result.size());
        assertEquals("01", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/departement/84680e6f-2e99-44c9-a9ba-2e96a2ae48b7", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.DEPARTEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(1967,12,31), resultItem1.getDateCreation());
        assertEquals("Ain", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._5, resultItem1.getTypeArticle());
        assertEquals("Ain", resultItem1.getIntitule());

        assertEquals(TerritoireTousAttributs.TypeEnum.REGION, result.get(1).getType());
    }

    //    geo/canton/0101/ascendants?date=2025-09-04&type=Departement renvoie 2 ascendants
    @Test
    void should_return_1_departement_when_CantonCodeAscendants_code0101_date20250904_typeDepartement(){
        var response  = endpoints.getcogcanasc("0101", LocalDate.of(2025, 9, 4), TypeEnumAscendantsCanton.DEPARTEMENT);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertEquals("01", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/departement/84680e6f-2e99-44c9-a9ba-2e96a2ae48b7", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.DEPARTEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(1967,12,31), resultItem1.getDateCreation());
        assertEquals("Ain", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._5, resultItem1.getTypeArticle());
        assertEquals("Ain", resultItem1.getIntitule());
    }

    /////////////////////////////////////////////////////////////////////
    ///                         geo/canton/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/canton/0101?date=2025-09-04
    @Test
    void should_return_CantonCode_0101_when_code22_date20250904() {
        var response  = endpoints.getcogcan("0101", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertNotNull(result);
        assertEquals("0101", result.getCode());
        assertEquals("http://id.insee.fr/geo/canton/f96a2438-478f-4ebb-b659-434305dff18f", result.getUri());
        assertEquals(Canton.TypeEnum.CANTON, result.getType());
        assertEquals(LocalDate.of(2016, 1, 1), result.getDateCreation());
        assertEquals("Ambérieu-en-Bugey", result.getIntituleSansArticle());
        assertEquals(Canton.TypeArticleEnum._1, result.getTypeArticle());
        assertEquals("Ambérieu-en-Bugey", result.getIntitule());
        assertEquals("01004", result.getChefLieu());
    }

    //    geo/canton/2B05?date=2025-09-04 (in addition to Metadata-API)
    @Test
    void should_return_CantonCode_2B05_when_code2B05_date20250904() {
        var response  = endpoints.getcogcan("2B05", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertNotNull(result);
        assertEquals("2B05", result.getCode());
        assertEquals("http://id.insee.fr/geo/canton/7d8565df-5f17-4a13-9a19-10792df227e1", result.getUri());
        assertEquals(Canton.TypeEnum.CANTON, result.getType());
        assertEquals(LocalDate.of(2016, 1, 1), result.getDateCreation());
        assertEquals("Biguglia-Nebbio", result.getIntituleSansArticle());
        assertEquals(Canton.TypeArticleEnum._0, result.getTypeArticle());
        assertEquals("Biguglia-Nebbio", result.getIntitule());
        assertEquals("2B037", result.getChefLieu());
    }


//    geo/canton/01011?date=2025-09-04 return 404
    @Test
    //le canton 0100 n'existe pas
    void should_return_404_when_CantonCode_code0102_date20250904() throws Exception{
        mockMvc.perform(get("/geo/canton/0100")
                        .param("date", "2025-09-01"))
                .andExpect(status().isNotFound());
    }


    /////////////////////////////////////////////////////////////////////
    ///                  geo/canton/{code}/communes                   ///
    /////////////////////////////////////////////////////////////////////

//    geo/canton/0101/communes?date=2025-09-04
    @Test
    void should_return_18_communes_when_CantonCodeCommunes_code0101_date20250904() {
        var response  = endpoints.getcogcancom ("0101", LocalDate.of(2025,9,4));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(18, result.size());
        assertAll(
                () -> assertEquals("01002", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/43018c68-c278-433a-b285-3531e8d5347e", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Abergement-de-Varey", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._5, resultItem1.getTypeArticle()),
                () -> assertEquals(TerritoireTousAttributs.InclusionEnum.TOTALE, resultItem1.getInclusion()),
                () -> assertEquals("L'Abergement-de-Varey", resultItem1.getIntitule())
        );
    }

    ////////////////////////////////////////////////////////////////////
    ///                          geo/cantons                         ///
    ////////////////////////////////////////////////////////////////////

//    geo/cantons?date=2025-09-04
    @Test
    void should_return_2054_cantons_when_Cantons_date20250904(){
        var response  = endpoints.getcogcanliste ("2025-09-04");
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(2054, result.size());
        assertEquals("0101", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/canton/f96a2438-478f-4ebb-b659-434305dff18f",resultItem1.getUri());
        assertEquals(Canton.TypeEnum.CANTON, resultItem1.getType());
        assertEquals(LocalDate.of(2016, 1, 1), resultItem1.getDateCreation());
        assertEquals("01004", resultItem1.getChefLieu());
        assertEquals("Ambérieu-en-Bugey", resultItem1.getIntitule());
        assertEquals("Ambérieu-en-Bugey", resultItem1.getIntituleSansArticle());
        assertEquals(Canton.TypeArticleEnum._1, resultItem1.getTypeArticle());
    }

//    geo/cantons?date=*
    @Test
    void should_return_2250_cantons_when_Cantons_dateEtoile(){
        var response  = endpoints.getcogcanliste ("*");
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(2250, result.size());
        assertEquals("0101", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/canton/f96a2438-478f-4ebb-b659-434305dff18f",resultItem1.getUri());
        assertEquals(Canton.TypeEnum.CANTON, resultItem1.getType());
        assertEquals(LocalDate.of(2016, 1, 1), resultItem1.getDateCreation());
        assertEquals("01004", resultItem1.getChefLieu());
        assertEquals("Ambérieu-en-Bugey", resultItem1.getIntitule());
        assertEquals("Ambérieu-en-Bugey", resultItem1.getIntituleSansArticle());
        assertEquals(Canton.TypeArticleEnum._1, resultItem1.getTypeArticle());
    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/canton/{code}/precedents                ///
    ////////////////////////////////////////////////////////////////////

//    geo/canton/69385/precedents renvoie 1 canton
    @Test
    void should_return_1_canton_when_CantonCodePrecedents_code0103_date20250904() {
        var response  = endpoints.getcogcanprec ("0103", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1 = result.getFirst();
        assertEquals(1, result.size());
        assertEquals("0103", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/canton/849dca85-8ef3-4a00-bc06-3165aeff1dbd",resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.CANTON, resultItem1.getType());
        assertEquals(LocalDate.of(2016, 1, 1), resultItem1.getDateCreation());
        assertEquals(LocalDate.of(2020, 3, 7), resultItem1.getDateSuppression());
        assertEquals("Bellegarde-sur-Valserine", resultItem1.getIntitule());
        assertEquals("Bellegarde-sur-Valserine", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
    }

//    geo/canton/0101/precedents?date=1945-01-01  renvoie 404
    @Test
    void should_return_404_when_CantonCodePrecedents_code0101_date19450101() throws Exception{
        mockMvc.perform(get("/geo/canton/0101/precedents")
                        .param("date", "1945-01-01"))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///                   geo/canton/{code}/projetes                 ///
    ////////////////////////////////////////////////////////////////////

//    geo/canton/0103/projetes?date=2025-09-04&dateProjection=2020-01-01
    @Test
    void should_return_1_canton_when_CantonCodeProjetes_code0103_date20250904() {
        var response  = endpoints.getcogcanproj ("0103", LocalDate.of(2020,1,1), LocalDate.of(2025,9,4));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertAll(
                () -> assertEquals("0103", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/canton/849dca85-8ef3-4a00-bc06-3165aeff1dbd", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.CANTON, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateCreation()),
                () -> assertEquals(LocalDate.of(2020,3,7), resultItem1.getDateSuppression()),
                () -> assertEquals("Bellegarde-sur-Valserine", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Bellegarde-sur-Valserine", resultItem1.getIntitule())
        );
    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/canton/{code}/suivants                  ///
    ////////////////////////////////////////////////////////////////////

//    geo/canton/0103/suivants?date=2020-01-01
    @Test
    void should_return_1_canton_when_CantonCodeSuivants_code0103_date20250904() {
        var response  = endpoints.getcogcansuiv ("0103", LocalDate.of(2020,1,1));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertAll(
                () -> assertEquals("0103", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/canton/51c5f329-4745-46c0-b0d6-14b0d7046582", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.CANTON, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2020,3,7), resultItem1.getDateCreation()),
                () -> assertEquals("Valserhône", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Valserhône", resultItem1.getIntitule())
        );
    }

//    geo/canton/0103/suivants?date=2025-09-004 : return 404
    @Test
    void should_return_404_when_CantonCodeSuivants_code0101_date20250904() throws Exception{
        mockMvc.perform(get("/geo/canton/0101/suivants")
                        .param("date", "2025-09-04"))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/canton/{code}/intersections            ///
    ////////////////////////////////////////////////////////////////////

// geo/canton/0101/intersections?date=2025-09-04
    @Test
    void should_return_19_territoires_when_CantonCodeIntersections_code0101_date20250904(){
        var response  = endpoints.getcogcanintersect ("0101", LocalDate.of(2025,9,4), null);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(19, result.size()),
                () -> assertEquals("01", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/departement/84680e6f-2e99-44c9-a9ba-2e96a2ae48b7", resultItem1.getUri()),
                () -> assertEquals(TerritoireBaseRelation.TypeEnum.DEPARTEMENT, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1967,12,31), resultItem1.getDateCreation()),
                () -> assertEquals("Ain", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireBaseRelation.TypeArticleEnum._5, resultItem1.getTypeArticle()),
                () -> assertEquals("Ain", resultItem1.getIntitule()),
                () -> assertEquals("inclus", resultItem1.getRelation())
        );
    }

    @Test
    void should_return_18_communes_when_CantonCodeIntersections_code0101_date20250904_typeCommune(){
        var response  = endpoints.getcogcanintersect ("0101", LocalDate.of(2025,9,4), TypeEnum.COMMUNE);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        var resultItem2= result.get(1);

        assertAll(
                () -> Assertions.assertEquals(18, result.size()),

                () -> assertEquals("01002", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/43018c68-c278-433a-b285-3531e8d5347e", resultItem1.getUri()),
                () -> assertEquals(TerritoireBaseRelation.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Abergement-de-Varey", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireBaseRelation.TypeArticleEnum._5, resultItem1.getTypeArticle()),
                () -> assertEquals("L'Abergement-de-Varey", resultItem1.getIntitule()),
                () -> assertEquals("contient", resultItem1.getRelation()),

                () -> assertEquals("01004", resultItem2.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/9957029c-4f49-4183-8c94-f6001a6e5a92", resultItem2.getUri()),
                () -> assertEquals(TerritoireBaseRelation.TypeEnum.COMMUNE, resultItem2.getType()),
                () -> assertEquals(LocalDate.of(1955,3,31), resultItem2.getDateCreation()),
                () -> assertEquals("Ambérieu-en-Bugey", resultItem2.getIntituleSansArticle()),
                () -> assertEquals(TerritoireBaseRelation.TypeArticleEnum._1, resultItem2.getTypeArticle()),
                () -> assertEquals("Ambérieu-en-Bugey", resultItem2.getIntitule()),
                () -> assertEquals("contient", resultItem2.getRelation())
        );
    }

}

