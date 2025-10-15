package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoCommuneEndpoints;
import fr.insee.rmes.metadata.model.*;
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
        Assertions.assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(9, result.size());
        assertEquals("024", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/aireDAttractionDesVilles2020/9d05148e-a733-4bc4-9223-e8a27618c7c0", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.AIRE_D_ATTRACTION_DES_VILLES2020, resultItem1.getType());
        assertEquals(LocalDate.of(2020,1,1), resultItem1.getDateCreation());
        assertEquals("Caen", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Caen", resultItem1.getIntitule());
    }


//    geo/commune/14475/ascendants?date=1950-01-01&type=departement renvoie le département 14 avec 9 champs
    @Test
    void should_return_1_departement_when_CommuneCodeAscendants_code14475_date20250904_typeDepartement(){
        var response  = endpoints.getcogcomasc("14475", LocalDate.of(2025, 9, 4), TypeEnumAscendantsCommune.DEPARTEMENT);
        var result = response.getBody();
        Assertions.assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertEquals("14", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/departement/b01dce92-b91f-4648-80e7-536bd1823c2c", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.DEPARTEMENT, resultItem1.getType());
        assertEquals(LocalDate.of(2018,1,1), resultItem1.getDateCreation());
        assertEquals("Calvados", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._2, resultItem1.getTypeArticle());
        assertEquals("14118", resultItem1.getChefLieu());
        assertEquals("Calvados", resultItem1.getIntitule());
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
        assertNotNull(result);
        assertAll(
                () -> assertEquals("14475", result.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/4b88116a-9ede-42f5-aef5-a70304de593b", result.getUri()),
                () -> assertEquals(Commune.TypeEnum.COMMUNE, result.getType()),
                () -> assertEquals(LocalDate.of(2017,1,1), result.getDateCreation()),
                () -> assertEquals("Val d'Arry", result.getIntituleSansArticle()),
                () -> assertEquals(Commune.TypeArticleEnum._0, result.getTypeArticle()),
                () -> assertEquals("Val d'Arry", result.getIntitule())
        );
    }

    //    geo/commune/69392?date=2025-09-04 renvoie 404
    @Test
    void should_return_404_when_CommuneCode_code69392_date20250904() throws Exception{
        mockMvc.perform(get("/geo/commune/69392")
                        .param("date", "2025-09-01"))
                .andExpect(status().isNotFound());
    }


    /////////////////////////////////////////////////////////////////////
    ///                geo/commune/{code}/cantons                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/commune/14475/cantons?date=2025-09-04
    @Test
    void should_return_1_canton_when_CommuneCodeCantons_code14475_date20250904() {
        var response  = endpoints.getcogcomcan("14475", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertAll(
                () -> assertEquals("1401", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/canton/25982682-5635-40ad-8040-09110edb43e1", resultItem1.getUri()),
                () -> assertEquals(Canton.TypeEnum.CANTON, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2021,2,26), resultItem1.getDateCreation()),
                () -> assertEquals("Monts d’Aunay", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(Canton.TypeArticleEnum._4, resultItem1.getTypeArticle()),
                () -> assertEquals("14027", resultItem1.getChefLieu()),
                () -> assertEquals("Les Monts d’Aunay", resultItem1.getIntitule())
        );

    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/communes/descendants                    ///
    ////////////////////////////////////////////////////////////////////

//    geo/commune/14475/descendants?date=2025-09-04 renvoie 4 communes déléguées
    @Test
    void should_return_4_communesDeleguees_when_CommuneCodeDescendants_code14475_date20250904_typeNull() {
        var response  = endpoints.getcogcomdesc("14475", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(4, result.size());
        assertAll(
                () -> assertEquals("14373", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/communeDeleguee/33afe07c-f132-4cdd-a188-4500c6928e62", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE_DELEGUEE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2017,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Locheur", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._2, resultItem1.getTypeArticle()),
                () -> assertEquals("Le Locheur", resultItem1.getIntitule())
        );

    }

    @Test
    void should_return_16_arrondissementsMunicipaux_when_CommuneCodeDescendants_code13055_date20250904_typeArrondissementMunicipal() {
        var response  = endpoints.getcogcomdesc("13055", LocalDate.of(2025, 9, 4), TypeEnumDescendantsCommune.ARRONDISSEMENT_MUNICIPAL);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(16, result.size());
        assertAll(
                () -> assertEquals("13201", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/arrondissementMunicipal/d2ae811d-f0b8-4bac-972d-01dabe292665", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT_MUNICIPAL, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1946,10,18), resultItem1.getDateCreation()),
                () -> assertEquals("Marseille 1er Arrondissement", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Marseille 1er Arrondissement", resultItem1.getIntitule())
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
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(3, result.size());
        assertAll(
                () -> assertEquals("25073", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/2ac33139-2a97-4b09-87b3-263cbf14c0b6", resultItem1.getUri()),
                () -> assertEquals(TerritoireBase.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Bonnay", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireBase.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Bonnay", resultItem1.getIntitule())
        );
    }

//    geo/communes?date=*
    @Test
    void should_return_43905_communes_when_Communes_dateEtoile(){
        var response  = endpoints.getcogcomliste ("*", null,  null);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(43905, result.size());
        assertAll(
                () -> assertEquals("01001", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/166857ef-114f-4067-9d3d-f712562850c5", resultItem1.getUri()),
                () -> assertEquals(TerritoireBase.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Abergement-Clémenciat", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireBase.TypeArticleEnum._5, resultItem1.getTypeArticle()),
                () -> assertEquals("L'Abergement-Clémenciat", resultItem1.getIntitule())
        );
    }


    ////////////////////////////////////////////////////////////////////
    ///                geo/commune/{code}/precedents                 ///
    ////////////////////////////////////////////////////////////////////

//    geo/commune/14475/precedents?date=2025-09-04
    @Test
    void should_return_3_communes_when_CommunesCodePrecedents_date20250904(){
        var response  = endpoints.getcogcomprec ("14475", LocalDate.of(2025,9,4));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(3, result.size()),
                () -> assertEquals("14373", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/9b9298ba-d31f-4570-82f1-ad821704a413", resultItem1.getUri()),
                () -> assertEquals(TerritoireBase.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals(LocalDate.of(2017,1,1), resultItem1.getDateSuppression()),
                () -> assertEquals("Locheur", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireBase.TypeArticleEnum._2, resultItem1.getTypeArticle()),
                () -> assertEquals("Le Locheur", resultItem1.getIntitule())
        );
    }

//    geo/commune/14475/precedents?date=1945-01-01  renvoie 404
    @Test
    void should_return_404_when_communeCodePrecedents_code14475_date19450101() throws Exception{
        mockMvc.perform(get("/geo/commune/14475/precedents")
                        .param("date", "1945-01-01"))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/commune/{code}/projetes                 ///
    ////////////////////////////////////////////////////////////////////

//    geo/commune/14475/projetes?date=2025-09-04&dateProjection=1945-06-26
    @Test
    void should_return_4_communes_when_CommunesCodeProjetes_date20250904_datePorjection19450626(){
        var response  = endpoints.getcogcomproj ("14475", LocalDate.of(1945,6,26), LocalDate.of(2025,9,4));
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(4, result.size()),
                () -> assertEquals("14373", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/9b9298ba-d31f-4570-82f1-ad821704a413", resultItem1.getUri()),
                () -> assertEquals(TerritoireBase.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals(LocalDate.of(2017,1,1), resultItem1.getDateSuppression()),
                () -> assertEquals("Locheur", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireBase.TypeArticleEnum._2, resultItem1.getTypeArticle()),
                () -> assertEquals("Le Locheur", resultItem1.getIntitule())
        );
    }

//    geo/commune/14475/projetes?date=2025-09-01
    @Test
    void should_return_400_when_CommuneCodeProjetes_dateProjectionNull() throws Exception{
        mockMvc.perform(get("/geo/commune/14475/projetes")
                        .param("date", "2025-09-01"))
                .andExpect(status().isBadRequest());
    }

//    geo/commune/14475/projetes?date=2025-09-01&dateProjection=
    @Test
    public void should_return_400_when_CommuneCodeProjetes_dateProjectionEmpty() throws Exception {
        mockMvc.perform(get("/geo/commune/14475/projetes")
                        .param("dateProjection", "")  // Valeur vide
                        .param("date", "2025-09-01"))
                .andExpect(status().isBadRequest());
    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/commune/{code}/suivants                 ///
    ////////////////////////////////////////////////////////////////////

//    geo/commune/14475/suivants?date=1945-06-26 renvoie 1 commune
    @Test
    void should_return_1_commune_when_CommunesCodeSuivants_date19450626(){
        var response  = endpoints.getcogcomsuiv ("14475", LocalDate.of(1945,6,26));
        var result = response.getBody();
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("14475", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/commune/c2e2d19d-1ace-4cdc-b80d-c37a1aa59d1e", resultItem1.getUri()),
                () -> assertEquals(TerritoireBase.TypeEnum.COMMUNE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1958,10,13), resultItem1.getDateCreation()),
                () -> assertEquals(LocalDate.of(2016,1,1), resultItem1.getDateSuppression()),
                () -> assertEquals("Noyers-Bocage", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireBase.TypeArticleEnum._0, resultItem1.getTypeArticle()),
                () -> assertEquals("Noyers-Bocage", resultItem1.getIntitule())
        );
    }

}
