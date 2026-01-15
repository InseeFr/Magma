package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.OperationsEndpoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class OperationsQueriesTest extends TestcontainerTest {

    @Autowired
    OperationsEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /// //////////////////////////////////////////////////////////////////
    ///             operations/rapportQualite/{id}                     ///
    /// //////////////////////////////////////////////////////////////////

    @Test
    void should_return_rapportQualite1979_when_OperationsRapportQualite_id1979() throws IOException {
        var response = endpoints.getRapportQualiteByCode("1979");
        var result = response.getBody();
        Assertions.assertNotNull(result);

        var rubrique1 = result.getRubriques().getFirst(); // TEXT type rubric
        var rubrique7 = result.getRubriques().get(6); // DATE type rubric
        var rubrique8 = result.getRubriques().get(7); // RICH_TEXT type rubric
        var rubrique10 = result.getRubriques().get(9); // GEOGRAPHY type rubric
        var rubrique13 = result.getRubriques().get(12); // CODE_LIST type rubric

        assertAll(
                () -> assertEquals("1979", result.getId()),
                () -> assertEquals(URI.create("http://id.insee.fr/qualite/rapport/1979"), result.getUri()),
                () -> assertEquals(("Rapport qualité : Enquête emploi en continu 2020"), result.getLabel().getFirst().getContenu()),
                () -> assertEquals(("fr"), result.getLabel().getFirst().getLangue()),
                () -> assertEquals(("Quality report: Labour force survey 2020"), result.getLabel().getLast().getContenu()),
                () -> assertEquals(("en"), result.getLabel().getLast().getLangue()),
                () -> assertEquals(27, result.getRubriques().size()),

                // 1st rubric
                () -> assertEquals("I.6.4", rubrique1.getId()),
                () -> assertEquals("http://id.insee.fr/qualite/simsv2fr/attribut/I.6.4", rubrique1.getUri()),
                () -> assertEquals("S.6", rubrique1.getIdParent()),
                () -> assertEquals("TEXT", rubrique1.getType()),
                () -> assertEquals("<p>2018T006EC</p>", rubrique1.getLabel().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique1.getLabel().getFirst().getLangue()),
                () -> assertEquals("<p>2018T006EC</p>", rubrique1.getLabel().getLast().getContenu()),
                () -> assertEquals("en", rubrique1.getLabel().getLast().getLangue()),
                () -> assertEquals("n° de visa", rubrique1.getTitre().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique1.getTitre().getFirst().getLangue()),
                () -> assertEquals("n° visa", rubrique1.getTitre().getLast().getContenu()),
                () -> assertEquals("en", rubrique1.getTitre().getLast().getLangue()),

                // 7th rubric
                () -> assertEquals("S.2.3", rubrique7.getId()),
                () -> assertEquals("http://ec.europa.eu/eurostat/simsv2/attribute/S.2.3", rubrique7.getUri()),
                () -> assertEquals("S.2", rubrique7.getIdParent()),
                () -> assertEquals("DATE", rubrique7.getType()),
                () -> assertEquals("Dernière mise à jour des métadonnées", rubrique7.getTitre().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique7.getTitre().getFirst().getLangue()),
                () -> assertEquals("Metadata last update", rubrique7.getTitre().getLast().getContenu()),
                () -> assertEquals("en", rubrique7.getTitre().getLast().getLangue()),
                () -> assertEquals("2018-12-17", rubrique7.getDate()),

                // 8th rubric
                () -> assertEquals("S.10.6", rubrique8.getId()),
                () -> assertEquals("http://id.insee.fr/qualite/simsv2fr/attribut/S.10.6", rubrique8.getUri()),
                () -> assertEquals("S.10", rubrique8.getIdParent()),
                () -> assertEquals("RICH_TEXT", rubrique8.getType()),
                () -> assertEquals("Documentation sur la méthodologie", rubrique8.getTitre().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique8.getTitre().getFirst().getLangue()),
                () -> assertEquals("Documentation on methodology", rubrique8.getTitre().getLast().getContenu()),
                () -> assertEquals("en", rubrique8.getTitre().getLast().getLangue()),
                () -> assertEquals("Note méthodologique EEC juillet 2021", rubrique8.getContenus().getFirst().getDocuments().getFirst().getLabel().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique8.getContenus().getFirst().getDocuments().getFirst().getLabel().getFirst().getLangue()),
                () -> assertEquals("LFS methodological note July 2021", rubrique8.getContenus().getFirst().getDocuments().getFirst().getLabel().getLast().getContenu()),
                () -> assertEquals("en", rubrique8.getContenus().getFirst().getDocuments().getFirst().getLabel().getLast().getLangue()),
                () -> assertEquals("2021-07-13T00:00:00", rubrique8.getContenus().getFirst().getDocuments().getFirst().getDateMiseAJour()),
                () -> assertEquals("fr", rubrique8.getContenus().getFirst().getDocuments().getFirst().getLangue()),
                () -> assertEquals("https://www.insee.fr/fr/metadonnees/source/fichier/EEC2021_Note-methodologique-juillet-2021.pdf", rubrique8.getContenus().getFirst().getDocuments().getFirst().getUrl()),
                () -> assertEquals("", rubrique8.getContenus().getFirst().getTexte()),
                () -> assertEquals("fr", rubrique8.getContenus().getFirst().getLangue()),

                () -> assertEquals("Note méthodologique EEC juillet 2021", rubrique8.getContenus().getLast().getDocuments().getFirst().getLabel().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique8.getContenus().getLast().getDocuments().getFirst().getLabel().getFirst().getLangue()),
                () -> assertEquals("LFS methodological note July 2021", rubrique8.getContenus().getLast().getDocuments().getFirst().getLabel().getLast().getContenu()),
                () -> assertEquals("en", rubrique8.getContenus().getLast().getDocuments().getFirst().getLabel().getLast().getLangue()),
                () -> assertEquals("2021-07-13T00:00:00", rubrique8.getContenus().getLast().getDocuments().getFirst().getDateMiseAJour()),
                () -> assertEquals("fr", rubrique8.getContenus().getLast().getDocuments().getFirst().getLangue()),
                () -> assertEquals("https://www.insee.fr/fr/metadonnees/source/fichier/EEC2021_Note-methodologique-juillet-2021.pdf", rubrique8.getContenus().getLast().getDocuments().getFirst().getUrl()),
                () -> assertEquals("", rubrique8.getContenus().getLast().getTexte()),
                () -> assertEquals("en", rubrique8.getContenus().getLast().getLangue()),

                // 10th rubric
                () -> assertEquals("S.3.7", rubrique10.getId()),
                () -> assertEquals("http://id.insee.fr/qualite/simsv2fr/attribut/S.3.7", rubrique10.getUri()),
                () -> assertEquals("S.3", rubrique10.getIdParent()),
                () -> assertEquals("GEOGRAPHY", rubrique10.getType()),
                () -> assertEquals("Zone géographique de référence", rubrique10.getTitre().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique10.getTitre().getFirst().getLangue()),
                () -> assertEquals("Reference area", rubrique10.getTitre().getLast().getContenu()),
                () -> assertEquals("en", rubrique10.getTitre().getLast().getLangue()),
                () -> {
                    Assertions.assertNotNull(rubrique10.getTerritoire());
                    assertEquals("franceHorsMayotte", rubrique10.getTerritoire().getId());
                },
                () -> {
                    Assertions.assertNotNull(rubrique10.getTerritoire());
                    assertEquals(URI.create("http://id.insee.fr/qualite/territoire/franceHorsMayotte"), rubrique10.getTerritoire().getUri());
                    Assertions.assertNotNull(rubrique10.getTerritoire());
                    assertEquals("France hors Mayotte", rubrique10.getTerritoire().getLabel().getFirst().getContenu());
                    assertEquals("fr", rubrique10.getTerritoire().getLabel().getFirst().getLangue());
                },

                // 13th rubric
                () -> assertEquals("I.18.8", rubrique13.getId()),
                () -> assertEquals("http://id.insee.fr/qualite/simsv2fr/attribut/I.18.8", rubrique13.getUri()),
                () -> assertEquals("S.18", rubrique13.getIdParent()),
                () -> assertEquals("CODE_LIST", rubrique13.getType()),
                () -> assertEquals("Mode de collecte", rubrique13.getTitre().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique13.getTitre().getFirst().getLangue()),
                () -> assertEquals("Collection mode", rubrique13.getTitre().getLast().getContenu()),
                () -> assertEquals("en", rubrique13.getTitre().getLast().getLangue()),

                () -> assertEquals("F", rubrique13.getCodes().getFirst().getId()),
                () -> assertEquals(URI.create("http://id.insee.fr/codes/modeCollecte/F"), rubrique13.getCodes().getFirst().getUri()),
                () -> assertEquals("Face à face par enquêteur", rubrique13.getCodes().getFirst().getLabel().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique13.getCodes().getFirst().getLabel().getFirst().getLangue()),
                () -> assertEquals("Face to face by interviewer", rubrique13.getCodes().getFirst().getLabel().getLast().getContenu()),
                () -> assertEquals("en", rubrique13.getCodes().getFirst().getLabel().getLast().getLangue()),

                () -> assertEquals("P", rubrique13.getCodes().getLast().getId()),
                () -> assertEquals(URI.create("http://id.insee.fr/codes/modeCollecte/P"), rubrique13.getCodes().getLast().getUri()),
                () -> assertEquals("Par téléphone", rubrique13.getCodes().getLast().getLabel().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique13.getCodes().getLast().getLabel().getFirst().getLangue()),
                () -> assertEquals("By phone", rubrique13.getCodes().getLast().getLabel().getLast().getContenu()),
                () -> assertEquals("en", rubrique13.getCodes().getLast().getLabel().getLast().getLangue())

        );
    }

    @Test
    void should_return_rapportQualite1981_when_OperationsRapportQualite_id1981() throws IOException {
        var response = endpoints.getRapportQualiteByCode("1981");
        var result = response.getBody();
        Assertions.assertNotNull(result);

        var rubrique8 = result.getRubriques().get(7); // ORGANIZATION type rubric

        assertAll(
                () -> assertEquals("S.1.2", rubrique8.getId()),
                () -> assertEquals("http://id.insee.fr/qualite/simsv2fr/attribut/S.1.2", rubrique8.getUri()),
                () -> assertEquals("S.1", rubrique8.getIdParent()),
                () -> assertEquals("ORGANIZATION", rubrique8.getType()),
                () -> assertEquals("Unité d'appartenance du  contact dans l'organisme", rubrique8.getTitre().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique8.getTitre().getFirst().getLangue()),
                () -> assertEquals("Contact organisation unit", rubrique8.getTitre().getLast().getContenu()),
                () -> assertEquals("en", rubrique8.getTitre().getLast().getLangue()),
                () -> {
                    Assertions.assertNotNull(rubrique8.getOrganisme());
                    assertEquals("DG75-E330", rubrique8.getOrganisme().getId());
                    assertEquals(URI.create("http://id.insee.fr/organisations/insee/HIE2000265"), rubrique8.getOrganisme().getUri());
                    assertEquals("Division Elaboration des statistiques de production industrielle (ESPRI)", rubrique8.getOrganisme().getLabel().getFirst().getContenu());
                    assertEquals("fr", rubrique8.getOrganisme().getLabel().getFirst().getLangue());
                }
        );
    }
}

