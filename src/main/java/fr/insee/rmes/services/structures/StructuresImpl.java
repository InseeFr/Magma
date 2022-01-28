package fr.insee.rmes.services.structures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fr.insee.rmes.persistence.FreeMarkerUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;

@Service 
public class StructuresImpl extends RdfService implements StructuresServices {


	
	@Override
	public String getAllStructures() throws RmesException {
		HashMap<String, Object> params = new HashMap<>();
		params.put("STRUCTURES_GRAPH", Config.BASE_GRAPH+Config.STRUCTURES_GRAPH);
		JSONArray structures =  repoGestion.getResponseAsArray(buildRequest(Constants.STRUCTURES_QUERIES_PATH,"getAllStructures.ftlh", params));
		for (int i = 0; i < structures.length(); i++) {
			JSONObject structure = structures.getJSONObject(i);
			String validationState = structure.getString("statutValidation");
			structure.put("statutValidation", this.getValidationState(validationState));
		}

		return structures.toString();
	}

	@Override
	public String getStructure(String id) throws RmesException {
		String defaultDate = "2020-01-01T00:00:00.000";
		HashMap<String, Object> params = new HashMap<>();
		params.put("STRUCTURES_GRAPH", Config.BASE_GRAPH+Config.STRUCTURES_GRAPH);
		params.put("STRUCTURE_ID", id);
		params.put("LG1", Config.LG1);
		params.put("LG2", Config.LG2);

		JSONObject structure =  repoGestion.getResponseAsObject(buildRequest(Constants.STRUCTURES_QUERIES_PATH,"getStructure.ftlh", params));

		structure.put("label", this.formatLabel(structure));
		structure.remove("prefLabelLg1");
		structure.remove("prefLabelLg2");

		if(structure.has("statutValidation")){
			String validationState = structure.getString("statutValidation");
			structure.put("statutValidation", this.getValidationState(validationState));
		}

		if(!structure.has("dateCréation")){
			structure.put("dateCréation", defaultDate);
		}
		if(!structure.has("dateMiseAJour")){
			structure.put("dateMiseAJour", defaultDate);
		}

		getStructureComponents(id, structure);
		return structure.toString();
	}

	private void getStructureComponents(String id, JSONObject structure) throws RmesException {
		HashMap<String, Object> params = new HashMap<>();
		params.put("STRUCTURES_GRAPH", Config.BASE_GRAPH+Config.STRUCTURES_GRAPH);
		params.put("STRUCTURES_COMPONENTS_GRAPH", Config.BASE_GRAPH+Config.STRUCTURES_COMPONENTS_GRAPH);
		params.put("CONCEPTS_GRAPH", Config.BASE_GRAPH+Config.CONCEPTS_GRAPH);
		params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);

		params.put("STRUCTURE_ID", id);
		params.put("LG1", Config.LG1);
		params.put("LG2", Config.LG2);

		JSONArray components = repoGestion.getResponseAsArray(buildRequest(Constants.STRUCTURES_QUERIES_PATH,"getStructureComponents.ftlh", params));

		JSONArray measures = new JSONArray();
		JSONArray dimensions = new JSONArray();
		JSONArray attributes = new JSONArray();

		for (int i = 0; i < components.length(); i++) {
			JSONObject component = components.getJSONObject(i);
			component.put("label", this.formatLabel(component));
			component.remove("prefLabelLg1");
			component.remove("prefLabelLg2");

			if(component.has("listeCodeUri")){
				component.put("representation", "liste de code");

				JSONObject listCode = new JSONObject();
				listCode.put("uri", component.getString("listeCodeUri"));
				listCode.put("id", component.getString("listeCodeNotation"));
				component.put("listCode", listCode);
				component.remove("listeCodeUri");
				component.remove("listeCodeNotation");
			}

			if(component.has("conceptUri")){

				JSONObject concept = new JSONObject();
				concept.put("uri", component.getString("conceptUri"));
				concept.put("id", component.getString("conceptId"));
				component.put("concept", concept);
				component.remove("conceptUri");
				component.remove("conceptId");
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

			String idComponent = component.getString("id");
			component.remove("id");
			if(idComponent.startsWith("a")){
				attributes.put(component);
			}
			if(idComponent.startsWith("m")){
				measures.put(component);
			}
			if(idComponent.startsWith("d")){
				dimensions.put(component);
			}
		}
		structure.put("attributs", attributes);
		structure.put("mesures", measures);
		structure.put("dimensions", dimensions);
	}

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
		HashMap<String, Object> params = new HashMap<>();
		params.put("STRUCTURES_COMPONENTS_GRAPH", Config.BASE_GRAPH+Config.STRUCTURES_COMPONENTS_GRAPH);
		params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);
		params.put("CONCEPTS_BASE_URI", Config.CONCEPTS_BASE_URI);
		params.put("ID", id);
		params.put("LG1", Config.LG1);
		params.put("LG2", Config.LG2);

		JSONObject component =  repoGestion.getResponseAsObject(buildRequest("getComponent.ftlh", params));

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

		JSONArray flatChildren = repoGestion.getResponseAsArray(buildRequest("getComponentChildren.ftlh", params));
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
			if(component.getString("representation").endsWith("dateTime")){
				component.put("representation", "date et heure");
			} else if(component.getString("representation").endsWith("date")){
				component.put("representation", "date");
			} else if(component.getString("representation").endsWith("integer")){
				component.put("representation", "entier");
			} else if(component.getString("representation").endsWith("double")){
				component.put("representation", "décimal");
			} else if(component.getString("representation").endsWith("string")){
				component.put("representation", "texte");
			} else if(component.getString("representation").endsWith("paysOuTerritoire")){
				component.put("representation", "Pays ou territoire");
			}
		}

		if(component.has("minLength") || component.has("maxLength") || component.has("minInclusive") || component.has("maxInclusive") || component.has("pattern")){
			JSONObject format = new JSONObject();

			if (component.has("minLength")) {
				format.put("longueurMin", component.get("minLength"));
			}
			if (component.has("maxLength")) {
				format.put("longueurMax", component.get("maxLength"));
			}
			if (component.has("minInclusive")) {
				format.put("valeurMin", component.get("minInclusive"));
			}
			if (component.has("maxInclusive")) {
				format.put("valeurMax", component.get("maxInclusive"));
			}
			if (component.has("pattern")) {
				format.put("expressionReguliere", component.get("pattern"));
			}
			component.put("format", format);
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

	private static String buildRequest(String fileName, HashMap<String, Object> params) throws RmesException {
		return FreeMarkerUtils.buildRequest("components/", fileName, params);
	}

}
