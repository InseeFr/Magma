package fr.insee.rmes.persistence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.*;

class RdfServiceTest {

    private RdfService rdfService;

    @BeforeEach
    void init(){
        rdfService=new RdfService() {};
    }

    @Test
    @DisplayName("Test de formatLabel, cas nominal")
    void formatLabelTest_nominal() throws JSONException{
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("prefLabelLg1", "description en français");
        jsonObject.put("prefLabelLg2", "english description");
        JSONArray expected=new JSONArray("[{\"contenu\":\"description en français\"},{\"contenu\":\"english description\"}]");
        JSONAssert.assertEquals(expected, rdfService.formatLabel(jsonObject), false);
    }

    @Test
    void formatLabelTest_empty() {
        JSONObject jsonObject=new JSONObject();
        assertThrows(JSONException.class, ()->rdfService.formatLabel(jsonObject));
    }

    @Test
    @DisplayName("Test de formatNom, cas nominal")
    void formatNomTest_nominal() throws JSONException{
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("altLabelLg1", "description en français");
        jsonObject.put("altLabelLg2", "english description");
        JSONArray expected=new JSONArray("[{\"contenu\":\"description en français\"},{\"contenu\":\"english description\"}]");
        System.out.println(rdfService.formatNom(jsonObject));
        JSONAssert.assertEquals(expected, rdfService.formatNom(jsonObject), false);
    }

    @Test
    @DisplayName("Test de getValidationState")
    void getValidationStateTest() {
        //GIVEN
        String str = "Validated";
        String str2 = "UnPubLIShed";
        String str3 = "Modified";
        //WHEN
        String result = rdfService.getValidationState(str);
        String result2 = rdfService.getValidationState(str2);
        String result3 = rdfService.getValidationState(str3);
        //THEN
        assertEquals("Publiée", result);
        assertEquals("Provisoire, jamais publiée", result2);
        assertEquals("Provisoire, déjà publiée", result3);
    }



}