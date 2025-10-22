package fr.insee.rmes.magma.gestion.old.services.utils;

import org.json.JSONObject;

public class CommonMethods {


    public static void removePrefLabels(JSONObject jsonObject){
        if (jsonObject.has("prefLabelLg1") && jsonObject.has("prefLabelLg2")){
            jsonObject.remove("prefLabelLg1");
            jsonObject.remove("prefLabelLg2");
        }
    }
}
