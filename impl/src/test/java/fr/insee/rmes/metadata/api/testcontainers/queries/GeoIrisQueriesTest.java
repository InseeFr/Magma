package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoIntercommunaliteEndpoints;
import fr.insee.rmes.metadata.api.GeoIrisEndpoints;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class GeoIrisQueriesTest  extends TestcontainerTest {

    @Autowired
    GeoIrisEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

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
