package fr.insee.rmes.services.components;

import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static fr.insee.rmes.utils.config.Config.CODELIST_GRAPH;

@Service
public class ComponentImpl extends RdfService implements ComponentServices {

    private static final String STATUT_VALIDATION = "statutValidation";

    @Override
    public String getAllComponents() throws RmesException {
        Map<String, Object> params = initParams();
        JSONArray components =  repoGestion.getResponseAsArray(buildRequest(Constants.COMPONENTS_QUERIES_PATH,"getComponents.ftlh", params));

        for (int i = 0; i < components.length(); i++) {
            JSONObject  component= components.getJSONObject(i);
            if(component.has(STATUT_VALIDATION)){
                String validationState = component.getString(STATUT_VALIDATION);
                component.put(STATUT_VALIDATION, this.getValidationState(validationState));
            }
        }

        return components.toString();
    }

    @Override
    public String getComponent(String id) throws RmesException {
        Map<String, Object> params = new HashMap<>();
        params.put("STRUCTURES_COMPONENTS_GRAPH", Config.BASE_GRAPH+Config.STRUCTURES_COMPONENTS_GRAPH);
        params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);
        params.put("CONCEPTS_BASE_URI", Config.CONCEPTS_BASE_URI);
        params.put("ID", id);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);

        JSONObject component =  repoGestion.getResponseAsObject(buildRequest(Constants.COMPONENTS_QUERIES_PATH,"getComponent.ftlh", params));

        component.put("label", this.formatLabel(component));
        component.remove("prefLabelLg1");
        component.remove("prefLabelLg2");

        if(component.has("uriComponentParentId")){
            JSONObject parent = new JSONObject();
            parent.put("id", component.getString("uriComponentParentId"));
            parent.put("notation", component.getString("uriComponentParentNotation"));
            component.remove("uriComponentParentId");
            component.remove("uriComponentParentNotation");
            component.put("parent", parent);
        }

        JSONArray flatChildren = repoGestion.getResponseAsArray(buildRequest(Constants.COMPONENTS_QUERIES_PATH,"getComponentChildren.ftlh", params));
        if(flatChildren.length() > 0) {
            component.put("enfants", flatChildren);
        }
        if(component.has("statutValidation")){
            String validationState = component.getString("statutValidation");
            component.put("statutValidation", this.getValidationState(validationState));
        }

        if(component.has("uriListeCode")){
            component.put("representation", "liste de code");

            JSONArray codes = getCodes(component.getString("idListeCode"));
            JSONObject listCode = new JSONObject();
            listCode.put("uri", component.getString("uriListeCode"));
            listCode.put("id", component.getString("idListeCode"));
            listCode.put("codes", codes);
            component.put("listeCode", listCode);
            component.remove("uriListeCode");
            component.remove("idListeCode");
        }

        if(component.has("uriConcept")){

            JSONObject concept = new JSONObject();
            concept.put("uri", component.getString("uriConcept"));
            concept.put("id", component.getString("idConcept"));
            component.put("concept", concept);
            component.remove("uriConcept");
            component.remove("idConcept");
        }

        if(component.has("representation")){
            if(component.getString("representation").endsWith("date")){
                component.put("representation", "date");
            } else if(component.getString("representation").endsWith("int")){
                component.put("representation", "entier");
            } else if(component.getString("representation").endsWith("float")){
                component.put("representation", "décimal");
            }
        }

        return component.toString();

    }

    public Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("STRUCTURES_COMPONENTS_GRAPH", Config.BASE_GRAPH+Config.STRUCTURES_COMPONENTS_GRAPH);
        return params;
    }

    private JSONArray getCodes(String notation) throws RmesException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);
        params.put("NOTATION", notation);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);

        JSONArray codes =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodes.ftlh", params));
        JSONArray levels =  repoGestion.getResponseAsArray(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodeLevel.ftlh", params));

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
}
