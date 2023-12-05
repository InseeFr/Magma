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

@Service
public class DataSetsImpl extends RdfService implements DataSetsServices {

    public Map<String,Object> params = initParams();
    public ObjectMapper objectMapper = new ObjectMapper();
       @Override
    public String getListDataSets () throws RmesException, JsonProcessingException {

        JSONArray listDataSet =  repoGestion.getResponseAsArray(buildRequest(Constants.DATASETS_QUERIES_PATH,"getListDatasets.ftlh", params));


        DataSet[] dataSets = objectMapper.readValue(listDataSet.toString(), DataSet[].class);
        List<DataSetModelSwagger> dataSetListModelSwaggerS= new ArrayList<>();

        for (DataSet byDataSet : dataSets) {

            List<LangContent> titres = setTitreList(byDataSet.getTitreLg1(),byDataSet.getTitreLg2());
            Id id1 = new Id(byDataSet.getId());
            Uri uri = new Uri(byDataSet.getUri());
            Modified modified = new Modified (byDataSet.getDateMiseAJour());
            DataSetModelSwagger dataSetModelSwagger = new DataSetModelSwagger(id1,titres,uri, modified, byDataSet.getStatutValidation());
            dataSetListModelSwaggerS.add(dataSetModelSwagger);
        }

        return objectMapper.writeValueAsString(dataSetListModelSwaggerS);

    }



    @Override
    public String getDataSetByID (String id) throws RmesException, JsonProcessingException {
        JsonNode dataSetFinalNode = emptyDataSetModelSwagger(findDataSetModelSwagger(id));
        return dataSetFinalNode.toString();
    }

    private JsonNode emptyDataSetModelSwagger(DataSetModelSwagger dataSetModelSwagger) {
        JsonNode dataSetFinalNode = objectMapper.valueToTree(dataSetModelSwagger);
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
        //paramétrage de la requête

        params.put("ID", id);


        JSONObject catalogue_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetById_catalogue.ftlh", params));
        JSONObject adms_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetById_catalogueAdms.ftlh", params));
        JSONObject codes_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetById_catalogueCodes.ftlh", params));
        JSONObject ontologies_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetById_catalogueOntologies.ftlh", params));
        JSONObject organisations_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetById_catalogueOrganisations.ftlh", params));
        JSONObject structures_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetById_catalogueStructures.ftlh", params));


        //récupération du titre
        List<LangContent> title = setTitreList(catalogue_result.getString("titleLg1"), catalogue_result.getString("titleLg2"));

        Id id1=new Id(catalogue_result.getString("id"));
        Uri uri = new Uri(catalogue_result.getString("uri"));
        Modified modified = new Modified(catalogue_result.getString("dateModification"));


        List<String> creatorUris = List.of(catalogue_result.getString("creator").split(","));
        Creator creator = getCreator(creatorUris);
        DisseminationStatus disseminationStatus = new DisseminationStatus(ontologies_result.getString("labeldisseminationStatusLg1"));
        CatalogRecordCreated catalogRecordCreated = new CatalogRecordCreated(catalogue_result.getString("catalogRecordCreated"));
        CatalogRecordModified catalogRecordModified = new CatalogRecordModified(catalogue_result.getString("catalogRecordModified"));
        CatalogRecordCreator catalogRecordCreator = new CatalogRecordCreator(catalogue_result.getString("catalogRecordCreator"));
        CatalogRecordContributor catalogRecordContributor = new CatalogRecordContributor(catalogue_result.getString("catalogRecordContributor"));
        String validationState = catalogue_result.getString("statutValidation");
        DataSetModelSwagger response = new DataSetModelSwagger(id1, title, uri, modified, validationState, creator, disseminationStatus, catalogRecordCreated,catalogRecordModified,catalogRecordCreator,catalogRecordContributor);

        testPresenceVariablePuisAjout(response,catalogue_result,adms_result,codes_result,organisations_result,structures_result);
        return response;
    }


