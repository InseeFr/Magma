package fr.insee.rmes.services.codelists;

import java.util.HashMap;
import java.util.Iterator;
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
    public String getAllCodesLists() throws RmesException {
        Map<String, Object> params = initParams();

        JSONArray codesLists =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getAllCodesLists.ftlh", params));

     for (int i = 0; i < codesLists.length(); i++) {
            JSONObject  codesList= codesLists.getJSONObject(i);
            if(codesList.has(STATUT_VALIDATION)){
                String validationState = codesList.getString(STATUT_VALIDATION);
                codesList.put(STATUT_VALIDATION, this.getValidationState(validationState));
            }
        }

        return codesLists.toString();

    }

    @Override
    public String getCodesList(String notation) throws RmesException {
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

        codesList.put("codes", this.getCodes(notation));

        return codesList.toString();
    }

	public Map<String, Object> initParams() {
		Map<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);
		return params;
	}

    private JSONArray getCodes(String notation) throws RmesException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", Config.BASE_GRAPH + config.getCodelistGraph());
        params.put("NOTATION", notation);
        params.put("LG1", config.getLG1());
        params.put("LG2", config.getLG2());

        JSONArray codes =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodes.ftlh", params));
        JSONArray levels =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodeLevel.ftlh", params));

        JSONObject childrenMapping = new JSONObject();
        JSONObject formattedCodes = new JSONObject();

        for (int i = 0; i < codes.length(); i++) {
            JSONObject code = codes.getJSONObject(i);

            HashMap<String, Object> closeMatchParams = new HashMap<>();
            closeMatchParams.put("CONCEPTS_GRAPH", Config.BASE_GRAPH + config.getCodelistGraph());
            closeMatchParams.put("CONCEPT_ID", code.getString("code"));
            JSONArray closeMatch = repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCloseMatch.ftlh", closeMatchParams));

            if(closeMatch.length() > 0){
                JSONArray codesEquivalents = new JSONArray();
                for(int j = 0; j < closeMatch.length(); j++){
                    JSONObject codeEquivalent = new JSONObject();
                    codeEquivalent.put("code", closeMatch.getJSONObject(j).getString("closeMatchNotation"));
                    codeEquivalent.put("uri", closeMatch.getJSONObject(j).getString("closeMatch"));
                    codesEquivalents.put(codeEquivalent);
                }
                code.put("codesEquivalents", codesEquivalents);
            }
            if(code.has(Constants.PARENTS)){
                JSONArray children = new JSONArray();
                String parentCode = code.getString(Constants.PARENTS);
                if(childrenMapping.has(parentCode)){
                    children = childrenMapping.getJSONArray(parentCode);
                }
                children.put(code.get("code"));
                childrenMapping.put(parentCode, children);
            }

            JSONArray codeLevels = new JSONArray();
            for(int j = 0; j < levels.length(); j++){
                if(levels.getJSONObject(j).getString(Constants.URI).equalsIgnoreCase(code.getString(Constants.URI))){
                    codeLevels.put(new JSONObject().put("id", levels.getJSONObject(j).getString("idNiveau")));
                }
            }
            if(codeLevels.length() > 0){
                code.put("niveaux", codeLevels);
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
