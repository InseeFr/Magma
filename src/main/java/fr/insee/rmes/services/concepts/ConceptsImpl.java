package fr.insee.rmes.services.concepts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.model.concept.ConceptById;
import fr.insee.rmes.model.concept.ConceptDefCourte;
import fr.insee.rmes.model.concept.ConceptSDMX;
import fr.insee.rmes.modelSwagger.concept.CollectionOfConceptsModelSwagger;
import fr.insee.rmes.modelSwagger.concept.ConceptByIdModelSwagger;
import fr.insee.rmes.modelSwagger.concept.LabelConcept;
import fr.insee.rmes.modelSwagger.dataset.LangContent;
import fr.insee.rmes.persistence.FreeMarkerUtils;
import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class ConceptsImpl extends RdfService implements ConceptsServices {


    public ConceptsImpl(FreeMarkerUtils freeMarkerUtils) {
        super(freeMarkerUtils);
    }
    private static final String CONCEPTS_GRAPH = "CONCEPTS_GRAPH";
    private static final String ADMS_GRAPH = "ADMS_GRAPH";
    public static final String CONTENU = "contenu";


    @Override
    public String getDetailedConcept(String id) throws RmesException, JsonProcessingException {
        Map<String, Object> params = initParams();
        params.put("ID", id);

        JSONObject concept = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH, "getDetailedConcept.ftlh", params));
        JSONObject defcourtefr = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH, "getConceptDefCourteFR.ftlh", params));
        JSONObject defcourteen = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH, "getConceptDefCourteEN.ftlh", params));

        if (concept.has("id")){

        List<ConceptDefCourte> defCourtes = new ArrayList<>();
        if (defcourtefr.has(CONTENU)) {
            ConceptDefCourte defCourtesFR = new ConceptDefCourte((String) defcourtefr.get(CONTENU), Config.LG1);
            defCourtes.add(defCourtesFR);
        }
        if (defcourteen.has(CONTENU)) {
            ConceptDefCourte defCourteEN = new ConceptDefCourte((String) defcourteen.get(CONTENU), Config.LG2);
            defCourtes.add(defCourteEN);
        }
        ObjectMapper jsonResponse = new ObjectMapper();
        ConceptById conceptById = jsonResponse.readValue(concept.toString(), ConceptById.class);

        LabelConcept labelConcept1 = new LabelConcept(Config.LG1, conceptById.getPrefLabelLg1());
        LabelConcept labelConcept2 = new LabelConcept(Config.LG2, conceptById.getPrefLabelLg2());
        List<LabelConcept> labelConcepts = new ArrayList<>();
        if (labelConcept1.getLangue() != null) {
            labelConcepts.add(labelConcept1);
            labelConcepts.add(labelConcept2);
        }


        JSONArray sdmxArray = repoGestion.getResponseAsArray(buildRequest(Constants.CONCEPTS_QUERIES_PATH, "getConceptsSdmx.ftlh", params));
        ObjectMapper mapper = new ObjectMapper();
        ConceptByIdModelSwagger conceptByIdModelSwagger = new ConceptByIdModelSwagger();
        conceptByIdModelSwagger = new ConceptByIdModelSwagger(conceptById.getDateCreation(), conceptById.getDateMiseAJour(), conceptById.getStatutValidation(), conceptById.getId(), labelConcepts, conceptById.getDateFinValidite(), conceptById.getUri(), conceptById.getVersion(), defCourtes);

        if (sdmxArray.length() > 0) {
            ObjectMapper jsonResponse2 = new ObjectMapper();
            ConceptSDMX[] conceptsSDMX = jsonResponse2.readValue(sdmxArray.toString(), ConceptSDMX[].class);
            conceptByIdModelSwagger.setConceptsSDMX(conceptsSDMX);
        }

        if (concept.has("identifierADMS")) {
            String identifierADMS = concept.getString("identifierADMS");
            params.put("IDENTIFIERADMS", identifierADMS);
            params.put(ADMS_GRAPH, Config.BASE_GRAPH + Config.ADMS_GRAPH);
            JSONObject nameADMS = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH, "getNameADMS.ftlh", params));
            String name = nameADMS.getString("name");
            conceptByIdModelSwagger.setName(name);
        }

        return mapper.writeValueAsString(conceptByIdModelSwagger);
        }
        else{
            throw new RmesException(HttpStatus.NOT_FOUND, "Non existent concept identifier", "The id " + id + " does not correspond to any concept");
        }
    }

    @Override
    public String getDetailedConceptDateMAJ(String id) throws RmesException, JsonProcessingException {
        Map<String, Object> params = initParams();
        params.put("ID", id);
        JSONObject concept = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getDetailedConceptDateMAJ.ftlh", params));
        if (concept.has("id")) {

            ObjectMapper jsonResponse = new ObjectMapper();
            ConceptById conceptById = jsonResponse.readValue(concept.toString(), ConceptById.class);

            ObjectMapper mapper = new ObjectMapper();
            ConceptByIdModelSwagger conceptByIdModelSwagger = new ConceptByIdModelSwagger(conceptById.getDateCreation(), conceptById.getDateMiseAJour(), conceptById.getStatutValidation(), conceptById.getId(), conceptById.getDateFinValidite(), conceptById.getUri(), conceptById.getVersion());
            return mapper.writeValueAsString(conceptByIdModelSwagger);
        }
        else {
            throw new RmesException(HttpStatus.NOT_FOUND, "Non existent concept identifier", "The id " + id + " does not correspond to any concept");
        }
    }

    @Override
    public String getAllConcepts(String dateMiseAJour) throws RmesException {
        Map<String, Object> params = initParams();
        JSONArray conceptLists = new JSONArray();
        if (dateMiseAJour ==""){
            conceptLists = repoGestion.getResponseAsArray(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getAllConcepts.ftlh", params));
        }
        else {
            params.put("DATE",dateMiseAJour);
            conceptLists = repoGestion.getResponseAsArray(buildRequest(Constants.CONCEPTS_QUERIES_PATH,"getAllConceptsFilterByDate.ftlh", params));
        }
        return conceptLists.toString();
    }

    public Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
        params.put(CONCEPTS_GRAPH, Config.BASE_GRAPH+Config.CONCEPTS_GRAPH);
        return params;
    }

    @Override
    public String getCollectionOfConcepts(String id) throws RmesException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> params = initParams();
        params.put("ID", id);

        try{

            JSONObject concept = repoGestion.getResponseAsObject(buildRequest(Constants.CONCEPTS_QUERIES_PATH, "getCollectionOfConcepts.ftlh", params));

            List<LangContent> langContents = new ArrayList<>();
            List<ConceptDefCourte> descriptions = new ArrayList<>();

            String uri = concept.getString("uri");
            String dateOfUpdate= concept.getString("date_mis_a_jour");

            CollectionOfConceptsModelSwagger Concepts = new CollectionOfConceptsModelSwagger(uri,id,dateOfUpdate);

            if (concept.has("intitule_fr")) {
                LangContent langContent = new LangContent(Config.LG1, concept.getString("intitule_fr"));
                langContents.add(langContent);
            }

            if (concept.has("intitule_en")) {
                LangContent langContent = new LangContent(Config.LG2, concept.getString("intitule_en"));
                langContents.add(langContent);
            }

            if(!langContents.isEmpty()) Concepts.setLabel(langContents);

            if (concept.has("description_fr")) {
                ConceptDefCourte descriptionLG1= new ConceptDefCourte(concept.getString("description_fr"), Config.LG1);
                descriptions.add(descriptionLG1);
            }

            if (concept.has("description_en")) {
                ConceptDefCourte descriptionLG2 = new ConceptDefCourte(concept.getString("description_en"), Config.LG2);
                descriptions.add(descriptionLG2);
            }

            if(!descriptions.isEmpty()) Concepts.setDescription(descriptions);

            return  mapper.writeValueAsString(Concepts);

        } catch ( Exception e){

            throw new RmesException(404, "Non existent collection identifier", "The id " + id + " does not correspond to any collection.");
        }
    }

    @Override
    public String getSetOfConceptsInACollection(String id) throws RmesException{

        Map<String, Object> params = initParams();
        params.put("ID", id);

        JSONArray concepts = repoGestion.getResponseAsArray(buildRequest(Constants.CONCEPTS_QUERIES_PATH, "getSetOfConceptsInACollection.ftlh", params));

        if(concepts == null || concepts.isEmpty()|| concepts.length()==0) throw new RmesException(404, "Non existent collection identifier", "The id " + id + " does not correspond to any collection.");

        return concepts.toString();
    }



}
