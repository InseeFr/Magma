package fr.insee.rmes.services.codelists;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;

@Service
public class CodeListImpl extends RdfService implements CodeListsServices {

    private static final String DATE_MISE_A_JOUR = "dateMiseAJour";
	private static final String STATUT_VALIDATION = "statutValidation";
	private static final String DEFAULT_DATE = "2020-01-01T00:00:00.000";

	@Override
    public List getAllCodesLists() throws RmesException {
        Map<String, Object> params = initParams();

        JSONArray codesLists =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getAllCodesLists.ftlh", params));

      for (int i = 0; i < codesLists.length(); i++) {
            JSONObject  codesList= codesLists.getJSONObject(i);
            if(!codesList.has(DATE_MISE_A_JOUR)){
                codesList.put(DATE_MISE_A_JOUR, DEFAULT_DATE);
            }
            if(codesList.has(STATUT_VALIDATION)){
                String validationState = codesList.getString(STATUT_VALIDATION);
                codesList.put(STATUT_VALIDATION, this.getValidationState(validationState));
            }
        }

        return codesLists.toList();

    }

    @Override
    public Object getCodesList(String notation) throws RmesException {
        Map<String, Object> params = initParams();
        params.put("NOTATION", notation);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);

        JSONObject codesList =  repoGestion.getResponseAsObject(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodesList.ftlh", params));

        codesList.put("label", this.formatLabel(codesList));
        codesList.remove("prefLabelLg1");
        codesList.remove("prefLabelLg2");

        if(codesList.has(STATUT_VALIDATION)){
            String validationState = codesList.getString(STATUT_VALIDATION);
            codesList.put(STATUT_VALIDATION, this.getValidationState(validationState));
        }

        if(!codesList.has("dateCréation")){
            codesList.put("dateCréation", DEFAULT_DATE);
        }
        if(!codesList.has(DATE_MISE_A_JOUR)){
            codesList.put(DATE_MISE_A_JOUR, DEFAULT_DATE);
        }

        codesList.put("codes", this.getCodes(notation));

        return codesList;
    }

	public Map<String, Object> initParams() {
		Map<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);
		return params;
	}

    private JSONArray getCodes(String notation) throws RmesException {
        Map<String, Object> params = initParams();
        params.put("NOTATION", notation);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);

        JSONArray codes =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodes.ftlh", params));

        JSONObject childrenMapping = new JSONObject();
        JSONObject formattedCodes = new JSONObject();

        for (int i = 0; i < codes.length(); i++) {
            JSONObject code = codes.getJSONObject(i);
            mapOtherChildren(childrenMapping, code);
            formatCode(formattedCodes, code);
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

	public void mapOtherChildren(JSONObject childrenMapping, JSONObject code) {
		if(code.has(Constants.PARENTS)){
		    JSONArray children = new JSONArray();
		    String parentCode = code.getString(Constants.PARENTS);
		    if(childrenMapping.has(parentCode)){
		        children = childrenMapping.getJSONArray(parentCode);
		    }
		    children.put(code.get("code"));
		    childrenMapping.put(parentCode, children);
		}
	}

	public void formatCode(JSONObject formattedCodes, JSONObject code) {
		if(formattedCodes.has(code.getString(Constants.URI))){
		    JSONObject c = formattedCodes.getJSONObject(code.getString(Constants.URI));

		    if(code.has(Constants.PARENTS)){
		        JSONArray parents = c.getJSONArray(Constants.PARENTS);
		        parents.put(code.getString(Constants.PARENTS));
		        c.put(Constants.PARENTS, parents);
		    }
		} else {
		    code.put(Constants.LABEL, this.formatLabel(code));
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

    
}
