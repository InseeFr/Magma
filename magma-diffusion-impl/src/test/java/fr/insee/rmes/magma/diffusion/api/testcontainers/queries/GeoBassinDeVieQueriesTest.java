package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.GeoBassinDeVieEndpoints;
import fr.insee.rmes.magma.diffusion.model.BassinDeVie2022;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsBassinDeVie;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class GeoBassinDeVieQueriesTest extends TestcontainerTest{

    @Autowired
    GeoBassinDeVieEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;



    /////////////////////////////////////////////////////////////////////
    ///                geo/bassinDeVie2022/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/bassinDeVie2022/01004?date=2025-09-04
    @Test
    void should_return_BassinDeVie2022Code_01004_when_code01004_date20250904() {
        var response = endpoints.getcogbass("01004", LocalDate.of(2025, 9, 4));
        var result = response.getBody();

        assertEquals("01004", result.getCode());
        assertEquals("http://id.insee.fr/geo/bassinDeVie2022/0e5bcc78-f043-404d-92af-d3d660772675", result.getUri());
        assertEquals(BassinDeVie2022.TypeEnum.BASSIN_DE_VIE2022, result.getType());
        assertEquals(LocalDate.of(2022, 1, 1), result.getDateCreation());
        assertEquals("Ambérieu-en-Bugey", result.getIntituleSansArticle());
        assertEquals(BassinDeVie2022.TypeArticleEnum._1, result.getTypeArticle());
        assertEquals("Ambérieu-en-Bugey", result.getIntitule());
    }

    //    geo/bassinDeVie2022/01001?date=2025-09-04 renvoie 404
    @Test
    void should_return_404_when_BassinDeVie2022Code_code01001_date20250904() throws Exception{
        mockMvc.perform(get("/geo/bassinDeVie20222/01001")
                        .param("date", "2025-09-01"))
                .andExpect(status().isNotFound());
    }

    /////////////////////////////////////////////////////////////////////
    ///               geo/bassinDeVie2022/{code}/descendants          ///
    /////////////////////////////////////////////////////////////////////


    //    geo/bassinDeVie2022/35176/descendants?date=2025-09-04&type=Commune
    @Test
    void should_return_2_communes_when_BassinDeVie2022CodeDescendants_code35176_date20250904_typeCommune(){
        var response  = endpoints.getcogbassdes("35176", LocalDate.of(2025, 9, 4), TypeEnumDescendantsBassinDeVie.COMMUNE);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(2, result.size());
        assertEquals("35155", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/commune/4df5a6eb-4ced-4e81-9953-42ff31f073f9", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType());
        assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation());
        assertEquals("Lohéac", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Lohéac", resultItem1.getIntitule());
    }

    //    geo/departement/22/descendants?date=2025-09-04
    @Test
    void should_return_2_communes_when_BassinDeVie2022CodeDescendants_code35176_date20250904(){
        var response  = endpoints.getcogbassdes("35176", LocalDate.of(2025, 9, 4), TypeEnumDescendantsBassinDeVie.COMMUNE);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(2, result.size());
        assertEquals("35155", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/commune/4df5a6eb-4ced-4e81-9953-42ff31f073f9", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType());
        assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation());
        assertEquals("Lohéac", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Lohéac", resultItem1.getIntitule());
    }



    ////////////////////////////////////////////////////////////////////
    ///                 geo/bassinsDeVie2022                         ///
    ////////////////////////////////////////////////////////////////////

//    geo/bassinsDeVie2022?date=2025-09-04&filtreNom=Ambérieu-en-Bugey//
    @Test
    void should_return_1_bassinDeVie2022_when_BassinsDeVie2022_date20250904_filtreNomAmberieuEnBugey() {
        var response = endpoints.getcogbassliste("2025-09-04","Amberieu-en-Bugey");
        var result = response.getBody();
        var resultItem1 = result.getFirst();

        // Vérification du nombre total de bassins de vie
        assertEquals(1, result.size()); // Remplacez 1254 par le nombre réel attendu

        assertEquals("01004", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/bassinDeVie2022/0e5bcc78-f043-404d-92af-d3d660772675", resultItem1.getUri());
        assertEquals(BassinDeVie2022.TypeEnum.BASSIN_DE_VIE2022, resultItem1.getType());
        assertEquals(LocalDate.of(2022, 1, 1), resultItem1.getDateCreation());
        assertEquals("Ambérieu-en-Bugey", resultItem1.getIntituleSansArticle());
        assertEquals(BassinDeVie2022.TypeArticleEnum._1, resultItem1.getTypeArticle());
        assertEquals("Ambérieu-en-Bugey", resultItem1.getIntitule());
    }

//    geo/bassinsDeVie2022?date=*
    @Test
    void should_return_1735_bassinDeVie2022_when_BassinsDeVie2022_date20250904_filtreNomNull() {
        var response = endpoints.getcogbassliste("*",null);
        var result = response.getBody();
        var resultItem1 = result.getFirst();

        // Vérification du nombre total de bassins de vie
        assertEquals(1735, result.size());

        assertEquals("01004", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/bassinDeVie2022/0e5bcc78-f043-404d-92af-d3d660772675", resultItem1.getUri());
        assertEquals(BassinDeVie2022.TypeEnum.BASSIN_DE_VIE2022, resultItem1.getType());
        assertEquals(LocalDate.of(2022, 1, 1), resultItem1.getDateCreation());
        assertEquals("Ambérieu-en-Bugey", resultItem1.getIntituleSansArticle());
        assertEquals(BassinDeVie2022.TypeArticleEnum._1, resultItem1.getTypeArticle());
        assertEquals("Ambérieu-en-Bugey", resultItem1.getIntitule());
    }
}
