package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoIntercommunaliteEndpoints;
import fr.insee.rmes.metadata.model.Intercommunalite;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.model.TypeEnumDescendantsIntercommunalite;
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

public class GeoIntercommunaliteQueriesTest extends TestcontainerTest {

    @Autowired
    GeoIntercommunaliteEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    ////////////////////////////////////////////////////////////////////
    ///                geo/intercommunalite/{code}/precedents        ///
    ////////////////////////////////////////////////////////////////////

//    geo/intercommunalite/200046977/precedents?date=2025-09-04
    @Test
    void should_return_1_intercommunalite_when_intercommunaliteCodePrecedents_date20250904(){
        var response  = endpoints.getcogintercoprec ("200046977", LocalDate.of(2025,9,4));
        var result = response.getBody();
        Assertions.assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("246900245", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/intercommunalite/831d6a01-3f71-47a0-940b-04df167053c1", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.INTERCOMMUNALITE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2014,1,1), resultItem1.getDateCreation()),
                () -> assertEquals(LocalDate.of(2015,1,1), resultItem1.getDateSuppression()),
                () -> assertEquals("Lyon (Grand Lyon)", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0 , resultItem1.getTypeArticle()),
                () -> assertEquals("Communauté urbaine de Lyon (Grand Lyon)", resultItem1.getIntituleComplet()),
                () -> assertEquals("Communauté urbaine", resultItem1.getCategorieJuridique()),
                () -> assertEquals("Lyon (Grand Lyon)", resultItem1.getIntitule())

        );
    }


