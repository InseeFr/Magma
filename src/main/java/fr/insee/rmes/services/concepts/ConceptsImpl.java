package fr.insee.rmes.services.concepts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.model.concept.ConceptDefCourte;
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



    private static final String CONCEPTS_GRAPH = "CONCEPTS_GRAPH";
    private static final String ADMS_GRAPH = "ADMS_GRAPH";


    @Override
    public String getDetailedConcept(String id) throws RmesException, JsonProcessingException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
        params.put("ID", id);
        params.put(CONCEPTS_GRAPH, Config.BASE_GRAPH+Config.CONCEPTS_GRAPH);

        JSONObject concept = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getDetailedConcept.ftlh", params));
        JSONObject defcourtefr = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getConceptDefCourteFR.ftlh", params));
        JSONObject defcourteen = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getConceptDefCourteEN.ftlh", params));

        if (concept.has("identifierADMS")) {
            String identifierADMS = concept.getString("identifierADMS");
            params.put("identifierADMS", identifierADMS);
            params.put(ADMS_GRAPH, Config.BASE_GRAPH + Config.ADMS_GRAPH);

            JSONObject nameADMS = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH, "getNameADMS.ftlh", params));
            String name = nameADMS.getString("name");
            concept.put("name",name);
        }


        ConceptDefCourte defCourtesFR = new ConceptDefCourte((String) defcourtefr.get("contenu"),Config.LG1);
        ConceptDefCourte defCourteEN = new ConceptDefCourte((String) defcourteen.get("contenu"),Config.LG2);
        List <ConceptDefCourte> defCourtes = new ArrayList<>();
        defCourtes.add(defCourtesFR);
        defCourtes.add(defCourteEN);

        ObjectMapper jsonResponse = new ObjectMapper();
        ConceptById conceptById = jsonResponse.readValue(concept.toString(), ConceptById.class);


        LabelConcept labelConcept1 = new LabelConcept(Config.LG1, conceptById.getPrefLabelLg1());
        LabelConcept labelConcept2 = new LabelConcept(Config.LG2, conceptById.getPrefLabelLg2());
        List<LabelConcept> labelConcepts = new ArrayList<>();
        if (labelConcept1.getLangue() !=null) {
            labelConcepts.add(labelConcept1);
            labelConcepts.add(labelConcept2);   }



        JSONArray sdmxArray = repoGestion.getResponseAsArray(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getConceptsSdmx.ftlh", params));
        ObjectMapper mapper = new ObjectMapper();
        ConceptByIdModelSwagger conceptByIdModelSwagger = new ConceptByIdModelSwagger();
        if(sdmxArray.length() > 0){
            ObjectMapper jsonResponse2 = new ObjectMapper();
            ConceptSDMX[] conceptsSDMX = jsonResponse2.readValue(sdmxArray.toString(), ConceptSDMX[].class);
            conceptByIdModelSwagger = new ConceptByIdModelSwagger(conceptById.getDateCreation(), conceptById.getDateMiseAJour(), conceptById.getStatutValidation(), conceptById.getId(), labelConcepts, conceptById.getDateFinValidite(), conceptById.getUri(), conceptById.getVersion(), conceptsSDMX, defCourtes, name);

        } else {
            conceptByIdModelSwagger = new ConceptByIdModelSwagger(conceptById.getDateCreation(), conceptById.getDateMiseAJour(), conceptById.getStatutValidation(), conceptById.getId(), labelConcepts, conceptById.getDateFinValidite(), conceptById.getUri(), conceptById.getVersion(), defCourtes, name);
        }
        return mapper.writeValueAsString(conceptByIdModelSwagger);
    }

    @Override
    public String getDetailedConceptDateMAJ(String id) throws RmesException, JsonProcessingException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
        params.put("ID", id);
        params.put(CONCEPTS_GRAPH, Config.BASE_GRAPH+Config.CONCEPTS_GRAPH);

        JSONObject concept = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getDetailedConceptDateMAJ.ftlh", params));
        ObjectMapper jsonResponse = new ObjectMapper();
        ConceptById conceptById = jsonResponse.readValue(concept.toString(), ConceptById.class);

        ObjectMapper mapper = new ObjectMapper();
        ConceptByIdModelSwagger conceptByIdModelSwagger=new ConceptByIdModelSwagger(conceptById.getDateCreation(),conceptById.getDateMiseAJour(),conceptById.getStatutValidation(),conceptById.getId(),conceptById.getDateFinValidite(),conceptById.getUri(),conceptById.getVersion());
        return mapper.writeValueAsString(conceptByIdModelSwagger);

    }


    @Override
    public String getAllConcepts() throws RmesException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
        params.put(CONCEPTS_GRAPH, Config.BASE_GRAPH+Config.CONCEPTS_GRAPH);
        JSONArray conceptLists= repoGestion.getResponseAsArray(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getAllConcepts.ftlh", params));
        return conceptLists.toString();
    }



}
