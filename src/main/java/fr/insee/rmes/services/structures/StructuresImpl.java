package fr.insee.rmes.services.structures;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.modelSwagger.structure.StructureByIdModelSwagger;
import fr.insee.rmes.persistence.FreeMarkerUtils;
import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.persistence.ontologies.IGEO;
import fr.insee.rmes.persistence.ontologies.QB;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.xml.soap.SAAJResult;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Service 
public class StructuresImpl extends RdfService implements StructuresServices {

	private static final String STRUCTURES_GRAPH = "STRUCTURES_GRAPH";
	private static final String STRUCTURE_ID = "STRUCTURE_ID";
	private static final String LABEL = "label";
	
	private static final String ID_RELATION="idRelation";
	private static final String ID_PARENT = "idParent";
	private static final String URI_PARENT = "uriParent";
	private static final String ID_PARENT_RELATION = "idParentRelation";
	private static final String STRUCTURES_COMPONENTS_GRAPH_KEY = "STRUCTURES_COMPONENTS_GRAPH";

	private static final String ORDRE="ordre";
	private static final String OBLIGATOIRE = "obligatoire";
	private static final String CODELIST_GRAPH_KEY = "CODELIST_GRAPH";
	private static final String URI_COMPONENT_PARENT_ID = "uriComponentParentId";
	private static final String URI_LISTE_CODE = "uriListeCode";
	private static final String REPRESENTATION = "representation";

	private static final String LISTE_CODE = "ListeCode";
	private static final String ID_LISTE_CODE = "idListeCode";
	private static final String URI_PARENT_LISTE_CODE = "uriParentListCode";
	private static final String ID_PARENT_LISTE_CODE = "idParentListCode";
	private static final String MIN_LENGTH = "minLength";
	private static final String MAX_LENGTH = "maxLength";
	private static final String MIN_INCLUSIVE = "minInclusive";
	private static final String MAX_INCLUSIVE = "maxInclusive";
	private static final String PATTERN = "pattern";
	private static final String URI_CONCEPT = "uriConcept";
	String defaultDate = "2020-01-01T00:00:00.000";
	
	@Override
	public String getAllStructures() throws RmesException {
		HashMap<String, Object> params = new HashMap<>();
		params.put(STRUCTURES_GRAPH, Config.BASE_GRAPH+Config.STRUCTURES_GRAPH);
		JSONArray structures =  repoGestion.getResponseAsArray(buildRequest(Constants.STRUCTURES_QUERIES_PATH,"getAllStructures.ftlh", params));
		for (int i = 0; i < structures.length(); i++) {
			JSONObject structure = structures.getJSONObject(i);
			String validationState = structure.getString(STATUT_VALIDATION);
			structure.put(STATUT_VALIDATION, this.getValidationState(validationState));
		}

		return structures.toString();
	}

