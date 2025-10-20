package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.GeoIrisEndpoints;
import fr.insee.rmes.magma.diffusion.model.Iris;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class GeoIrisQueriesTest extends TestcontainerTest {

    @Autowired
    GeoIrisEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    ////////////////////////////////////////////////////////////////////
    ///                         geo/iris/{code}                      ///
    ////////////////////////////////////////////////////////////////////

//  geo/iris/010040101?date=2025-09-04 (hasIrisDescendant = true, does not end with 0000)
    @Test
    void should_return_irisCode_010040101_when_code010040101_date20250904() {
        var response  = endpoints.getcogiris("010040101", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertAll(
                () -> assertEquals("010040101", result.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/iris/b8c772de-9551-4f13-81c5-eca5bb0f2f7d", result.getUri()),
                () -> assertEquals(Iris.TypeEnum.IRIS, result.getType()),
                () -> assertEquals(LocalDate.of(2008,1,1), result.getDateCreation()),
                () -> assertEquals("H", result.getTypeDIris()),
                () -> assertEquals("Pérouses-Triangle d'Activités", result.getIntituleSansArticle()),
                () -> assertEquals(Iris.TypeArticleEnum._4, result.getTypeArticle()),
                () -> assertEquals("Les Pérouses-Triangle d'Activités", result.getIntitule())
        );
    }

//  geo/iris/010020000?date=2025-09-04 (hasIrisDescendant = false, ends with 0000)
    @Test
    void should_return_irisCode_010020000_when_code010020000_date20250904() {
        var response  = endpoints.getcogiris("010020000", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertAll(
                () -> assertEquals("010020000", result.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/43018c68-c278-433a-b285-3531e8d5347e", result.getUri()),
                () -> assertEquals(Iris.TypeEnum.COMMUNE, result.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), result.getDateCreation()),
                () -> assertEquals("Abergement-de-Varey", result.getIntituleSansArticle()),
                () -> assertEquals(Iris.TypeArticleEnum._5, result.getTypeArticle()),
                () -> assertEquals("L'Abergement-de-Varey", result.getIntitule())
        );
    }

//  geo/iris/010040000?date=2025-09-04 (hasIrisDescendant = true, ends with 0000)
    @Test
    void should_return_404_when_code010040000_date20250904() throws Exception {
        mockMvc.perform(get("/geo/iris/010040000")
                        .param("date", "2025-09-01"))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///                         geo/iris                             ///
    ////////////////////////////////////////////////////////////////////

//    geo/iris?date=2025-09-04
    @Test
    void should_return_49343_territoires_when_Iris_date20250904(){
        var response  = endpoints.getcogirislist (LocalDate.of(2025,9,4), null);
        var result = response.getBody();
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(49343, result.size()),
                () -> assertEquals("010010000", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/166857ef-114f-4067-9d3d-f712562850c5", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Abergement-Clémenciat", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._5 , resultItem1.getTypeArticle()),
                () -> assertEquals("L'Abergement-Clémenciat", resultItem1.getIntitule())

        );
    }

//    geo/iris?date=2025-09-04&com=true
    @Test
    void should_return_49444_territoires_when_Iris_date20250904_comTrue(){
        var response  = endpoints.getcogirislist (LocalDate.of(2025,9,4), true);
        var result = response.getBody();
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(49444, result.size()),
                () -> assertEquals("010010000", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/166857ef-114f-4067-9d3d-f712562850c5", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Abergement-Clémenciat", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._5 , resultItem1.getTypeArticle()),
                () -> assertEquals("L'Abergement-Clémenciat", resultItem1.getIntitule())

        );
    }
}