    ////////////////////////////////////////////////////////////////////
    ///                geo/intercommunalite/{code}                   ///
    ////////////////////////////////////////////////////////////////////

//    geo/intercommunalite/240100883?date=2025-09-04
    @Test
    void should_return_intercommunalite240100883_when_IntercommunaliteCode_code240100883_date20250904(){
        var response  = endpoints.getcoginterco ("240100883", LocalDate.of(2025,9,4));
        var result = response.getBody();
        assertNotNull(result);
        assertAll(
                () -> assertEquals("240100883", result.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/intercommunalite/5a238840-5cbd-469f-80c8-43713bf8e4a8", result.getUri()),
                () -> assertEquals(Intercommunalite.TypeEnum.INTERCOMMUNALITE, result.getType()),
                () -> assertEquals(LocalDate.of(2017,1,1), result.getDateCreation()),
                () -> assertEquals("Plaine de l'Ain", result.getIntituleSansArticle()),
                () -> assertEquals(Intercommunalite.TypeArticleEnum._3, result.getTypeArticle()),
                () -> assertEquals("Communauté de communes de La Plaine de l'Ain", result.getIntituleComplet()),
                () -> assertEquals("Communauté de communes", result.getCategorieJuridique()),
                () -> assertEquals("La Plaine de l'Ain", result.getIntitule())

        );
    }

    ////////////////////////////////////////////////////////////////////
    ///                geo/intercommunalite/{code}/descendants       ///
    ////////////////////////////////////////////////////////////////////

//    geo/intercommunalite/200000438/descendants?date=2025-09-04
    @Test
    void should_return_11_territoires_when_IntercommunaliteCodeDescendants_code200000438_date20250904_typeNull(){
        var response  = endpoints.getcogintercodes("200000438", LocalDate.of(2025,9,4), null);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(11, result.size());
        assertAll(
                () -> assertEquals("44050", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/b1b6ffdd-2493-485f-9da2-ff640677adc3", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Crossac", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Crossac", resultItem1.getIntitule())

        );
    }

    //    geo/intercommunalite/200000438/descendants?date=2025-09-04&type=IRIS
    @Test
    void should_return_2_territoires_when_IntercommunaliteCodeDescendants_code200000438_date20250904_typeIris(){
        var response  = endpoints.getcogintercodes("200000438", LocalDate.of(2025,9,4), TypeEnumDescendantsIntercommunalite.IRIS);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(2, result.size());
        assertAll(
                () -> assertEquals("441290101", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/iris/3394e3ae-bce6-44c4-ac19-190acdaa6a2d", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.IRIS, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2008,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("H", resultItem1.getTypeDIris()),
                () -> assertEquals("Agglomération", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum.X, resultItem1.getTypeArticle()),//en prod : X, mais la log veut 0
                () -> assertEquals("Agglomération", resultItem1.getIntitule())

        );
    }


    ////////////////////////////////////////////////////////////////////
    ///                  geo/intercommunalites                       ///
    ////////////////////////////////////////////////////////////////////

//    geo/intercommunalites?date=2025-09-04&filtreNom=Bonnay&com=false//
    @Test
    void should_return_1_intercommunalite_when_Intercommunalites_date20250904_filtreNomPlaineDeLAin() {
        var response  = endpoints.getcogintercoliste("2025-09-04", "Plaine de l'Ain");
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("240100883", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/intercommunalite/5a238840-5cbd-469f-80c8-43713bf8e4a8", resultItem1.getUri()),
                () -> assertEquals(Intercommunalite.TypeEnum.INTERCOMMUNALITE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2017,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Plaine de l'Ain", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(Intercommunalite.TypeArticleEnum._3, resultItem1.getTypeArticle()),
                () -> assertEquals("Communauté de communes de La Plaine de l'Ain", resultItem1.getIntituleComplet()),
                () -> assertEquals("Communauté de communes", resultItem1.getCategorieJuridique()),
                () -> assertEquals("La Plaine de l'Ain", resultItem1.getIntitule())
        );
    }

    //    geo/intercommunalites?date=*filtreNom=Bonnay&com=false//
    @Test
    void should_return_2_intercommunalite_when_Intercommunalites_dateEtoile_filtreNomPlaineDeLAin() {
        var response  = endpoints.getcogintercoliste("*", "Plaine de l'Ain");
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("240100883", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/intercommunalite/da6087eb-5061-4d99-82c4-03ae9ef5334f", resultItem1.getUri()),
                () -> assertEquals(Intercommunalite.TypeEnum.INTERCOMMUNALITE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2008,1,1), resultItem1.getDateCreation()),
                () -> assertEquals(LocalDate.of(2017,1,1), resultItem1.getDateSuppression()),
                () -> assertEquals("Plaine de l'Ain", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(Intercommunalite.TypeArticleEnum._3, resultItem1.getTypeArticle()),
                () -> assertEquals("Communauté de communes de La Plaine de l'Ain", resultItem1.getIntituleComplet()),
                () -> assertEquals("Communauté de communes", resultItem1.getCategorieJuridique()),
                () -> assertEquals("La Plaine de l'Ain", resultItem1.getIntitule())
        );
    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/intercommunalite/{code}/projetes        ///
    ////////////////////////////////////////////////////////////////////

//    geo/intercommunalite/200046977/projetes?date=2025-09-04&dateProjection=2013-01-01
    @Test
    void should_return_1_intercommunalite_when_IntercommunalitesCodeProjetes_date20250904_dateProjection20130101() {
        var response  = endpoints.getcogintercoproj("200046977", LocalDate.of(2013,1,1), LocalDate.of(2025,9,4));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("246900245", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/intercommunalite/3e9c401d-ba60-4640-97d4-a3c2f024c59d", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.INTERCOMMUNALITE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2011,1,1), resultItem1.getDateCreation()),
                () -> assertEquals(LocalDate.of(2014,1,1), resultItem1.getDateSuppression()),
                () -> assertEquals("Lyon (Grand Lyon)", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Communauté urbaine de Lyon (Grand Lyon)", resultItem1.getIntituleComplet()),
                () -> assertEquals("Communauté urbaine", resultItem1.getCategorieJuridique()),
                () -> assertEquals("Lyon (Grand Lyon)", resultItem1.getIntitule())
        );
    }

    //    geo/intercommunalite/200046977/projetes?date=2025-09-01
    @Test
    void should_return_400_when_IntercommunaliteCodeProjetes_dateProjectionNull() throws Exception{
        mockMvc.perform(get("/geo/intercommunalite/200046977/projetes")
                        .param("date", "2025-09-01"))
                .andExpect(status().isBadRequest());
    }

    //    geo/intercommunalite/200046977/projetes?date=2025-09-01&dateProjection=
    @Test
    public void should_return_400_when_IntercommunaliteCodeProjetes_dateProjectionEmpty() throws Exception {
        mockMvc.perform(get("/geo/intercommunalite/200046977/projetes")
                        .param("dateProjection", "")  // Valeur vide
                        .param("date", "2025-09-01"))
                .andExpect(status().isBadRequest());
    }


    ////////////////////////////////////////////////////////////////////
    ///                  geo/intercommunalite/{code}/suivants        ///
    ////////////////////////////////////////////////////////////////////

//    geo/intercommunalite/246900245/suivants?date=2025-09-04
    @Test
    void should_return_1_intercommunalite_when_IntercommunalitesCodeSuivants_Code246900245_date20140101() {
        var response  = endpoints.getcogintercosuiv("246900245", LocalDate.of(2014,1,1));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("200046977", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/intercommunalite/66a4c53d-5215-4258-b828-490773264671", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.INTERCOMMUNALITE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2015,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Métropole de Lyon", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum.Y, resultItem1.getTypeArticle()),
                () -> assertEquals("Métropole de Lyon", resultItem1.getIntituleComplet()),
                () -> assertEquals("(Autre) Collectivité territoriale", resultItem1.getCategorieJuridique()),
                () -> assertEquals("Métropole de Lyon", resultItem1.getIntitule())
        );
    }
}
