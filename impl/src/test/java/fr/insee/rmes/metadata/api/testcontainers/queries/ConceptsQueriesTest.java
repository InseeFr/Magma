package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.ConceptsEndpoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class ConceptsQueriesTest extends TestcontainerTest {

    @Autowired
    ConceptsEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;


    /////////////////////////////////////////////////////////////////////
    ///             concepts/definition/{id}                          ///
    /////////////////////////////////////////////////////////////////////

//    concepts/definition/c2066
    @Test
    void should_return_conceptc2066_when_ConceptsDefinitionCode_codec2066(){
        var response  = endpoints.getconcept("c2066");
        var result = response.getBody();
        assertAll(
                () -> {
                    Assertions.assertNotNull(result);
                    assertEquals("c2066", result.getId());
                },
                () -> assertEquals(URI.create("http://id.insee.fr/concepts/definition/c2066"), result.getUri()),
                () -> assertEquals("Auto-entrepreneur", result.getIntitule().getFirst().getContenu()),
                () -> assertEquals("fr", result.getIntitule().getFirst().getLangue()),
                () -> assertEquals("Auto-entrepreneur", result.getIntitule().getLast().getContenu()),
                () -> assertEquals("en", result.getIntitule().getLast().getLangue()),
                () -> assertEquals("<div xmlns=\"http://www.w3.org/1999/xhtml\"><p>Le régime de l'« auto-entrepreneur » s'applique depuis le 1er janvier 2009 aux personnes physiques qui créent ou possèdent déjà une entreprise individuelle pour exercer une activité commerciale, artisanale ou libérale (hormis certaines activités exclues), à titre principal ou complémentaire, et dont l'entreprise individuelle remplit les conditions du régime fiscal de la micro-entreprise et qui opte pour exercer en franchise de TVA.</p><p>Il offre des formalités de création d'entreprises allégées ainsi qu'un mode de calcul et de paiement simplifié des cotisations sociales et de l'impôt sur le revenu. L'auto-entrepreneur bénéficie :</p><ul class=\"list1\"><li>d'un régime micro-social simplifié ;</li><li>d'une dispense d'immatriculation au registre du commerce et des sociétés (RCS) pour les commerçants, ou au répertoire des métiers (RM) pour les artisans ; toutefois, l'auto-entrepreneur qui crée une activité artisanale à titre principal, doit s'inscrire au RM.</li><li>d'une exonération de TVA ;</li><li>et sur option, d'un régime micro-fiscal simplifié (versement libératoire de l'impôt sur le revenu) et d'une exonération de la cotisation foncière des entreprises pendant 3 ans à compter de la date de création.</li></ul></div>", result.getDefinition().getFirst().getContenu()),
                () -> assertEquals("fr", result.getDefinition().getFirst().getLangue()),
                () -> assertEquals("<div xmlns=\"http://www.w3.org/1999/xhtml\"><p>Since January 1st 2009, ‘auto-entrepreneur’ status applies to natural persons who set up or already possess a sole proprietorship, for the purpose of exercising a commercial or artisanal activity or one of the professions (with the exception of certain activities), as a main or complementary activity, and whose sole proprietorship fulfils the conditions of the micro-enterprise fiscal category, and who opt for VAT exemption.</p><p>This status offers less demanding regulations for starting up the business, as well as a simplified method for calculating and paying social security contributions and income tax. Auto-entrepreneurs benefit from:</p><ul class=\"list1\"><li>a simplified social scheme</li><li>exemption from the requirement of registration with the business register for commercial professionals, or the trade register for artisans; however, the auto-entrepreneur who creates an artisan activity with main title, has to join the RM.</li><li>exemption from VAT;</li><li>the option of a simplified tax scheme (payment in discharge of income tax) and exemption from porperty tax of firms for the first three years after establishment of the business.</li></ul></div>", result.getDefinition().getLast().getContenu()),
                () -> assertEquals("en", result.getDefinition().getLast().getLangue()),
                () -> assertEquals("<div xmlns=\"http://www.w3.org/1999/xhtml\"><p>Les professions libérales relevant de la Caisse interprofessionnelle de prévoyance et d'assurance vieillesse (CIPAV) et créateurs d'activité à compter du 1er janvier 2009 peuvent également bénéficier du statut d'auto-entrepreneur. Depuis janvier 2011, l'auto-entrepreneur peut bénéficier du statut de l'EIRL (entrepreneur individuel à responsabilité limitée) en affectant à son activité professionnelle un patrimoine spécifique séparé de son patrimoine personnel. Il conserve toutefois le régime fiscal et social forfaitaire lié au régime de l'auto-entreprise.</p></div>", result.getNoteEditoriale().getFirst().getContenu()),
                () -> assertEquals("fr", result.getNoteEditoriale().getFirst().getLangue()),
                () -> assertEquals("<div xmlns=\"http://www.w3.org/1999/xhtml\"><p>The professions falling under the aegis of the Inter-profession Body for Retirement Planning and Insurance (CIPAV) and those launching a business after January 1st 2009 can also benefit from auto-entrepreneur status. Since January, 2011, the auto-entrepreneur can benefit from EIRL (individual entrepreneur with limited liability) status by allocating to his professional activity a specific patrimony separated from his personal patrimony. However he preserves the fixed tax and social regime connected to the auto-entrepreneur status.</p></div>", result.getNoteEditoriale().getLast().getContenu()),
                () -> assertEquals("en", result.getNoteEditoriale().getLast().getLangue()),
                () -> assertEquals(LocalDate.of(2016,10,13), result.getDateMiseAJour()),
                () -> assertEquals("c1500", result.getConceptsSuivants().getFirst().getId()),
                () -> assertEquals(URI.create("http://id.insee.fr/concepts/definition/c1500"), result.getConceptsSuivants().getFirst().getUri())
        );
    }


//    concepts/definition/c1000
@Test
void should_return_404_when_ConceptsDefinitionCode_codec1000() throws Exception{
    mockMvc.perform(get("/geo/concepts/definition/c1000"))
            .andExpect(status().isNotFound());
}


    /////////////////////////////////////////////////////////////////////
    ///             concepts/definitions                              ///
    /////////////////////////////////////////////////////////////////////

//    geo/concepts/definitions?libelle=elect
    @Test
    void should_return_16_concepts_when_ConceptsDefinitions_libelleÉlect() {
        var response  = endpoints.getconceptsliste("Élect");
        var result = response.getBody();
        Assertions.assertNotNull(result);
        var resultItem1= result.getFirst();
        var resultItem4= result.get(3);
        assertAll(
                () -> assertEquals(16, result.size()),

                () -> assertEquals("c1769", resultItem1.getId()),
                () -> assertEquals(URI.create("http://id.insee.fr/concepts/definition/c1769"), resultItem1.getUri()),
                () -> assertEquals("Commerce électronique", resultItem1.getIntitule()),


                () -> assertEquals("c1158", resultItem4.getId()),
                () -> assertEquals(URI.create("http://id.insee.fr/concepts/definition/c1158"), resultItem4.getUri()),
                () -> assertEquals("Fichier général des électeurs ", resultItem4.getIntitule()),
                () -> assertEquals("c2131", resultItem4.getConceptsSuivants().getFirst().getId()),
                () -> assertEquals(URI.create("http://id.insee.fr/concepts/definition/c2131"), resultItem4.getConceptsSuivants().getFirst().getUri())

        );
    }

//    geo/concepts/definitions
    @Test
    void should_return_1230_concepts_when_ConceptsDefinitions_libelleNull() {
        var response  = endpoints.getconceptsliste("");
        var result = response.getBody();
        Assertions.assertNotNull(result);
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(1230, result.size()),
                () -> assertEquals("c1601", resultItem1.getId()),
                () -> assertEquals(URI.create("http://id.insee.fr/concepts/definition/c1601"), resultItem1.getUri()),
                () -> assertEquals("ADSL", resultItem1.getIntitule())
        );
    }

}
