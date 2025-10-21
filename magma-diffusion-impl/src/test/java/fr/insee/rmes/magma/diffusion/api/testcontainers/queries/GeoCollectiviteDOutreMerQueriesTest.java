package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;


import fr.insee.rmes.magma.diffusion.api.GeoCollectiviteDOutreMerEndpoints;
import fr.insee.rmes.magma.diffusion.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;


@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class GeoCollectiviteDOutreMerQueriesTest extends TestcontainerTest{

    @Autowired
    GeoCollectiviteDOutreMerEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;



    /////////////////////////////////////////////////////////////////////
    ///        geo/collectiviteDOutreMer/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/collectiviteDOutreMer/69385?date=2025-09-04
    @Test
    void should_return_COMCode_988_when_code988_date20250904() {
        var response  = endpoints.getcogcoll("988", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        //utilisation de assertAll() pour que le test ne s'arrête pas à la première erreur du test
        assertAll(
                () -> assertEquals("988", result.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/collectiviteDOutreMer/bc93b612-59f5-463a-a05f-e5ed9013dc8d", result.getUri()),
                () -> assertEquals(CollectiviteDOutreMer.TypeEnum.COLLECTIVITE_D_OUTRE_MER, result.getType()),
                () -> assertEquals(LocalDate.of(1969,3,30), result.getDateCreation()),
                () -> assertEquals("Nouvelle-Calédonie", result.getIntituleSansArticle()),
                () -> assertEquals(CollectiviteDOutreMer.TypeArticleEnum._0, result.getTypeArticle()),
                () -> assertEquals("Nouvelle-Calédonie", result.getIntitule())
        );
    }


    /////////////////////////////////////////////////////////////////////
    ///        geo/collectiviteDOutreMer/{code}/descendants           ///
    /////////////////////////////////////////////////////////////////////

//    /geo/collectiviteDOutreMer/975/descendants?date=2025-09-04 renvoie 2 communes 2 iris
    @Test
    void should_return_2_communes_2_iris_when_COMCodeDescendants_code975_date20250904_typeNull_filtreNomNull(){
        var response  = endpoints.getcogcolldes("975", LocalDate.of(2025, 9, 4), null,null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(4, result.size());
        Assert.assertEquals("97501", resultItem1.getCode());
        Assert.assertEquals("http://id.insee.fr/geo/commune/8655edf3-9550-4486-8efa-03d97ebe6561", resultItem1.getUri());
        Assert.assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType());
        Assert.assertEquals(LocalDate.of(1976,7,21), resultItem1.getDateCreation());
        Assert.assertEquals("Miquelon-Langlade", resultItem1.getIntituleSansArticle());
        Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        Assert.assertEquals("Miquelon-Langlade", resultItem1.getIntitule());
    }

//    geo/collectiviteDOutreMer/975/descendants?date=2025-09-04&type=Commune&filtreNom=Miquelon
    @Test
    void should_return_1_commune_when_COMCodeDescendants_code975_date20250904_typeCommune_filtreNomMiquelon(){
        var response  = endpoints.getcogcolldes("975", LocalDate.of(2025, 9, 4), TypeEnumDescendantsCollectiviteDOutreMer.COMMUNE,"Miquelon");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        Assert.assertEquals("97501", resultItem1.getCode());
        Assert.assertEquals("http://id.insee.fr/geo/commune/8655edf3-9550-4486-8efa-03d97ebe6561", resultItem1.getUri());
        Assert.assertEquals(TerritoireTousAttributs.TypeEnum.COMMUNE, resultItem1.getType());
        Assert.assertEquals(LocalDate.of(1976,7,21), resultItem1.getDateCreation());
        Assert.assertEquals("Miquelon-Langlade", resultItem1.getIntituleSansArticle());
        Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        Assert.assertEquals("Miquelon-Langlade", resultItem1.getIntitule());
    }

    ////////////////////////////////////////////////////////////////////
    ///        geo/collectivitesDOutreMer                         ///
    ////////////////////////////////////////////////////////////////////

//    geo/collectivitesDOutreMer?date=2025-09-04//
    @Test
    void should_return_9_COM_when_CollectivitesDOutreMer_date20250904(){
        var response  = endpoints.getcogcollliste ("2025-09-04");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(9, result.size());
        Assert.assertEquals("975", resultItem1.getCode());
        Assert.assertEquals("http://id.insee.fr/geo/collectiviteDOutreMer/352968dd-fcc7-4950-8b71-8c94053cb126", resultItem1.getUri());
        Assert.assertEquals(CollectiviteDOutreMer.TypeEnum.COLLECTIVITE_D_OUTRE_MER, resultItem1.getType());
        Assert.assertEquals(LocalDate.of(1985,6,15), resultItem1.getDateCreation());
        Assert.assertEquals("Saint-Pierre-et-Miquelon", resultItem1.getIntituleSansArticle());
        Assert.assertEquals(CollectiviteDOutreMer.TypeArticleEnum._0, resultItem1.getTypeArticle());
        Assert.assertEquals("Saint-Pierre-et-Miquelon", resultItem1.getIntitule());
    }

//    geo/collectivitesDOutreMer?date=*
@Test
void should_return_67_COM_when_CollectivitesDOutreMer_dateEtoile(){
    var response  = endpoints.getcogcollliste ("*");
    var result = response.getBody();
    var resultItem1= result.getFirst();
    assertEquals(67, result.size());
    Assert.assertEquals("90bis", resultItem1.getCode());
    Assert.assertEquals("http://id.insee.fr/geo/collectiviteDOutreMer/163502e0-72fb-4dab-99d8-996a858733c9", resultItem1.getUri());
    Assert.assertEquals(CollectiviteDOutreMer.TypeEnum.COLLECTIVITE_D_OUTRE_MER, resultItem1.getType());
    Assert.assertEquals(LocalDate.of(1947,12,17), resultItem1.getDateCreation());
    Assert.assertEquals(LocalDate.of(1957,1,1), resultItem1.getDateSuppression());
    Assert.assertEquals("Sarre", resultItem1.getIntituleSansArticle());
    Assert.assertEquals(CollectiviteDOutreMer.TypeArticleEnum._3, resultItem1.getTypeArticle());
    Assert.assertEquals("Sarre", resultItem1.getIntitule());
}


}
