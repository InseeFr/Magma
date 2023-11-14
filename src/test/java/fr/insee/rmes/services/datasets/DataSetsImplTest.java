package fr.insee.rmes.services.datasets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.model.datasets.*;
import fr.insee.rmes.modelSwagger.dataset.*;
import fr.insee.rmes.services.utils.ResponseUtilsTest;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@Disabled("Aim of these tests ?")
public class DataSetsImplTest {

    public static final String URI_TEST = "http://uri.test";

    private static DataSetModelSwagger response=new DataSetModelSwagger();

    @Test
    void getListDataSetsTest() throws JsonProcessingException {

        DataSet dataSet1 = new DataSet("IdDataset1", "uriDataset1", "titreFrDataset1", "titreEnDataset1", "01/01/2022", "publié", "01/01/2021");
        DataSet dataSet2 = new DataSet("IdDataset2", "uriDataset2", "titreFrDataset2", "titreEnDataset2", "01/01/2022", "provisoire", "01/01/2021");
        List<DataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);
        ObjectMapper mapper = new ObjectMapper();
        List<DataSetModelSwagger> dataSetListDTOS = new ArrayList<>();

        for (DataSet byDataSet : dataSets) {
            LangContent titre1 = new LangContent(Config.LG1, byDataSet.getTitreLg1());
            LangContent titre2 = new LangContent(Config.LG2, byDataSet.getTitreLg2());
            Id id1 = new Id(byDataSet.getId());
            Uri uri = new Uri(byDataSet.getUri());
            Modified modified = new Modified (byDataSet.getDateMiseAJour());
            List<LangContent> titres = new ArrayList<>();
            titres.add(titre1);
            titres.add(titre2);
            DataSetModelSwagger dataSetSwagger = new DataSetModelSwagger(id1, titres, uri, modified, byDataSet.getStatutValidation());
            dataSetListDTOS.add(dataSetSwagger);
        }

