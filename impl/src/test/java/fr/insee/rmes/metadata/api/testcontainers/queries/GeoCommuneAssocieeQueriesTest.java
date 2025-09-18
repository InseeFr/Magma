package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoCommuneAssocieeEndpoints;
import fr.insee.rmes.metadata.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
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


public class GeoCommuneAssocieeQueriesTest extends TestcontainerTest {
    
        @Autowired
        GeoCommuneAssocieeEndpoints endpoints;
        @Autowired
        private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///        geo/communeAssociee/{code}/ascendants        ///
    /////////////////////////////////////////////////////////////////////

    //   geo/communeAssociee/59355/ascendants?date=2025-09-04 : renvoie 10 territoires
    @Test
    void should_return_10_territoires_when_CommuneAssocieesCodeAscendants_code59355_date20250904_typeNull(){
        var response  = endpoints.getcogcomaasc("59355", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(10, result.size()),
                () -> Assert.assertEquals("004", resultItem1.getCode()),
                () -> Assert.assertEquals("http://id.insee.fr/geo/aireDAttractionDesVilles2020/83109274-b915-41be-a7b6-0e09a0a625c1", resultItem1.getUri()),
                () -> Assert.assertEquals(TerritoireTousAttributs.TypeEnum.AIRE_D_ATTRACTION_DES_VILLES2020, resultItem1.getType()),
                () -> Assert.assertEquals(LocalDate.of(2020,1,1), resultItem1.getDateCreation()),
                () -> Assert.assertEquals("Lille (partie française)", resultItem1.getIntituleSansArticle()),
                () -> Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, resultItem1.getTypeArticle()),
                () -> Assert.assertEquals("Lille (partie française)", resultItem1.getIntitule())
        );
    }


//   geo/communeAssociee/59355/ascendants?date=2025-09-04&type=Departement : renvoie 1 territoire
@Test
void should_return_1_departement_when_CommuneAssocieesCodeAscendants_code59355_date20250904_typeDepartement(){
    var response  = endpoints.getcogcomaasc("59355", LocalDate.of(2025, 9, 4), TypeEnumAscendantsCommuneAssociee.DEPARTEMENT);
    var result = response.getBody();
    var resultItem1= result.getFirst();
    assertAll(
            () -> assertEquals(1, result.size()),
            () -> Assert.assertEquals("59", resultItem1.getCode()),
            () -> Assert.assertEquals("http://id.insee.fr/geo/departement/1cabcdea-d4dc-4df1-96c2-ed1db0fa594c", resultItem1.getUri()),
            () -> Assert.assertEquals(TerritoireTousAttributs.TypeEnum.DEPARTEMENT, resultItem1.getType()),
            () -> Assert.assertEquals(LocalDate.of(1973,9,1), resultItem1.getDateCreation()),
            () -> Assert.assertEquals("Nord", resultItem1.getIntituleSansArticle()),
            () -> Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._2_ARTICLE_LE_CHARNIERE_DU_, resultItem1.getTypeArticle()),
            () -> Assert.assertEquals("59350", resultItem1.getChefLieu()),
            () -> Assert.assertEquals("Nord", resultItem1.getIntitule())
    );
}


        /////////////////////////////////////////////////////////////////////
        ///        geo/communeAssociee/{code}                     ///
        /////////////////////////////////////////////////////////////////////

//    geo/communeAssociee/59355?date=2025-09-04
        @Test
        void should_return_communeCode_59355_when_code59355_date20250904() {
            var response  = endpoints.getcogcoma("59355", LocalDate.of(2025, 9, 4));
            var result = response.getBody();
            assertAll(
                    () -> {
                        Assertions.assertNotNull(result);
                        Assert.assertEquals("59355", result.getCode());
                    },
                    () -> Assert.assertEquals("http://id.insee.fr/geo/communeAssociee/84564ad2-a211-4744-9e01-1bb1900e2e68", result.getUri()),
                    () -> Assert.assertEquals(CommuneAssociee.TypeEnum.COMMUNE_ASSOCIEE, result.getType()),
                    () -> Assert.assertEquals(LocalDate.of(2000,2,27), result.getDateCreation()),
                    () -> Assert.assertEquals("Lomme", result.getIntituleSansArticle()),
                    () -> Assert.assertEquals(CommuneAssociee.TypeArticleEnum._0_CHARNIERE_DE_, result.getTypeArticle()),
                    () -> Assert.assertEquals("Lomme", result.getIntitule())
            );
        }


//    geo/communeAssociee/69392?date=2025-09-04 renvoie 404
    @Test
    void should_return_404_when_CommuneAssocieeCode_code69392_date20250904() throws Exception{
        mockMvc.perform(get("/geo/communeAssociee/69392")
                        .param("date", "2025-09-01"))
                .andExpect(status().isNotFound());
    }



        ////////////////////////////////////////////////////////////////////
        ///        geo/communesAssociees                         ///
        ////////////////////////////////////////////////////////////////////

//    geo/communesAssociees?date=2025-09-04//
        @Test
        void should_return_475_communesAssociees_when_CommunesAssociees_date20250904() {
            var response  = endpoints.getcogcomaliste("2025-09-04");
            var result = response.getBody();
            var resultItem1= result.getFirst();

            assertAll(
                    () -> assertEquals(475, result.size()),
                    () -> Assert.assertEquals("01120", resultItem1.getCode()),
                    () -> Assert.assertEquals("http://id.insee.fr/geo/communeAssociee/2c8fa8e3-dc3b-4b8a-907a-82e4d07bce2c", resultItem1.getUri()),
                    () -> Assert.assertEquals(CommuneAssociee.TypeEnum.COMMUNE_ASSOCIEE, resultItem1.getType()),
                    () -> Assert.assertEquals(LocalDate.of(1973,1,1), resultItem1.getDateCreation()),
                    () -> Assert.assertEquals("Cordieux", resultItem1.getIntituleSansArticle()),
                    () -> Assert.assertEquals(CommuneAssociee.TypeArticleEnum._0_CHARNIERE_DE_, resultItem1.getTypeArticle()),
                    () -> Assert.assertEquals("Cordieux", resultItem1.getIntitule())
            );
        }


//    geo/communesAssociees?date=*
    @Test
    void should_return_1046_communesAssociees_when_CommunesAssociees_dateEtoile(){
        var response  = endpoints.getcogcomaliste ("*");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(1046, result.size());
        assertAll(
                () -> Assert.assertEquals("01003", resultItem1.getCode()),
                () -> Assert.assertEquals("http://id.insee.fr/geo/communeAssociee/b86253e9-d7b0-46cf-81ae-e940ad457a85", resultItem1.getUri()),
                () -> Assert.assertEquals(CommuneAssociee.TypeEnum.COMMUNE_ASSOCIEE, resultItem1.getType()),
                () -> Assert.assertEquals(LocalDate.of(1974,1,1), resultItem1.getDateCreation()),
                () -> Assert.assertEquals(LocalDate.of(1983,1,1), resultItem1.getDateSuppression()),
                () -> Assert.assertEquals("Amareins", resultItem1.getIntituleSansArticle()),
                () -> Assert.assertEquals(CommuneAssociee.TypeArticleEnum._1_CHARNIERE_D_, resultItem1.getTypeArticle()),
                () -> Assert.assertEquals("Amareins", resultItem1.getIntitule())
        );
    }

}
