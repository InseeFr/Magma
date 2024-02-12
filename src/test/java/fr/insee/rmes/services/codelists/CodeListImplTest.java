package fr.insee.rmes.services.codelists;

import fr.insee.rmes.persistence.FreeMarkerUtils;
import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.persistence.RepositoryGestion;
import fr.insee.rmes.services.utils.CommonMethods;
import fr.insee.rmes.stubs.FreeMarkerUtilsStub;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;
import java.util.Map;

import static fr.insee.rmes.utils.Constants.STATUT_VALIDATION;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CodeListImplTest {

    public static final String CODELIST_TEST_WITH_STATUT_VALIDATION = "{\"dateMiseAJour\":\"2023-01-01T00:00:00\",\"dateCreation\":\"2023-01-01T00:00:00\",\"statutValidation\":\"Provisoire, jamais publiée\",\"id\":\"CL_TEST\"}";
    public static final String CODELIST_TEST_WITHOUT_STATUT_VALIDATION = "{\"dateMiseAJour\":\"2023-01-01T00:00:00\",\"dateCreation\":\"2023-01-01T00:00:00\",\"id\":\"CL_TEST\"}";


    public static final String LABEL = "[{\"langue\":\"fr\",\"contenu\":\"test\"},{\"langue\":\"en\",\"contenu\":\"test\"}]";
    public static final String CODELIST_EXPECTED_WITH_STATUT_VALIDATION = "{\"dateMiseAJour\":\"2023-01-01T00:00:00\",\"dateCreation\":\"2023-01-01T00:00:00\",\"statutValidation\":\"Provisoire, jamais publiée\",\"id\":\"CL_TEST\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"test\"},{\"langue\":\"en\",\"contenu\":\"test\"}]}";
    public static final String CODELIST_EXPECTED_WITHOUT_STATUT_VALIDATION = "{\"dateMiseAJour\":\"2023-01-01T00:00:00\",\"dateCreation\":\"2023-01-01T00:00:00\",\"id\":\"CL_TEST\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"test\"},{\"langue\":\"en\",\"contenu\":\"test\"}]}";

    public static final String STATUT_VALIDATION_PUBLIEE = "Publiée";


    @Mock
    RepositoryGestion repoGestion;

    @InjectMocks
    CodeListImpl codeListImpl=new CodeListImpl(new FreeMarkerUtilsStub());

    @Test
    void useMock() throws RmesException {
        when(repoGestion.getResponseAsObject("getAllCodesLists.ftlh")).thenReturn(new JSONObject());
        when(repoGestion.getResponseAsObject("getCodes.ftlh")).thenReturn()
        assertEquals("", codeListImpl.getCodesList("1"));
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