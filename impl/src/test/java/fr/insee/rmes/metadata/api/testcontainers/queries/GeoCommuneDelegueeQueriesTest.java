package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoCommuneDelegueeEndpoints;
import fr.insee.rmes.metadata.model.*;
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


public class GeoCommuneDelegueeQueriesTest extends TestcontainerTest {

    @Autowired
    GeoCommuneDelegueeEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///        geo/communeDeleguee/{code}/ascendants                  ///
    /////////////////////////////////////////////////////////////////////

//    geo/communeDeleguee/46248/ascendants?date=2025-09-04
    @Test
    void should_return_10_territoires_when_CommuneDelegueeCodeAscendants_code46248_date20250904_typeNull(){
        var response  = endpoints.getcogcomdasc("46248", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(10, result.size()),
                () -> assertEquals("153", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/aireDAttractionDesVilles2020/51453875-c332-4dc8-907d-ad950b5b7a7e", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.AIRE_D_ATTRACTION_DES_VILLES2020, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2020,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Cahors", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Cahors", resultItem1.getIntitule())
        );
    }

//    geo/communeDeleguee/46248/ascendants?date=2025-09-04&type=Arrondissement
    @Test
    void should_return_1_arrondissement_when_CommuneDelegueeCodeAscendants_code46248_date20250904_typeArrondissement(){
        var response  = endpoints.getcogcomdasc("46248", LocalDate.of(2025, 9, 4), TypeEnumAscendantsCommuneDeleguee.ARRONDISSEMENT);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("461", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/arrondissement/35fe4c0f-c974-483e-b204-7b266bb7876a", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2017,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Cahors", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("46042", resultItem1.getChefLieu()),
                () -> assertEquals("Cahors", resultItem1.getIntitule())
        );
    }

    @Test
    void should_return_404_when_CommuneDelegueeCodeAscendants_code46248_date19500101_typeNull() throws Exception{
        mockMvc.perform(get("/geo/communeDeleguee/46248/ascendants")
                        .param("date", "1950-01-01"))
                .andExpect(status().isNotFound());
    }

    /////////////////////////////////////////////////////////////////////
    ///                geo/communeDeleguee/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/communeDeleguee/46248?date=2025-09-04
    @Test
    void should_return_communeCode_14475_when_code14475_date20250904() {
        var response  = endpoints.getcogcomd("46248", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertAll(
                () -> assertEquals("46248", result.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/communeDeleguee/c333331f-b09b-4253-b012-dc0d0a65a290", result.getUri()),
                () -> assertEquals(CommuneDeleguee.TypeEnum.COMMUNE_DELEGUEE, result.getType()),
                () -> assertEquals(LocalDate.of(2017,1,1), result.getDateCreation()),
                () -> assertEquals("Sainte-Alauzie", result.getIntituleSansArticle()),
                () -> assertEquals(CommuneDeleguee.TypeArticleEnum._0, result.getTypeArticle()),
                () -> assertEquals("Sainte-Alauzie", result.getIntitule())
        );
    }

//    geo/communeDeleguee/46249?date=2025-09-04 renvoie 404
    @Test
    void should_return_404_when_CommuneDelegueeCode_code46249_date20250904() throws Exception{
        mockMvc.perform(get("/geo/communeDeleguee/46249")
                        .param("date", "2025-09-01"))
                .andExpect(status().isNotFound());
    }


    ////////////////////////////////////////////////////////////////////
    ///        geo/communesDeleguees                         ///
    ////////////////////////////////////////////////////////////////////

//    geo/communesDeleguees?date=2025-09-04//
    @Test
    void should_return_2152_communesDeleguees_when_CommunesDeleguees_date20250904() {
        var response  = endpoints.getcogcomdliste("2025-09-04");
        var result = response.getBody();
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(2152, result.size()),
                () -> assertEquals("01015", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/communeDeleguee/3c07001c-7efe-40f9-90fc-6a892af20238", resultItem1.getUri()),
                () -> assertEquals(CommuneDeleguee.TypeEnum.COMMUNE_DELEGUEE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Arbignieu", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(CommuneDeleguee.TypeArticleEnum._1, resultItem1.getTypeArticle()),
                () -> assertEquals("Arbignieu", resultItem1.getIntitule())
        );
    }

//    geo/communesDeleguees?date=*
    @Test
    void should_return_2599_communesDeleguees_when_CommunesDeleguees_dateEtoile() {
        var response  = endpoints.getcogcomdliste("*");
        var result = response.getBody();
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(2599, result.size()),
                () -> assertEquals("01015", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/communeDeleguee/3c07001c-7efe-40f9-90fc-6a892af20238", resultItem1.getUri()),
                () -> assertEquals(CommuneDeleguee.TypeEnum.COMMUNE_DELEGUEE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Arbignieu", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(CommuneDeleguee.TypeArticleEnum._1, resultItem1.getTypeArticle()),
                () -> assertEquals("Arbignieu", resultItem1.getIntitule())
        );
    }



}
