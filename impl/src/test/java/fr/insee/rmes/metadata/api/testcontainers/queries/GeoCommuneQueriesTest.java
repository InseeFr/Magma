package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoCommuneEndpoints;
import fr.insee.rmes.metadata.model.*;
import org.junit.Assert;
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


public class GeoCommuneQueriesTest extends TestcontainerTest {

    @Autowired
    GeoCommuneEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///                geo/commune/{code}/ascendants                  ///
    /////////////////////////////////////////////////////////////////////

//    geo/commune/14475/ascendants?date=2025-09-04 renvoie 9 territoires
    @Test
    void should_return_9_territoires_when_CommuneCodeAscendants_code14475_date20250904_typeNull(){
        var response  = endpoints.getcogcomasc("14475", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(9, result.size());
        Assert.assertEquals("024", resultItem1.getCode());
        Assert.assertEquals("http://id.insee.fr/geo/aireDAttractionDesVilles2020/9d05148e-a733-4bc4-9223-e8a27618c7c0", resultItem1.getUri());
        Assert.assertEquals(TerritoireTousAttributs.TypeEnum.AIRE_D_ATTRACTION_DES_VILLES2020, resultItem1.getType());
        Assert.assertEquals(LocalDate.of(2020,1,1), resultItem1.getDateCreation());
        Assert.assertEquals("Caen", resultItem1.getIntituleSansArticle());
        Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, resultItem1.getTypeArticle());
        Assert.assertEquals("Caen", resultItem1.getIntitule());
    }


//    geo/commune/14475/ascendants?date=1950-01-01&type=departement renvoie le département 14 avec 9 champs
    @Test
    void should_return_1_departement_when_CommuneCodeAscendants_code14475_date20250904_typeDepartement(){
        var response  = endpoints.getcogcomasc("14475", LocalDate.of(2025, 9, 4), TypeEnumAscendantsCommune.DEPARTEMENT);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        Assert.assertEquals("14", resultItem1.getCode());
        Assert.assertEquals("http://id.insee.fr/geo/departement/b01dce92-b91f-4648-80e7-536bd1823c2c", resultItem1.getUri());
        Assert.assertEquals(TerritoireTousAttributs.TypeEnum.DEPARTEMENT, resultItem1.getType());
        Assert.assertEquals(LocalDate.of(2018,1,1), resultItem1.getDateCreation());
        Assert.assertEquals("Calvados", resultItem1.getIntituleSansArticle());
        Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._2_ARTICLE_LE_CHARNIERE_DU_, resultItem1.getTypeArticle());
        Assert.assertEquals("14118", resultItem1.getChefLieu());
        Assert.assertEquals("Calvados", resultItem1.getIntitule());
    }


//    geo/commune/14475/ascendants?date=1950-01-01&type=Arrondissement renvoie 404
    @Test
    void should_return_404_when_CommuneCodeAscendants_code14475_date20250904_typeArrondissement() throws Exception{
        mockMvc.perform(get("/geo/commune/14475/ascendants")
                        .param("date", "1950-01-01")
                        .param("type", String.valueOf(TypeEnumAscendantsCommune.ARRONDISSEMENT)))
                .andExpect(status().isNotFound());
    }



    /////////////////////////////////////////////////////////////////////
    ///                geo/commune/{code}                             ///
    /////////////////////////////////////////////////////////////////////

//    geo/commune/69385?date=2025-09-04
    @Test
    void should_return_communeCode_14475_when_code14475_date20250904() {
        var response  = endpoints.getcogcom("14475", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertAll(
                () -> Assert.assertEquals("14475", result.getCode()),
                () -> Assert.assertEquals("http://id.insee.fr/geo/commune/4b88116a-9ede-42f5-aef5-a70304de593b", result.getUri()),
                () -> Assert.assertEquals(Commune.TypeEnum.COMMUNE, result.getType()),
                () -> Assert.assertEquals(LocalDate.of(2017,1,1), result.getDateCreation()),
                () -> Assert.assertEquals("Val d'Arry", result.getIntituleSansArticle()),
                () -> Assert.assertEquals(Commune.TypeArticleEnum._0_CHARNIERE_DE_, result.getTypeArticle()),
                () -> Assert.assertEquals("Val d'Arry", result.getIntitule())
        );
    }

    //    geo/commune/69392?date=2025-09-04 renvoie 404
    @Test
    void should_return_404_when_CommuneCode_code69392_date20250904() throws Exception{
        mockMvc.perform(get("/geo/commune/69392")
                        .param("date", "2025-09-01"))
                .andExpect(status().isNotFound());
    }


    ////////////////////////////////////////////////////////////////////
    ///                  geo/communes/descendants                    ///
    ////////////////////////////////////////////////////////////////////

//    geo/commune/14475/descendants?date=2025-09-04 renvoie 4 communes déléguées
    @Test
    void should_return_4_communesDeleguees_when_CommuneCodeDescendants_code14475_date20250904_typeNull() {
        var response  = endpoints.getcogcomdesc("14475", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(4, result.size());
        assertAll(
                () -> Assert.assertEquals("14373", resultItem1.getCode()),
                () -> Assert.assertEquals("http://id.insee.fr/geo/communeDeleguee/33afe07c-f132-4cdd-a188-4500c6928e62", resultItem1.getUri()),
                () -> Assert.assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE_DELEGUEE, resultItem1.getType()),
                () -> Assert.assertEquals(LocalDate.of(2017,1,1), resultItem1.getDateCreation()),
                () -> Assert.assertEquals("Locheur", resultItem1.getIntituleSansArticle()),
                () -> Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._2_ARTICLE_LE_CHARNIERE_DU_, resultItem1.getTypeArticle()),
                () -> Assert.assertEquals("Le Locheur", resultItem1.getIntitule())
        );

    }

    @Test
    void should_return_16_arrondissementsMunicipaux_when_CommuneCodeDescendants_code13055_date20250904_typeArrondissementMunicipal() {
        var response  = endpoints.getcogcomdesc("13055", LocalDate.of(2025, 9, 4), TypeEnumDescendantsCommune.ARRONDISSEMENT_MUNICIPAL);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(16, result.size());
        assertAll(
                () -> Assert.assertEquals("13201", resultItem1.getCode()),
                () -> Assert.assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/d2ae811d-f0b8-4bac-972d-01dabe292665", resultItem1.getUri()),
                () -> Assert.assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT_MUNICIPAL, resultItem1.getType()),
                () -> Assert.assertEquals(LocalDate.of(1946,10,18), resultItem1.getDateCreation()),
                () -> Assert.assertEquals("Marseille 1er Arrondissement", resultItem1.getIntituleSansArticle()),
                () -> Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, resultItem1.getTypeArticle()),
                () -> Assert.assertEquals("Marseille 1er Arrondissement", resultItem1.getIntitule())
        );
    }

