package fr.insee.rmes.services.codelists;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.utils.config.Config;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CodeListImplTest {


    public static final String URI_TEST = "http://uri.test";

    private static final String DATE_TEST_A = "2020-01-01T00:00:00.000";
    private static final String DATE_TEST_B = "2023-01-04T08:00:00.000";
    private static final String STATUT_VALIDATION_A = "Provisoire, jamais publiée";
    private static final String STATUT_VALIDATION_B = "Publiée";



    @Test
    void getAllCodesLists() throws JSONException {
        Map<String, Object> params = new HashMap<>();
        JSONObject codeA = new JSONObject();
        codeA.put("notation","A");
        codeA.put("dateMiseAJour",DATE_TEST_A);
        codeA.put("statutValidation",STATUT_VALIDATION_A);
        JSONObject codeB = new JSONObject();
        codeB.put("notation","B");
        codeB.put("dateMiseAJour",DATE_TEST_B);
        codeB.put("statutValidation",STATUT_VALIDATION_B);
        JSONObject codeC = new JSONObject();
        codeC.put("notation","C");
        JSONArray codeLists = new JSONArray();
        codeLists.put(codeA);
        codeLists.put(codeB);
        codeLists.put(codeC);
        codeLists.toString();
    }

    @Test
    void getCodesList() throws JSONException {
        JSONObject codeA = new JSONObject();
        codeA.put("notation","A");
        codeA.put("dateMiseAJour",DATE_TEST_A);
        codeA.put("statutValidation",STATUT_VALIDATION_A);
        JSONArray label = new JSONArray();
        JSONObject lg1 = new JSONObject();
        JSONObject lg2 = new JSONObject();
        lg1.put("langue", Config.LG1);
        lg2.put("langue", Config.LG2);
        lg1.put("contenu", "prefLabelLg1");
        lg2.put("contenu", "prefLabelLg2");
        label.put(lg1);
        label.put(lg2);
        codeA.put("label",label);
        codeA.toString()
    }

    @Test
    void initParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);
    }

    @Test
    void initParamsNotation() {
        Map<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);
        params.put("NOTATION", "notation");
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
    }

    @Test
    void getCodesListDateMiseAJour() {
    }

    @Test
    void mapOtherChildren() {
    }

    @Test
    void formatCode() {
    }
}