	@Override
	public String getStructure(String id) throws RmesException, JsonProcessingException {
		HashMap<String, Object> params = new HashMap<>();
		params.put(STRUCTURES_GRAPH, config.getBaseGraph() + config.getStructuresGraph());
		params.put(STRUCTURE_ID, id);
		params.put("LG1", config.getLG1());
		params.put("LG2", config.getLG2());

		JSONArray structureArray =  repoGestion.getResponseAsArray(buildRequest(Constants.STRUCTURES_QUERIES_PATH,"getStructure.ftlh", params));
		JSONObject structure = (JSONObject) structureArray.get(0);

		structure.put(LABEL, this.formatLabel(structure));
		structure.remove("prefLabelLg1");
		structure.remove("prefLabelLg2");


		if(structureArray.length() > 1){
			JSONArray necessairePour = new JSONArray();
			for (int i = 0; i < structureArray.length(); i++) {
				necessairePour.put(structureArray.getJSONObject(i).getString("necessairePour"));

			}

			structure.put("necessairePour", necessairePour);
		}
		if(structure.has(ID_RELATION)){
			structure.put("dsdSdmx", extractSdmx(structure.getString(ID_RELATION)));
			structure.remove(ID_RELATION);
		}
		if(structure.has(ID_PARENT) && structure.has(URI_PARENT)){
			JSONObject parent = new JSONObject();
			parent.put("id", structure.getString(ID_PARENT));
			parent.put("uri", structure.getString(URI_PARENT));

			if(structure.has(ID_PARENT_RELATION)){
				parent.put("dsdSdmx", extractSdmx(structure.getString(ID_PARENT_RELATION)));
				structure.remove(ID_PARENT_RELATION);
			}

			structure.put("parent", parent);
			structure.remove(ID_PARENT);
			structure.remove(URI_PARENT);
		}
		if(structure.has(Constants.STATUT_VALIDATION)){
			String validationState = structure.getString(Constants.STATUT_VALIDATION);
			structure.put(Constants.STATUT_VALIDATION, this.getValidationState(validationState));
		}

		if(!structure.has("dateCreation")){
			structure.put("dateCreation", defaultDate);
		}
		if(!structure.has("dateMiseAJour")){
			structure.put("dateMiseAJour", defaultDate);
		}

		getStructureComponents(id, structure);

		ObjectMapper mapper = new ObjectMapper();
		StructureByIdModelSwagger structureByID = mapper.readValue(structure.toString(), StructureByIdModelSwagger.class);

		return  mapper.writeValueAsString(structureByID);
	}


	@Override
	public String getStructureDateMAJ(String id) throws RmesException, JsonProcessingException {
		HashMap<String, Object> params = new HashMap<>();
		params.put(STRUCTURES_GRAPH, config.getBaseGraph() + config.getStructuresGraph());
		params.put(STRUCTURE_ID, id);

		JSONArray structureArray =  repoGestion.getResponseAsArray(buildRequest(Constants.STRUCTURES_QUERIES_PATH,"getStructureDateMAJ.ftlh", params));
		JSONObject structure = (JSONObject) structureArray.get(0);

		ObjectMapper mapper = new ObjectMapper();
		StructureByIdModelSwagger structureByID = mapper.readValue(structure.toString(), StructureByIdModelSwagger.class);

		return  mapper.writeValueAsString(structureByID);
	}

	private JSONObject extractSdmx(String originalRelation) {
		String iri = originalRelation.replace("urn:sdmx:org.sdmx.infomodel.metadatastructure.MetadataStructure=", "");
		JSONObject relation = new JSONObject();
		relation.put("id", iri.substring(iri.indexOf(":") + 1, iri.indexOf("(") ));
		relation.put("agence", iri.substring(0, iri.indexOf(":")));
		relation.put("version", iri.substring(iri.indexOf("(") + 1, iri.indexOf(")")));
		return relation;
	}