//    geo/commune/14475/descendants?date=2025-09-04&type=iris renvoie 404
    @Test
    void should_return_404_when_CommuneCodeDescendants_code14475_date20250904_typeIris() throws Exception{
        mockMvc.perform(get("/geo/commune/14475/descendants")
                        .param("date", "2025-09-01")
                        .param("type", String.valueOf(TypeEnumDescendantsCommune.IRIS)))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/communes                                ///
    ////////////////////////////////////////////////////////////////////

//    geo/communes?date=2025-09-04&filtreNom=Bonnay&com=false//
    @Test
    void should_return_3_communes_when_Communes_date20250904_filtreNomBonnay_comFalse() {
        var response  = endpoints.getcogcomliste("2025-09-04", "Bonnay", false);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(3, result.size());
        assertAll(
                () -> Assert.assertEquals("25073", resultItem1.getCode()),
                () -> Assert.assertEquals("http://id.insee.fr/geo/commune/2ac33139-2a97-4b09-87b3-263cbf14c0b6", resultItem1.getUri()),
                () -> Assert.assertEquals(TerritoireBase.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> Assert.assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> Assert.assertEquals("Bonnay", resultItem1.getIntituleSansArticle()),
                () -> Assert.assertEquals(TerritoireBase.TypeArticleEnum._0_CHARNIERE_DE_, resultItem1.getTypeArticle()),
                () -> Assert.assertEquals("Bonnay", resultItem1.getIntitule())
        );
    }

//    geo/communes?date=*
    @Test
    void should_return_43905_communes_when_Communes_dateEtoile(){
        var response  = endpoints.getcogcomliste ("*", null,  null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(43905, result.size());
        assertAll(
                () -> Assert.assertEquals("01001", resultItem1.getCode()),
                () -> Assert.assertEquals("http://id.insee.fr/geo/commune/166857ef-114f-4067-9d3d-f712562850c5", resultItem1.getUri()),
                () -> Assert.assertEquals(TerritoireBase.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> Assert.assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> Assert.assertEquals("Abergement-Clémenciat", resultItem1.getIntituleSansArticle()),
                () -> Assert.assertEquals(TerritoireBase.TypeArticleEnum._5_ARTICLE_L_CHARNIERE_DE_L_, resultItem1.getTypeArticle()),
                () -> Assert.assertEquals("L'Abergement-Clémenciat", resultItem1.getIntitule())
        );
    }

    ////////////////////////////////////////////////////////////////////
    ///                     geo/commune/{code}/descendants           ///
    ////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////
    ///                geo/commune/{code}/precedents                 ///
    ////////////////////////////////////////////////////////////////////

//    geo/commune/69385/precedents renvoie l’arrondissement municipal de Lyon 5e

//    geo/commune/69385/precedents?date=1945-01-01  renvoie 404
//    @Test
//    void should_return_404_when_communeCodePrecedents_code69385_date19450101() throws Exception{
//        mockMvc.perform(get("/geo/commune/69385/precedents")
//                        .param("date", "1945-01-01"))
//                .andExpect(status().isNotFound());
//    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/commune/{code}/projetes                 ///
    ////////////////////////////////////////////////////////////////////

//    geo/commune/69385/projetes?date=1960-01-01&dateProjection=2011-12-31: renvoie les arrondissements de Lyon 5e et Lyon 9e

    ////////////////////////////////////////////////////////////////////
    ///                  geo/commune/{code}/suivants                 ///
    ////////////////////////////////////////////////////////////////////

//    geo/commune/69385/suivants?date=1960-01-01 renvoie 2 arrondissements municipaux


}
