package fr.insee.rmes.services.codelists;

import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CodeListImpl extends RdfService implements CodeListsServices {

    private static final String DATE_MISE_A_JOUR = "dateMiseAJour";
    private static final String STATUT_VALIDATION = "statutValidation";
    private static final String DEFAULT_DATE = "2020-01-01T00:00:00.000";
    private static final String NOTATION = "NOTATION";

    @Override
    public String getAllCodesLists() throws RmesException {
        Map<String, Object> params = initParams();

        JSONArray codesLists =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getAllCodesLists.ftlh", params));

        for (int i = 0; i < codesLists.length(); i++) {
            JSONObject codesList= codesLists.getJSONObject(i);
            if(codesList.has(STATUT_VALIDATION)){
                String validationState = codesList.getString(STATUT_VALIDATION);
                codesList.put(STATUT_VALIDATION, this.getValidationState(validationState));
            }
        }

        return codesLists.toString();

    }

    @Override
    public String getCodesList(String notation) throws RmesException {

        Map<String, Object> params = initParamsNotation(notation);

        JSONObject codesList =  repoGestion.getResponseAsObject(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodesList.ftlh", params));

        codesList.put("label", this.formatLabel(codesList));
        codesList.remove("prefLabelLg1");
        codesList.remove("prefLabelLg2");

        if(codesList.has(STATUT_VALIDATION)){
            String validationState = codesList.getString(STATUT_VALIDATION);
            codesList.put(STATUT_VALIDATION, this.getValidationState(validationState));
        }

        codesList.put("codes", this.getCodes(notation));
        codesList.remove(Constants.URI);
        return codesList.toString();
    }

    public Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);
        return params;
    }

    //the same method as initParams() with notations, LG1 and LG2 in addition

    public Map<String, Object> initParamsNotation(String notation) {
        Map<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);
        params.put(NOTATION, notation);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
        return params;
    }


    private JSONArray getCodes(String notation) throws RmesException {
        Map<String, Object> params = initParamsNotation(notation);

        JSONArray codes =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodes.ftlh", params));
        JSONArray levels =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodeLevel.ftlh", params));

        ArrayList<JSONObject> FormattedCodeAndChildrenMapping = getFormattedCodeAndChildrenMapping(codes, levels);

        JSONObject formattedCodes = FormattedCodeAndChildrenMapping.get(0);
        JSONObject childrenMapping = FormattedCodeAndChildrenMapping.get(1);

        JSONArray result =  getResult(formattedCodes, childrenMapping, codes);

        return  result;
    }


    private ArrayList<JSONObject> getFormattedCodeAndChildrenMapping(JSONArray codes, JSONArray levels) throws RmesException {

        JSONObject childrenMapping = new JSONObject();
        JSONObject formattedCodes = new JSONObject();


        for (int i = 0; i < codes.length(); i++) {
            JSONObject code = codes.getJSONObject(i);

            HashMap<String, Object> matchParams = new HashMap<>();
            matchParams.put("CONCEPTS_GRAPH", Config.BASE_GRAPH + config.getCodelistGraph());
            matchParams.put("URI",code.getString("uri"));
            JSONArray match = repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getMatch.ftlh", matchParams));

            if(match.length() > 0){
                List<String> codesEquivalents = new ArrayList<>();
                for(int j = 0; j < match.length(); j++){
                    String codeEquivalent = match.getJSONObject(j).getString("matchNotation");
                    codesEquivalents.add(codeEquivalent);
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
        ArrayList<JSONObject> rep = new ArrayList<>();
        rep.add(formattedCodes);
        rep.add(childrenMapping);

        return rep;
    }


    private JSONArray getResult(JSONObject formattedCodes, JSONObject childrenMapping, JSONArray codes) {
        Iterator<String> keys = formattedCodes.keys();
        JSONArray result = new JSONArray();

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
        JSONArray resultWithoutURI = new JSONArray();
        for (int i = 0; i < result.length(); i++) {
            JSONObject resultObject = codes.getJSONObject(i);
            resultObject.remove(Constants.URI);
            resultWithoutURI.put(resultObject);
        }
        return resultWithoutURI;
    }

    @Override
    public String getCodesListDateMiseAJour(String notation) throws RmesException{
        Map<String, Object> params = initParams();
        params.put(NOTATION, notation);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);

        JSONObject codesList =  repoGestion.getResponseAsObject(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodesListDateMAJ.ftlh", params));

        if(codesList.has(STATUT_VALIDATION)){
            String validationState = codesList.getString(STATUT_VALIDATION);
            codesList.put(STATUT_VALIDATION, this.getValidationState(validationState));
        }

        return codesList.toString();
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

    @Override
    public Integer getMaxpage(String notation) throws RmesException {
        Map<String, Object> params = initParamsNotation(notation);
        JSONObject counter = repoGestion.getResponseAsObject(buildRequest(Constants.CODELISTS_QUERIES_PATH,"countNumberOfCodes.ftlh",params));
        int quotient_page = Integer.valueOf((String) counter.get("count")) / config.PERPAGE;
        int reste_page = Integer.valueOf((String) counter.get("count")) % config.PERPAGE;
        int total = 0;
        if (reste_page == 0){
            total = quotient_page;
        } else if (reste_page != 0 ) {
            total = quotient_page + 1;
        }
        return total;
    }

    public String getCodesListPagination(String notation, int pageNumber)throws RmesException{
        Map<String, Object> params = initParamsNotation(notation);
        JSONObject result = repoGestion.getResponseAsObject(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodesList.ftlh",params));
        result.put("label", this.formatLabel(result));
        result.remove("prefLabelLg1");
        result.remove("prefLabelLg2");
        if(result.has(STATUT_VALIDATION)){
            String validationState = result.getString(STATUT_VALIDATION);
            result.put(STATUT_VALIDATION, this.getValidationState(validationState));
        }

        Integer total = getMaxpage(notation);
        result.put("maxPage",total);
        result.put("codes", this.getCodesPagination(notation,pageNumber));
        result.remove(Constants.URI);
        String page = String.valueOf(pageNumber) + "/" + String.valueOf(total);
        result.put("page", page);
        return result.toString();
    }

    private JSONArray getCodesPagination(String notation,int pageNumber) throws RmesException {
        Map<String, Object> params = initParamsNotation(notation);
        params.put("OFFSET",config.PERPAGE * (pageNumber - 1));
        params.put("PER_PAGE",config.PERPAGE);
        JSONArray codes =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodesPagination.ftlh", params));
        JSONArray levels =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodeLevel.ftlh", params));

        ArrayList<JSONObject> FormattedCodeAndChildrenMapping = getFormattedCodeAndChildrenMapping(codes, levels);

        JSONObject formattedCodes = FormattedCodeAndChildrenMapping.get(0);
        JSONObject childrenMapping = FormattedCodeAndChildrenMapping.get(1);

        JSONArray result =  getResult(formattedCodes, childrenMapping, codes);

        return  result;
    }


}
