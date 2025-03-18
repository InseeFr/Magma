package fr.insee.rmes.services.concepts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.modelSwagger.concept.CollectionOfConceptsModelSwagger;
import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.persistence.RepositoryGestion;
import fr.insee.rmes.services.utils.ResponseUtilsTest;
import fr.insee.rmes.stubs.FreeMarkerUtilsStub;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import static fr.insee.rmes.services.utils.ResponseUtilsTest.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConceptsImplTest extends RdfService {

    @InjectMocks
    ConceptsImpl conceptsImpl = new ConceptsImpl(new FreeMarkerUtilsStub());
    @Mock
    RepositoryGestion repoGestion;

    @Test
    void getDetailedConceptDateMAJ_shouldReturn404IfInexistentId() throws RmesException, JsonProcessingException {
        JSONObject mockJSON = new JSONObject(EMPTY_JSON_OBJECT);
        when(repoGestion.getResponseAsObject(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(()->conceptsImpl.getDetailedConceptDateMAJ("1")).isInstanceOf(RmesException.class)
                .matches(rmesException->((RmesException)rmesException).getStatus()==404)
                .hasMessageContaining("Non existent concept identifier");
    }

    @Test
    void getDetailedConceptDateMAJFalse_shouldReturn404IfInexistentId() throws RmesException, JsonProcessingException {
        JSONObject mockJSON = new JSONObject(EMPTY_JSON_OBJECT);
        when(repoGestion.getResponseAsObject(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(()->conceptsImpl.getDetailedConcept("1")).isInstanceOf(RmesException.class)
                .matches(rmesException->((RmesException)rmesException).getStatus()==404)
                .hasMessageContaining("Non existent concept identifier");
    }

    @Test
    void getCollectionOfConcepts_shouldCheckConditions() throws RmesException, JsonProcessingException {

        JSONObject mockJSON = new JSONObject(ResponseUtilsTest.COLLECTION_JSON_OBJECT );

        List<Boolean> checks = List.of(!mockJSON.isEmpty(),
                mockJSON.has("intitule_fr"),
                mockJSON.has("intitule_en"),
                mockJSON.has("description_fr"),
                mockJSON.has("description_en")
                );

        assertEquals(List.of(true, true, true, true, true), checks);
    }

    @Test
    void getCollectionOfConcepts_shouldCreatePartOfTheCorrectMapper() throws RmesException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        JSONObject mockJSON = new JSONObject(ResponseUtilsTest.COLLECTION_JSON_OBJECT );

        CollectionOfConceptsModelSwagger Concepts = new CollectionOfConceptsModelSwagger(mockJSON.getString("uri"),"definitions-insee-fr",mockJSON.getString("date_mis_a_jour"));

        assertEquals(COLLECTION_MODELSWAGGER, mapper.writeValueAsString(Concepts));

    }

    @Test
    void getSetOfConceptsInACollection_shouldCheckJSONCharacteristics() throws RmesException, JsonProcessingException {

        JSONArray mockJSON1 = new JSONArray(SET_OF_CONCEPTS_JSON_ARRAY);
        JSONArray mockJSON2 = new JSONArray(EMPTY_JSON_ARRAY);
        JSONObject mockJSON3 = new JSONObject(EMPTY_JSON_OBJECT);

        List<Boolean> checksEmpty = List.of(!mockJSON1.isEmpty(),mockJSON2.isEmpty(),mockJSON3.isEmpty());
        List<Boolean> checksEmptyExpected = List.of(true, true, true);

        List<Integer> checksLength = List.of(mockJSON1.length(),mockJSON2.length(),mockJSON3.length());
        List<Integer> checksLengthExpected = List.of(2,0,0);

        assertEquals(List.of(checksEmptyExpected,checksLengthExpected),List.of(checksEmpty,checksLength));

    }

    @Test
    void getSetOfConceptsInACollection_shouldReturnCorrectsParams() throws RmesException, JsonProcessingException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("ID","definitions-insee-fr");
        assertEquals(("{ID=definitions-insee-fr}"), params.toString());
    }
    static final Logger logger = LoggerFactory.getLogger(CollectionOfConceptsModelSwagger.class);

    @Test
    public void givenJsonString_whenConvertToXMLUsingJackson_thenConverted() throws JsonProcessingException {
        JSONObject mockJSON = new JSONObject(ResponseUtilsTest.COLLECTION_JSON_OBJECT );
        String xml = XML.toString(mockJSON);
        logger.info(xml);

    }

    }


