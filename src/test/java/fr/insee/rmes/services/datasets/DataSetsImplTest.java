package fr.insee.rmes.services.datasets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import fr.insee.rmes.dto.datasets.PatchDatasetDTO;
import fr.insee.rmes.model.datasets.Theme;
import fr.insee.rmes.modelSwagger.dataset.*;
import fr.insee.rmes.persistence.RepositoryGestion;
import fr.insee.rmes.services.utils.DataSetsUtilsTest;
import fr.insee.rmes.services.utils.ResponseUtilsTest;
import fr.insee.rmes.stubs.FreeMarkerUtilsStub;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataSetsImplTest {

    private static final DataSetModelSwagger response = new DataSetModelSwagger();
    @InjectMocks
    DataSetsImpl dataSetsImpl = new DataSetsImpl(new FreeMarkerUtilsStub());
    @Mock
    RepositoryGestion repoGestion;
    public static final ObjectMapper MAPPER = new JsonMapper();

    @Test
    void getListDataSetsTest() throws RmesException, JsonProcessingException {
        JSONArray mockJSON = new JSONArray(DataSetsUtilsTest.DATA_SET_LIST);
        when(repoGestion.getResponseAsArray(Mockito.anyString())).thenReturn(mockJSON);
        assertThat(MAPPER.readTree(dataSetsImpl.getListDataSets(""))).isEqualTo(MAPPER.readTree(DataSetsUtilsTest.EXPECTED_GET_DATA_SET_LIST.toString()));
    }

    @Test
    void setTitreListTest() {
        LangContent titre1 = new LangContent("fr", "elementLg1");
        LangContent titre2 = new LangContent("en", "elementLg2");
        List<LangContent> titres = new ArrayList<>();
        titres.add(titre1);
        titres.add(titre2);
        assertThat(titres).hasToString(DataSetsUtilsTest.EXPECTED_JSON_SET_TITRE_LIST);
    }

    @Test
    void initParamsTest() {
        Map<String, Object> params = new HashMap<>();
        params.put("DATASETS_GRAPH", "DatasetGraphTest");
        params.put("LG1", "fr");
        params.put("LG2", "en");
        params.put("ADMS_GRAPH", "AdmsGraphTest");
        params.put("STRUCTURES_GRAPH", "StructureGraphTest");
        params.put("CODES_GRAPH", "CodesGraphTest");
        params.put("CONCEPTS_GRAPH", "ConceptsGraphTest");
        params.put("ORGANISATIONS_GRAPH", "OrganisationsGraphTest");
        params.put("OPERATIONS_GRAPH", "OperationsGraphTest");
        params.put("ONTOLOGIES_GRAPH", "OntologiesGraphTest");
        assertEquals(DataSetsUtilsTest.EXPECTED_MAP_INIT_PARAMS, params.toString());
    }

    @Test
     void shouldDisplayDataStructureDefinitionFormat() {

        JSONObject structures_result = new JSONObject();
        structures_result.put("uri", "http://uriTest");
        structures_result.put("dsd", "TEST_CHAMP");
        structures_result.put("structureId","dsdTest");
        structures_result.put("type", "http://purl.org/linked-data/cube#DataStructureDefinition");
        structures_result.put("DataStructureDefinition", "true");

        boolean condition1 = structures_result.has("structureId") && structures_result.has("dsd");
        boolean condition2 = (structures_result.has("uri") && structures_result.has("DataStructureDefinition"));
        boolean condition3 = ("true").equals(structures_result.getString("DataStructureDefinition"));

        assertEquals(List.of(true, true, true), List.of(condition1,condition2,condition3));
    }

    @Test
    void shouldnotDisplayDataStructureDefinitionFormat1() {

        JSONObject structures_result = new JSONObject();
        structures_result.put("uri", "http://uriTest");
        structures_result.put("dsd", "TEST_CHAMP");
        structures_result.put("structureId","dsdTest");
        structures_result.put("type", "http://purl.org/linked-data/cube#DataStructureDefinition");

        boolean condition1 = structures_result.has("structureId") && structures_result.has("dsd");
        boolean condition2 = (structures_result.has("uri") && structures_result.has("DataStructureDefinition"));

        assertEquals(List.of(true,false), List.of(condition1,condition2));
    }

    @Test
    void shouldnotDisplayDataStructureDefinitionFormat2() {
        JSONObject structures_result = new JSONObject();
        assertTrue(structures_result.isEmpty());
    }

    @Test
    void shouldnotDisplayDataStructureDefinitionFormat3() {

        JSONObject structures_result = new JSONObject();
        structures_result.put("uri", "http://uriTest");
        structures_result.put("dsd", "TEST_CHAMP");
        structures_result.put("structureId","dsdTest");
        structures_result.put("type", "http://purl.org/linked-data/cube#DataStructureDefinition");
        structures_result.put("DataStructureDefinition", "false");

        boolean condition1 = structures_result.has("structureId") && structures_result.has("dsd");
        boolean condition2 = (structures_result.has("uri") && structures_result.has("DataStructureDefinition"));
        boolean condition3 = ("false").equals(structures_result.getString("DataStructureDefinition"));

        assertEquals(List.of(true, true, true), List.of(condition1,condition2,condition3));
    }

    //inutile je pense
    @Test
    void setIdLabelTest() {
        List<LangContent> langContentList = new ArrayList<>();
        LangContent langContent1 = new LangContent("fr", "content1");
        LangContent langContent2 = new LangContent("en", "content2");
        langContentList.add(langContent1);
        langContentList.add(langContent2);
        IdLabel idlabeltest = new IdLabel("id", langContentList);
        assertEquals(DataSetsUtilsTest.EXPECTED_LANGCONTENTLIST_SET_ID_LABEL, idlabeltest.toString());
        idlabeltest.setType("type");
        assertEquals(DataSetsUtilsTest.EXPECTED_LANGCONTENTLIST_SET_ID_LABEL_WITH_TYPE, idlabeltest.toString());
    }

    //TODO petite méthode d'extraction des URIs
    @Test
    void getThemeModelSwaggerSTest() {
        String liste_uris = "uri0, uri1 ,uri2 , uri3";
        String[] parts = liste_uris.split(",");
        List<ThemeModelSwagger> themeListModelSwaggerS = new ArrayList<>();
        for (int i = 0; i < Arrays.stream(parts).count(); i++) {
            Map<String, Object> params = new HashMap<>();
            params.put("URI", parts[i].replace(" ", ""));
            String expectedParams = "{URI=uri" + i + "}";
            assertEquals(expectedParams, params.toString());
            params.remove("URI");
            Theme themetest = new Theme("uri" + i, "labelThemeLg1" + i, "labelThemeLg2" + i, "themeTaxonomy" + i);
            LabelDataSet labelDataSet1 = new LabelDataSet("fr", themetest.getLabelThemeLg1());
            LabelDataSet labelDataSet2 = new LabelDataSet("en", themetest.getLabelThemeLg2());
            List<LabelDataSet> labelDataSets = new ArrayList<>();
            labelDataSets.add(labelDataSet1);
            labelDataSets.add(labelDataSet2);
            ThemeModelSwagger themeModelSwagger = new ThemeModelSwagger(themetest.getUri(), labelDataSets, themetest.getThemeTaxonomy());
            themeListModelSwaggerS.add(themeModelSwagger);
        }
        assertEquals(DataSetsUtilsTest.EXPECTED_THEMELIST_GET_THEME_MODEL_SWAGGERS, themeListModelSwaggerS.toString());
    }

    @ParameterizedTest(name = "{0}")
    /*@CsvSource({
            "subtitleLg1, subtitleLg2"
    })*/
    @MethodSource(value = "argumentsProvider")
    void testPresenceVariablePuisAjoutTest_checkFieldIsAdded(String key1, String key2, Supplier<List<LangContent>> getListLangContent) throws RmesException, JsonProcessingException {
        var catalogue_result = new JSONObject(Map.of(key1, "l1", key2, "l2"));
        var expected = dataSetsImpl.constructLangContent("l1", "l2");
        dataSetsImpl.testPresenceVariablePuisAjout(response, catalogue_result, new JSONObject(), new JSONObject(), new JSONObject(), new JSONObject());
        assertThat(getListLangContent.get()).isEqualTo(expected);
    }


    static Stream<Arguments> argumentsProvider() {
        return Stream.of(
                Arguments.of("subtitleLg1", "subtitleLg2", (Supplier<List<LangContent>>) response::getSubtitle),
                Arguments.of("abstractLg1", "abstractLg2", (Supplier<List<LangContent>>) response::getAbstractDataset),
                Arguments.of("scopeNoteLg1", "scopeNoteLg2", (Supplier<List<LangContent>>) response::getScopeNote)
        );
    }

    @BeforeAll
    static void setUp() {
        Config.LG1 = "fr";
        Config.LG2 = "en";
    }

    @Test
    void getDataSetByIDDateMiseAJourFalse_shouldReturn404IfInexistentId() throws RmesException {
        JSONObject mockJSON = new JSONObject(ResponseUtilsTest.EMPTY_JSON_OBJECT);
        when(repoGestion.getResponseAsObject(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(() -> dataSetsImpl.findDataSetModelSwagger("1")).isInstanceOf(RmesException.class)
                .matches(rmesException -> ((RmesException) rmesException).getStatus() == 404)
                .hasMessageContaining("Non existent dataset identifier");
    }




    @Test
    void getDataSetByIDDateMiseAJour_shouldReturn404IfInexistentId() throws RmesException {
        JSONObject mockJSON = new JSONObject(ResponseUtilsTest.EMPTY_JSON_OBJECT);
        when(repoGestion.getResponseAsObject(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(() -> dataSetsImpl.getDataSetByIDSummary("1")).isInstanceOf(RmesException.class)
                .matches(rmesException -> ((RmesException) rmesException).getStatus() == 404)
                .hasMessageContaining("Non existent dataset identifier");
    }


    @Test
    void patchDataset_shouldReturn400() {
        assertThatThrownBy(() -> dataSetsImpl.patchDataset("jdtest", new PatchDatasetDTO(null, null, null, null, null), ResponseUtilsTest.FAKE_TOKEN, empty())).isInstanceOf(RuntimeException.class)
                .hasCause(new RmesException(HttpStatus.BAD_REQUEST, "All required fields are null", "Fill in at least one valid field"));
    }

    @Test
    void testPresenceRelationsPuisAjout_checkFieldIsAdded() throws RmesException, JsonProcessingException {

        var datasetImpl = new DataSetsImpl(null, null);
        var swaggerResponse = new DataSetModelSwagger();
        var catalogue_result = new JSONObject(DataSetsUtilsTest.CATALOGUE_RESULT_RELATIONS);
        var expected = new JSONArray(DataSetsUtilsTest.EXPECTED_RELATIONS);
        datasetImpl.testPresenceVariablePuisAjout(swaggerResponse, catalogue_result, new JSONObject(), new JSONObject(), new JSONObject(), new JSONObject());
        assertThat(swaggerResponse.getRelations().get(0)).isEqualTo(expected.get(0));
        assertThat(swaggerResponse.getRelations().get(1)).isEqualTo(expected.get(1));
    }

    @Test
    void testConstructLangContentList(){
        String actual_1= dataSetsImpl.constructLangContentList("contentLg1,contentLg1_","lg1").toString();
        String actual_2= dataSetsImpl.constructLangContentList("contentLg2","lg2").toString();
        String expected_1 = "[LangContent(lang=lg1, content=contentLg1), LangContent(lang=lg1, content=contentLg1_)]";
        String expected_2 = "[LangContent(lang=lg2, content=contentLg2)]";
        assertThat(actual_1).isEqualTo(expected_1);
        assertThat(actual_2).isEqualTo(expected_2);


    }


}