    private void testPresenceVariablePuisAjout(DataSetModelSwagger reponse, JSONObject catalogue_result, JSONObject adms_result, JSONObject codes_result, JSONObject organisations_result, JSONObject structures_result) throws RmesException, JsonProcessingException {
        //récupération du subtitle
        if (catalogue_result.has("subtitleLg1") && catalogue_result.has("subtitleLg2")) {
            List<LangContent> subtitle = setTitreList(catalogue_result.getString("subtitleLg1"), catalogue_result.getString("subtitleLg2"));
            reponse.setSubtitle(subtitle);
        }
        //récupération de l'abstract
        if (catalogue_result.has("abstractLg1") && catalogue_result.has("abstractLg2")) {
            List<LangContent> abstractDataset = setTitreList(catalogue_result.getString("abstractLg1"), catalogue_result.getString("abstractLg2"));
            reponse.setAbstractDataset(abstractDataset);
        }
        //récupération de la description
        if (catalogue_result.has("descriptionLg1") && catalogue_result.has("descriptionLg2")) {
            List<LangContent> description = setTitreList(catalogue_result.getString("descriptionLg1"), catalogue_result.getString("descriptionLg2"));
            reponse.setDescription(description);
        }
        //récupération de la scopeNote
        if (catalogue_result.has("scopeNoteLg1") && catalogue_result.has("scopeNoteLg2")) {
            List<LangContent> scopeNote = setTitreList(catalogue_result.getString("scopeNoteLg1"), catalogue_result.getString("scopeNoteLg2"));
            reponse.setScopeNote(scopeNote);
        }
        //récupération de la landingPage
        if (catalogue_result.has("landingPageLg1") && catalogue_result.has("landingPageLg2")) {
            List<LandingPage> landingPage = new ArrayList<>();
            LandingPage landingPage1 = new LandingPage(Config.LG1,catalogue_result.getString("landingPageLg1"));
            LandingPage landingPage2 = new LandingPage(Config.LG2,catalogue_result.getString("landingPageLg2"));
            landingPage.add(landingPage1);
            landingPage.add(landingPage2);
            reponse.setLandingPage(landingPage);
        }
        //récupération du processStep
        if (catalogue_result.has("processStepLg1") && catalogue_result.has("processStepLg2")) {
            List<LangContent> processStep = setTitreList(catalogue_result.getString("processStepLg1"), catalogue_result.getString("processStepLg2"));
            reponse.setProcessStep(processStep);
        }
        //récupération de publisher
        if (organisations_result.has("idPublisher")) {
            IdLabel publisher = setIdLabel(organisations_result.getString("idPublisher"),organisations_result.getString("labelPublisherLg1"),organisations_result.getString("labelPublisherLg2"));
            reponse.setPublisher(publisher);
        }
        //récupération de type
        if (codes_result.has("labeltypeLg1") && codes_result.has("labeltypeLg2")) {
            List<LangContent> type = setTitreList(codes_result.getString("labeltypeLg1"), codes_result.getString("labeltypeLg2"));
            reponse.setType(type);
        }

        //récupération de accessRights
        if (codes_result.has("labelaccessRightsLg1") && codes_result.has("labelaccessRightsLg2")) {
            List<LangContent> accessRights = setTitreList(codes_result.getString("labelaccessRightsLg1"), codes_result.getString("labelaccessRightsLg2"));
            reponse.setAccessRights(accessRights);
        }
        //récupération de confidentialityStatus
        if (codes_result.has("labelconfidentialityStatusLg1") && codes_result.has("labelconfidentialityStatusLg2")) {
            List<LangContent> confidentialityStatus = setTitreList(codes_result.getString("labelconfidentialityStatusLg1"), codes_result.getString("labelconfidentialityStatusLg2"));
            reponse.setConfidentialityStatus(confidentialityStatus);
        }
        //récupération de accrualPeriodicity
        if (codes_result.has("labelaccrualPeriodicityLg1") && codes_result.has("labelaccrualPeriodicityLg2")) {
            List<LangContent> accrualPeriodicityListTitle = setTitreList(codes_result.getString("labelaccrualPeriodicityLg1"), codes_result.getString("labelaccrualPeriodicityLg2"));
            Label accrualPeriodicity = new Label(accrualPeriodicityListTitle);
            reponse.setAccrualPeriodicity(accrualPeriodicity);
        }
        //récupération de temporal
        if (catalogue_result.has("startPeriod") && catalogue_result.has("endPeriod")) {
            Temporal temporal = new Temporal(catalogue_result.getString("startPeriod"),catalogue_result.getString("endPeriod"));
            reponse.setTemporal(temporal);
        }

        //récupération de spatial
        if (codes_result.has("spatialId")) {
            IdLabel spatial = setIdLabel(codes_result.getString("spatialId"),codes_result.getString("labelspatialLg1"),codes_result.getString("labelspatialLg2"));
            reponse.setSpatial(spatial);
        }

        if (catalogue_result.has("spatialTemporal")){
            SpatialTemporal spatialTemporal = new SpatialTemporal(catalogue_result.getString("spatialTemporal"));
            reponse.setSpatialTemporal(spatialTemporal);
        }

        //récupération de statisticalUnit
        if (codes_result.has("labelstatisticalUnitLg1") && codes_result.has("labelstatisticalUnitLg2")){
            List<LangContent> statisticalUnit = setTitreList(codes_result.getString("labelstatisticalUnitLg1"),codes_result.getString("labelstatisticalUnitLg2"));
            reponse.setStatisticalUnit(statisticalUnit);
        }
        //récupération de structure
        if (structures_result.has("structureId") && structures_result.has("dsd")) {
            Structure structure = new Structure(structures_result.getString("structureId"),structures_result.getString("dsd"));
            reponse.setStructure(structure);
        }
        //récupération de issued
        if (catalogue_result.has("dateEmission")){
            reponse.setIssued(catalogue_result.getString("dateEmission"));
        }
        //récupération de version
        if (catalogue_result.has("version")){
            reponse.setVersion(catalogue_result.getString("version"));
        }
        //récupération de numObservations
        if (catalogue_result.has("numObservations")){
            reponse.setNumObservations(catalogue_result.getInt("numObservations"));
        }
        //récupération de numSeries
        if (catalogue_result.has("numSeries")){
            reponse.setNumSeries(catalogue_result.getInt("numSeries"));
        }
        //récupération de identifier
        if (adms_result.has("identifier")){
            reponse.setIdentifier(adms_result.getString("identifier"));
        }

        //récupération variable contenant le ou les thèmes du dataset
        if (catalogue_result.has("names")) {
            List<ThemeModelSwagger> themeListModelSwaggerS = getThemeModelSwaggerS(catalogue_result);
            reponse.setThemeModelSwaggerS(themeListModelSwaggerS);
        }

        if (catalogue_result.has("operationStat")) {
            //récupération de(s) série(s) ou de(s) opération(s) dont est issu le dataset
            List<String> operationStat = List.of(catalogue_result.getString("operationStat").split(","));
            List<IdLabel> wasGeneratedByList = getWasGeneratedBy(operationStat);
            reponse.setWasGeneratedBy(wasGeneratedByList);
        }
        //récupération de archiveUnit
        if (adms_result.has("archiveUnits")) {
            List<String> urisArchiveUnit = List.of(adms_result.getString("archiveUnits").split(","));
            List<IdLabel> archiveUnitList = getArchiveUnit(urisArchiveUnit);
            reponse.setArchiveUnit(archiveUnitList);
        }
        //récupération de temporalResolution
        if (codes_result.has("temporalResolutions") ){
            List<String> uristemporalResolution = List.of(codes_result.getString("temporalResolutions").split(","));
            List<Label> temporalResolutionList = getTemporalResolution(uristemporalResolution);
            reponse.setTemporalResolution(temporalResolutionList);
        }

        //récupération de spatialResolution
        if (codes_result.has("spatialResolutions")) {
            List<String> urisSpatialResolution = List.of(codes_result.getString("spatialResolutions").split(","));
            List<IdLabel> spatialResolutionList = getSpatialResolution(urisSpatialResolution);
            reponse.setSpatialResolution(spatialResolutionList);

        }
    }


