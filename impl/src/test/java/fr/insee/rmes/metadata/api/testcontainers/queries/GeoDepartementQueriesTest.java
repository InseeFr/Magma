package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoDepartementEndpoints;
import fr.insee.rmes.metadata.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Tag("integration")
class GeoDepartementQueriesTest extends TestcontainerTest{

    @Autowired
    GeoDepartementEndpoints endpoints;


    @Test
    void should_return_1_region_when_ascendantsDepartement_code22_typeNull(){
        var response  = endpoints.getcogdepasc("22", LocalDate.of(2025, 9, 4), null);
        assertEquals(1, response.getBody().size());
        Assert.assertEquals("53", response.getBody().getFirst().getCode());
        Assert.assertEquals("http://id.insee.fr/geo/region/6c83500c-454c-4d69-aec5-b988fb6f6f1c", response.getBody().getFirst().getUri());
        Assert.assertEquals(TerritoireTousAttributs.TypeEnum.REGION, response.getBody().getFirst().getType());
        Assert.assertEquals(LocalDate.of(2019,1,1), response.getBody().getFirst().getDateCreation());
        Assert.assertEquals("Bretagne", response.getBody().getFirst().getIntituleSansArticle());
        Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, response.getBody().getFirst().getTypeArticle());
        Assert.assertEquals("Bretagne", response.getBody().getFirst().getIntitule());
    }

    @Test
    void should_return_1_region_when_ascendantsDepartement_code22_date20250904_typeRegion(){
        var response  = endpoints.getcogdepasc("22", LocalDate.of(2025, 9, 4), TypeEnumAscendantsDepartement.REGION);
        assertEquals(1, response.getBody().size());
        Assert.assertEquals("53", response.getBody().getFirst().getCode());
        Assert.assertEquals("http://id.insee.fr/geo/region/6c83500c-454c-4d69-aec5-b988fb6f6f1c", response.getBody().getFirst().getUri());
        Assert.assertEquals(TerritoireTousAttributs.TypeEnum.REGION, response.getBody().getFirst().getType());
        Assert.assertEquals(LocalDate.of(2019,1,1), response.getBody().getFirst().getDateCreation());
        Assert.assertEquals("Bretagne", response.getBody().getFirst().getIntituleSansArticle());
        Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, response.getBody().getFirst().getTypeArticle());
        Assert.assertEquals("Bretagne", response.getBody().getFirst().getIntitule());
    }


    @Test
    void should_return_departement_22_when_code22_date20250904() {
        var response  = endpoints.getcogdep("22", LocalDate.of(2025, 9, 4));
        Assert.assertEquals("22", response.getBody().getCode());
        Assert.assertEquals("http://id.insee.fr/geo/departement/f07f6a49-9dce-4f2d-a99e-5d61eedf2827", response.getBody().getUri());
        Assert.assertEquals(Departement.TypeEnum.DEPARTEMENT, response.getBody().getType());
        Assert.assertEquals(LocalDate.of(1990,3,8), response.getBody().getDateCreation());
        Assert.assertEquals("Côtes-d'Armor", response.getBody().getIntituleSansArticle());
        Assert.assertEquals(Departement.TypeArticleEnum._4_ARTICLE_LES_CHARNIERE_DES_, response.getBody().getTypeArticle());
        Assert.assertEquals("22278", response.getBody().getChefLieu());
        Assert.assertEquals("Côtes-d'Armor", response.getBody().getIntitule());


    }

    @Test
    void should_return_1_commune_when_descendantsDepartement_code45_date20250904_typeCommune_filtreNomOrleans(){
        var response  = endpoints.getcogdepdesc("45", LocalDate.of(2025, 9, 4), TypeEnumDescendantsDepartement.COMMUNE,"Orleans");
        assertEquals(1, response.getBody().size());
        Assert.assertEquals("45234", response.getBody().getFirst().getCode());
        Assert.assertEquals("http://id.insee.fr/geo/commune/4180d005-bb9a-4271-ae5c-dc6b46b874da", response.getBody().getFirst().getUri());
        Assert.assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, response.getBody().getFirst().getType());
        Assert.assertEquals(LocalDate.of(1943,1,1), response.getBody().getFirst().getDateCreation());
        Assert.assertEquals("Orléans", response.getBody().getFirst().getIntituleSansArticle());
        Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._1_CHARNIERE_D_, response.getBody().getFirst().getTypeArticle());
        Assert.assertEquals("Orléans", response.getBody().getFirst().getIntitule());
    }

    @Test
    void should_return_523_territoires_when_descendantsDepartement_code45_date20250904_typeNull_filtreNomNull(){
        var response  = endpoints.getcogdepdesc("45", LocalDate.of(2025, 9, 4), null,null);
        assertEquals(523, response.getBody().size());
        Assert.assertEquals("451", response.getBody().getFirst().getCode());
        Assert.assertEquals("http://id.insee.fr/geo/arrondissement/a9f9ff71-7658-4ef0-98b0-f048c8831be1", response.getBody().getFirst().getUri());
        Assert.assertEquals(TerritoireTousAttributs.TypeEnum.ARRONDISSEMENT, response.getBody().getFirst().getType());
        Assert.assertEquals(LocalDate.of(2017,1,1), response.getBody().getFirst().getDateCreation());
        Assert.assertEquals("Montargis", response.getBody().getFirst().getIntituleSansArticle());
        Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_, response.getBody().getFirst().getTypeArticle());
        Assert.assertEquals("Montargis", response.getBody().getFirst().getIntitule());
    }


    @Test
    void should_return_101_departements_when_Departements_date20250904(){
        var response  = endpoints.getcogdepts ("2025-09-04");
        assertEquals(101, response.getBody().size());
        Assert.assertEquals("01", response.getBody().getFirst().getCode());
        Assert.assertEquals("http://id.insee.fr/geo/departement/84680e6f-2e99-44c9-a9ba-2e96a2ae48b7", response.getBody().getFirst().getUri());
        Assert.assertEquals(TerritoireBaseChefLieu.TypeEnum.DEPARTEMENT, response.getBody().getFirst().getType());
        Assert.assertEquals(LocalDate.of(1967,12,31), response.getBody().getFirst().getDateCreation());
        Assert.assertEquals("Ain", response.getBody().getFirst().getIntituleSansArticle());
        Assert.assertEquals(TerritoireBaseChefLieu.TypeArticleEnum._5_ARTICLE_L_CHARNIERE_DE_L_, response.getBody().getFirst().getTypeArticle());
        Assert.assertEquals("01053", response.getBody().getFirst().getChefLieu());
        Assert.assertEquals("Ain", response.getBody().getFirst().getIntitule());
    }

    @Test
    void should_return_154_departements_when_Departements_dateEtoile(){
        var response  = endpoints.getcogdepts ("*");
        assertEquals(154, response.getBody().size());
        Assert.assertEquals("01", response.getBody().getFirst().getCode());
        Assert.assertEquals("http://id.insee.fr/geo/departement/69043f56-a413-47c8-804e-9d9ac0c0b67c", response.getBody().getFirst().getUri());
        Assert.assertEquals(TerritoireBaseChefLieu.TypeEnum.DEPARTEMENT, response.getBody().getFirst().getType());
        Assert.assertEquals(LocalDate.of(1943,1,1), response.getBody().getFirst().getDateCreation());
        Assert.assertEquals(LocalDate.of(1967,12,31), response.getBody().getFirst().getDateSuppression());
        Assert.assertEquals("Ain", response.getBody().getFirst().getIntituleSansArticle());
        Assert.assertEquals(TerritoireBaseChefLieu.TypeArticleEnum._5_ARTICLE_L_CHARNIERE_DE_L_, response.getBody().getFirst().getTypeArticle());
        Assert.assertEquals("01053", response.getBody().getFirst().getChefLieu());
        Assert.assertEquals("Ain", response.getBody().getFirst().getIntitule());
    }
}



