package fr.insee.rmes.services.concepts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.modelSwagger.concept.ConceptByIdModelSwagger;
import fr.insee.rmes.modelSwagger.concept.LabelConcept;
import fr.insee.rmes.model.concept.ConceptById;
import fr.insee.rmes.model.concept.ConceptSDMX;
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
    public String getDetailedConcept(String id) throws RmesException, JsonProcessingException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
        params.put("ID", id);
        params.put("CONCEPTS_GRAPH", Config.BASE_GRAPH+Config.CONCEPTS_GRAPH);

        JSONObject concept = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getDetailedConcept.ftlh", params));
        ObjectMapper jsonResponse = new ObjectMapper();
        ConceptById conceptById = jsonResponse.readValue(concept.toString(), ConceptById.class);

        LabelConcept labelConcept1 = new LabelConcept(Config.LG1, conceptById.getPrefLabelLg1());
        LabelConcept labelConcept2 = new LabelConcept(Config.LG2, conceptById.getPrefLabelLg2());
        List<LabelConcept> labelConcepts = new ArrayList<>();
        if (labelConcept1.getLangue() !=null) {
            labelConcepts.add(labelConcept1);
            labelConcepts.add(labelConcept2);   }

        JSONObject sdmx = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getConceptsSdmx.ftlh", params));
        ObjectMapper mapper = new ObjectMapper();
        if(sdmx.length() > 0){
            ObjectMapper jsonResponse2 = new ObjectMapper();
            ConceptSDMX conceptSDMX = jsonResponse2.readValue(sdmx.toString(), ConceptSDMX.class);
            ConceptByIdModelSwagger conceptByIdModelSwagger=new ConceptByIdModelSwagger(conceptById.getDateCreation(),conceptById.getDateMiseAjour(),conceptById.getStatutValidation(),conceptById.getId(),labelConcepts,conceptById.getDateFinValidite(),conceptById.getUri(),conceptById.getVersion(),conceptSDMX);
            return mapper.writeValueAsString(conceptByIdModelSwagger);
        } else {
            ConceptByIdModelSwagger conceptByIdModelSwagger=new ConceptByIdModelSwagger(conceptById.getDateCreation(),conceptById.getDateMiseAjour(),conceptById.getStatutValidation(),conceptById.getId(),labelConcepts,conceptById.getDateFinValidite(),conceptById.getUri(),conceptById.getVersion());
            return mapper.writeValueAsString(conceptByIdModelSwagger);
        }

    }

    @Override
    public String getDetailedConceptDateMAJ(String id) throws RmesException, JsonProcessingException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
        params.put("ID", id);
        params.put("CONCEPTS_GRAPH", Config.BASE_GRAPH+Config.CONCEPTS_GRAPH);

        JSONObject concept = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getDetailedConceptDateMAJ.ftlh", params));
        ObjectMapper jsonResponse = new ObjectMapper();
        ConceptById conceptById = jsonResponse.readValue(concept.toString(), ConceptById.class);

        LabelConcept labelConcept1 = new LabelConcept(Config.LG1, conceptById.getPrefLabelLg1());
        LabelConcept labelConcept2 = new LabelConcept(Config.LG2, conceptById.getPrefLabelLg2());
        List<LabelConcept> labelConcepts = new ArrayList<>();
        if (labelConcept1.getLangue() !=null) {
            labelConcepts.add(labelConcept1);
            labelConcepts.add(labelConcept2);   }

        JSONObject sdmx = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getConceptsSdmx.ftlh", params));
        ObjectMapper mapper = new ObjectMapper();
        if(sdmx.length() > 0){
            ObjectMapper jsonResponse2 = new ObjectMapper();
            ConceptSDMX conceptSDMX = jsonResponse2.readValue(sdmx.toString(), ConceptSDMX.class);
            ConceptByIdModelSwagger conceptByIdModelSwagger=new ConceptByIdModelSwagger(conceptById.getDateCreation(),conceptById.getDateMiseAjour(),conceptById.getStatutValidation(),conceptById.getId(),labelConcepts,conceptById.getDateFinValidite(),conceptById.getUri(),conceptById.getVersion(),conceptSDMX);
            return mapper.writeValueAsString(conceptByIdModelSwagger);
        } else {
            ConceptByIdModelSwagger conceptByIdModelSwagger=new ConceptByIdModelSwagger(conceptById.getDateCreation(),conceptById.getDateMiseAjour(),conceptById.getStatutValidation(),conceptById.getId(),labelConcepts,conceptById.getDateFinValidite(),conceptById.getUri(),conceptById.getVersion());
            return mapper.writeValueAsString(conceptByIdModelSwagger);
        }

    }


    @Override
    public String getAllConcepts() throws RmesException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
        params.put("CONCEPTS_GRAPH", Config.BASE_GRAPH+Config.CONCEPTS_GRAPH);
        JSONArray conceptLists= repoGestion.getResponseAsArray(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getAllConcepts.ftlh", params));
        return conceptLists.toString();
    }

}
