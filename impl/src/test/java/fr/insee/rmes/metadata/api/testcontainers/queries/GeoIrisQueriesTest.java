package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoIntercommunaliteEndpoints;
import fr.insee.rmes.metadata.api.GeoIrisEndpoints;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.model.TypeEnumAscendantsIris;
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

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class GeoIrisQueriesTest  extends TestcontainerTest {

    @Autowired
    GeoIrisEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    ////////////////////////////////////////////////////////////////////
    ///                         geo/iris/ascendants                  ///
    ////////////////////////////////////////////////////////////////////

//    geo/iris/010040101/ascendants?date=2025-09-04 (real Iris)
    @Test
    void should_return_11_territoires_when_IrisCodeAscendants_Code010040101_date20250904_typeNull(){
        var response  = endpoints.getcogirisasc ("010040101", LocalDate.of(2025,9,4), null);
        var result = response.getBody();
        Assertions.assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(11, result.size()),
                () -> assertEquals("243", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/aireDAttractionDesVilles2020/4af81671-0c2f-4547-a213-dff3f13531e2", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.AIRE_D_ATTRACTION_DES_VILLES2020, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2020,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Ambérieu-en-Bugey", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._1 , resultItem1.getTypeArticle()),
                () -> assertEquals("Ambérieu-en-Bugey", resultItem1.getIntitule())

        );
    }

//    geo/iris/010040101/ascendants?date=2025-09-04 (real Iris)
    @Test
    void should_return_1_arrondissement_when_IrisCodeAscendants_Code010040101_date20250904_typeArrondissement(){
        var response  = endpoints.getcogirisasc ("010040101", LocalDate.of(2025,9,4), TypeEnumAscendantsIris.ARRONDISSEMENT);
        var result = response.getBody();
        Assertions.assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("011", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/arrondissement/cc3aee67-96dc-4e9a-ae4e-26860a90e0d5", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2017,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Belley", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0 , resultItem1.getTypeArticle()),
                () -> assertEquals("01034" , resultItem1.getChefLieu()),
                () -> assertEquals("Belley", resultItem1.getIntitule())

        );
    }

//    geo/iris/010050000/ascendants?date=2025-09-04 (= false-Iris = non irised commune)
    @Test
    void should_return_10_territoires_when_IrisCodeAscendants_Code010050000_date20250904_typeNull(){
        var response  = endpoints.getcogirisasc ("010050000", LocalDate.of(2025,9,4), null);
        var result = response.getBody();
        Assertions.assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(10, result.size()),
                () -> assertEquals("002", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/aireDAttractionDesVilles2020/7f3934bb-4333-40bf-9753-875b0ecb8829", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.AIRE_D_ATTRACTION_DES_VILLES2020, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2020,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Lyon", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0 , resultItem1.getTypeArticle()),
                () -> assertEquals("Lyon", resultItem1.getIntitule())

        );
    }

//    geo/iris/010050000/ascendants?date=2025-09-04?type=arrondissement (false-Iris)
    @Test
    void should_return_1_arrondissement_when_IrisCodeAscendants_Code010050000_date20250904_typeArrondissement(){
        var response  = endpoints.getcogirisasc ("010050000", LocalDate.of(2025,9,4), TypeEnumAscendantsIris.ARRONDISSEMENT);
        var result = response.getBody();
        Assertions.assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("012", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/arrondissement/34ccfd7d-aeeb-4c1d-ae46-c989a82d05b0", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2017,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Bourg-en-Bresse", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0 , resultItem1.getTypeArticle()),
                () -> assertEquals("Bourg-en-Bresse", resultItem1.getIntitule())

        );
    }

    ////////////////////////////////////////////////////////////////////
    ///                         geo/iris                             ///
    ////////////////////////////////////////////////////////////////////

//    geo/iris?date=2025-09-04
    @Test
    void should_return_49343_territoires_when_Iris_date20250904(){
        var response  = endpoints.getcogirislist (LocalDate.of(2025,9,4), null);
        var result = response.getBody();
        Assertions.assertNotNull(result);
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
        Assertions.assertNotNull(result);
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
