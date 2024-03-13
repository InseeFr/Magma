package fr.insee.rmes.services.codelists;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import fr.insee.rmes.persistence.RepositoryGestion;
import fr.insee.rmes.stubs.FreeMarkerUtilsStub;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CodeListImplTest {

    public static final String EMPTY_CODELIST = "{}";
    public static final String CODELIST_WITH_STATUT_VALIDATION = "{\"prefLabelLg1\":\"Fréquence\",\"prefLabelLg2\":\"Frequency\",\"statutValidation\":\"Unpublished\",\"id\":\"CL_TEST\"}";
    public static final String CODELIST_WITH_STATUT_VALIDATION_EXPECTED= "{\"codes\":[{\"code\":\"B\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Quotidienne \\u2013 jours ouvrés\"},{\"langue\":\"en\",\"contenu\":\"Quotidienne \\u2013 jours ouvrés\"}]},{\"code\":\"Q\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Trimestrielle\"},{\"langue\":\"en\",\"contenu\":\"Trimestrielle\"}]},{\"code\":\"L\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Infra-annuelle\"},{\"langue\":\"en\",\"contenu\":\"Infra-annuelle\"}]},{\"code\":\"A\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Annuelle\"},{\"langue\":\"en\",\"contenu\":\"Annuelle\"}]},{\"code\":\"M\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Mensuelle\"},{\"langue\":\"en\",\"contenu\":\"Mensuelle\"}]},{\"code\":\"S\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Semestrielle\"},{\"langue\":\"en\",\"contenu\":\"Semestrielle\"}]},{\"code\":\"P\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Ponctuelle ou apériodique\"},{\"langue\":\"en\",\"contenu\":\"Ponctuelle ou apériodique\"}]},{\"code\":\"I\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Bisannuelle\"},{\"langue\":\"en\",\"contenu\":\"Bisannuelle\"}]},{\"code\":\"N\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Toutes les minutes\"},{\"langue\":\"en\",\"contenu\":\"Toutes les minutes\"}]},{\"code\":\"C\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Continue\"},{\"langue\":\"en\",\"contenu\":\"Continue\"}]},{\"code\":\"T\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Bimestrielle\"},{\"langue\":\"en\",\"contenu\":\"Bimestrielle\"}]},{\"code\":\"W\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Hebdomadaire\"},{\"langue\":\"en\",\"contenu\":\"Hebdomadaire\"}]},{\"code\":\"D\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Quotidienne\"},{\"langue\":\"en\",\"contenu\":\"Quotidienne\"}]},{\"code\":\"H\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Toutes les heures\"},{\"langue\":\"en\",\"contenu\":\"Toutes les heures\"}]},{\"code\":\"U\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Pluriannuelle\"},{\"langue\":\"en\",\"contenu\":\"Pluriannuelle\"}]},{\"code\":\"A3\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Triannuelle\"},{\"langue\":\"en\",\"contenu\":\"Triannuelle\"}]}],\"statutValidation\":\"Provisoire, jamais publiée\",\"id\":\"CL_TEST\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Fréquence\"},{\"langue\":\"en\",\"contenu\":\"Frequency\"}]}";
    public static final String CODELIST_WITHOUT_STATUT_VALIDATION = "{\"prefLabelLg1\":\"Fréquence\",\"prefLabelLg2\":\"Frequency\",\"id\":\"CL_TEST\"}";
    public static final String CODELIST_WITHOUT_STATUT_VALIDATION_EXPECTED="{\"codes\":[{\"code\":\"B\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Quotidienne \\u2013 jours ouvrés\"},{\"langue\":\"en\",\"contenu\":\"Quotidienne \\u2013 jours ouvrés\"}]},{\"code\":\"Q\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Trimestrielle\"},{\"langue\":\"en\",\"contenu\":\"Trimestrielle\"}]},{\"code\":\"L\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Infra-annuelle\"},{\"langue\":\"en\",\"contenu\":\"Infra-annuelle\"}]},{\"code\":\"A\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Annuelle\"},{\"langue\":\"en\",\"contenu\":\"Annuelle\"}]},{\"code\":\"M\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Mensuelle\"},{\"langue\":\"en\",\"contenu\":\"Mensuelle\"}]},{\"code\":\"S\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Semestrielle\"},{\"langue\":\"en\",\"contenu\":\"Semestrielle\"}]},{\"code\":\"P\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Ponctuelle ou apériodique\"},{\"langue\":\"en\",\"contenu\":\"Ponctuelle ou apériodique\"}]},{\"code\":\"I\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Bisannuelle\"},{\"langue\":\"en\",\"contenu\":\"Bisannuelle\"}]},{\"code\":\"N\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Toutes les minutes\"},{\"langue\":\"en\",\"contenu\":\"Toutes les minutes\"}]},{\"code\":\"C\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Continue\"},{\"langue\":\"en\",\"contenu\":\"Continue\"}]},{\"code\":\"T\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Bimestrielle\"},{\"langue\":\"en\",\"contenu\":\"Bimestrielle\"}]},{\"code\":\"W\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Hebdomadaire\"},{\"langue\":\"en\",\"contenu\":\"Hebdomadaire\"}]},{\"code\":\"D\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Quotidienne\"},{\"langue\":\"en\",\"contenu\":\"Quotidienne\"}]},{\"code\":\"H\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Toutes les heures\"},{\"langue\":\"en\",\"contenu\":\"Toutes les heures\"}]},{\"code\":\"U\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Pluriannuelle\"},{\"langue\":\"en\",\"contenu\":\"Pluriannuelle\"}]},{\"code\":\"A3\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Triannuelle\"},{\"langue\":\"en\",\"contenu\":\"Triannuelle\"}]}],\"id\":\"CL_TEST\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Fréquence\"},{\"langue\":\"en\",\"contenu\":\"Frequency\"}]}";
    public final String CODES = "[{\"code\":\"B\",\"prefLabelLg1\":\"Quotidienne \\u2013 jours ouvrés\",\"prefLabelLg2\":\"Quotidienne \\u2013 jours ouvrés\",\"uri\":\"http://bauhaus/codes/frequence/B\"},{\"code\":\"Q\",\"prefLabelLg1\":\"Trimestrielle\",\"prefLabelLg2\":\"Trimestrielle\",\"uri\":\"http://bauhaus/codes/frequence/Q\"},{\"code\":\"L\",\"prefLabelLg1\":\"Infra-annuelle\",\"prefLabelLg2\":\"Infra-annuelle\",\"uri\":\"http://bauhaus/codes/frequence/L\"},{\"code\":\"A\",\"prefLabelLg1\":\"Annuelle\",\"prefLabelLg2\":\"Annuelle\",\"uri\":\"http://bauhaus/codes/frequence/A\"},{\"code\":\"M\",\"prefLabelLg1\":\"Mensuelle\",\"prefLabelLg2\":\"Mensuelle\",\"uri\":\"http://bauhaus/codes/frequence/M\"},{\"code\":\"S\",\"prefLabelLg1\":\"Semestrielle\",\"prefLabelLg2\":\"Semestrielle\",\"uri\":\"http://bauhaus/codes/frequence/S\"},{\"code\":\"P\",\"prefLabelLg1\":\"Ponctuelle ou apériodique\",\"prefLabelLg2\":\"Ponctuelle ou apériodique\",\"uri\":\"http://bauhaus/codes/frequence/P\"},{\"code\":\"I\",\"prefLabelLg1\":\"Bisannuelle\",\"prefLabelLg2\":\"Bisannuelle\",\"uri\":\"http://bauhaus/codes/frequence/I\"},{\"code\":\"N\",\"prefLabelLg1\":\"Toutes les minutes\",\"prefLabelLg2\":\"Toutes les minutes\",\"uri\":\"http://bauhaus/codes/frequence/N\"},{\"code\":\"C\",\"prefLabelLg1\":\"Continue\",\"prefLabelLg2\":\"Continue\",\"uri\":\"http://bauhaus/codes/frequence/C\"},{\"code\":\"T\",\"prefLabelLg1\":\"Bimestrielle\",\"prefLabelLg2\":\"Bimestrielle\",\"uri\":\"http://bauhaus/codes/frequence/T\"},{\"code\":\"W\",\"prefLabelLg1\":\"Hebdomadaire\",\"prefLabelLg2\":\"Hebdomadaire\",\"uri\":\"http://bauhaus/codes/frequence/W\"},{\"code\":\"D\",\"prefLabelLg1\":\"Quotidienne\",\"prefLabelLg2\":\"Quotidienne\",\"uri\":\"http://bauhaus/codes/frequence/D\"},{\"code\":\"H\",\"prefLabelLg1\":\"Toutes les heures\",\"prefLabelLg2\":\"Toutes les heures\",\"uri\":\"http://bauhaus/codes/frequence/H\"},{\"code\":\"U\",\"prefLabelLg1\":\"Pluriannuelle\",\"prefLabelLg2\":\"Pluriannuelle\",\"uri\":\"http://bauhaus/codes/frequence/U\"},{\"code\":\"A3\",\"prefLabelLg1\":\"Triannuelle\",\"prefLabelLg2\":\"Triannuelle\",\"uri\":\"http://bauhaus/codes/frequence/A3\"}]";
    public final String CODELIST_GRAPH="CODELIST_GRAPH";
    public final String LEVEL = "[]";
    public final String MATCH = "[]";
    public static final ObjectMapper MAPPER = new JsonMapper();
    @Mock
    RepositoryGestion repoGestion;
    @Mock
    Config config;
    @InjectMocks
    CodeListImpl codeListImpl=new CodeListImpl(new FreeMarkerUtilsStub());
    @InjectMocks
    CodeListImpl codeList = new CodeListImpl();

    @BeforeAll
    static void setUp(){
        Config.LG1="fr";
        Config.LG2="en";
        Config.BASE_GRAPH="http://rdf.insee.fr/graphes/";
        Config.CODELIST_GRAPH="codes";
    }

    @MockitoSettings(strictness = Strictness.LENIENT)

    public static final String RDF_OUTPUT_EXPECTED = """
            [
              {
                "notation": "ISO-639",
                "statutValidation": "Publiée"
              },
              {
                "notation": "CL_SURVEY_UNIT"
              },
              {
                "notation": "CL_SURVEY_STATUS"
              },
              {
                "notation": "CL_FREQ"
              },
              {
                "notation": "CL_COLLECTION_MODE"
              },
              {
                "notation": "CL_SOURCE_CATEGORY"
              }
            ]
            """;

    public static final String RDF_OUTPUT = """
            [
              {
                "notation": "ISO-639",
                "statutValidation": "Validated"
              },
              {
                "notation": "CL_SURVEY_UNIT"
              },
              {
                "notation": "CL_SURVEY_STATUS"
              },
              {
                "notation": "CL_FREQ"
              },
              {
                "notation": "CL_COLLECTION_MODE"
              },
              {
                "notation": "CL_SOURCE_CATEGORY"
              }
            ]
            """;




    @Test
    void getAllCodesLists_shouldReturnList() throws RmesException, JsonProcessingException {
        JSONArray mockJSON = new JSONArray(RDF_OUTPUT);

        Mockito.when(repoGestion.getResponseAsArray(Mockito.anyString())).thenReturn(mockJSON);
        assertThat(MAPPER.readTree(codeList.getAllCodesLists())).isEqualTo(MAPPER.readTree(RDF_OUTPUT_EXPECTED));

    }


    @Test
    void getCodesListWithCodeWithStatutValidation_shouldReturnExpectedCodeList() throws RmesException, JsonProcessingException {
        JSONObject mockJSON_requete1 = new JSONObject(CODELIST_WITH_STATUT_VALIDATION);
        JSONArray mockJSON_requete2 = new JSONArray(CODES);
        JSONArray mockJSON_requete3 = new JSONArray(LEVEL);
        String mockString = new String (CODELIST_GRAPH);
        JSONArray mockJSON_requete4 = new JSONArray(MATCH);
        when(repoGestion.getResponseAsObject("getCodesList.ftlh")).thenReturn(mockJSON_requete1);
        when(repoGestion.getResponseAsArray("getCodes.ftlh")).thenReturn(mockJSON_requete2);
        when(repoGestion.getResponseAsArray("getCodeLevel.ftlh")).thenReturn(mockJSON_requete3);
        when(config.getCodelistGraph()).thenReturn(mockString);
        when(repoGestion.getResponseAsArray("getMatch.ftlh")).thenReturn(mockJSON_requete4);
        assertThat(MAPPER.readTree(codeListImpl.getCodesList("1"))).isEqualTo(MAPPER.readTree(CODELIST_WITH_STATUT_VALIDATION_EXPECTED.toString()));
    }

    @Test
    void getCodesListWithCodeWithoutStatutValidation_shouldReturnExpectedCodeList() throws RmesException, JsonProcessingException {
        JSONObject mockJSON_requete1 = new JSONObject(CODELIST_WITHOUT_STATUT_VALIDATION);
        JSONArray mockJSON_requete2 = new JSONArray(CODES);
        JSONArray mockJSON_requete3 = new JSONArray(LEVEL);
        String mockString = new String (CODELIST_GRAPH);
        JSONArray mockJSON_requete4 = new JSONArray(MATCH);
        when(repoGestion.getResponseAsObject("getCodesList.ftlh")).thenReturn(mockJSON_requete1);
        when(repoGestion.getResponseAsArray("getCodes.ftlh")).thenReturn(mockJSON_requete2);
        when(repoGestion.getResponseAsArray("getCodeLevel.ftlh")).thenReturn(mockJSON_requete3);
        when(config.getCodelistGraph()).thenReturn(mockString);
        when(repoGestion.getResponseAsArray("getMatch.ftlh")).thenReturn(mockJSON_requete4);
        assertThat(MAPPER.readTree(codeListImpl.getCodesList("1"))).isEqualTo(MAPPER.readTree(CODELIST_WITHOUT_STATUT_VALIDATION_EXPECTED.toString()));
    }

    @Test
    void getCodesListWithCode_shouldReturn404IfInexistentId() throws RmesException, JsonProcessingException {
        JSONObject mockJSON = new JSONObject(EMPTY_CODELIST);
        when(repoGestion.getResponseAsObject(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(()->codeListImpl.getCodesList("1")).isInstanceOf(RmesException.class)
                .matches(rmesException->((RmesException)rmesException).getStatus()==404)
                .hasMessageContaining("Non existent identifier");
    }
}