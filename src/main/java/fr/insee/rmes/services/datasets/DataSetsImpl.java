package fr.insee.rmes.services.datasets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.model.datasets.*;
import fr.insee.rmes.modelSwagger.dataset.*;
import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DataSetsImpl extends RdfService implements DataSetsServices {

    @Override
    public String getListDataSets () throws RmesException, JsonProcessingException {
        Map<String, Object> params = initParams();

        JSONArray listDataSet =  repoGestion.getResponseAsArray(buildRequest(Constants.DATASETS_QUERIES_PATH,"getListDatasets.ftlh", params));

        ObjectMapper jsonResponse = new ObjectMapper();
        DataSet[] dataSets = jsonResponse.readValue(listDataSet.toString(), DataSet[].class);
        ObjectMapper mapper = new ObjectMapper();
        List<DataSetModelSwagger> dataSetListModelSwaggerS= new ArrayList<>();

        for (DataSet byDataSet : dataSets) {

            List<Title> titres = getTitreList(byDataSet);
            DataSetModelSwagger dataSetModelSwagger = new DataSetModelSwagger(byDataSet.getId(),titres,byDataSet.getUri(),byDataSet.getDateMiseAJour(),byDataSet.getStatutValidation());
            dataSetListModelSwaggerS.add(dataSetModelSwagger);
        }

        return mapper.writeValueAsString(dataSetListModelSwaggerS);

    }



    @Override
    public String getDataSetByID (String id) throws RmesException, JsonProcessingException {
        JsonNode dataSetFinalNode = emptyDataSetModelSwagger(findDataSetModelSwagger(id));
        return dataSetFinalNode.toString();
    }

    private JsonNode emptyDataSetModelSwagger(DataSetModelSwagger dataSetModelSwagger) {
        ObjectMapper dataSetFinal = new ObjectMapper();
        JsonNode dataSetFinalNode = dataSetFinal.valueToTree(dataSetModelSwagger);
        Iterator<JsonNode> it = dataSetFinalNode.iterator();

        while (it.hasNext()) {
            JsonNode node = it.next();
            if (node.isContainerNode() && node.isEmpty()) {
                it.remove();
            }

        }
        return dataSetFinalNode;
    }

    protected DataSetModelSwagger findDataSetModelSwagger(String id) throws RmesException, JsonProcessingException {
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
        List<Title> title = getTitreList(dataSet);

        //récupération variable contenant le ou les thèmes du dataset
        List<ThemeModelSwagger> themeListModelSwaggerS = getThemeModelSwaggerS(dataSetId);

        //récupération de(s) série(s) ou de(s) opération(s) dont est issu le dataset

        List<String> operationStat = List.of(dataSetId.getString("operationStat").split(","));
        Stream<String> streamSerie = operationStat.stream();
        List<String> serieUri = streamSerie.filter(v -> v.contains("/serie/"))
                .collect(Collectors.toList());
        Stream<String> streamOperation = operationStat.stream();
        List<String> operationUri = streamOperation.filter(v -> v.contains("/operation/"))
                .collect(Collectors.toList());

        //traitement de(s) série(s)/opération(s) lié(s) au dataset

        List<SerieModelSwagger> serieListModelSwaggerS = getSerieModelSwaggerS(serieUri);
        List<OperationModelSwagger> operationListModelSwaggerS = getOperationModelSwaggerS(operationUri);

        // fusion de l'ensemble des objets précédents dans datasetModelSwagger en fonction du contenu

        return new DataSetModelSwagger(dataSet.getId(), title, dataSet.getUri(), dataSet.getDateMiseAJour(), dataSet.getDateCreation(), dataSet.getStatutValidation(),themeListModelSwaggerS, serieListModelSwaggerS, operationListModelSwaggerS);
    }

    @Override
    public String getDataSetByIDFilterByDateMaj (String id) throws RmesException, JsonProcessingException {
        //parametrage de la requête
        Map<String, Object> params = initParams();
        params.put("ID", id);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);

        //requête intiale
        JSONObject dataSetId = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetByIdDateMAJ.ftlh", params));
        ObjectMapper jsonResponse = new ObjectMapper();
        DataSet dataSet = jsonResponse.readValue(dataSetId.toString(), DataSet.class);


        DataSetModelSwagger dataSetModelSwagger = new DataSetModelSwagger(dataSet.getId(), dataSet.getUri(), dataSet.getDateMiseAJour());
        JsonNode dataSetFinalNode = emptyDataSetModelSwagger(dataSetModelSwagger);
        return dataSetFinalNode.toString();
    }

    @Override
    public Distribution findDistributions(String id) throws RmesException, JsonProcessingException {

        var datasetModelSwagger = findDataSetModelSwagger(id);
        return new Distribution(datasetModelSwagger.getId(), datasetModelSwagger.getUri());
    }

    @Override
    public Distributions getDataSetDistributionsById(String id) throws RmesException, JsonProcessingException {
        //parametrage de la requête
        Map<String, Object> params = initParams();
        params.put("ID", id);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);

        //requête initiale
        JSONArray distributionsId = repoGestion.getResponseAsArray(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDistributionsById.ftlh", params));

        /*liste des identifiers (identifiants des distributions) avec leur liste de downloadURL associés*/
        Map<String,List<String>> mapIdentifiersAndDownloadUrls = new HashMap<>();
        List<String> listIdentifiers = new ArrayList<>();

        //Boucle pour créer la listIdentifiers et associer les identifiers à leur liste de DownloadUrls (via mapIdentifiersAndDownloadUrls)
        for (int i=0; i < distributionsId.length(); i++) {
            // On récupère la ième distribution de distributionsId et on y récupère son identfier et sa downloadURL (pas encore de liste de downloadURL)
            JSONObject distributioni = distributionsId.getJSONObject(i);
            String identifieri = distributioni.getString("identifier");
            String downloadURLi = distributioni.getString("downloadURL");

            // si on n'a pas encore vu cet identifier alors on l'ajoute à la liste et à la map
            // puis on lui crée une listDownloadUrls (qui ne contient qu'un élément pour l'instant)
            if (!mapIdentifiersAndDownloadUrls.containsKey(identifieri)){
                List<String> listDownloadUrls = new ArrayList<>();
                listDownloadUrls.add(downloadURLi);
                mapIdentifiersAndDownloadUrls.put(identifieri,listDownloadUrls);
                listIdentifiers.add(identifieri);
            }
            // si on a déjà traité l'identifier, on récupère sa listDownloadUrls pour y ajouter un downloadURL
            else{
                List<String> listDownloadUrls=mapIdentifiersAndDownloadUrls.get(identifieri);
                listDownloadUrls.add(downloadURLi);
                mapIdentifiersAndDownloadUrls.put(identifieri,listDownloadUrls);
            }
        }

        // tailleRep la taille de listIdentifiers
        Integer tailleRep  = listIdentifiers.toArray().length;
        // On crée une liste de Distributions de la taille de listIdentifiers
        // C'est distributionReponse que l'on va renvoyer en sortie
        Distributions[] distributionReponse =  new Distributions[tailleRep];
        // On va faire une boucle for de la taille de distributionsId pour traiter toutes les distributions de la reponse de la requete SPARQL
        // taille de distributionsId >= tailleRep (il peut y avoir plusieurs downloadURL par identifier)
        for (int i=0; i < distributionsId.length(); i++) {
            // On récupère le ième JSONObject de distributionsId
            JSONObject distributionTemp = distributionsId.getJSONObject(i);
            // on récupère l'identifiant de ce ième JSONObject
            String identifiant = distributionTemp.getString("identifier");
            // Si listIdentifiers contient l'identifier alors on traite  le ième JSONObject sinon on ne le traite pas
            // Si il n'y est pas, cela veut dire qu'on a déjà traité cet identifiant (car on l'a déjà supprimé de listIdentifiers)
            if (listIdentifiers.contains(identifiant)) {
                // on récupère la listDownloadUrls associée à l'identifiant (via mapIdentifiersAndDownloadUrls)
                // puis on remove downloadURL du ième JSONObject pour y mettre listDownloadUrls à la place
                List<String> listDownloadUrls = mapIdentifiersAndDownloadUrls.get(identifiant);
                distributionTemp.remove("downloadURL");
                distributionTemp.put("downloadURL", listDownloadUrls);

                // si le ième JSONObject a un attribut descriptionLg2 & un attribut descriptionLg1 alors on met en forme
                //un attribut description qui contient les deux. Puis on l'ajoute à distributionTemp
                if ((distributionTemp.has("descriptionLg2")) & (distributionTemp.has("descriptionLg1"))) {
                    Title descriptionLg1 = new Title(Config.LG1, (String) distributionTemp.get("descriptionLg1"));
                    Title descriptionLg2 = new Title(Config.LG2, (String) distributionTemp.get("descriptionLg2"));
                    List<Title> description = new ArrayList<>();
                    description.add(descriptionLg1);
                    description.add(descriptionLg2);
                    distributionTemp.remove("descriptionLg1");
                    distributionTemp.remove("descriptionLg2");
                    distributionTemp.put("description", description);

                }

                // si le ième JSONObject a un attribut titleLg1 & un attribut titleLg2 alors on met en forme
                //un attribut title qui contient les deux. Puis, on l'ajoute à distributionTemp
                if ((distributionTemp.has("titleLg1")) & (distributionTemp.has("titleLg2"))) {
                    Title titleLg1 = new Title(Config.LG1, (String) distributionTemp.get("titleLg1"));
                    Title titleLg2 = new Title(Config.LG2, (String) distributionTemp.get("titleLg2"));
                    List<Title> title = new ArrayList<>();
                    title.add(titleLg1);
                    title.add(titleLg2);
                    distributionTemp.remove("titleLg1");
                    distributionTemp.remove("titleLg2");
                    distributionTemp.put("title", title);
                }

                /*mapping pour que le résultat ait la tête d'une distribution (ordre et type des variables, etc)*/
                ObjectMapper jsonResponse = new ObjectMapper();
                Distributions distributionFinale = jsonResponse.readValue(distributionTemp.toString(), Distributions.class);
                // dans distributionReponse on met les distributions dans le bon ordre (l'ordre dans lequel on les rencontre à  la sortie de la requête SPARQL)
                // tailleRep - listIdentifiers.toArray().length correspond (aux nombres d'identifiants) - (le nombre d'identifiants qu'il reste à traiter)
                distributionReponse[tailleRep - listIdentifiers.toArray().length] = distributionFinale;
                // on enlève l'identifiant déjà traité de la liste des identifiants
                listIdentifiers.remove(identifiant);
            }
        }
        return distributionReponse;
    }

    @NotNull
    private List<OperationModelSwagger> getOperationModelSwaggerS(List<String> operationUri) throws RmesException, JsonProcessingException {
        List<OperationModelSwagger> operationListModelSwaggerS = new ArrayList<>();
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
            OperationModelSwagger operationModelSwagger = new OperationModelSwagger(operation.getUri(), operation.getId(), labelDataSets);
            operationListModelSwaggerS.add(operationModelSwagger);


        }
        return operationListModelSwaggerS;
    }

    @NotNull
    private List<SerieModelSwagger> getSerieModelSwaggerS(List<String> serieUri) throws RmesException, JsonProcessingException {
        List<SerieModelSwagger> serieListModelSwaggerS = new ArrayList<>();
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
            SerieModelSwagger serieModelSwagger = new SerieModelSwagger(serie.getUri(), serie.getId(), labelDataSets);
            serieListModelSwaggerS.add(serieModelSwagger);

        }
        return serieListModelSwaggerS;
    }


    private List<ThemeModelSwagger> getThemeModelSwaggerS(JSONObject dataSetId) throws RmesException, JsonProcessingException {
        String[] parts = dataSetId.getString("names").split(",");
        List<ThemeModelSwagger> themeListModelSwaggerS = new ArrayList<>();

        if (dataSetId.getString("names").isEmpty()) {
          return   themeListModelSwaggerS;
        } else {
            for (int i = 0; i < Arrays.stream(parts).count(); i++) {

                Map<String, Object> params2 = initParams();
                params2.put("URI", parts[i].replace(" ", ""));
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
                ThemeModelSwagger themeModelSwagger = new ThemeModelSwagger(dataSetId2.getString("uri"), labelDataSets);
                themeListModelSwaggerS.add(themeModelSwagger);
                dataSetId2.clear();
            }
            return themeListModelSwaggerS;
        }
    }


    @NotNull
    private List<Title> getTitreList(DataSet byDataSet) {
        Title titre1 = new Title(Config.LG1, byDataSet.getTitreLg1());
        Title titre2 = new Title(Config.LG2, byDataSet.getTitreLg2());
        List<Title> titres = new ArrayList<>();
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
