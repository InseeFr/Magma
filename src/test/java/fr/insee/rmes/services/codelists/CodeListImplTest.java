package fr.insee.rmes.services.codelists;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import fr.insee.rmes.persistence.RepositoryGestion;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.insee.rmes.utils.Constants.STATUT_VALIDATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CodeListImplTest {

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

    public static final String CODELIST_TEST_WITH_STATUT_VALIDATION = "{\"dateMiseAJour\":\"2023-01-01T00:00:00\",\"dateCreation\":\"2023-01-01T00:00:00\",\"statutValidation\":\"Provisoire, jamais publiée\",\"id\":\"CL_TEST\"}";
    public static final String CODELIST_TEST_WITHOUT_STATUT_VALIDATION = "{\"dateMiseAJour\":\"2023-01-01T00:00:00\",\"dateCreation\":\"2023-01-01T00:00:00\",\"id\":\"CL_TEST\"}";


    public static final String LABEL = "[{\"langue\":\"fr\",\"contenu\":\"test\"},{\"langue\":\"en\",\"contenu\":\"test\"}]";
    public static final String CODELIST_EXPECTED_WITH_STATUT_VALIDATION = "{\"dateMiseAJour\":\"2023-01-01T00:00:00\",\"dateCreation\":\"2023-01-01T00:00:00\",\"statutValidation\":\"Provisoire, jamais publiée\",\"id\":\"CL_TEST\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"test\"},{\"langue\":\"en\",\"contenu\":\"test\"}]}";
    public static final String CODELIST_EXPECTED_WITHOUT_STATUT_VALIDATION = "{\"dateMiseAJour\":\"2023-01-01T00:00:00\",\"dateCreation\":\"2023-01-01T00:00:00\",\"id\":\"CL_TEST\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"test\"},{\"langue\":\"en\",\"contenu\":\"test\"}]}";

    public static final String STATUT_VALIDATION_PUBLIEE = "Publiée";

    @InjectMocks
    CodeListImpl codeList = new CodeListImpl();
    @Mock
    RepositoryGestion repoGestion;
    public static final ObjectMapper MAPPER = new JsonMapper();

    @Test
    void getAllCodesLists_shouldReturnList() throws RmesException, JsonProcessingException {
        JSONArray mockJSON = new JSONArray(RDF_OUTPUT);

        Mockito.when(repoGestion.getResponseAsArray(Mockito.anyString())).thenReturn(mockJSON);
        assertThat(MAPPER.readTree(codeList.getAllCodesLists())).isEqualTo(MAPPER.readTree(RDF_OUTPUT_EXPECTED));

    }

    @Test
    void getCodesListWithoutCodes_With_Statut_Validation() throws JSONException {

        JSONObject codesList = new JSONObject(CODELIST_TEST_WITH_STATUT_VALIDATION);
        JSONArray label = new JSONArray(LABEL);
        codesList.put("label",label);

        if(codesList.has(STATUT_VALIDATION)){
            String validationState = codesList.getString(STATUT_VALIDATION);
            codesList.put(STATUT_VALIDATION, validationState);
        }
        codesList.remove(Constants.URI);
        assertTrue(codesList.toString().equals(CODELIST_EXPECTED_WITH_STATUT_VALIDATION));

    }

    @Test
    void getCodesListWithoutCodes_Without_Statut_Validation() throws JSONException {

        JSONObject codesList = new JSONObject(CODELIST_TEST_WITHOUT_STATUT_VALIDATION);
        JSONArray label = new JSONArray(LABEL);
        codesList.put("label",label);

        if(codesList.has(STATUT_VALIDATION)){
            String validationState = codesList.getString(STATUT_VALIDATION);
            codesList.put(STATUT_VALIDATION, STATUT_VALIDATION_PUBLIEE);
        }
        codesList.remove(Constants.URI);
        assertTrue(codesList.toString().equals(CODELIST_EXPECTED_WITHOUT_STATUT_VALIDATION));

    }

}

