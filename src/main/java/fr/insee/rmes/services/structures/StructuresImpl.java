package fr.insee.rmes.services.structures;

import java.util.HashMap;

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


}
