package fr.insee.rmes.services.utils;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommonMethodsTest {

    @Test
    void shouldNotRemovePrefLabelsWhenBothPrefLabelsAreAbsents(){
        JSONObject jsonObject = new JSONObject().put("sentence","hello the world").put("animal","rabbit");
        String before= jsonObject.toString();
        CommonMethods.removePrefLabels(jsonObject);
        assertEquals(before,jsonObject.toString());
    }

    @Test
    void shouldNotRemovePrefLabelsWhenOneOfPrefLabelsIsAbsent(){
        JSONObject jsonObject = new JSONObject().put("prefLabelLg1","label1").put("animal","rabbit");
        String before= jsonObject.toString();
        CommonMethods.removePrefLabels(jsonObject);
        assertEquals(before,jsonObject.toString());
    }

    @Test
    void shouldRemovePrefLabelsWhenNoPrefLabelsIsAbsent(){
        JSONObject jsonObject = new JSONObject().put("prefLabelLg1","label1").put("animal","rabbit").put("prefLabelLg2","label2");
        String before= jsonObject.toString();
        CommonMethods.removePrefLabels(jsonObject);
        assertEquals("{\"animal\":\"rabbit\"}",jsonObject.toString());
    }

}