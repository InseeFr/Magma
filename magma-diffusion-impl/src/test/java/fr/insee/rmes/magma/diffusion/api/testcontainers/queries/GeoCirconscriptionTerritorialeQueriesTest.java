package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.GeoCirconscriptionTerritorialeEndpoints;
import fr.insee.rmes.magma.diffusion.model.CirconscriptionTerritoriale;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class GeoCirconscriptionTerritorialeQueriesTest extends TestcontainerTest {

    @Autowired
    GeoCirconscriptionTerritorialeEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///        geo/circonscriptionTerritoriale/{code}/ascendants          ///
    /////////////////////////////////////////////////////////////////////

//    geo/circonscriptionTerritoriale/98611/ascendants?date=2025-09-04 renvoie 11 ascendants

    @Test
    void should_return_1_circonscriptionTerritoriale_when_CirconscriptionTerritorialeCodeAscendants_code98611_date20250904_typeNull(){
        var response  = endpoints.getcogcirasc("98611", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        Assertions.assertNotNull(result);
        var resultItem1= result.getFirst();
        assertEquals(1, result.size());
        assertEquals("986", resultItem1.getCode());
        assertEquals("http://id.insee.fr/geo/collectiviteDOutreMer/ecfac5cd-a301-4dff-bed9-dfc54421bf6c", resultItem1.getUri());
        assertEquals(TerritoireTousAttributs.TypeEnum.COLLECTIVITE_D_OUTRE_MER, resultItem1.getType());
        assertEquals(LocalDate.of(1985,1,1), resultItem1.getDateCreation());
        assertEquals("Wallis-et-Futuna", resultItem1.getIntituleSansArticle());
        assertEquals(TerritoireTousAttributs.TypeArticleEnum._0, resultItem1.getTypeArticle());
        assertEquals("Wallis-et-Futuna", resultItem1.getIntitule());
    }

    //    geo/circonscriptionTerritoriale/98611/ascendants?date=2025-09-04&type=Region
    @Test
    void should_return_404_when_CirconscriptionTerritorialeCodeAscendants_code98611_date20250904_typeRegion() throws Exception{
        mockMvc.perform(get("/geo/circonscriptionTerritoriale/98611/precedents")
                        .param("date", "2025-09-01")
                        .param("type", "Region"))
                .andExpect(status().isNotFound());
    }



    /////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementMunicipal/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/circonscriptionTerritoriale/98611?date=2025-09-04
    @Test
    void should_return_circonscriptionTerritorialeCode_98611_when_code98611_date20250904() {
        var response  = endpoints.getcogcir("98611", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        Assertions.assertNotNull(result);
        assertEquals("98611", result.getCode());
        assertEquals("http://id.insee.fr/geo/circonscriptionTerritoriale/31f556e9-55e5-4e48-9dac-2d8113fa609e", result.getUri());
        assertEquals(CirconscriptionTerritoriale.TypeEnum.CIRCONSCRIPTION_TERRITORIALE, result.getType());
        assertEquals(LocalDate.of(1985,1,1), result.getDateCreation());
        assertEquals("Alo", result.getIntituleSansArticle());
        assertEquals(CirconscriptionTerritoriale.TypeArticleEnum._1, result.getTypeArticle());
        assertEquals("Alo", result.getIntitule());
    }



//    geo/circonscriptionTerritoriale/98611?date=2007-01-01 renvoie 404
    @Test
    void should_return_404_when_circonscriptionTerritorialeCode_code98611_date19800101() throws Exception{
        mockMvc.perform(get("/geo/circonscriptionTerritoriale/98611")
                    .param("date", "1980-01-01"))
            .andExpect(status().isNotFound());
    }


}
