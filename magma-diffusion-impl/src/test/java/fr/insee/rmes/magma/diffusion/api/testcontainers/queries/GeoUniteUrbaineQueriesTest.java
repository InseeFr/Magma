package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.GeoUniteUrbaineEndpoints;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsUniteUrbaine;
import fr.insee.rmes.magma.diffusion.model.UniteUrbaine2020;
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

public class GeoUniteUrbaineQueriesTest extends TestcontainerTest{

    @Autowired
    GeoUniteUrbaineEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///                geo/uniteUrbaine2020/{code}                    ///
    /////////////////////////////////////////////////////////////////////

//    geo/uniteUrbaine2020/01121?date=2025-09-04
    @Test
    void should_return_uniteUrbaine01121_when_UniteUrbaine2020Code01121_date20250904() {
        var response  = endpoints.getcoguu("01121", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertNotNull(result);
        assertAll(
                () -> assertEquals("01121", result.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/uniteUrbaine2020/57a37c9b-e426-430e-b8c1-ac0fd640e345", result.getUri()),
                () -> assertEquals(UniteUrbaine2020.TypeEnum.UNITE_URBAINE2020, result.getType()),
                () -> assertEquals(LocalDate.of(2020,1,1), result.getDateCreation()),
                () -> assertEquals("Jujurieux", result.getIntituleSansArticle()),
                () -> assertEquals(UniteUrbaine2020.TypeArticleEnum._0, result.getTypeArticle()),
                () -> assertEquals("Jujurieux", result.getIntitule())
        );
    }

    /////////////////////////////////////////////////////////////////////
    ///                geo/uniteUrbaine2020/{code}/descendants        ///
    /////////////////////////////////////////////////////////////////////


//   geo/uniteUrbaine2020/01121/descendants?date=2025-09-04 : renvoie 2 communes
    @Test
    void should_return_2_communes_when_UniteUrbaine2020CodeDescendants_code01121_date20250904_typeNull(){
        var response  = endpoints.getcoguudes("01121", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("01199", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/0d69a92e-70d3-4ebf-aa3b-db76e6bedf7e", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Jujurieux", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Jujurieux", resultItem1.getIntitule())
        );
    }

//   geo/uniteUrbaine2020/01121/descendants?date=2025-09-04&type=Commune : renvoie 2 communes
    @Test
    void should_return_2_communes_when_UniteUrbaine2020CodeDescendants_code01121_date20250904_typeCommune(){
        var response  = endpoints.getcoguudes("01121", LocalDate.of(2025, 9, 4), TypeEnumDescendantsUniteUrbaine.COMMUNE);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("01199", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/0d69a92e-70d3-4ebf-aa3b-db76e6bedf7e", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Jujurieux", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Jujurieux", resultItem1.getIntitule())
        );
    }


//   geo/uniteUrbaine2020/01121/descendants?date=2025-09-04&type=ArrondissementMunicipal : renvoie 404
    @Test
    void should_return_404_when_UniteUrbaine2020CodePrecedents_code01121_date20250904() throws Exception{
        mockMvc.perform(get("/geo/uniteUrbaine2020/01121/precedents")
                        .param("date", "2025-09-04")
                        .param("type", "ArrondissementMunicipal"))
                .andExpect(status().isNotFound());
    }


    /////////////////////////////////////////////////////////////////////
    ///                geo/unitesUrbaines2020                         ///
    /////////////////////////////////////////////////////////////////////

//   geo/unitesUrbaines2020?date=2025-09-04 : renvoie 30 territoires
    @Test
    void should_return_2472_unitesUrbaines_when_UnitesUrbaines2020_date20250904(){
        var response  = endpoints.getcoguuliste("2025-09-04");
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(2472, result.size()),
                () -> assertEquals("00151", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/uniteUrbaine2020/a7355a50-7516-4f7b-8919-a03bc28cd12b", resultItem1.getUri()),
                () -> assertEquals(UniteUrbaine2020.TypeEnum.UNITE_URBAINE2020, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2020,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Lécluse", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(UniteUrbaine2020.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Lécluse", resultItem1.getIntitule())
        );
    }
    //  geo/unitesUrbaines2020?date=*
    @Test
    void should_return_2496_unitesUrbaines_when_UnitesUrbaines2020_dateEtoile(){
        var response  = endpoints.getcoguuliste("*");
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(2496, result.size()),
                () -> assertEquals("00151", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/uniteUrbaine2020/a7355a50-7516-4f7b-8919-a03bc28cd12b", resultItem1.getUri()),
                () -> assertEquals(UniteUrbaine2020.TypeEnum.UNITE_URBAINE2020, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2020,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Lécluse", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(UniteUrbaine2020.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Lécluse", resultItem1.getIntitule())
        );
    }


}
