package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoZoneDEmploiEndpoints;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.model.TypeEnumDescendantsZoneDEmploi;
import fr.insee.rmes.metadata.model.ZoneDEmploi2020;
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

public class GeoZoneDEmploiQueriesTest extends TestcontainerTest{

    @Autowired
    GeoZoneDEmploiEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///                geo/zoneDEmploi2020/{code}                    ///
    /////////////////////////////////////////////////////////////////////

//    geo/zoneDEmploi2020/01121?date=2025-09-04
    @Test
    void should_return_uniteUrbaine2415_when_zoneDEmploi2020Code2415_date20250904() {
        var response  = endpoints.getcogze("2415", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertAll(
                () -> assertEquals("2415", result.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/zoneDEmploi2020/dbab03e7-3d8d-4797-8ab1-0ed36a4db9c1", result.getUri()),
                () -> assertEquals(ZoneDEmploi2020.TypeEnum.ZONE_D_EMPLOI2020, result.getType()),
                () -> assertEquals(LocalDate.of(2020,1,1), result.getDateCreation()),
                () -> assertEquals("Vierzon", result.getIntituleSansArticle()),
                () -> assertEquals(ZoneDEmploi2020.TypeArticleEnum._0, result.getTypeArticle()),
                () -> assertEquals("Vierzon", result.getIntitule())
        );
    }

    /////////////////////////////////////////////////////////////////////
    ///                geo/zoneDEmploi2020/{code}/descendants        ///
    /////////////////////////////////////////////////////////////////////


//   geo/zoneDEmploi2020/2415/descendants?date=2025-09-04
    @Test
    void should_return_41_territoires_when_zoneDEmploi2020CodeDescendants_code2415_date20250904_typeNull(){
        var response  = endpoints.getcogzedesc("2415", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(41, result.size()),
                () -> assertEquals("18036", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/9d090028-9065-4c75-bb2b-6ea30d430af7", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Brinay", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Brinay", resultItem1.getIntitule())
        );
    }

    //   geo/zoneDEmploi2020/2415/descendants?date=2025-09-04=Commune
    @Test
    void should_return_28_communes_when_zoneDEmploi2020CodeDescendants_code01121_date20250904_typeCommune(){
        var response  = endpoints.getcogzedesc("2415", LocalDate.of(2025, 9, 4), TypeEnumDescendantsZoneDEmploi.COMMUNE);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(28, result.size()),
                () -> assertEquals("18036", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/9d090028-9065-4c75-bb2b-6ea30d430af7", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Brinay", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Brinay", resultItem1.getIntitule())
        );
    }


    //   geo/zoneDEmploi2020/2415/descendants?date=2025-09-04&type=ArrondissementMunicipal : renvoie 404
    @Test
    void should_return_404_when_zoneDEmploi2020CodeDescendants_code2415_date20250904_typeArrondissementMunicipal() throws Exception{
        mockMvc.perform(get("/geo/zoneDEmploi2020/2415/descendants")
                        .param("date", "2025-09-04")
                        .param("type", "ArrondissementMunicipal"))
                .andExpect(status().isNotFound());
    }


    /////////////////////////////////////////////////////////////////////
    ///                geo/zoneDEmploi2020                         ///
    /////////////////////////////////////////////////////////////////////

//   geo/zoneDEmploi2020?date=2025-09-04
    @Test
    void should_return_306_zonesDEmploi_when_ZonesDEmploi2020_date20250904(){
        var response  = endpoints.getcogzeliste("2025-09-04");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(306, result.size()),
                () -> assertEquals("0051", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/zoneDEmploi2020/e6911283-05f4-4997-916a-cc2b58c1e013", resultItem1.getUri()),
                () -> assertEquals(ZoneDEmploi2020.TypeEnum.ZONE_D_EMPLOI2020, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2025,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Alençon", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(ZoneDEmploi2020.TypeArticleEnum._1, resultItem1.getTypeArticle()),
                () -> assertEquals("Alençon", resultItem1.getIntitule())
        );
    }
    //  geo/unitesUrbaines2020?date=*
    @Test
    void should_return_332_zonesDEmploi_when_ZonesDEmploi2020_dateEtoile(){
        var response  = endpoints.getcogzeliste("*");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(332, result.size()),
                () -> assertEquals("0051", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/zoneDEmploi2020/1d5754fc-cb01-47cf-a9a8-f16f30110d9a", resultItem1.getUri()),
                () -> assertEquals(ZoneDEmploi2020.TypeEnum.ZONE_D_EMPLOI2020, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2020,1,1), resultItem1.getDateCreation()),
                () -> assertEquals(LocalDate.of(2025,1,1), resultItem1.getDateSuppression()),
                () -> assertEquals("Alençon", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(ZoneDEmploi2020.TypeArticleEnum._1, resultItem1.getTypeArticle()),
                () -> assertEquals("Alençon", resultItem1.getIntitule())
        );
    }


}
