package fr.insee.rmes.services.codelists;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import fr.insee.rmes.persistence.RepositoryGestion;
import fr.insee.rmes.services.utils.CodeListUtilsTest;
import fr.insee.rmes.services.utils.ResponseUtilsTest;
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
    @MockitoSettings(strictness = Strictness.LENIENT)
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


    @Test
    void getAllCodesLists_shouldReturnList() throws RmesException, JsonProcessingException {
        JSONArray mockJSON = new JSONArray(CodeListUtilsTest.RDF_OUTPUT);

        Mockito.when(repoGestion.getResponseAsArray(Mockito.anyString())).thenReturn(mockJSON);
        assertThat(MAPPER.readTree(codeList.getAllCodesLists())).isEqualTo(MAPPER.readTree(CodeListUtilsTest.RDF_OUTPUT_EXPECTED));

    }


    @Test
    void getCodesListWithCodeWithStatutValidation_shouldReturnExpectedCodeList() throws RmesException, JsonProcessingException {
        JSONObject mockJSON_requete1 = new JSONObject(CodeListUtilsTest.CODELIST_WITH_STATUT_VALIDATION);
        JSONArray mockJSON_requete2 = new JSONArray(CodeListUtilsTest.CODES);
        JSONArray mockJSON_requete3 = new JSONArray(ResponseUtilsTest.EMPTY_JSON_ARRAY);
        String mockString = new String (CodeListUtilsTest.CODELIST_GRAPH);
        JSONArray mockJSON_requete4 = new JSONArray(ResponseUtilsTest.EMPTY_JSON_ARRAY);
        when(repoGestion.getResponseAsObject("getCodesList.ftlh")).thenReturn(mockJSON_requete1);
        when(repoGestion.getResponseAsArray("getCodes.ftlh")).thenReturn(mockJSON_requete2);
        when(repoGestion.getResponseAsArray("getCodeLevel.ftlh")).thenReturn(mockJSON_requete3);
        when(config.getCodelistGraph()).thenReturn(mockString);
        when(repoGestion.getResponseAsArray("getMatch.ftlh")).thenReturn(mockJSON_requete4);
        assertThat(MAPPER.readTree(codeListImpl.getCodesList("1"))).isEqualTo(MAPPER.readTree(CodeListUtilsTest.CODELIST_WITH_STATUT_VALIDATION_EXPECTED.toString()));
    }

    @Test
    void getCodesListWithCodeWithoutStatutValidation_shouldReturnExpectedCodeList() throws RmesException, JsonProcessingException {
        JSONObject mockJSON_requete1 = new JSONObject(CodeListUtilsTest.CODELIST_WITHOUT_STATUT_VALIDATION);
        JSONArray mockJSON_requete2 = new JSONArray(CodeListUtilsTest.CODES);
        JSONArray mockJSON_requete3 = new JSONArray(ResponseUtilsTest.EMPTY_JSON_ARRAY);
        String mockString = new String (CodeListUtilsTest.CODELIST_GRAPH);
        JSONArray mockJSON_requete4 = new JSONArray(ResponseUtilsTest.EMPTY_JSON_ARRAY);
        when(repoGestion.getResponseAsObject("getCodesList.ftlh")).thenReturn(mockJSON_requete1);
        when(repoGestion.getResponseAsArray("getCodes.ftlh")).thenReturn(mockJSON_requete2);
        when(repoGestion.getResponseAsArray("getCodeLevel.ftlh")).thenReturn(mockJSON_requete3);
        when(config.getCodelistGraph()).thenReturn(mockString);
        when(repoGestion.getResponseAsArray("getMatch.ftlh")).thenReturn(mockJSON_requete4);
        assertThat(MAPPER.readTree(codeListImpl.getCodesList("1"))).isEqualTo(MAPPER.readTree(CodeListUtilsTest.CODELIST_WITHOUT_STATUT_VALIDATION_EXPECTED.toString()));
    }

    @Test
    void getCodesListWithCode_shouldReturn404IfInexistentId() throws RmesException, JsonProcessingException {
        JSONObject mockJSON = new JSONObject(ResponseUtilsTest.EMPTY_JSON_OBJECT);
        when(repoGestion.getResponseAsObject(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(()->codeListImpl.getCodesList("1")).isInstanceOf(RmesException.class)
                .matches(rmesException->((RmesException)rmesException).getStatus()==404)
                .hasMessageContaining("Non existent codes list identifier");
    }

    @Test
    void getCodesListPagination_shouldReturn404IfInexistentId() throws RmesException, JsonProcessingException {
        JSONObject mockJSON = new JSONObject(ResponseUtilsTest.EMPTY_JSON_OBJECT);
        when(repoGestion.getResponseAsObject(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(()->codeListImpl.getMaxpage("1")).isInstanceOf(RmesException.class)
                .matches(rmesException->((RmesException)rmesException).getStatus()==404)
                .hasMessageContaining("Non existent codes list identifier");
    }
}