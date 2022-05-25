package fr.insee.rmes.services.datasets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.dto.dataset.*;
import fr.insee.rmes.model.datasets.DataSet;
import fr.insee.rmes.model.datasets.Operation;
import fr.insee.rmes.model.datasets.Serie;
import fr.insee.rmes.model.datasets.Theme;
import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DataSetsImpl extends RdfService implements DataSetsServices {

    @Override
    public String getListDataSets () throws RmesException, JsonProcessingException {
        Map<String, Object> params = initParams();

        JSONArray listDataSet =  repoGestion.getResponseAsArray(buildRequest(Constants.DATASETS_QUERIES_PATH,"getListDataSets.ftlh", params));

        ObjectMapper jsonResponse = new ObjectMapper();
        DataSet[] dataSets = jsonResponse.readValue(listDataSet.toString(), DataSet[].class);

        ObjectMapper mapper = new ObjectMapper();
        List<DataSetDTO> dataSetListDTOS= new ArrayList<>();

        for (DataSet byDataSet : dataSets) {

            List<Titre> titres = getTitreList(byDataSet);
            DataSetDTO dataSetDTO = new DataSetDTO(byDataSet.getId(),titres,byDataSet.getUri(),byDataSet.getDateMiseAJour(),byDataSet.getStatutValidation());
            dataSetListDTOS.add(dataSetDTO);
        }

        return mapper.writeValueAsString(dataSetListDTOS);

    }



    @Override
    public String getDataSetByID (String id) throws RmesException, JsonProcessingException {
        //parametrage de la requête
        Map<String, Object> params = initParams();
        params.put("ID", id);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);

        //requête intiale
        JSONObject dataSetId = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetById.ftlh", params));
        ObjectMapper jsonResponse = new ObjectMapper();
        DataSet dataSet = jsonResponse.readValue(dataSetId.toString(), DataSet.class);

        //récupération du titre du dataset
        List<Titre> titre = getTitreList(dataSet);

        //récupération variable contenant le ou les thèmes du dataset
        List<ThemeDTO> themeListDTOS = getThemeDTOS(dataSetId);

        //récupération de(s) série(s) ou de(s) opération(s) dont est issu le dataset

        List<String> operationStat = List.of(dataSetId.getString("operationStat").split(","));
        Stream<String> streamSerie = operationStat.stream();
        List<String> serieUri = streamSerie.filter(v -> v.contains("/serie/"))
                .collect(Collectors.toList());
        Stream<String> streamOperation = operationStat.stream();
        List<String> operationUri = streamOperation.filter(v -> v.contains("/operation/"))
                .collect(Collectors.toList());

        //traitement de(s) série(s)/opération(s) lié(s) au dataset

        List<SerieDTO> serieListDTOS = getSerieDTOS(serieUri);
        List<OperationDTO> operationListDTOS = getOperationDTOS(operationUri);

        // fusion de l'ensemble des objets précédents dans datasetDTO en fonction du contenu

        DataSetDTO dataSetDTO = new DataSetDTO(dataSet.getId(), titre, dataSet.getUri(), dataSet.getDateMiseAJour(), dataSet.getDateCreation(), dataSet.getStatutValidation(),themeListDTOS, serieListDTOS, operationListDTOS);
        ObjectMapper dataSetFinal = new ObjectMapper();
        JsonNode dataSetFinalNode=dataSetFinal.valueToTree(dataSetDTO);
        Iterator<JsonNode> it= dataSetFinalNode.iterator();

        while (it.hasNext()){
            JsonNode node=it.next();
            if (node.isContainerNode() && node.isEmpty()){
                it.remove();
            }

        }


        return dataSetFinalNode.toString();
    }

    @NotNull
    private List<OperationDTO> getOperationDTOS(List<String> operationUri) throws RmesException, JsonProcessingException {
        List<OperationDTO> operationListDTOS = new ArrayList<>();
        for (String s : operationUri) {

            Map<String, Object> params4 = initParams();
            params4.put("URI", s.replace(" ", ""));
            params4.put("LG1", Config.LG1);
            params4.put("LG2", Config.LG2);

            JSONObject dataSetId3 = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetByIdOperation.ftlh", params4));
            ObjectMapper jsonResponse4 = new ObjectMapper();
            Operation operation = jsonResponse4.readValue(dataSetId3.toString(), Operation.class);
            LabelDataSet labelDataSet1 = new LabelDataSet(Config.LG1, operation.getlabelOperationLg1());
            LabelDataSet labelDataSet2 = new LabelDataSet(Config.LG2, operation.getlabelOperationLg2());
            List<LabelDataSet> labelDataSets = new ArrayList<>();
            labelDataSets.add(labelDataSet1);
            labelDataSets.add(labelDataSet2);
            OperationDTO operationDTO = new OperationDTO(operation.getUri(), operation.getId(), labelDataSets);
            operationListDTOS.add(operationDTO);


        }
        return operationListDTOS;
    }

    @NotNull
    private List<SerieDTO> getSerieDTOS(List<String> serieUri) throws RmesException, JsonProcessingException {
        List<SerieDTO> serieListDTOS = new ArrayList<>();
        for (String value : serieUri) {

            Map<String, Object> params3 = initParams();
            params3.put("URI", value.replace(" ", ""));
            params3.put("LG1", Config.LG1);
            params3.put("LG2", Config.LG2);

            JSONObject dataSetId3 = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetByIdSerie.ftlh", params3));
            ObjectMapper jsonResponse3 = new ObjectMapper();
            Serie serie = jsonResponse3.readValue(dataSetId3.toString(), Serie.class);
            LabelDataSet labelDataSet1 = new LabelDataSet(Config.LG1, serie.getLabelSerieLg1());
            LabelDataSet labelDataSet2 = new LabelDataSet(Config.LG2, serie.getLabelSerieLg2());
            List<LabelDataSet> labelDataSets = new ArrayList<>();
            labelDataSets.add(labelDataSet1);
            labelDataSets.add(labelDataSet2);
            SerieDTO serieDTO = new SerieDTO(serie.getUri(), serie.getId(), labelDataSets);
            serieListDTOS.add(serieDTO);

        }
        return serieListDTOS;
    }

    @NotNull
    private List<ThemeDTO> getThemeDTOS(JSONObject dataSetId) throws RmesException, JsonProcessingException {
        String[] parts = dataSetId.getString("names").split(",");
        List<ThemeDTO> themeListDTOS = new ArrayList<>();

        for (int i = 0; i < Arrays.stream(parts).count(); i++) {

            Map<String, Object> params2 = initParams();
            params2.put("ID", parts[i].replace(" ", ""));
            params2.put("LG1", Config.LG1);
            params2.put("LG2", Config.LG2);

            JSONObject dataSetId2 = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetByIdTheme.ftlh", params2));
            ObjectMapper jsonResponse2 = new ObjectMapper();
            Theme theme1 = jsonResponse2.readValue(dataSetId2.toString(), Theme.class);
            LabelDataSet labelDataSet1 = new LabelDataSet(Config.LG1, theme1.getLabelThemeLg1());
            LabelDataSet labelDataSet2 = new LabelDataSet(Config.LG2, theme1.getLabelThemeLg2());
            List<LabelDataSet> labelDataSets = new ArrayList<>();
            labelDataSets.add(labelDataSet1);
            labelDataSets.add(labelDataSet2);
            ThemeDTO themeDTO = new ThemeDTO(dataSetId2.getString("uri"), labelDataSets);
            themeListDTOS.add(themeDTO);
            dataSetId2.clear();
        }
        return themeListDTOS;
    }


    @NotNull
    private List<Titre> getTitreList(DataSet byDataSet) {
        Titre titre1 = new Titre(Config.LG1, byDataSet.getTitreLg1());
        Titre titre2 = new Titre(Config.LG2, byDataSet.getTitreLg2());
        List<Titre> titres = new ArrayList<>();
        if (byDataSet.getTitreLg1()!=null) {
            titres.add(titre1);
            titres.add(titre2);   }
        return titres;
    }

    public Map<String, Object> initParams () {
            Map<String, Object> params = new HashMap<>();
            params.put("DATASETS_GRAPH", Config.BASE_GRAPH + Config.DATASETS_GRAPH);
            return params;
        }



    }
