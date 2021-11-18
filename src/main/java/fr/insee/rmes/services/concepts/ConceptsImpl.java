package fr.insee.rmes.services.concepts;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;

@Service
public class ConceptsImpl extends RdfService implements ConceptsServices {

	
	
	@Override
    public Object getDetailedConcept(String id) throws RmesException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
        params.put("ID", id);
        params.put("CONCEPTS_GRAPH", Config.BASE_GRAPH+Config.CONCEPTS_GRAPH);

        JSONObject concept = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getDetailedConcept.ftlh", params));
        JSONArray labels = new JSONArray();

        String labelLg1 = concept.getString(Constants.PREF_LABEL_LG1);
        JSONObject labelLg1Object = new JSONObject();
        labelLg1Object.put("langue", Config.LG1);
        labelLg1Object.put("contenu", labelLg1);
        labels.put(labelLg1Object);
        concept.remove(Constants.PREF_LABEL_LG1);

        if(concept.has(Constants.PREF_LABEL_LG2)){
            String labelLg2 = concept.getString(Constants.PREF_LABEL_LG2);
            JSONObject labelLg2Object = new JSONObject();
            labelLg2Object.put("langue", Config.LG2);
            labelLg2Object.put("contenu", labelLg2);
            labels.put(labelLg2Object);
            concept.remove(Constants.PREF_LABEL_LG2);
        }

        concept.put(Constants.LABEL, labels);

        JSONArray conceptsSdmx = repoGestion.getResponseAsArray(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getConceptsSdmx.ftlh", params));
        if(conceptsSdmx.length() > 0){
            concept.put("conceptsSdmx", conceptsSdmx);
        }

        return concept;
    }

    @Override
    public List getAllConcepts() throws RmesException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
        params.put("CONCEPTS_GRAPH", Config.BASE_GRAPH+Config.CONCEPTS_GRAPH);
        JSONArray conceptLists= repoGestion.getResponseAsArray(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getAllConcepts.ftlh", params));
        return conceptLists.toList();
    }

}
