package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.GeoDistrictEndpoints;
import fr.insee.rmes.magma.diffusion.model.District;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsDistrict;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
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
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("984", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/collectiviteDOutreMer/f6496613-8f78-4184-80ab-81a077db6b37", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.COLLECTIVITE_D_OUTRE_MER, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2007,2,23), resultItem1.getDateCreation()),
                () -> assertEquals("Terres australes et antarctiques françaises", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._4, resultItem1.getTypeArticle()),
                () -> assertEquals("Terres australes et antarctiques françaises", resultItem1.getIntitule())
        );
    }

//    geo/district/98411/ascendants?date=2025-09-04&type=CollectiviteDOutreMer
    @Test
    void should_return_1_arrondissement_when_CommuneDelegueeCodeAscendants_code46248_date20250904_typeCollectiviteDoutreMer(){
        var response  = endpoints.getcogdisasc("98411", LocalDate.of(2025, 9, 4), TypeEnumAscendantsDistrict.COLLECTIVITE_D_OUTRE_MER);
        var result = response.getBody();
        assertNotNull(result);
        var resultItem1= result.getFirst();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("984", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/collectiviteDOutreMer/f6496613-8f78-4184-80ab-81a077db6b37", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.COLLECTIVITE_D_OUTRE_MER, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2007,2,23), resultItem1.getDateCreation()),
                () -> assertEquals("Terres australes et antarctiques françaises", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(TerritoireTousAttributs.TypeArticleEnum._4, resultItem1.getTypeArticle()),
                () -> assertEquals("Terres australes et antarctiques françaises", resultItem1.getIntitule())
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
        assertNotNull(result);
        assertAll(
                () -> assertEquals("98411", result.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/district/d028b78a-9c4d-4e22-9b60-efffd7085eb0", result.getUri()),
                () -> assertEquals(District.TypeEnum.DISTRICT, result.getType()),
                () -> assertEquals(LocalDate.of(2007,2,23), result.getDateCreation()),
                () -> assertEquals("Îles Saint-Paul et Amsterdam", result.getIntituleSansArticle()),
                () -> assertEquals(District.TypeArticleEnum._4, result.getTypeArticle()),
                () -> assertEquals("Îles Saint-Paul et Amsterdam", result.getIntitule())
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
