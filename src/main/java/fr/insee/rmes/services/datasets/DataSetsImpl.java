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

            List<Title> titres = setTitreList(byDataSet.getTitreLg1(),byDataSet.getTitreLg2());
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
        params.put("ADMS_GRAPH",Config.BASE_GRAPH +  Config.ADMS_GRAPH);
        params.put("STRUCTURES_GRAPH",Config.BASE_GRAPH + Config.STRUCTURES_GRAPH);
        params.put("CODES_GRAPH",Config.BASE_GRAPH + Config.CODELIST_GRAPH);
        params.put("OPERATIONS_GRAPH",Config.BASE_GRAPH + Config.OPERATIONS_SERIES_GRAPH);
        params.put("ORGANISATIONS_GRAPH",Config.BASE_GRAPH + Config.ORGANISATIONS_GRAPH);
        params.put("ONTOLOGIES_GRAPH",Config.BASE_GRAPH + Config.ONTOLOGIES_BASE_URI);

        //requête intiale
        //TODO vérifier les bons OPTIONAl dans la requête SPARQl
        JSONObject dataSetId = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetById.ftlh", params));
        ObjectMapper jsonResponse = new ObjectMapper();
        DataSet dataSet = jsonResponse.readValue(dataSetId.toString(), DataSet.class);


        //récupération du titre
        List<Title> title = setTitreList(dataSetId.getString("titleLg1"), dataSetId.getString("titleLg2"));

        DataSetModelSwagger reponse = new DataSetModelSwagger(dataSetId.getString("id"), title, dataSetId.getString("uri"), dataSetId.getString("dateModification"), dataSetId.getString("dateCreation"), dataSetId.getString("statutValidation"), dataSetId.getString("contributor"), dataSetId.getString("creator"), dataSetId.getString("labeldisseminationStatusLg1"));


        //TODO faire tests sur les optionals
        //TODO rassembler les variables dans la query

        //récupération du subtitle
        if (dataSetId.has("subtitleLg1") && dataSetId.has("subtitleLg2")) {
            List<Title> subtitle = setTitreList(dataSetId.getString("subtitleLg1"), dataSetId.getString("subtitleLg2"));
            reponse.setSubtitle(subtitle);
        }
        //récupération de l'abstract
        if (dataSetId.has("abstractLg1") && dataSetId.has("abstractLg2")) {
            List<Title> abstractDataset = setTitreList(dataSetId.getString("abstractLg1"), dataSetId.getString("abstractLg2"));
            reponse.setAbstractDataset(abstractDataset);
        }
        //récupération de la description
        if (dataSetId.has("descriptionLg1") && dataSetId.has("descriptionLg2")) {
            List<Title> description = setTitreList(dataSetId.getString("descriptionLg1"), dataSetId.getString("descriptionLg2"));
            reponse.setDescription(description);
        }
        //récupération de la scopeNote
        if (dataSetId.has("scopeNoteLg1") && dataSetId.has("scopeNoteLg2")) {
            List<Title> scopeNote = setTitreList(dataSetId.getString("scopeNoteLg1"), dataSetId.getString("scopeNoteLg2"));
            reponse.setScopeNote(scopeNote);
        }
        //récupération de la landingPage
        if (dataSetId.has("landingPageLg1") && dataSetId.has("landingPageLg2")) {
            List<Title> landingPage = setTitreList(dataSetId.getString("landingPageLg1"), dataSetId.getString("landingPageLg2"));
            reponse.setLandingPage(landingPage);
        }
        //récupération du processStep
        if (dataSetId.has("processStepLg1") && dataSetId.has("processStepLg2")) {
            List<Title> processStep = setTitreList(dataSetId.getString("processStepLg1"), dataSetId.getString("processStepLg2"));
            reponse.setProcessStep(processStep);
        }
        //récupération de publisher
        if (dataSetId.has("idPublisher")) {
            IdLabel publisher = setIdLabel(dataSetId.getString("idPublisher"),dataSetId.getString("labelPublisherLg1"),dataSetId.getString("labelPublisherLg2"));
            reponse.setPublisher(publisher);
        }
        //récupération de wasGeneratedBy
        if (dataSetId.has("wasGeneratedById")) {
            IdLabel wasGeneratedBy = setIdLabel(dataSetId.getString("wasGeneratedById"),dataSetId.getString("labelwasGeneratedByLg1"),dataSetId.getString("labelwasGeneratedByLg2"));
            wasGeneratedBy.setType("type");
            reponse.setWasGeneratedBy(wasGeneratedBy);
        }
        //récupération de type
        if (dataSetId.has("labeltypeLg1") && dataSetId.has("labeltypeLg2")) {
            List<Title> type = setTitreList(dataSetId.getString("labeltypeLg1"), dataSetId.getString("labeltypeLg2"));
            reponse.setType(type);
        }
        //récupération de archiveUnit
        if (dataSetId.has("idarchiveUnit")) {
            List<IdLabel> archiveUnit = new ArrayList<>();
            IdLabel idLabelArchiveUnit = setIdLabel(dataSetId.getString("idarchiveUnit"),dataSetId.getString("labelarchiveUnitLg1"),dataSetId.getString("labelarchiveUnitLg2"));
            archiveUnit.add(idLabelArchiveUnit);
            reponse.setArchiveUnit(archiveUnit);
        }
        //récupération de accessRights
        if (dataSetId.has("labelaccessRightsLg1") && dataSetId.has("labelaccessRightsLg2")) {
            List<Title> accessRights = setTitreList(dataSetId.getString("labelaccessRightsLg1"), dataSetId.getString("labelaccessRightsLg2"));
            reponse.setAccessRights(accessRights);
        }
        //récupération de confidentialityStatus
        if (dataSetId.has("labelconfidentialityStatusLg1") && dataSetId.has("labelconfidentialityStatusLg2")) {
            List<Title> confidentialityStatus = setTitreList(dataSetId.getString("labelconfidentialityStatusLg1"), dataSetId.getString("labelconfidentialityStatusLg2"));
            reponse.setConfidentialityStatus(confidentialityStatus);
        }
        //récupération de accrualPeriodicity
        if (dataSetId.has("labelaccrualPeriodicityLg1") && dataSetId.has("labelaccrualPeriodicityLg2")) {
            List<Title> accrualPeriodicity = setTitreList(dataSetId.getString("labelaccrualPeriodicityLg1"), dataSetId.getString("labelaccrualPeriodicityLg2"));
            reponse.setAccrualPeriodicity(accrualPeriodicity);
        }
        //récupération de temporal
        if (dataSetId.has("startPeriod") && dataSetId.has("endPeriod")) {
            Temporal temporal = new Temporal(dataSetId.getString("startPeriod"),dataSetId.getString("endPeriod"));
            reponse.setTemporal(temporal);
        }
        //récupération de temporalResolution
        if (dataSetId.has("labeltemporalResolutionLg1") && dataSetId.has("labeltemporalResolutionLg2")){
            List<Label> temporalResolution = new ArrayList<>();
            List<Title> titleTemporalResolution = setTitreList(dataSetId.getString("labeltemporalResolutionLg1"), dataSetId.getString("labeltemporalResolutionLg2"));
            Label labelTemporalResolution = new Label(titleTemporalResolution);
            temporalResolution.add(labelTemporalResolution);
            reponse.setTemporalResolution(temporalResolution);
        }
        //récupération de spatial
        if (dataSetId.has("spatialId")) {
            IdLabel spatial = setIdLabel(dataSetId.getString("spatialId"),dataSetId.getString("labelspatialLg1"),dataSetId.getString("labelspatialLg2"));
            reponse.setSpatial(spatial);
        }
        //récupération de spatialResolution
        if (dataSetId.has("spatialResolutionId")) {
            List<IdLabel> spatialResolution = new ArrayList<>();
            IdLabel idLabelSpatialResolution = setIdLabel(dataSetId.getString("spatialResolutionId"),dataSetId.getString("labelspatialResolutionLg1"),dataSetId.getString("labelspatialResolutionLg2"));
            spatialResolution.add(idLabelSpatialResolution);
            reponse.setSpatialResolution(spatialResolution);
        }
        //récupération de statisticalUnit
        if (dataSetId.has("labelstatisticalUnitLg1") && dataSetId.has("labelstatisticalUnitLg2")){
            List<Title> statisticalUnit = setTitreList(dataSetId.getString("labelstatisticalUnitLg1"),dataSetId.getString("labelstatisticalUnitLg2"));
            reponse.setStatisticalUnit(statisticalUnit);
        }
        //récupération de structure
        if (dataSetId.has("structureId") && dataSetId.has("dsd")) {
            Structure structure = new Structure(dataSetId.getString("structureId"),dataSetId.getString("dsd"));
            reponse.setStructure(structure);
        }
        //récupération de issued
        if (dataSetId.has("dateEmission")){
            reponse.setIssued(dataSetId.getString("dateEmission"));
        }
        //récupération de version
        if (dataSetId.has("version")){
            reponse.setVersion(dataSetId.getString("version"));
        }
        //récupération de numObservations
        if (dataSetId.has("numObservations")){
            reponse.setNumObservations(dataSetId.getString("numObservations"));
        }
        //récupération de numSeries
        if (dataSetId.has("numSeries")){
            reponse.setNumSeries(dataSetId.getString("numSeries"));
        }



        //récupération variable contenant le ou les thèmes du dataset