        mapper.writeValueAsString(dataSetListDTOS);


    }

    @Test
    void getDataSetByIDTest() {
        DataSet dataSet = new DataSet("publie", "01/01/2021", "themeUn,ThemeDeux", "01/01/2022", "antecedent1/serie/1,antecedent1/serie/2,antecedent2/operation/1,antecedent2/operation/2", "nomFrDataset", "iddatset", "nomDataset", "UriDataset");
        LangContent titre1 = new LangContent("fr", dataSet.getTitreLg1());
        LangContent titre2 = new LangContent("en", dataSet.getTitreLg2());
        List<LangContent> titres = new ArrayList<>();
        titres.add(titre1);
        titres.add(titre2);

        String[] parts = dataSet.getNames().split(",");
        List<ThemeModelSwagger> themeListDTOS = new ArrayList<>();

        if (dataSet.getNames().endsWith("[]")) {
            themeListDTOS = themeListDTOS;
        } else {
            for (int i = 0; i < Arrays.stream(parts).count(); i++) {
                Theme theme1 = new Theme("uriTheme", "labelThemeFR", "labelThemeEn","themeTaxonomy");
                LabelDataSet labelDataSet1 = new LabelDataSet(Config.LG1, theme1.getLabelThemeLg1());
                LabelDataSet labelDataSet2 = new LabelDataSet(Config.LG2, theme1.getLabelThemeLg2());
                List<LabelDataSet> labelDataSets = new ArrayList<>();
                labelDataSets.add(labelDataSet1);
                labelDataSets.add(labelDataSet2);
                ThemeModelSwagger themeSwagger = new ThemeModelSwagger(theme1.getUri(), labelDataSets, theme1.getThemeTaxonomy());
                themeListDTOS.add(themeSwagger);

            }
        }
        List<String> operationStat = List.of(dataSet.getOperationStat().split(","));

        Stream<String> streamSerie = operationStat.stream();
        List<String> serieUri = streamSerie.filter(v -> v.contains("/serie/"))
                .collect(Collectors.toList());


        List<SerieModelSwagger> serieListSwaggerS = new ArrayList<>();
        for (String value : serieUri) {
            Serie serie1 = new Serie("uriSerie", "idSerie", "labelSerieFr", "labelSerieEn");
            LabelDataSet labelDataSet1 = new LabelDataSet(Config.LG1, serie1.getLabelSerieLg1());
            LabelDataSet labelDataSet2 = new LabelDataSet(Config.LG2, serie1.getLabelSerieLg2());
            List<LabelDataSet> labelDataSets = new ArrayList<>();
            labelDataSets.add(labelDataSet1);
            labelDataSets.add(labelDataSet2);
            SerieModelSwagger serieSwagger = new SerieModelSwagger(serie1.getUri(), serie1.getId(), labelDataSets);
            serieListSwaggerS.add(serieSwagger);
        }
        List<SerieModelSwagger> serieListDTOS = serieListSwaggerS;

        Stream<String> streamOperation = operationStat.stream();
        List<String> operationUri = streamOperation.filter(v -> v.contains("/operation/"))
                .collect(Collectors.toList());
        List<OperationModelSwagger> operationListSwaggerS = new ArrayList<>();
        for (String s : operationUri) {
            Operation operation1 = new Operation("uriOp1", "idOp1", "labelOp1", "labelOp1");
            LabelDataSet labelDataSet1 = new LabelDataSet(Config.LG1, operation1.getlabelOperationLg1());
            LabelDataSet labelDataSet2 = new LabelDataSet(Config.LG2, operation1.getlabelOperationLg2());
            List<LabelDataSet> labelDataSets = new ArrayList<>();
            labelDataSets.add(labelDataSet1);
            labelDataSets.add(labelDataSet2);
            OperationModelSwagger operationSwagger = new OperationModelSwagger(operation1.getUri(), operation1.getId(), labelDataSets);
            operationListSwaggerS.add(operationSwagger);
        }
        List<OperationModelSwagger> operationListDTOS = operationListSwaggerS;
        Id id1 = new Id(dataSet.getId());
        Uri uri = new Uri(dataSet.getUri());
        Modified modified = new Modified (dataSet.getDateMiseAJour());
        Created created = new Created(dataSet.getDateCreation());
        DataSetModelSwagger dataSetSwagger = new DataSetModelSwagger(id1, titres, uri, modified, created, dataSet.getStatutValidation(), themeListDTOS);
        ObjectMapper dataSetFinal = new ObjectMapper();
        JsonNode dataSetFinalNode = dataSetFinal.valueToTree(dataSetSwagger);
        Iterator<JsonNode> it = dataSetFinalNode.iterator();

        while (it.hasNext()) {
            JsonNode node = it.next();
            if (node.isContainerNode() && node.isEmpty()) {
                it.remove();
            }
        }
    }

    @Test
    void setTitreListTest(){
        LangContent titre1 = new LangContent("fr", "elementLg1");
        LangContent titre2 = new LangContent("en", "elementLg2");
        List<LangContent> titres = new ArrayList<>();
        titres.add(titre1);
        titres.add(titre2);
        assertThat(titres.toString()).isEqualTo(ResponseUtilsTest.EXPECTED_JSON_SET_TITRE_LIST);
    }



    @Test
    void initParamsTest(){
        Map<String, Object> params = new HashMap<>();
        params.put("DATASETS_GRAPH", "DatasetGraphTest");
        params.put("LG1", "fr");
        params.put("LG2", "en");
        params.put("ADMS_GRAPH","AdmsGraphTest");
        params.put("STRUCTURES_GRAPH","StructureGraphTest");
        params.put("CODES_GRAPH","CodesGraphTest");
        params.put("CONCEPTS_GRAPH","ConceptsGraphTest");
        params.put("ORGANISATIONS_GRAPH","OrganisationsGraphTest");
        params.put("OPERATIONS_GRAPH","OperationsGraphTest");
        params.put("ONTOLOGIES_GRAPH","OntologiesGraphTest");
        assertTrue(params.toString().equals(ResponseUtilsTest.EXPECTED_MAP_INIT_PARAMS));
    }


    //inutile je pense
    @Test
    void setIdLabelTest(){
        List<LangContent> langContentList = new ArrayList<>();
        LangContent langContent1 = new LangContent("fr","content1");
        LangContent langContent2 = new LangContent("en","content2");
        langContentList.add(langContent1);
        langContentList.add(langContent2);
        IdLabel idlabeltest = new IdLabel("id",langContentList);
        assertTrue(idlabeltest.toString().equals(ResponseUtilsTest.EXPECTED_LANGCONTENTLIST_SET_ID_LABEL));
        idlabeltest.setType("type");
        assertTrue(idlabeltest.toString().equals(ResponseUtilsTest.EXPECTED_LANGCONTENTLIST_SET_ID_LABEL_WITH_TYPE));
    }

    //TODO petite méthode d'extraction des URIs
    @Test
    void getThemeModelSwaggerSTest(){
        String liste_uris = "uri0, uri1 ,uri2 , uri3";
        String[] parts = liste_uris.split(",");
        List<ThemeModelSwagger> themeListModelSwaggerS = new ArrayList<>();
        for (int i = 0; i <  Arrays.stream(parts).count(); i++){
            Map<String, Object> params = new HashMap<>();
            params.put("URI",parts[i].replace(" ", ""));
            String expectedParams = "{URI=uri"+i+"}";
            assertTrue(params.toString().equals(expectedParams));
            params.remove("URI");
            Theme themetest = new Theme("uri"+i,"labelThemeLg1"+i,"labelThemeLg2"+i,"themeTaxonomy"+i);
            LabelDataSet labelDataSet1 = new LabelDataSet("fr", themetest.getLabelThemeLg1());
            LabelDataSet labelDataSet2 = new LabelDataSet("en", themetest.getLabelThemeLg2());
            List<LabelDataSet> labelDataSets = new ArrayList<>();
            labelDataSets.add(labelDataSet1);
            labelDataSets.add(labelDataSet2);
            ThemeModelSwagger themeModelSwagger = new ThemeModelSwagger(themetest.getUri(), labelDataSets,themetest.getThemeTaxonomy());
            themeListModelSwaggerS.add(themeModelSwagger);
        }
        assertTrue(themeListModelSwaggerS.toString().equals(ResponseUtilsTest.EXPECTED_THEMELIST_GET_THEME_MODEL_SWAGGERS));
    }

    @ParameterizedTest(name = "{0}")
    /*@CsvSource({
            "subtitleLg1, subtitleLg2"
    })*/
    @MethodSource(value = "argumentsProvider")
    void testPresenceVariablePuisAjoutTest_checkFieldIsAdded(String key1, String key2, Supplier<List<LangContent>> getListLangContent) throws RmesException, JsonProcessingException {
        var datasetImpl=new DataSetsImpl();
        var catalogue_result = new JSONObject(Map.of(key1, "l1", key2,"l2" ));
        var expected=datasetImpl.setTitreList("l1", "l2");
        datasetImpl.testPresenceVariablePuisAjout(response, catalogue_result, new JSONObject(), new JSONObject(), new JSONObject(), new JSONObject());
        assertThat(getListLangContent.get()).isEqualTo(expected);
    }

    static Stream<Arguments> argumentsProvider(){
        return Stream.of(
                Arguments.of("subtitleLg1", "subtitleLg2", (Supplier<List<LangContent>>) response::getSubtitle),
                Arguments.of("abstractLg1", "abstractLg2", (Supplier<List<LangContent>>) response::getAbstractDataset),
                Arguments.of("scopeNoteLg1", "scopeNoteLg2", (Supplier<List<LangContent>>) response::getScopeNote),
                Arguments.of("processStepLg1", "processStepLg2", (Supplier<List<LangContent>>) response::getProcessStep)
        );
    }



}