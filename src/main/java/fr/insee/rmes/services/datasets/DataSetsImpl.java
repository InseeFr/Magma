package fr.insee.rmes.services.datasets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.dto.datasets.PatchDatasetDTO;
import fr.insee.rmes.model.CodeList.Code;
import fr.insee.rmes.model.datasets.*;
import fr.insee.rmes.modelSwagger.dataset.*;
import fr.insee.rmes.persistence.FreeMarkerUtils;
import fr.insee.rmes.persistence.RdfService;
import fr.insee.security.User;
import fr.insee.rmes.services.codelists.CodeListsServices;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.*;
@Service
public class DataSetsImpl extends RdfService implements DataSetsServices {

    private static final Logger logger = LoggerFactory.getLogger(DataSetsImpl.class);
    public static final String CONTENU = "contenu";
    public static final String LANGUE = "langue";
    public static final String DISTRIBUTIONS_PATH ="getDistributionsById/";
    public static final String DATASET_BY_ID_PATH ="getDatasetById/";
    public static final String DATASET_LIST ="getListDatasets/";

    private final Map<String,Object> params = initParams();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestClient restClient;
    private final CodeListsServices codeListsServices;

    protected DataSetsImpl(FreeMarkerUtils freeMarkerUtils) {
        super(freeMarkerUtils);
        restClient=null;
        codeListsServices=null;
    }

    @Autowired
    public DataSetsImpl(RestClient restClient, CodeListsServices codeListsServices){
        this.restClient=restClient;
        this.codeListsServices=codeListsServices;
    }

    private  List<IdLabel>  getStatisticalUnit(List<String> uriStatistical,String id) throws RmesException {
        List<IdLabel>  statistical = new ArrayList<>();
        for (String uri : uriStatistical){
            params.put("URI",uri);
            params.put("ID",id);
            JSONObject spatialResolutionQuery = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDataSetByIdUriStatistical.ftlh", params));
            List<LangContent> spatialResolutionTitles = constructLangContent(spatialResolutionQuery.getString("labelUriStatisticalLg1"),spatialResolutionQuery.getString("labelUriStatisticalLg2"));
            IdLabel spatialResolutionIdLabel = new IdLabel(uri,spatialResolutionTitles);
            statistical.add(spatialResolutionIdLabel);
        }
        return statistical;
    }

