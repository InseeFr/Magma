package fr.insee.rmes.services.codelists;

import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.services.utils.CommonMethods;
import fr.insee.rmes.utils.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static fr.insee.rmes.utils.Constants.STATUT_VALIDATION;
import static org.junit.jupiter.api.Assertions.*;

class CodeListImplTest {

    public static final String CODELIST_TEST_WITH_STATUT_VALIDATION = "{\"dateMiseAJour\":\"2023-01-01T00:00:00\",\"dateCreation\":\"2023-01-01T00:00:00\",\"statutValidation\":\"Provisoire, jamais publiée\",\"id\":\"CL_TEST\"}";
    public static final String CODELIST_TEST_WITHOUT_STATUT_VALIDATION = "{\"dateMiseAJour\":\"2023-01-01T00:00:00\",\"dateCreation\":\"2023-01-01T00:00:00\",\"id\":\"CL_TEST\"}";


    public static final String LABEL = "[{\"langue\":\"fr\",\"contenu\":\"test\"},{\"langue\":\"en\",\"contenu\":\"test\"}]";
    public static final String CODELIST_EXPECTED_WITH_STATUT_VALIDATION = "{\"dateMiseAJour\":\"2023-01-01T00:00:00\",\"dateCreation\":\"2023-01-01T00:00:00\",\"statutValidation\":\"Provisoire, jamais publiée\",\"id\":\"CL_TEST\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"test\"},{\"langue\":\"en\",\"contenu\":\"test\"}]}";
    public static final String CODELIST_EXPECTED_WITHOUT_STATUT_VALIDATION = "{\"dateMiseAJour\":\"2023-01-01T00:00:00\",\"dateCreation\":\"2023-01-01T00:00:00\",\"id\":\"CL_TEST\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"test\"},{\"langue\":\"en\",\"contenu\":\"test\"}]}";

    public static final String STATUT_VALIDATION_PUBLIEE = "Publiée";

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