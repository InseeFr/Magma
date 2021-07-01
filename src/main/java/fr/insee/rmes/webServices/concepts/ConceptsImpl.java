package fr.insee.rmes.webServices.concepts;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.exceptions.RmesException;

@Service
public class ConceptsImpl extends RdfService implements ConceptsServices {


	@Value("${fr.insee.rmes.magma.concepts.graph}")
	public String CONCEPTS_GRAPH;
	
	
	@Override
    public String getDetailedConcept(String id) throws RmesException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("LG1", LG1);
        params.put("LG2", LG2);
        params.put("ID", id);
        params.put("CONCEPTS_GRAPH", BASE_GRAPH+CONCEPTS_GRAPH);

        JSONObject concept = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getDetailedConcept.ftlh", params));
        JSONArray labels = new JSONArray();

        String labelLg1 = concept.getString("prefLabelLg1");
        JSONObject labelLg1Object = new JSONObject();
        labelLg1Object.put("langue", LG1);
        labelLg1Object.put("contenu", labelLg1);
        labels.put(labelLg1Object);
        concept.remove("prefLabelLg1");

        if(concept.has("prefLabelLg2")){
            String labelLg2 = concept.getString("prefLabelLg2");
            JSONObject labelLg2Object = new JSONObject();
            labelLg2Object.put("langue", LG2);
            labelLg2Object.put("contenu", labelLg2);
            labels.put(labelLg2Object);
            concept.remove("prefLabelLg2");
        }

        concept.put("label", labels);

        JSONArray conceptsSdmx = repoGestion.getResponseAsArray(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getConceptsSdmx.ftlh", params));
        if(conceptsSdmx.length() > 0){
            concept.put("conceptsSdmx", conceptsSdmx);
        }

        return concept.toString();
    }

    @Override
    public String getAllConcepts() throws RmesException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("LG1", LG1);
        params.put("LG2", LG2);
        params.put("CONCEPTS_GRAPH", BASE_GRAPH+CONCEPTS_GRAPH);
        return repoGestion.getResponseAsArray(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getAllConcepts.ftlh", params)).toString();
    }

}
