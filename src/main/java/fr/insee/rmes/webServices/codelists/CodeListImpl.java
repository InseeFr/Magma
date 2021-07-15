package fr.insee.rmes.webServices.codelists;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.exceptions.RmesException;

@Service
public class CodeListImpl extends RdfService implements CodeListsServices {

    @Override
    public String getAllCodesLists() throws RmesException {
        String defaultDate = "2020-01-01T00:00:00.000";

        HashMap<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", CODELIST_GRAPH);

        JSONArray codesLists =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getAllCodesLists.ftlh", params));
        for (int i = 0; i < codesLists.length(); i++) {
            JSONObject codesList = codesLists.getJSONObject(i);
            if(!codesList.has("dateMiseAJour")){
                codesList.put("dateMiseAJour", defaultDate);
            }
            if(codesList.has("statutValidation")){
                String validationState = codesList.getString("statutValidation");
                codesList.put("statutValidation", this.getValidationState(validationState));
            }
        }
        return codesLists.toString();
    }

    @Override
    public String getCodesList(String notation) throws RmesException {
        String defaultDate = "2020-01-01T00:00:00.000";

        HashMap<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", CODELIST_GRAPH);
        params.put("NOTATION", notation);
        params.put("LG1", LG1);
        params.put("LG2", LG2);

        JSONObject codesList =  repoGestion.getResponseAsObject(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodesList.ftlh", params));

        codesList.put("label", this.formatLabel(codesList));
        codesList.remove("prefLabelLg1");
        codesList.remove("prefLabelLg2");

        if(codesList.has("statutValidation")){
            String validationState = codesList.getString("statutValidation");
            codesList.put("statutValidation", this.getValidationState(validationState));
        }

        if(!codesList.has("dateCréation")){
            codesList.put("dateCréation", defaultDate);
        }
        if(!codesList.has("dateMiseAJour")){
            codesList.put("dateMiseAJour", defaultDate);
        }

        codesList.put("codes", this.getCodes(notation));

        return codesList.toString();
    }
	
    private JSONArray getCodes(String notation) throws RmesException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", CODELIST_GRAPH);
        params.put("NOTATION", notation);
        params.put("LG1", LG1);
        params.put("LG2", LG2);

        JSONArray codes =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodes.ftlh", params));

        JSONObject childrenMapping = new JSONObject();

        JSONObject formattedCodes = new JSONObject();

        for (int i = 0; i < codes.length(); i++) {
            JSONObject code = codes.getJSONObject(i);

            if(code.has(Constants.PARENTS)){
                JSONArray children = new JSONArray();
                String parentCode = code.getString(Constants.PARENTS);
                if(childrenMapping.has(parentCode)){
                    children = childrenMapping.getJSONArray(parentCode);
                }
                children.put(code.get("code"));
                childrenMapping.put(parentCode, children);
            }


            if(formattedCodes.has(code.getString(Constants.URI))){
                JSONObject c = formattedCodes.getJSONObject(code.getString(Constants.URI));

                if(code.has(Constants.PARENTS)){
                    JSONArray parents = c.getJSONArray(Constants.PARENTS);
                    parents.put(code.getString(Constants.PARENTS));
                    c.put(Constants.PARENTS, parents);
                }
            } else {
                code.put("label", this.formatLabel(code));
                code.remove(Constants.PREF_LABEL_LG1);
                code.remove(Constants.PREF_LABEL_LG2);

                if(code.has(Constants.PARENTS)){
                    JSONArray parents = new JSONArray();
                    parents.put(code.getString(Constants.PARENTS));
                    code.put(Constants.PARENTS, parents);
                } else {
                    code.put(Constants.PARENTS, new JSONArray());
                }
                formattedCodes.put(code.getString(Constants.URI), code);
            }
        }

        JSONArray result = new JSONArray();
        Iterator<String> keys = formattedCodes.keys();

        while(keys.hasNext()) {
            String key = keys.next();
            JSONObject code = formattedCodes.getJSONObject(key);
            if(childrenMapping.has(code.getString("code"))){
                code.put("enfants", childrenMapping.getJSONArray(code.getString("code")));
            }
            if(code.getJSONArray(Constants.PARENTS).length() == 0){
                code.remove(Constants.PARENTS);
            }
            result.put(code);
        }
        return result;
    }

    
}