    @Override
    public String getDataSetByIDSummary(String id) throws RmesException, JsonProcessingException {
        //parametrage de la requête

        params.put("ID", id);

        //requête intiale
        JSONObject dataSetId = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetByIDSummary.ftlh", params));
        DataSet dataSet = objectMapper.readValue(dataSetId.toString(), DataSet.class);

        Id id1=new Id(dataSet.getId());
        Uri uri = new Uri(dataSet.getUri());
        Modified modified = new Modified(dataSet.getDateMiseAJour());
        DataSetModelSwagger dataSetModelSwagger = new DataSetModelSwagger(id1, uri, modified);
        JsonNode dataSetFinalNode = emptyDataSetModelSwagger(dataSetModelSwagger);
        return dataSetFinalNode.toString();
    }



    @Override
    public Distribution findDistributions(String id) throws RmesException, JsonProcessingException {

        var datasetModelSwagger = findDataSetModelSwagger(id);
        var uri = datasetModelSwagger.getUriWithTypeUri();
        Distribution d = new Distribution(datasetModelSwagger.getIdWithTypeId(), uri);
        return new Distribution(datasetModelSwagger.getIdWithTypeId(), uri);

    }

    @Override
    public Distributions[] getDataSetDistributionsById(String id) throws RmesException, JsonProcessingException {
        //parametrage de la requête
       params.put("ID", id);

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
        int tailleRep  = listIdentifiers.toArray().length;
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
                    List<LangContent> description = setTitreList(distributionTemp.getString("descriptionLg1"),(distributionTemp.getString("descriptionLg2")));
                    distributionTemp.remove("descriptionLg2");
                    distributionTemp.remove("descriptionLg1");
                    distributionTemp.put("description", description);
                }

