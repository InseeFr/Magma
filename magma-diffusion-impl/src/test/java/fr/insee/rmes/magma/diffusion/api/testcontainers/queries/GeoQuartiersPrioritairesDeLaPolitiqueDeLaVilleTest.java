package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.GeoQuartierPrioritaireDeLaPolitiqueDeLaVilleEndpoints;
import fr.insee.rmes.magma.diffusion.model.ArrondissementMunicipal;
import fr.insee.rmes.magma.diffusion.model.CommuneAssociee;
import fr.insee.rmes.magma.diffusion.model.QuartierPrioritaireDeLaPolitiqueDeLaVille2024;
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


class GeoQuartiersPrioritairesDeLaPolitiqueDeLaVilleTest extends TestcontainerTest {

    @Autowired
    GeoQuartierPrioritaireDeLaPolitiqueDeLaVilleEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /// ///////////////////////////////////////////////////////////////////////
    ///        geo/quartierPrioritaireDeLaPolitiqueDeLaVille2024/{code}     ///
    /// ///////////////////////////////////////////////////////////////////////

//    geo/quartiersPrioritairesDeLaPolitiqueDeLaVille2024/QN06255M?date=2025-09-04
    @Test
    void should_return_quartierPrioritaireDeLaVilleCode_QN06255M_when_codeQN06255M_date20250904() {
        var response = endpoints.getcogqpv("QN06255M", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        Assertions.assertNotNull(result);
        assertAll(
                () -> assertEquals("QN06255M", result.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/quartierPrioritaireDeLaPolitiqueDeLaVille2024/23498c07-2891-41e9-b3d5-d8846188ba0c", result.getUri()),
                () -> assertEquals(QuartierPrioritaireDeLaPolitiqueDeLaVille2024.TypeEnum.QUARTIER_PRIORITAIRE_DE_LA_POLITIQUE_DE_LA_VILLE2024, result.getType()),
                () -> assertEquals(LocalDate.of(2024, 1, 1), result.getDateCreation()),
                () -> assertEquals("Ville Centre", result.getIntituleSansArticle()),
                () -> assertEquals(QuartierPrioritaireDeLaPolitiqueDeLaVille2024.TypeArticleEnum.X, result.getTypeArticle()),
                () -> assertEquals("Ville Centre", result.getIntitule())
        );
    }

    /// ///////////////////////////////////////////////////////////////////////
    ///        geo/quartierPrioritaireDeLaPolitiqueDeLaVille2024            ///
    /// ///////////////////////////////////////////////////////////////////////

//    geo/quartiersPrioritairesDeLaPolitiqueDeLaVille2024?date=2025-09-04//
    @Test
    void should_return_1609_QPV_when_quartierPrioritaireDeLaPolitiqueDeLaVille2024_date20250904() {
        var response  = endpoints.getcogqpvliste(LocalDate.of(2025,9,4));
        var result = response.getBody();
        Assertions.assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(1609, result.size()),
                () -> assertEquals("QN00101M", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/quartierPrioritaireDeLaPolitiqueDeLaVille2024/40526c49-2c78-4856-a4ed-21714bf70cb9", resultItem1.getUri()),
                () -> assertEquals(QuartierPrioritaireDeLaPolitiqueDeLaVille2024.TypeEnum.QUARTIER_PRIORITAIRE_DE_LA_POLITIQUE_DE_LA_VILLE2024, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(2024,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("Grande Reyssouze Terre Des Fleurs", resultItem1.getIntituleSansArticle()),
                () -> assertEquals(QuartierPrioritaireDeLaPolitiqueDeLaVille2024.TypeArticleEnum.X, resultItem1.getTypeArticle()),
                () -> assertEquals("Grande Reyssouze Terre Des Fleurs", resultItem1.getIntitule())
        );
    }

}
