package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.OperationsEndpoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    //  end to end test without any mock of a rapportQualite with all types of rubrics except ORGANIZATION
    //    operations/rapportQualite/1979

    @Test
    void should_return_rapportQualite1979_when_OperationsRapportQualite_id1979() {
        var response = endpoints.getRapportQualiteByCode("1979");
        var result = response.getBody();
        Assertions.assertNotNull(result);

        var rubrique_I_6_4 = result.getRubriques().stream()
                .filter(r -> "I.6.4".equals(r.getId()))
                .findFirst()
                .orElseThrow(); // TEXT type rubric
        var rubrique_S_2_3 = result.getRubriques().stream()
                .filter(r -> "S.2.3".equals(r.getId()))
                .findFirst()
                .orElseThrow(); // DATE type rubric
        var rubrique_S_10_6 = result.getRubriques().stream()
                .filter(r -> "S.10.6".equals(r.getId()))
                .findFirst()
                .orElseThrow(); // RICH_TEXT type rubric
        var rubrique_S_3_7 = result.getRubriques().stream()
                .filter(r -> "S.3.7".equals(r.getId()))
                .findFirst()
                .orElseThrow(); // GEOGRAPHY type rubric
        var _rubrique_I_18_8 = result.getRubriques().stream()
                .filter(r -> "I.18.8".equals(r.getId()))
                .findFirst()
                .orElseThrow(); // CODE_LIST type rubric

        assertAll(
                () -> assertEquals("1979", result.getId()),
                () -> assertEquals(URI.create("http://id.insee.fr/qualite/rapport/1979"), result.getUri()),
                () -> assertEquals(("Rapport qualité : Enquête emploi en continu 2020"), result.getLabel().getFirst().getContenu()),
                () -> assertEquals(("fr"), result.getLabel().getFirst().getLangue()),
                () -> assertEquals(("Quality report: Labour force survey 2020"), result.getLabel().getLast().getContenu()),
                () -> assertEquals(("en"), result.getLabel().getLast().getLangue()),
                () -> assertEquals(27, result.getRubriques().size()),

                () -> assertEquals("http://id.insee.fr/qualite/simsv2fr/attribut/I.6.4", rubrique_I_6_4.getUri()),
                () -> assertEquals("S.6", rubrique_I_6_4.getIdParent()),
                () -> assertEquals("TEXT", rubrique_I_6_4.getType()),
                () -> assertEquals("<p>2018T006EC</p>", rubrique_I_6_4.getLabel().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique_I_6_4.getLabel().getFirst().getLangue()),
                () -> assertEquals("<p>2018T006EC</p>", rubrique_I_6_4.getLabel().getLast().getContenu()),
                () -> assertEquals("en", rubrique_I_6_4.getLabel().getLast().getLangue()),
                () -> assertEquals("n° de visa", rubrique_I_6_4.getTitre().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique_I_6_4.getTitre().getFirst().getLangue()),
                () -> assertEquals("n° visa", rubrique_I_6_4.getTitre().getLast().getContenu()),
                () -> assertEquals("en", rubrique_I_6_4.getTitre().getLast().getLangue()),

                () -> assertEquals("http://ec.europa.eu/eurostat/simsv2/attribute/S.2.3", rubrique_S_2_3.getUri()),
                () -> assertEquals("S.2", rubrique_S_2_3.getIdParent()),
                () -> assertEquals("DATE", rubrique_S_2_3.getType()),
                () -> assertEquals("Dernière mise à jour des métadonnées", rubrique_S_2_3.getTitre().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique_S_2_3.getTitre().getFirst().getLangue()),
                () -> assertEquals("Metadata last update", rubrique_S_2_3.getTitre().getLast().getContenu()),
                () -> assertEquals("en", rubrique_S_2_3.getTitre().getLast().getLangue()),
                () -> assertEquals("2018-12-17", rubrique_S_2_3.getDate()),

                () -> assertEquals("http://id.insee.fr/qualite/simsv2fr/attribut/S.10.6", rubrique_S_10_6.getUri()),
                () -> assertEquals("S.10", rubrique_S_10_6.getIdParent()),
                () -> assertEquals("RICH_TEXT", rubrique_S_10_6.getType()),
                () -> assertEquals("Documentation sur la méthodologie", rubrique_S_10_6.getTitre().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique_S_10_6.getTitre().getFirst().getLangue()),
                () -> assertEquals("Documentation on methodology", rubrique_S_10_6.getTitre().getLast().getContenu()),
                () -> assertEquals("en", rubrique_S_10_6.getTitre().getLast().getLangue()),
                () -> assertEquals("Note méthodologique EEC juillet 2021", rubrique_S_10_6.getContenus().getFirst().getDocuments().getFirst().getLabel().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique_S_10_6.getContenus().getFirst().getDocuments().getFirst().getLabel().getFirst().getLangue()),
                () -> assertEquals("LFS methodological note July 2021", rubrique_S_10_6.getContenus().getFirst().getDocuments().getFirst().getLabel().getLast().getContenu()),
                () -> assertEquals("en", rubrique_S_10_6.getContenus().getFirst().getDocuments().getFirst().getLabel().getLast().getLangue()),
                () -> assertEquals("2021-07-13T00:00:00", rubrique_S_10_6.getContenus().getFirst().getDocuments().getFirst().getDateMiseAJour()),
                () -> assertEquals("fr", rubrique_S_10_6.getContenus().getFirst().getDocuments().getFirst().getLangue()),
                () -> assertEquals("https://www.insee.fr/fr/metadonnees/source/fichier/EEC2021_Note-methodologique-juillet-2021.pdf", rubrique_S_10_6.getContenus().getFirst().getDocuments().getFirst().getUrl()),
                () -> assertEquals("", rubrique_S_10_6.getContenus().getFirst().getTexte()),
                () -> assertEquals("fr", rubrique_S_10_6.getContenus().getFirst().getLangue()),

                () -> assertEquals("Note méthodologique EEC juillet 2021", rubrique_S_10_6.getContenus().getLast().getDocuments().getFirst().getLabel().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique_S_10_6.getContenus().getLast().getDocuments().getFirst().getLabel().getFirst().getLangue()),
                () -> assertEquals("LFS methodological note July 2021", rubrique_S_10_6.getContenus().getLast().getDocuments().getFirst().getLabel().getLast().getContenu()),
                () -> assertEquals("en", rubrique_S_10_6.getContenus().getLast().getDocuments().getFirst().getLabel().getLast().getLangue()),
                () -> assertEquals("2021-07-13T00:00:00", rubrique_S_10_6.getContenus().getLast().getDocuments().getFirst().getDateMiseAJour()),
                () -> assertEquals("fr", rubrique_S_10_6.getContenus().getLast().getDocuments().getFirst().getLangue()),
                () -> assertEquals("https://www.insee.fr/fr/metadonnees/source/fichier/EEC2021_Note-methodologique-juillet-2021.pdf", rubrique_S_10_6.getContenus().getLast().getDocuments().getFirst().getUrl()),
                () -> assertEquals("", rubrique_S_10_6.getContenus().getLast().getTexte()),
                () -> assertEquals("en", rubrique_S_10_6.getContenus().getLast().getLangue()),

                () -> assertEquals("S.3.7", rubrique_S_3_7.getId()),
                () -> assertEquals("http://id.insee.fr/qualite/simsv2fr/attribut/S.3.7", rubrique_S_3_7.getUri()),
                () -> assertEquals("S.3", rubrique_S_3_7.getIdParent()),
                () -> assertEquals("GEOGRAPHY", rubrique_S_3_7.getType()),
                () -> assertEquals("Zone géographique de référence", rubrique_S_3_7.getTitre().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique_S_3_7.getTitre().getFirst().getLangue()),
                () -> assertEquals("Reference area", rubrique_S_3_7.getTitre().getLast().getContenu()),
                () -> assertEquals("en", rubrique_S_3_7.getTitre().getLast().getLangue()),
                () -> {
                    Assertions.assertNotNull(rubrique_S_3_7.getTerritoire());
                    assertEquals("franceHorsMayotte", rubrique_S_3_7.getTerritoire().getId());
                },
                () -> {
                    Assertions.assertNotNull(rubrique_S_3_7.getTerritoire());
                    assertEquals(URI.create("http://id.insee.fr/qualite/territoire/franceHorsMayotte"), rubrique_S_3_7.getTerritoire().getUri());
                    Assertions.assertNotNull(rubrique_S_3_7.getTerritoire());
                    assertEquals("France hors Mayotte", rubrique_S_3_7.getTerritoire().getLabel().getFirst().getContenu());
                    assertEquals("fr", rubrique_S_3_7.getTerritoire().getLabel().getFirst().getLangue());
                },

                () -> assertEquals("http://id.insee.fr/qualite/simsv2fr/attribut/I.18.8", _rubrique_I_18_8.getUri()),
                () -> assertEquals("S.18", _rubrique_I_18_8.getIdParent()),
                () -> assertEquals("CODE_LIST", _rubrique_I_18_8.getType()),
                () -> assertEquals("Mode de collecte", _rubrique_I_18_8.getTitre().getFirst().getContenu()),
                () -> assertEquals("fr", _rubrique_I_18_8.getTitre().getFirst().getLangue()),
                () -> assertEquals("Collection mode", _rubrique_I_18_8.getTitre().getLast().getContenu()),
                () -> assertEquals("en", _rubrique_I_18_8.getTitre().getLast().getLangue()),

                () -> assertEquals("F", _rubrique_I_18_8.getCodes().getFirst().getId()),
                () -> assertEquals(URI.create("http://id.insee.fr/codes/modeCollecte/F"), _rubrique_I_18_8.getCodes().getFirst().getUri()),
                () -> assertEquals("Face à face par enquêteur", _rubrique_I_18_8.getCodes().getFirst().getLabel().getFirst().getContenu()),
                () -> assertEquals("fr", _rubrique_I_18_8.getCodes().getFirst().getLabel().getFirst().getLangue()),
                () -> assertEquals("Face to face by interviewer", _rubrique_I_18_8.getCodes().getFirst().getLabel().getLast().getContenu()),
                () -> assertEquals("en", _rubrique_I_18_8.getCodes().getFirst().getLabel().getLast().getLangue()),

                () -> assertEquals("P", _rubrique_I_18_8.getCodes().getLast().getId()),
                () -> assertEquals(URI.create("http://id.insee.fr/codes/modeCollecte/P"), _rubrique_I_18_8.getCodes().getLast().getUri()),
                () -> assertEquals("Par téléphone", _rubrique_I_18_8.getCodes().getLast().getLabel().getFirst().getContenu()),
                () -> assertEquals("fr", _rubrique_I_18_8.getCodes().getLast().getLabel().getFirst().getLangue()),
                () -> assertEquals("By phone", _rubrique_I_18_8.getCodes().getLast().getLabel().getLast().getContenu()),
                () -> assertEquals("en", _rubrique_I_18_8.getCodes().getLast().getLabel().getLast().getLangue())

        );
    }


    //  end to end test of a rapportQualite with all types of rubrics except ORGANIZATION rubric
    //    operations/rapportQualite/1979
    @Test
    void should_return_rapportQualite1979_when_OperationsRapportQualite_id1979_endToEndTest() throws Exception {
        var rubriques = endpoints.getRapportQualiteByCode("1979").getBody().getRubriques();
        int index_I_6_4  = IntStream.range(0, rubriques.size()).filter(i -> "I.6.4".equals(rubriques.get(i).getId())).findFirst().orElseThrow();
        int index_S_2_3  = IntStream.range(0, rubriques.size()).filter(i -> "S.2.3".equals(rubriques.get(i).getId())).findFirst().orElseThrow();
        int index_S_10_6 = IntStream.range(0, rubriques.size()).filter(i -> "S.10.6".equals(rubriques.get(i).getId())).findFirst().orElseThrow();
        int index_S_3_7  = IntStream.range(0, rubriques.size()).filter(i -> "S.3.7".equals(rubriques.get(i).getId())).findFirst().orElseThrow();
        int index_I_18_8 = IntStream.range(0, rubriques.size()).filter(i -> "I.18.8".equals(rubriques.get(i).getId())).findFirst().orElseThrow();

        mockMvc.perform(get("/operations/rapportQualite/1979"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Root level
                .andExpect(jsonPath("$.id").value("1979"))
                .andExpect(jsonPath("$.uri").value("http://id.insee.fr/qualite/rapport/1979"))
                .andExpect(jsonPath("$.label[0].contenu").value("Rapport qualité : Enquête emploi en continu 2020"))
                .andExpect(jsonPath("$.label[0].langue").value("fr"))
                .andExpect(jsonPath("$.label[1].contenu").value("Quality report: Labour force survey 2020"))
                .andExpect(jsonPath("$.label[1].langue").value("en"))
                .andExpect(jsonPath("$.rubriques.length()").value(27))

                // rubrique I.6.4 (TEXT type)
                .andExpect(jsonPath("$.rubriques[" + index_I_6_4 + "].id").value("I.6.4"))
                .andExpect(jsonPath("$.rubriques[" + index_I_6_4 + "].uri").value("http://id.insee.fr/qualite/simsv2fr/attribut/I.6.4"))
                .andExpect(jsonPath("$.rubriques[" + index_I_6_4 + "].idParent").value("S.6"))
                .andExpect(jsonPath("$.rubriques[" + index_I_6_4 + "].type").value("TEXT"))
                .andExpect(jsonPath("$.rubriques[" + index_I_6_4 + "].label[0].contenu").value("<p>2018T006EC</p>"))
                .andExpect(jsonPath("$.rubriques[" + index_I_6_4 + "].label[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_I_6_4 + "].label[1].contenu").value("<p>2018T006EC</p>"))
                .andExpect(jsonPath("$.rubriques[" + index_I_6_4 + "].label[1].langue").value("en"))
                .andExpect(jsonPath("$.rubriques[" + index_I_6_4 + "].titre[0].contenu").value("n° de visa"))
                .andExpect(jsonPath("$.rubriques[" + index_I_6_4 + "].titre[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_I_6_4 + "].titre[1].contenu").value("n° visa"))
                .andExpect(jsonPath("$.rubriques[" + index_I_6_4 + "].titre[1].langue").value("en"))

                // rubrique S.2.3 (DATE type)
                .andExpect(jsonPath("$.rubriques[" + index_S_2_3 + "].id").value("S.2.3"))
                .andExpect(jsonPath("$.rubriques[" + index_S_2_3 + "].uri").value("http://ec.europa.eu/eurostat/simsv2/attribute/S.2.3"))
                .andExpect(jsonPath("$.rubriques[" + index_S_2_3 + "].idParent").value("S.2"))
                .andExpect(jsonPath("$.rubriques[" + index_S_2_3 + "].type").value("DATE"))
                .andExpect(jsonPath("$.rubriques[" + index_S_2_3 + "].titre[0].contenu").value("Dernière mise à jour des métadonnées"))
                .andExpect(jsonPath("$.rubriques[" + index_S_2_3 + "].titre[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_S_2_3 + "].titre[1].contenu").value("Metadata last update"))
                .andExpect(jsonPath("$.rubriques[" + index_S_2_3 + "].titre[1].langue").value("en"))
                .andExpect(jsonPath("$.rubriques[" + index_S_2_3 + "].date").value("2018-12-17"))

                // rubrique S.10.6 (RICH_TEXT type)
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].id").value("S.10.6"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].uri").value("http://id.insee.fr/qualite/simsv2fr/attribut/S.10.6"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].idParent").value("S.10"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].type").value("RICH_TEXT"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].titre[0].contenu").value("Documentation sur la méthodologie"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].titre[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].titre[1].contenu").value("Documentation on methodology"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].titre[1].langue").value("en"))

                // rubrique S.10.6 - first content
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[0].documents[0].label[0].contenu").value("Note méthodologique EEC juillet 2021"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[0].documents[0].label[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[0].documents[0].label[1].contenu").value("LFS methodological note July 2021"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[0].documents[0].label[1].langue").value("en"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[0].documents[0].dateMiseAJour").value("2021-07-13T00:00:00"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[0].documents[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[0].documents[0].url").value("https://www.insee.fr/fr/metadonnees/source/fichier/EEC2021_Note-methodologique-juillet-2021.pdf"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[0].texte").value(""))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[0].langue").value("fr"))

                // rubrique S.10.6 - last content
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[1].documents[0].label[0].contenu").value("Note méthodologique EEC juillet 2021"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[1].documents[0].label[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[1].documents[0].label[1].contenu").value("LFS methodological note July 2021"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[1].documents[0].label[1].langue").value("en"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[1].documents[0].dateMiseAJour").value("2021-07-13T00:00:00"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[1].documents[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[1].documents[0].url").value("https://www.insee.fr/fr/metadonnees/source/fichier/EEC2021_Note-methodologique-juillet-2021.pdf"))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[1].texte").value(""))
                .andExpect(jsonPath("$.rubriques[" + index_S_10_6 + "].contenus[1].langue").value("en"))

                // rubrique S.3.7 (GEOGRAPHY type)
                .andExpect(jsonPath("$.rubriques[" + index_S_3_7 + "].id").value("S.3.7"))
                .andExpect(jsonPath("$.rubriques[" + index_S_3_7 + "].uri").value("http://id.insee.fr/qualite/simsv2fr/attribut/S.3.7"))
                .andExpect(jsonPath("$.rubriques[" + index_S_3_7 + "].idParent").value("S.3"))
                .andExpect(jsonPath("$.rubriques[" + index_S_3_7 + "].type").value("GEOGRAPHY"))
                .andExpect(jsonPath("$.rubriques[" + index_S_3_7 + "].titre[0].contenu").value("Zone géographique de référence"))
                .andExpect(jsonPath("$.rubriques[" + index_S_3_7 + "].titre[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_S_3_7 + "].titre[1].contenu").value("Reference area"))
                .andExpect(jsonPath("$.rubriques[" + index_S_3_7 + "].titre[1].langue").value("en"))
                .andExpect(jsonPath("$.rubriques[" + index_S_3_7 + "].territoire.id").value("franceHorsMayotte"))
                .andExpect(jsonPath("$.rubriques[" + index_S_3_7 + "].territoire.uri").value("http://id.insee.fr/qualite/territoire/franceHorsMayotte"))
                .andExpect(jsonPath("$.rubriques[" + index_S_3_7 + "].territoire.label[0].contenu").value("France hors Mayotte"))
                .andExpect(jsonPath("$.rubriques[" + index_S_3_7 + "].territoire.label[0].langue").value("fr"))

                // rubrique I.18.8 (CODE_LIST type)
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].id").value("I.18.8"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].uri").value("http://id.insee.fr/qualite/simsv2fr/attribut/I.18.8"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].idParent").value("S.18"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].type").value("CODE_LIST"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].titre[0].contenu").value("Mode de collecte"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].titre[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].titre[1].contenu").value("Collection mode"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].titre[1].langue").value("en"))

                // rubrique I.18.8 - first code
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].codes[0].id").value("F"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].codes[0].uri").value("http://id.insee.fr/codes/modeCollecte/F"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].codes[0].label[0].contenu").value("Face à face par enquêteur"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].codes[0].label[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].codes[0].label[1].contenu").value("Face to face by interviewer"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].codes[0].label[1].langue").value("en"))

                // rubrique I.18.8 - last code
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].codes[1].id").value("P"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].codes[1].uri").value("http://id.insee.fr/codes/modeCollecte/P"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].codes[1].label[0].contenu").value("Par téléphone"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].codes[1].label[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].codes[1].label[1].contenu").value("By phone"))
                .andExpect(jsonPath("$.rubriques[" + index_I_18_8 + "].codes[1].label[1].langue").value("en"));
    }


    //  end to end test without any mock, of an ORGANIZATION rubric
    //    operations/rapportQualite/1981

    @Test
    void should_return_rapportQualite1981_when_OperationsRapportQualite_id1981_BusinessLogicTest() {
        var response = endpoints.getRapportQualiteByCode("1981");
        var result = response.getBody();
        Assertions.assertNotNull(result);

        var rubrique_S_1_2 = result.getRubriques().stream()
                .filter(r -> "S.1.2".equals(r.getId()))
                .findFirst()
                .orElseThrow(); // ORGANIZATION type rubric

        assertAll(
                () -> assertEquals("http://id.insee.fr/qualite/simsv2fr/attribut/S.1.2", rubrique_S_1_2.getUri()),
                () -> assertEquals("S.1", rubrique_S_1_2.getIdParent()),
                () -> assertEquals("ORGANIZATION", rubrique_S_1_2.getType()),
                () -> assertEquals("Unité d'appartenance du  contact dans l'organisme", rubrique_S_1_2.getTitre().getFirst().getContenu()),
                () -> assertEquals("fr", rubrique_S_1_2.getTitre().getFirst().getLangue()),
                () -> assertEquals("Contact organisation unit", rubrique_S_1_2.getTitre().getLast().getContenu()),
                () -> assertEquals("en", rubrique_S_1_2.getTitre().getLast().getLangue()),
                () -> {
                    Assertions.assertNotNull(rubrique_S_1_2.getOrganisme());
                    assertEquals("DG75-E330", rubrique_S_1_2.getOrganisme().getId());
                    assertEquals(URI.create("http://id.insee.fr/organisations/insee/HIE2000265"), rubrique_S_1_2.getOrganisme().getUri());
                    assertEquals("Division Elaboration des statistiques de production industrielle (ESPRI)", rubrique_S_1_2.getOrganisme().getLabel().getFirst().getContenu());
                    assertEquals("fr", rubrique_S_1_2.getOrganisme().getLabel().getFirst().getLangue());
                }
        );
    }


    //  end to end test of an ORGANIZATION rubric
    //    operations/rapportQualite/1981
    @Test
    void should_return_organizationRubric_when_OperationsRapportQualite_id1981_endToEndTest() throws Exception {
        var rubriques = endpoints.getRapportQualiteByCode("1981").getBody().getRubriques();
        int index_S_1_2 = IntStream.range(0, rubriques.size()).filter(i -> "S.1.2".equals(rubriques.get(i).getId())).findFirst().orElseThrow();

        mockMvc.perform(get("/operations/rapportQualite/1981"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.rubriques[" + index_S_1_2 + "].id").value("S.1.2"))
                .andExpect(jsonPath("$.rubriques[" + index_S_1_2 + "].uri").value("http://id.insee.fr/qualite/simsv2fr/attribut/S.1.2"))
                .andExpect(jsonPath("$.rubriques[" + index_S_1_2 + "].idParent").value("S.1"))
                .andExpect(jsonPath("$.rubriques[" + index_S_1_2 + "].type").value("ORGANIZATION"))
                .andExpect(jsonPath("$.rubriques[" + index_S_1_2 + "].titre[0].contenu").value("Unité d'appartenance du  contact dans l'organisme"))
                .andExpect(jsonPath("$.rubriques[" + index_S_1_2 + "].titre[0].langue").value("fr"))
                .andExpect(jsonPath("$.rubriques[" + index_S_1_2 + "].titre[1].contenu").value("Contact organisation unit"))
                .andExpect(jsonPath("$.rubriques[" + index_S_1_2 + "].titre[1].langue").value("en"))
                .andExpect(jsonPath("$.rubriques[" + index_S_1_2 + "].organisme.id").value("DG75-E330"))
                .andExpect(jsonPath("$.rubriques[" + index_S_1_2 + "].organisme.uri").value("http://id.insee.fr/organisations/insee/HIE2000265"))
                .andExpect(jsonPath("$.rubriques[" + index_S_1_2 + "].organisme.label[0].contenu").value("Division Elaboration des statistiques de production industrielle (ESPRI)"))
                .andExpect(jsonPath("$.rubriques[" + index_S_1_2 + "].organisme.label[0].langue").value("fr"));
    }


}