	private void getStructureComponents(String id, JSONObject structure) throws RmesException {
		HashMap<String, Object> params = new HashMap<>();
		params.put(STRUCTURES_GRAPH, config.getBaseGraph() + config.getStructuresGraph());
		params.put(STRUCTURES_COMPONENTS_GRAPH_KEY, config.getBaseGraph() + Config.STRUCTURES_COMPONENTS_GRAPH);
		params.put(STRUCTURE_ID, id);

		JSONArray components = repoGestion.getResponseAsArray(buildRequest(Constants.STRUCTURES_QUERIES_PATH,"getStructureComponents.ftlh", params));

		JSONArray measures = new JSONArray();
		JSONArray dimensions = new JSONArray();
		JSONArray attributes = new JSONArray();

		for (int i = 0; i < components.length(); i++) {
			JSONObject componentSpecification = components.getJSONObject(i);
			String idComponent = componentSpecification.getString("id");
			JSONObject component = getComponent(idComponent);

			if(componentSpecification.has(ORDRE)){
				component.put(ORDRE, componentSpecification.getString(ORDRE));
			}
			if(componentSpecification.has("attachement")){
				component.put("attachement", componentSpecification.getString("attachment").replace(QB.NAMESPACE, ""));
			}
			if(componentSpecification.has(OBLIGATOIRE)){
				component.put(OBLIGATOIRE, componentSpecification.getString(OBLIGATOIRE).equalsIgnoreCase("true") ? "oui": "non");
			}

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
	public JSONObject getComponent(String id) throws RmesException {
		HashMap<String, Object> params = new HashMap<>();
		params.put(STRUCTURES_COMPONENTS_GRAPH_KEY, config.getBaseGraph() + Config.STRUCTURES_COMPONENTS_GRAPH);
		params.put(CODELIST_GRAPH_KEY, config.getBaseGraph() +config.getCodelistGraph());
		params.put("ID", id);
		params.put("LG1", config.getLG1());
		params.put("LG2", config.getLG2());

		JSONObject component =  repoGestion.getResponseAsObject(buildRequest("getComponent.ftlh", params));

		component.put(LABEL, this.formatLabel(component));
		component.remove("prefLabelLg1");
		component.remove("prefLabelLg2");

		if(component.has(URI_COMPONENT_PARENT_ID)){
			JSONObject parent = new JSONObject();
			parent.put("id", component.getString(URI_COMPONENT_PARENT_ID));
			parent.put("notation", component.getString("uriComponentParentNotation"));
			component.remove(URI_COMPONENT_PARENT_ID);
			component.remove("uriComponentParentNotation");
			component.put("parent", parent);
		}

		JSONArray flatChildren = repoGestion.getResponseAsArray(buildRequest(Constants.COMPONENTS_QUERIES_PATH,"getComponentChildren.ftlh", params));
		if(flatChildren.length() > 0) {
			component.put("enfants", flatChildren);
		}

		if(component.has(STATUT_VALIDATION)){
			String validationState = component.getString(STATUT_VALIDATION);
			component.put(STATUT_VALIDATION, this.getValidationState(validationState));
		}

		if(component.has(URI_LISTE_CODE)){
			component.put(REPRESENTATION, "liste de code");
			component.put(LISTE_CODE,component.getString(ID_LISTE_CODE));
			component.remove(URI_LISTE_CODE);
			component.remove(ID_LISTE_CODE);
		}

		if(component.has(URI_CONCEPT)){
			JSONObject concept = new JSONObject();
			concept.put("id", component.getString("idConcept"));
			HashMap<String, Object> paramsSDMX = new HashMap<>();
			paramsSDMX.put("LG1", Config.LG1);
			paramsSDMX.put("LG2", Config.LG2);
			paramsSDMX.put("ID", component.getString("idConcept"));
			paramsSDMX.put("CONCEPTS_GRAPH", Config.BASE_GRAPH+ Config.CONCEPTS_GRAPH);
			JSONObject conceptsSdmx = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getConceptsSdmx.ftlh", paramsSDMX));;
			concept.put("conceptsSdmx",conceptsSdmx);
			this.addCloseMatch(concept);
			component.put(Constants.CONCEPT, concept);
			component.remove(URI_CONCEPT);
			component.remove("idConcept");
		}

		if(component.has(REPRESENTATION)){
			component.put(REPRESENTATION, component.getString(REPRESENTATION).replace(IGEO.NAMESPACE, "").replace("http://www.w3.org/2001/XMLSchema#", ""));
		}

		if(component.has(MIN_LENGTH) || component.has(MAX_LENGTH) || component.has(MIN_INCLUSIVE) || component.has(MAX_INCLUSIVE) || component.has(PATTERN)){
			JSONObject format = new JSONObject();

			if (component.has(MIN_LENGTH)) {
				format.put("longueurMin", component.get(MIN_LENGTH));
				component.remove(MIN_LENGTH);
			}
			if (component.has(MAX_LENGTH)) {
				format.put("longueurMax", component.get(MAX_LENGTH));
				component.remove(MAX_LENGTH);
			}
			if (component.has(MIN_INCLUSIVE)) {
				format.put("valeurMin", component.get(MIN_INCLUSIVE));
				component.remove(MIN_INCLUSIVE);
			}
			if (component.has(MAX_INCLUSIVE)) {
				format.put("valeurMax", component.get(MAX_INCLUSIVE));
				component.remove(MAX_INCLUSIVE);
			}
			if (component.has(PATTERN)) {
				format.put("expressionReguliere", component.get(PATTERN));
				component.remove(PATTERN);
			}
			component.put("format", format);
		}

		if(id.startsWith("m")){
			JSONArray attributes = repoGestion.getResponseAsArray(buildRequest("getAttributeForMeasure.ftlh", params));
			if(attributes.length() > 0){
				JSONArray caracteristiques = new JSONArray();
				for(int i = 0; i < attributes.length(); i++){
					JSONObject attribute = attributes.getJSONObject(i);
					if(attribute.has("attributeId")){
						JSONObject attributeLink = getComponent(attribute.getString("attributeId"));
						JSONObject value = new JSONObject();
						value.put("code", attribute.getString("attributeValueCode"));
						value.put("uri", attribute.getString("attributeValueIri"));
						attributeLink.put("value", value);
						caracteristiques.put(attributeLink);
					}
				}
				component.put("caracteristiques", caracteristiques);
			}
		}

		return component;
	}


	@Override
	public JSONObject getComponentDateMAJ(String id) throws RmesException {
		HashMap<String, Object> params = new HashMap<>();
		params.put(STRUCTURES_COMPONENTS_GRAPH_KEY, config.getBaseGraph() + Config.STRUCTURES_COMPONENTS_GRAPH);
		params.put(CODELIST_GRAPH_KEY, config.getBaseGraph() +config.getCodelistGraph());
		params.put("ID", id);
		return repoGestion.getResponseAsObject(buildRequest("getComponentDateMAJ.ftlh", params));
	}

	private JSONArray getCodes(String notation) throws RmesException {
		HashMap<String, Object> params = new HashMap<>();
		params.put(CODELIST_GRAPH_KEY,config.getBaseGraph() + config.getCodelistGraph());
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
			closeMatchParams.put("CONCEPTS_GRAPH", config.getBaseGraph() +config.getCodelistGraph());
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
				code.put(LABEL, this.formatLabel(code));
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

	private void addCloseMatch(JSONObject concept) throws RmesException {
		HashMap<String, Object> params = new HashMap<>();
		params.put("CONCEPTS_GRAPH", config.getBaseGraph() +config.getConceptsGraph());
		params.put("CONCEPT_ID", concept.getString("id"));
		JSONArray closeMatch = repoGestion.getResponseAsArray(buildRequest("getCloseMatch.ftlh", params));
		if(closeMatch.length() > 0){
			JSONArray formattedCloseMatchArray  = new JSONArray();
			for(int i = 0; i < closeMatch.length(); i++){
				String iri = ((JSONObject) closeMatch.get(i)).getString("closeMatch").replace("urn:sdmx:org.sdmx.infomodel.conceptscheme.Concept=", "");
				JSONObject relation = new JSONObject();
				relation.put("agence", iri.substring(0, iri.indexOf(":")));
				relation.put("id", iri.substring(iri.lastIndexOf(".") + 1));
				formattedCloseMatchArray.put(relation);
			}
			concept.put("conceptsSdmx", formattedCloseMatchArray);
		}

	}

	public Map<String, Object> initParams() {
		Map<String, Object> params = new HashMap<>();
		params.put(STRUCTURES_COMPONENTS_GRAPH_KEY, Config.BASE_GRAPH + Config.STRUCTURES_COMPONENTS_GRAPH);
		return params;
	}

	private static String buildRequest(String fileName, HashMap<String, Object> params) throws RmesException {
		return FreeMarkerUtils.buildRequest("components/", fileName, params);
	}


}