//        List<ThemeModelSwagger> themeListModelSwaggerS = getThemeModelSwaggerS(dataSetId);

        //récupération de(s) série(s) ou de(s) opération(s) dont est issu le dataset

//        List<String> operationStat = List.of(dataSetId.getString("operationStat").split(","));
//        Stream<String> streamSerie = operationStat.stream();
//        List<String> serieUri = streamSerie.filter(v -> v.contains("/serie/"))
//                .collect(Collectors.toList());
//        Stream<String> streamOperation = operationStat.stream();
//        List<String> operationUri = streamOperation.filter(v -> v.contains("/operation/"))
//                .collect(Collectors.toList());

        //traitement de(s) série(s)/opération(s) lié(s) au dataset

//        List<SerieModelSwagger> serieListModelSwaggerS = getSerieModelSwaggerS(serieUri);
//        List<OperationModelSwagger> operationListModelSwaggerS = getOperationModelSwaggerS(operationUri);

        // fusion de l'ensemble des objets précédents dans datasetModelSwagger en fonction du contenu



        return reponse;

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
    public Distributions[] getDataSetDistributionsById(String id) throws RmesException, JsonProcessingException {
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
                    List<Title> description = setTitreList(distributionTemp.getString("descriptionLg1"),(distributionTemp.getString("descriptionLg2")));
                    distributionTemp.remove("descriptionLg2");
                    distributionTemp.remove("descriptionLg1");
                    distributionTemp.put("description", description);
                }

                // si le ième JSONObject a un attribut titleLg1 & un attribut titleLg2 alors on met en forme
                //un attribut title qui contient les deux. Puis, on l'ajoute à distributionTemp
                if ((distributionTemp.has("titleLg1")) & (distributionTemp.has("titleLg2"))) {
                    List<Title> title = setTitreList(distributionTemp.getString("titleLg1"),distributionTemp.getString("titleLg2"));
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
    private List<Title> setTitreList(String elementLg1, String elementLg2) {
        Title titre1 = new Title(Config.LG1, elementLg1);
        Title titre2 = new Title(Config.LG2, elementLg2);
        List<Title> titres = new ArrayList<>();
        titres.add(titre1);
        titres.add(titre2);
        return titres;
    }


    private IdLabel setIdLabel(String id, String labelLg1, String labelLg2) {
        List<Title> titleList = setTitreList(labelLg1,labelLg2);
        IdLabel idLabel = new IdLabel(id,titleList);
        return idLabel;
    }


    public Map<String, Object> initParams () {
            Map<String, Object> params = new HashMap<>();
            params.put("DATASETS_GRAPH", Config.BASE_GRAPH + Config.DATASETS_GRAPH);
            return params;
        }



    }