                // si le ième JSONObject a un attribut titleLg1 & un attribut titleLg2 alors on met en forme
                //un attribut title qui contient les deux. Puis, on l'ajoute à distributionTemp
                if ((distributionTemp.has("titleLg1")) & (distributionTemp.has("titleLg2"))) {
                    List<LangContent> title = setTitreList(distributionTemp.getString("titleLg1"),distributionTemp.getString("titleLg2"));
                    distributionTemp.remove("titleLg1");
                    distributionTemp.remove("titleLg2");
                    distributionTemp.put("title", title);
                }

                /*mapping pour que le résultat ait la tête d'une distribution (ordre et type des variables, etc)*/
                Distributions distributionFinale = objectMapper.readValue(distributionTemp.toString(), Distributions.class);
                // dans distributionReponse on met les distributions dans le bon ordre (l'ordre dans lequel on les rencontre à  la sortie de la requête SPARQL)
                // tailleRep - listIdentifiers.toArray().length correspond (aux nombres d'identifiants) - (le nombre d'identifiants qu'il reste à traiter)
                distributionReponse[tailleRep - listIdentifiers.toArray().length] = distributionFinale;
                // on enlève l'identifiant déjà traité de la liste des identifiants
                listIdentifiers.remove(identifiant);
            }
        }
        return distributionReponse;
    }

    private Creator getCreator(List<String> creatorUris) throws RmesException {
        List<String> creatorRep = new ArrayList<>();
        for (String s : creatorUris){
            params.put("URI",s.replace(" ",""));
            JSONObject creator = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH,"getCreator.ftlh",params));
            creatorRep.add(creator.getString("creator"));
        }

        return new Creator(creatorRep);
    }
    private List<IdLabel> getWasGeneratedBy(List<String> operationStat) throws RmesException {
        List<IdLabel> wasGeneratedBy = new ArrayList<>();
        for (String s : operationStat){
            params.put("URI", s.replace(" ", ""));

            JSONObject wasGeneratedByQuery = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetByIdWasGeneratedBy.ftlh", params));
            List<LangContent> wasGeneratedByTitles = setTitreList(wasGeneratedByQuery.getString("labelwasGeneratedByLg1"),wasGeneratedByQuery.getString("labelwasGeneratedByLg2"));
            IdLabel wasGeneratedByIdLabel = new IdLabel(wasGeneratedByQuery.getString("wasGeneratedById"),wasGeneratedByTitles);
            wasGeneratedByIdLabel.setType(wasGeneratedByQuery.getString("typeWasGeneratedBy"));
            wasGeneratedBy.add(wasGeneratedByIdLabel);
        }
        return wasGeneratedBy;
    }

    private List<IdLabel> getArchiveUnit(List<String> urisArchiveUnit) throws RmesException {
        List<IdLabel> archiveUnit = new ArrayList<>();
        for (String s : urisArchiveUnit){

            params.put("URI", s.replace(" ", ""));

            JSONObject archiveUnitQuery = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetByIdArchiveUnit.ftlh", params));
            List<LangContent> archiveUnitTitles = setTitreList(archiveUnitQuery.getString("labelarchiveUnitLg1"),archiveUnitQuery.getString("labelarchiveUnitLg2"));
            IdLabel archiveUnitIdLabel = new IdLabel(archiveUnitQuery.getString("idarchiveUnit"),archiveUnitTitles);
            archiveUnit.add(archiveUnitIdLabel);
        }
        return archiveUnit;
    }

    private List<Label> getTemporalResolution(List<String> uristemporalResolution) throws RmesException {
        List<Label> temporalResolution = new ArrayList<>();
        for (String s : uristemporalResolution){

            params.put("URI", s.replace(" ", ""));

            JSONObject temporalResolutionQuery = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDatasetByIdTemporalResolution.ftlh", params));
            List<LangContent> temporalResolutionTitles = setTitreList(temporalResolutionQuery.getString("labeltemporalResolutionLg1"),temporalResolutionQuery.getString("labeltemporalResolutionLg2"));
            Label temporalResolutionLabel = new Label(temporalResolutionTitles);
            temporalResolution.add(temporalResolutionLabel);
        }
        return temporalResolution;
    }


    private List<IdLabel> getSpatialResolution(List<String> urisSpatialResolution) throws RmesException {
        List<IdLabel> spatialResolution = new ArrayList<>();
        for (String s : urisSpatialResolution){

            params.put("URI", s.replace(" ", ""));

            JSONObject spatialResolutionQuery = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDatasetByIdSpatialResolution.ftlh", params));
            List<LangContent> spatialResolutionTitles = setTitreList(spatialResolutionQuery.getString("labelspatialResolutionLg1"),spatialResolutionQuery.getString("labelspatialResolutionLg2"));
            IdLabel spatialResolutionIdLabel = new IdLabel(spatialResolutionQuery.getString("spatialResolutionId"),spatialResolutionTitles);
            spatialResolution.add(spatialResolutionIdLabel);
        }
        return spatialResolution;
    }


    private List<ThemeModelSwagger> getThemeModelSwaggerS(JSONObject dataSetId) throws RmesException, JsonProcessingException {
        String[] parts = dataSetId.getString("names").split(",");
        List<ThemeModelSwagger> themeListModelSwaggerS = new ArrayList<>();

        if (dataSetId.getString("names").isEmpty()) {
          return   themeListModelSwaggerS;
        } else {
            for (int i = 0; i < Arrays.stream(parts).count(); i++) {

                params.put("URI", parts[i].replace(" ", ""));

                JSONObject dataSetId2 = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH, "getDataSetByIdTheme.ftlh", params));
                params.remove("URI");
                Theme theme1 = objectMapper.readValue(dataSetId2.toString(), Theme.class);
                LabelDataSet labelDataSet1 = new LabelDataSet(Config.LG1, theme1.getLabelThemeLg1());
                LabelDataSet labelDataSet2 = new LabelDataSet(Config.LG2, theme1.getLabelThemeLg2());
                List<LabelDataSet> labelDataSets = new ArrayList<>();
                labelDataSets.add(labelDataSet1);
                labelDataSets.add(labelDataSet2);
                ThemeModelSwagger themeModelSwagger = new ThemeModelSwagger(theme1.getUri(), labelDataSets,theme1.getThemeTaxonomy());
                themeListModelSwaggerS.add(themeModelSwagger);
                dataSetId2.clear();
            }
            return themeListModelSwaggerS;
        }
    }


    @NotNull
    private List<LangContent> setTitreList(String elementLg1, String elementLg2) {
        LangContent titre1 = new LangContent(Config.LG1, elementLg1);
        LangContent titre2 = new LangContent(Config.LG2, elementLg2);
        List<LangContent> titres = new ArrayList<>();
        titres.add(titre1);
        titres.add(titre2);
        return titres;
    }


    private IdLabel setIdLabel(String id, String labelLg1, String labelLg2) {
        List<LangContent> langContentList = setTitreList(labelLg1,labelLg2);
        return new IdLabel(id,langContentList);
    }

    public Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("DATASETS_GRAPH", Config.BASE_GRAPH + Config.DATASETS_GRAPH);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
        params.put("ADMS_GRAPH",Config.BASE_GRAPH +  Config.ADMS_GRAPH);
        params.put("STRUCTURES_GRAPH",Config.BASE_GRAPH + Config.STRUCTURES_GRAPH);
        params.put("CODES_GRAPH",Config.BASE_GRAPH + Config.CODELIST_GRAPH);
        params.put("CONCEPTS_GRAPH",Config.BASE_GRAPH + Config.CONCEPTS_BASE_URI);
        params.put("ORGANISATIONS_GRAPH",Config.BASE_GRAPH + Config.ORGANISATIONS_GRAPH);
        params.put("OPERATIONS_GRAPH",Config.BASE_GRAPH + Config.OPERATIONS_SERIES_GRAPH);
        params.put("ONTOLOGIES_GRAPH",Config.BASE_GRAPH + Config.ONTOLOGIES_BASE_URI);
        return params;
    }
}
