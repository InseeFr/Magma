package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoCommuneDelegueeEndpoints;
import fr.insee.rmes.metadata.api.GeoDistrictEndpoints;
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

public class GeoDistrictQueriesTest extends TestcontainerTest {

    @Autowired
    GeoDistrictEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///               geo/district/{code}/ascendants                  ///
    /////////////////////////////////////////////////////////////////////

//    geo/district/98411/ascendants?date=2025-09-04
    @Test
    void should_return_1_COM_when_CommuneDelegueeCodeAscendants_code46248_date20250904(){
        var response  = endpoints.getcogdisasc("98411", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> Assert.assertEquals("984", resultItem1.getCode()),
                () -> Assert.assertEquals("http://id.insee.fr/geo/collectiviteDOutreMer/f6496613-8f78-4184-80ab-81a077db6b37", resultItem1.getUri()),
                () -> Assert.assertEquals(TerritoireTousAttributs.TypeEnum.COLLECTIVITE_D_OUTRE_MER, resultItem1.getType()),
                () -> Assert.assertEquals(LocalDate.of(2007,2,23), resultItem1.getDateCreation()),
                () -> Assert.assertEquals("Terres australes et antarctiques françaises", resultItem1.getIntituleSansArticle()),
                () -> Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._4_ARTICLE_LES_CHARNIERE_DES_, resultItem1.getTypeArticle()),
                () -> Assert.assertEquals("Terres australes et antarctiques françaises", resultItem1.getIntitule())
        );
    }

//    geo/district/98411/ascendants?date=2025-09-04&type=CollectiviteDOutreMer
    @Test
    void should_return_1_arrondissement_when_CommuneDelegueeCodeAscendants_code46248_date20250904_typeCollectiviteDoutreMer(){
        var response  = endpoints.getcogdisasc("98411", LocalDate.of(2025, 9, 4), TypeEnumAscendantsDistrict.COLLECTIVITE_D_OUTRE_MER);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> Assert.assertEquals("984", resultItem1.getCode()),
                () -> Assert.assertEquals("http://id.insee.fr/geo/collectiviteDOutreMer/f6496613-8f78-4184-80ab-81a077db6b37", resultItem1.getUri()),
                () -> Assert.assertEquals(TerritoireTousAttributs.TypeEnum.COLLECTIVITE_D_OUTRE_MER, resultItem1.getType()),
                () -> Assert.assertEquals(LocalDate.of(2007,2,23), resultItem1.getDateCreation()),
                () -> Assert.assertEquals("Terres australes et antarctiques françaises", resultItem1.getIntituleSansArticle()),
                () -> Assert.assertEquals(TerritoireTousAttributs.TypeArticleEnum._4_ARTICLE_LES_CHARNIERE_DES_, resultItem1.getTypeArticle()),
                () -> Assert.assertEquals("Terres australes et antarctiques françaises", resultItem1.getIntitule())
        );
    }

    /////////////////////////////////////////////////////////////////////
    ///                       geo/district/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//geo/district/98411?date=2025-09-04
    @Test
    void should_return_communeCode_98411_when_code98411_date20250904() {
        var response  = endpoints.getcogdis("98411", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertAll(
                () -> Assert.assertEquals("98411", result.getCode()),
                () -> Assert.assertEquals("http://id.insee.fr/geo/district/d028b78a-9c4d-4e22-9b60-efffd7085eb0", result.getUri()),
                () -> Assert.assertEquals(District.TypeEnum.DISTRICT, result.getType()),
                () -> Assert.assertEquals(LocalDate.of(2007,2,23), result.getDateCreation()),
                () -> Assert.assertEquals("Îles Saint-Paul et Amsterdam", result.getIntituleSansArticle()),
                () -> Assert.assertEquals(District.TypeArticleEnum._4_ARTICLE_LES_CHARNIERE_DES_, result.getTypeArticle()),
                () -> Assert.assertEquals("Îles Saint-Paul et Amsterdam", result.getIntitule())
        );
    }

//geo/district/98410?date=2025-09-04 renvoie 4044

    @Test
    void should_return_404_when_DistrictCode_code98410_date20250904() throws Exception{
        mockMvc.perform(get("/geo/district/98410")
                        .param("date", "2025-09-01"))
                .andExpect(status().isNotFound());
    }
}