    @Override
    public String getListDataSets(String dateMiseAJour) throws RmesException, JsonProcessingException {
        JSONArray listDataSet;
        if (dateMiseAJour.isEmpty()){
            listDataSet =  repoGestion.getResponseAsArray(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_LIST,"getListDatasets.ftlh", params));
        }
        else{
            params.put("DATE",dateMiseAJour);
            listDataSet =  repoGestion.getResponseAsArray(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_LIST,"getListDatasetsFilterByDate.ftlh", params));
        }

        DataSet[] dataSets = objectMapper.readValue(listDataSet.toString(), DataSet[].class);
        List<DataSetModelSwagger> dataSetListModelSwaggerS= new ArrayList<>();

        for (DataSet byDataSet : dataSets) {

            List<LangContent> titres = constructLangContent(byDataSet.getTitreLg1(),byDataSet.getTitreLg2());
            Id id1 = new Id(byDataSet.getId());
            Uri uri = new Uri(byDataSet.getUri());
            Modified modified = new Modified (byDataSet.getDateMiseAJour());
            DataSetModelSwagger dataSetModelSwagger = new DataSetModelSwagger(id1,titres,uri, modified, byDataSet.getStatutValidation());
            dataSetListModelSwaggerS.add(dataSetModelSwagger);
        }

        return objectMapper.writeValueAsString(dataSetListModelSwaggerS);    }


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

    @Override
    public ResponseEntity<String> patchDataset(String datasetId, PatchDatasetDTO patchDataset, String token, Optional<User> user) {
        return httpPatchRequest(token,patchDataset,datasetId, user);
    }


    protected ResponseEntity<String> httpPatchRequest(String token, PatchDatasetDTO body, String datasetId, Optional<User>  user){

        Optional<String> id = user.map(User::id);
        Optional<String> email = user.map(User::email);
        ResponseEntity<Void> response = restClient.patch()
                .uri("/"+datasetId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .header("Authorization", token)
                .retrieve()
                .toBodilessEntity();
        logger.info("id {} ({}) has patched dataset :{}", id, email, datasetId);
        return ResponseEntity.status(response.getStatusCode()).build();
    }

    protected DataSetModelSwagger findDataSetModelSwagger(String id) throws RmesException, JsonProcessingException {
        //paramétrage de la requête
        params.put("ID", id);
        JSONObject catalogue_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH +DATASET_BY_ID_PATH, "getDataSetById_catalogue.ftlh", params));
        removeEmptyKeys(catalogue_result);
        
        if (catalogue_result.has("id")) {
            JSONObject adms_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDataSetById_catalogueAdms.ftlh", params));
            JSONObject codes_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDataSetById_catalogueCodes.ftlh", params));
            JSONObject ontologies_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDataSetById_catalogueOntologies.ftlh", params));
            JSONObject organisations_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDataSetById_catalogueOrganisations.ftlh", params));
            JSONObject structures_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDataSetById_catalogueStructures.ftlh", params));

            //récupération du titre
            List<LangContent> title = constructLangContent(catalogue_result.getString("titleLg1"), catalogue_result.getString("titleLg2"));
            Id id1=new Id(catalogue_result.getString("id"));
            Uri uri = new Uri(catalogue_result.getString("uri"));
            DisseminationStatus disseminationStatus = new DisseminationStatus(ontologies_result.getString("labeldisseminationStatusLg1"));
            CatalogRecordCreated catalogRecordCreated = new CatalogRecordCreated(catalogue_result.getString("catalogRecordCreated"));
            CatalogRecordModified catalogRecordModified = new CatalogRecordModified(catalogue_result.getString("catalogRecordModified"));
            CatalogRecordCreator catalogRecordCreator = new CatalogRecordCreator(catalogue_result.getString("catalogRecordCreator"));
            CatalogRecordContributor catalogRecordContributor = new CatalogRecordContributor(catalogue_result.getString("catalogRecordContributor"));
            String validationState = catalogue_result.getString("statutValidation");
            DataSetModelSwagger response = new DataSetModelSwagger(id1, title, uri, validationState, disseminationStatus, catalogRecordCreated,catalogRecordModified,catalogRecordCreator,catalogRecordContributor);
            testPresenceVariablePuisAjout(response,catalogue_result,adms_result,codes_result,organisations_result,structures_result);
            return response;
        } else {
            throw new RmesException(HttpStatus.NOT_FOUND, "Non existent dataset identifier", "The id " + id + " does not correspond to any dataset");
        }
    }

    private void removeEmptyKeys(JSONObject jsonObject) {
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if(jsonObject.getString(key).isEmpty()){
                keys.remove();
            }
        }
    }

    protected void testPresenceVariablePuisAjout(DataSetModelSwagger reponse, JSONObject catalogue_result, JSONObject adms_result, JSONObject codes_result, JSONObject organisations_result, JSONObject structures_result) throws RmesException, JsonProcessingException {
        //récupération de le date de mofidication
        if (catalogue_result.has("dateModification")) {
            Modified modified = new Modified(catalogue_result.getString("dateModification"));
            reponse.setModified(modified.toString());
        }
        //récupération de la liste de creators
        if (!catalogue_result.optString("creators").isEmpty()){
            List<String> creatorUris = List.of(catalogue_result.getString("creators").split(","));
            List<IdLabel> creator = getCreator(creatorUris);
            reponse.setCreator(creator);
        }
        //récupération du subtitle
        if (catalogue_result.has("subtitleLg1") && catalogue_result.has("subtitleLg2")) {
            List<LangContent> subtitle = constructLangContent(catalogue_result.getString("subtitleLg1"), catalogue_result.getString("subtitleLg2"));
            reponse.setSubtitle(subtitle);
        }
        //récupération de l'abstract
        if (catalogue_result.has("abstractLg1") && catalogue_result.has("abstractLg2")) {
            List<LangContent> abstractDataset = constructLangContent(catalogue_result.getString("abstractLg1"), catalogue_result.getString("abstractLg2"));
            reponse.setAbstractDataset(abstractDataset);
        }
        //récupération de la description
        if (catalogue_result.has("descriptionLg1") && catalogue_result.has("descriptionLg2")) {
            List<LangContent> description = constructLangContent(catalogue_result.getString("descriptionLg1"), catalogue_result.getString("descriptionLg2"));
            reponse.setDescription(description);
        }
        //récupération de la scopeNote
        if (catalogue_result.has("scopeNoteLg1") && catalogue_result.has("scopeNoteLg2")) {
            List<LangContent> scopeNote = constructLangContent(catalogue_result.getString("scopeNoteLg1"), catalogue_result.getString("scopeNoteLg2"));
            reponse.setScopeNote(scopeNote);
        }
        //récupération de la landingPage
        if (!catalogue_result.optString("landingPageLg1").isEmpty() && !catalogue_result.optString("landingPageLg2").isEmpty()) {
            List<LandingPage> landingPage = new ArrayList<>();
            LandingPage landingPage1 = new LandingPage(Config.LG1,catalogue_result.getString("landingPageLg1"));
            LandingPage landingPage2 = new LandingPage(Config.LG2,catalogue_result.getString("landingPageLg2"));
            landingPage.add(landingPage1);
            landingPage.add(landingPage2);
            reponse.setLandingPage(landingPage);
        }
        //récupération de la liste de relations
        if (!catalogue_result.optString("relations").isEmpty()){
            List<String> relationsUris = List.of(catalogue_result.getString("relations").split(","));
            reponse.setRelations(relationsUris);
        }

        //récupération du processStep
        if (codes_result.has("codeProcessStep")) {
            String codeProcessStepValue = codes_result.getString("codeProcessStep");
            params.put("codeProcessStep", codeProcessStepValue);
            JSONObject processStepResult = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getProcessStep.ftlh", params));
            ProcessStep processStep = constructCodeList(processStepResult.getString("notation"));
            reponse.setProcessStep(processStep);
        }
        //récupération de publisher
        if (organisations_result.has("idPublisher")) {
            IdLabel publisher = constructIdLabel(organisations_result.getString("idPublisher"),organisations_result.getString("labelPublisherLg1"),organisations_result.getString("labelPublisherLg2"));
            reponse.setPublisher(publisher);
        }

        //récupération de type
        if (codes_result.has("labeltypeLg1") && codes_result.has("labeltypeLg2")) {
            List<LangContent> type = constructLangContent(codes_result.getString("labeltypeLg1"), codes_result.getString("labeltypeLg2"));
            reponse.setType(type);
        }

        //récupération de accessRights
        if (codes_result.has("labelaccessRightsLg1") && codes_result.has("labelaccessRightsLg2")) {
            List<LangContent> accessRights = constructLangContent(codes_result.getString("labelaccessRightsLg1"), codes_result.getString("labelaccessRightsLg2"));
            reponse.setAccessRights(accessRights);
        }
        //récupération de confidentialityStatus
        if (codes_result.has("labelconfidentialityStatusLg1") && codes_result.has("labelconfidentialityStatusLg2")) {
            List<LangContent> confidentialityStatus = constructLangContent(codes_result.getString("labelconfidentialityStatusLg1"), codes_result.getString("labelconfidentialityStatusLg2"));
            reponse.setConfidentialityStatus(confidentialityStatus);
        }
        //récupération de accrualPeriodicity
        if (codes_result.has("labelaccrualPeriodicityLg1") && codes_result.has("labelaccrualPeriodicityLg2")) {
            List<LangContent> accrualPeriodicityListTitle = constructLangContent(codes_result.getString("labelaccrualPeriodicityLg1"), codes_result.getString("labelaccrualPeriodicityLg2"));
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
            IdLabel spatial = constructIdLabel(codes_result.getString("spatialId"),codes_result.getString("labelspatialLg1"),codes_result.getString("labelspatialLg2"));
            reponse.setSpatial(spatial);
        }

        if (catalogue_result.has("spatialTemporal")){
            SpatialTemporal spatialTemporal = new SpatialTemporal(catalogue_result.getString("spatialTemporal"));
            reponse.setSpatialTemporal(spatialTemporal);
        }
        //récupération de keyword
        reponse.setKeyword(new ArrayList<>());
        if (catalogue_result.has("keywordLg1")){
            List<LangContent> keyword = constructLangContentList(catalogue_result.getString("keywordLg1"),Config.LG1);
            reponse.setKeyword(keyword);
        }
        if (catalogue_result.has("keywordLg2")){
            List<LangContent> keyword = constructLangContentList(catalogue_result.getString("keywordLg2"),Config.LG2);
            if (reponse.getKeyword().size() >0){
                keyword.addAll(reponse.getKeyword());
            }
            reponse.setKeyword(keyword);
        }

        //récupération de statisticalUnit
       if (codes_result.has("labelstatisticalUnitLg1") && codes_result.has("labelstatisticalUnitLg2")){
           String id =catalogue_result.get("id").toString();
           params.put("ID",id);
           JSONObject answer= repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDataSetByIdUriStatisticalNames.ftlh", params));
           List<String> uriStatistical = List.of(answer.get("names").toString().split(","));
           List<IdLabel> spatialResolutionList = getStatisticalUnit(uriStatistical,catalogue_result.get("id").toString());
           reponse.setStatisticalUnit(spatialResolutionList);
        }

        //récupération de structure

        if(!structures_result.isEmpty()) {
          
            if (structures_result.has("DataStructureDefinition")){

                if (structures_result.has("structureId") && structures_result.has("dsd")) {
                    Structure structure = new Structure(structures_result.getString("structureId"), structures_result.getString("dsd"));
                    reponse.setStructure(structure);}

                if ( structures_result.has("uri") && structures_result.has("structureId") && structures_result.has("dsd")) {
                    Structure structure = new Structure(structures_result.getString("uri"),structures_result.getString("structureId"),structures_result.getString("dsd"));
                    reponse.setStructure(structure);}

            }

            else{
                
                if(structures_result.has("uri")){
                    Structure structureCase = new Structure(structures_result.getString("uri"));
                    reponse.setStructure(structureCase);
                }

            }
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
        //récupération de derivedFromS quand il est non vide
        if (!catalogue_result.optString("wasDerivedFromS").isEmpty()){
            List<String> wasDerivedFromList = List.of(catalogue_result.getString("wasDerivedFromS").split(","));

            if (!catalogue_result.optString("derivedDescriptionLg1").isEmpty() && !catalogue_result.optString("derivedDescriptionLg2").isEmpty()) {
                WasDerivedFrom wasDerivedFrom = constructWasDerivedFrom(wasDerivedFromList, catalogue_result.getString("derivedDescriptionLg1"), catalogue_result.getString("derivedDescriptionLg2"));
                reponse.setWasDerivedFrom(wasDerivedFrom);
            }
            else {
                WasDerivedFrom wasDerivedFrom = constructWasDerivedFrom(wasDerivedFromList);
                reponse.setWasDerivedFrom(wasDerivedFrom);
            }

        }
        //récupération de archiveUnit
        if (!adms_result.optString("archiveUnits").isEmpty()) {
            List<String> urisArchiveUnit = List.of(adms_result.getString("archiveUnits").split(","));
            List<IdLabel> archiveUnitList = getArchiveUnit(urisArchiveUnit);
            reponse.setArchiveUnit(archiveUnitList);
        }
        //récupération de temporalResolution
        if (!codes_result.optString("temporalResolutions").isEmpty()) {
            List<String> uristemporalResolution = List.of(codes_result.getString("temporalResolutions").split(","));
            List<Label> temporalResolutionList = getTemporalResolution(uristemporalResolution);
            reponse.setTemporalResolution(temporalResolutionList);
        }

        //récupération de spatialResolution
        if (!codes_result.optString("spatialResolutions").isEmpty()) {
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
        JSONObject dataSetId = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDataSetByIDSummary.ftlh", params));
        if (dataSetId.has("id")) {
            DataSet dataSet = objectMapper.readValue(dataSetId.toString(), DataSet.class);

            Id id1 = new Id(dataSet.getId());
            Uri uri = new Uri(dataSet.getUri());
            Modified modified = new Modified(dataSet.getDateMiseAJour());
            DataSetModelSwagger dataSetModelSwagger = new DataSetModelSwagger(id1, uri, modified);
            JsonNode dataSetFinalNode = emptyDataSetModelSwagger(dataSetModelSwagger);
            return dataSetFinalNode.toString();
        }
        else {
            throw new RmesException(HttpStatus.NOT_FOUND, "Non existent dataset identifier", "The id " + id + " does not correspond to any dataset");
        }
    }

    @Override
    public Distributions[] getDataSetDistributionsById(String id) throws RmesException, JsonProcessingException {
        //parametrage de la requête
        params.put("ID", id);

        //requête initiale
        JSONArray distributionsId = repoGestion.getResponseAsArray(buildRequest(Constants.DATASETS_QUERIES_PATH + DISTRIBUTIONS_PATH, "getDistributionsById.ftlh", params));

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
                if ((distributionTemp.has("descriptionLg2")) && (distributionTemp.has("descriptionLg1"))) {
                    List<LangContent> description = constructLangContent(distributionTemp.getString("descriptionLg1"),(distributionTemp.getString("descriptionLg2")));
                    distributionTemp.remove("descriptionLg2");
                    distributionTemp.remove("descriptionLg1");
                    distributionTemp.put("description", description);
                }

                // si le ième JSONObject a un attribut titleLg1 & un attribut titleLg2 alors on met en forme
                //un attribut title qui contient les deux. Puis, on l'ajoute à distributionTemp
                if ((distributionTemp.has("titleLg1")) && (distributionTemp.has("titleLg2"))) {
                    List<LangContent> title = constructLangContent(distributionTemp.getString("titleLg1"),distributionTemp.getString("titleLg2"));
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



    private List<IdLabel> getCreator(List<String> creatorUris) throws RmesException {
        List<IdLabel> creator = new ArrayList<>();
        for (String s : creatorUris){

            params.put("URI", s.replace(" ", ""));

            JSONObject creator_result = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDataSetByIdCreator.ftlh", params));
            List<LangContent> creatorTitles = constructLangContent(creator_result.getString("labelCreatorLg1"),creator_result.getString("labelCreatorLg2"));
            IdLabel creatorIdLabel = new IdLabel(creator_result.getString("idCreator"),creatorTitles);
            creator.add(creatorIdLabel);
        }
        return creator;
    }
    private List<IdLabel> getWasGeneratedBy(List<String> operationStat) throws RmesException {
        List<IdLabel> wasGeneratedBy = new ArrayList<>();
        for (String s : operationStat){
            params.put("URI", s.replace(" ", ""));

            JSONObject wasGeneratedByQuery = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDataSetByIdWasGeneratedBy.ftlh", params));
            List<LangContent> wasGeneratedByTitles = constructLangContent(wasGeneratedByQuery.getString("labelwasGeneratedByLg1"),wasGeneratedByQuery.getString("labelwasGeneratedByLg2"));
            IdLabel wasGeneratedByIdLabel = new IdLabel(wasGeneratedByQuery.getString("wasGeneratedById"),wasGeneratedByTitles);
            wasGeneratedByIdLabel.setType(wasGeneratedByQuery.getString("typeWasGeneratedBy"));
            wasGeneratedBy.add(wasGeneratedByIdLabel);
        }
        return wasGeneratedBy;
    }

    private List<IdLabel> getArchiveUnit(List<String> urisArchiveUnit) throws RmesException {
        List<IdLabel> archiveUnit = new ArrayList<>();
        for (String s : urisArchiveUnit){

            params.put("URI", s.trim());

            JSONObject archiveUnitQuery = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDataSetByIdArchiveUnit.ftlh", params));
            List<LangContent> archiveUnitTitles = constructLangContent(archiveUnitQuery.getString("labelarchiveUnitLg1"),archiveUnitQuery.getString("labelarchiveUnitLg2"));
            IdLabel archiveUnitIdLabel = new IdLabel(archiveUnitQuery.getString("idarchiveUnit"),archiveUnitTitles);
            archiveUnit.add(archiveUnitIdLabel);
        }
        return archiveUnit;
    }

    private List<Label> getTemporalResolution(List<String> uristemporalResolution) throws RmesException {
        List<Label> temporalResolution = new ArrayList<>();
        for (String s : uristemporalResolution){

            params.put("URI", s.replace(" ", ""));

            JSONObject temporalResolutionQuery = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDatasetByIdTemporalResolution.ftlh", params));
            List<LangContent> temporalResolutionTitles = constructLangContent(temporalResolutionQuery.getString("labeltemporalResolutionLg1"),temporalResolutionQuery.getString("labeltemporalResolutionLg2"));
            Label temporalResolutionLabel = new Label(temporalResolutionTitles);
            temporalResolution.add(temporalResolutionLabel);
        }
        return temporalResolution;
    }


    private List<IdLabel> getSpatialResolution(List<String> urisSpatialResolution) throws RmesException {
        List<IdLabel> spatialResolution = new ArrayList<>();
        for (String s : urisSpatialResolution){

            params.put("URI", s.replace(" ", ""));

            JSONObject spatialResolutionQuery = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDatasetByIdSpatialResolution.ftlh", params));
            List<LangContent> spatialResolutionTitles = constructLangContent(spatialResolutionQuery.getString("labelspatialResolutionLg1"),spatialResolutionQuery.getString("labelspatialResolutionLg2"));
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

                JSONObject dataSetId2 = repoGestion.getResponseAsObject(buildRequest(Constants.DATASETS_QUERIES_PATH+DATASET_BY_ID_PATH, "getDataSetByIdTheme.ftlh", params));
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


    protected List<LangContent> constructLangContent(String elementLg1, String elementLg2) {
        return List.of(LangContent.lg1(elementLg1), LangContent.lg2(elementLg2));
    }

    protected List<LangContent> constructLangContentList(String stringListLg, String lg) {
        List<String> listLg= List.of(stringListLg.split(","));
        List<LangContent> rep = new ArrayList<>();
        for (String content : listLg){
            LangContent langContent = new LangContent(lg,content);
            rep.add(langContent);
        }
        return rep;
    }


    private IdLabel constructIdLabel(String id, String labelLg1, String labelLg2) {
        List<LangContent> langContentList = constructLangContent(labelLg1,labelLg2);
        return new IdLabel(id,langContentList);
    }

    private WasDerivedFrom constructWasDerivedFrom(List<String> datasets, String derivedDescriptionLg1, String derivedDescriptionLg2) {
        List<LangContent> descriptions= constructLangContent(derivedDescriptionLg1,derivedDescriptionLg2);
        return new WasDerivedFrom(datasets,descriptions);
    }


    private ProcessStep constructCodeList(String notation) throws RmesException {
        String codeListString = codeListsServices.getCodesListForDataset(notation);
        JSONObject jsonCodeList = new JSONObject(codeListString);
        JSONArray codes = jsonCodeList.getJSONArray("codes");
        List<Code> listeDeCode = new ArrayList<>();
        for (int i = 0 ; i < codes.length(); i++){
            JSONObject obj = codes.getJSONObject(i);
            String id = obj.getString("code");
            JSONArray array = obj.getJSONArray("label");
            List<LangContent> langContentList = new ArrayList<>();
            LangContent langContent1 = new LangContent(array.getJSONObject(0).getString(LANGUE),array.getJSONObject(0).getString(CONTENU));
            langContentList.add(langContent1);
            LangContent langContent2 = new LangContent(array.getJSONObject(1).getString(LANGUE),array.getJSONObject(0).getString(CONTENU));
            langContentList.add(langContent2);
            Label label = new Label(langContentList);
            String uri = obj.getString("uri");
            Code code = new Code(id,label,uri);
            listeDeCode.add(code);
        }

        JSONArray array = jsonCodeList.getJSONArray("label");
        List<LangContent> langContentList = new ArrayList<>();
        LangContent langContent1 = new LangContent(array.getJSONObject(0).getString(LANGUE),array.getJSONObject(0).getString(CONTENU));
        langContentList.add(langContent1);
        LangContent langContent2 = new LangContent(array.getJSONObject(1).getString(LANGUE),array.getJSONObject(0).getString(CONTENU));
        langContentList.add(langContent2);
        Label label = new Label(langContentList);

        return new ProcessStep(notation,label);
    }

    private WasDerivedFrom constructWasDerivedFrom(List<String> datasets) {
        List<LangContent> descriptions = new ArrayList<>();
        return new WasDerivedFrom(datasets,descriptions);
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