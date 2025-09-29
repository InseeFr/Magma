package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoAireDAttractionDesVillesEndpoints;
import fr.insee.rmes.metadata.model.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")


class GeoAireDAttractionDesVillesQueriesTest extends TestcontainerTest{

    @Autowired
    GeoAireDAttractionDesVillesEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////
    ///        geo/aireDAttractionDesVilles2020/{code}    ///
    /////////////////////////////////////////////////////////

    //    geo/aireDAttractionDesVilles2020/062?date=2025-09-04
    @Test
    void should_return_1_aireDAttractionDesVilles_when_aireDAttractionDesVilles2020Code_code062_date20250904(){
        var response  = endpoints.getcogaav("062", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertEquals("062", result.getCode());
        assertEquals("http://id.insee.fr/geo/aireDAttractionDesVilles2020/858ff6ab-fb4c-4a03-896c-18a20ed01a45", result.getUri());
        assertEquals(AireDAttractionDesVilles2020.TypeEnum.AIRE_D_ATTRACTION_DES_VILLES2020, result.getType());
        assertEquals(LocalDate.of(2020,1,1), result.getDateCreation());
        assertEquals("Angoulême", result.getIntituleSansArticle());
        assertEquals(AireDAttractionDesVilles2020.TypeArticleEnum._1, result.getTypeArticle());
        assertEquals("Angoulême", result.getIntitule());
    }


    //////////////////////////////////////////////////////////////////////////
    ///        geo/aireDAttractionDesVilles2020/{code}/descendants         ///
    //////////////////////////////////////////////////////////////////////////

    //    geo/aireDAttractionDesVilles2020/002/descendants?date=2025-09-04&type=COMMUNE
    @Test
    void should_return_397_terrioires_when_DepartementCodeDescendants_code002_date20250904_typeCommune(){
        var response  = endpoints.getcogaavdesc("002", LocalDate.of(2025, 9, 4), TypeEnumDescendantsAireDAttractionDesVilles.COMMUNE);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(397, result.size());
        assertEquals("01005", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/commune/041f652a-f819-4b83-bf9c-d704f1edfcb2", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType());
        assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation());
        assertEquals("Ambérieux-en-Dombes", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._1, resultItem1.getTypeArticle());
        assertEquals("Ambérieux-en-Dombes", resultItem1.getIntitule());
    }

    //    geo/aireDAttractionDesVilles2020/062/descendants?date=2025-09-04
    @Test
    void should_return_92_territoires_when_aireDAttractionDesVilles2020CodeDescendants_code002_date20250904_typeNull(){
        var response  = endpoints.getcogaavdesc("002", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(1010, result.size());
        assertEquals("69381", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/36940e94-b61c-4565-9ea1-ecbcd812bd7e", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT_MUNICIPAL, resultItem1.getType());
        assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation());
        assertEquals("Lyon 1er Arrondissement", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Lyon 1er Arrondissement", resultItem1.getIntitule());
    }


    /////////////////////////////////////////////////////////
    ///        geo/aireDAttractionDesVilles2020           ///
    /////////////////////////////////////////////////////////

    //    geo/aireDAttractionDesVilles2020?date=2025-09-04
    @Test
    void should_return_699_aireDAttractionDesVilles2020_when_aireDAttractionDesVilles2020_date20250904(){
        var response  = endpoints.getcogaavliste ("2025-09-04");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(699, result.size());
        assertEquals("001", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/aireDAttractionDesVilles2020/3a65c4b6-8157-48be-bead-b74066f8456a", resultItem1.getUri());
        assertEquals(AireDAttractionDesVilles2020.TypeEnum.AIRE_D_ATTRACTION_DES_VILLES2020, resultItem1.getType());
        assertEquals(LocalDate.of(2020,1,1), resultItem1.getDateCreation());
        assertEquals("Paris", resultItem1.getIntituleSansArticle());
        assertEquals(AireDAttractionDesVilles2020.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Paris", resultItem1.getIntitule());
    }

    //    geo/aireDAttractionDesVilles2020?date=*
    @Test
    void should_return_721_aireDAttractionDesVilles2020_when_aireDAttractionDesVilles2020_dateEtoile(){
        var response  = endpoints.getcogaavliste("*");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(721, result.size());
        assertEquals("001", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/aireDAttractionDesVilles2020/3a65c4b6-8157-48be-bead-b74066f8456a", resultItem1.getUri());
        assertEquals(AireDAttractionDesVilles2020.TypeEnum.AIRE_D_ATTRACTION_DES_VILLES2020, resultItem1.getType());
        assertEquals(LocalDate.of(2020,1,1), resultItem1.getDateCreation());
        assertEquals("Paris", resultItem1.getIntituleSansArticle());
        assertEquals(AireDAttractionDesVilles2020.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Paris", resultItem1.getIntitule());
    }

}




