package fr.insee.rmes.magma.gestion.old.services.pogues;

import fr.insee.rmes.magma.gestion.old.model.operation.OperationById;
import fr.insee.rmes.magma.gestion.old.model.operation.OperationBySerieId;
import fr.insee.rmes.magma.gestion.old.model.operation.SerieById;
import fr.insee.rmes.magma.gestion.old.model.operation.SerieModel;
import fr.insee.rmes.magma.gestion.old.modelSwagger.operation.*;
import fr.insee.rmes.magma.gestion.old.persistence.FreeMarkerUtils;
import fr.insee.rmes.magma.gestion.old.persistence.RdfService;
import fr.insee.rmes.magma.gestion.old.utils.Constants;
import fr.insee.rmes.magma.gestion.old.utils.config.Config;
import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;
import fr.insee.rmes.magma.gestion.old.modelSwagger.operation.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class PoguesImpl extends RdfService implements PoguesServices {
    public PoguesImpl(FreeMarkerUtils freeMarkerUtils) {
        super(freeMarkerUtils);
    }
    @Override
    public String getAllSeriesLists(Boolean survey) throws RmesException, IOException {
        Map<String, Object> params = initParams();
        JSONArray seriesList;

        if (survey) {
            seriesList = repoGestion.getResponseAsArray(buildRequest(Constants.POGUES_QUERIES_PATH, "getAllSeriesSurvey.ftlh", params));
        } else {
            seriesList = repoGestion.getResponseAsArray(buildRequest(Constants.POGUES_QUERIES_PATH, "getAllSeries.ftlh", params));
        }


        JsonMapper jsonResponse = JsonMapper.builder().build();
        SerieModel[] listSeries = jsonResponse.readValue(seriesList.toString(), SerieModel[].class);

        JsonMapper mapper = JsonMapper.builder().build();
        List<SerieByIdModelSwagger> seriesListModelSwaggerS= new ArrayList<>();

        for (SerieModel bySerie : listSeries) {

            List <String> proprietaires = new ArrayList<>();
                if (bySerie.getProprietaire() !=null) {
                    String proprietaire = bySerie.getProprietaire();
                    proprietaires.add(proprietaire);
                }
            AltLabel altLabelSerie1 = new AltLabel(Config.LG1, bySerie.getSeriesAltLabelLg1());
            AltLabel altLabelSerie2 = new AltLabel(Config.LG2, bySerie.getSeriesAltLabelLg2());
                List<AltLabel> altLabelSerie = new ArrayList<>();
                if (bySerie.getSeriesAltLabelLg1()!=null) {
                    altLabelSerie.add(altLabelSerie1);
                    altLabelSerie.add(altLabelSerie2);   }
            Label labelSerie1= new Label(Config.LG1, bySerie.getSeriesLabelLg1());
            Label labelSerie2= new Label(Config.LG2, bySerie.getSeriesLabelLg2());
                List<Label> label = new ArrayList<>();
                 if (bySerie.getSeriesLabelLg1()!=null) {
                         label.add(labelSerie1);
                         label.add(labelSerie2); }
            Label labelType1=new Label(Config.LG1, bySerie.getTypeLabelLg1());
            Label labelType2=new Label(Config.LG2, bySerie.getTypeLabelLg2());
            List<Label> labelType=new ArrayList<>();
                if (bySerie.getTypeLabelLg1()!=null) {
                        labelType.add(labelType1);
                        labelType.add(labelType2);}
            Type typeSerie= new Type (bySerie.getTypeId(),labelType,bySerie.getType());
            Label labelFreq1=new Label(Config.LG1, bySerie.getPeriodicityLabelLg1());
            Label labelFreq2=new Label(Config.LG2, bySerie.getPeriodicityLabelLg2());
            List<Label> labelFreq=new ArrayList<>();
                if (bySerie.getPeriodicityLabelLg2()!=null) {
                        labelFreq.add(labelFreq1);
                        labelFreq.add(labelFreq2);}
            Frequence frequenceSerie= new Frequence (bySerie.getPeriodicityId(),labelFreq,bySerie.getPeriodicity());
            Label labelFamille1=new Label(Config.LG1, bySerie.getFamilyLabelLg1());
            Label labelFamille2=new Label(Config.LG2, bySerie.getFamilyLabelLg2());
            List<Label> labelFamille=new ArrayList<>();
                if (bySerie.getFamilyLabelLg2()!=null) {
                    labelFamille.add(labelFamille1);
                    labelFamille.add(labelFamille2);}
            Famille familleSerie= new Famille (bySerie.getFamilyId(),labelFamille,bySerie.getFamily());

            SerieByIdModelSwagger serieByIdModelSwagger= new SerieByIdModelSwagger(altLabelSerie,label,typeSerie,bySerie.getSeries(),bySerie.getId(),frequenceSerie,bySerie.getNbOperation(),familleSerie, proprietaires);
            seriesListModelSwaggerS.add(serieByIdModelSwagger);
        }
//        at this stage, a series has as many lines as proprietaires
//        for each series, we map the list of proprietaires (there may be several)
        HashMap<String, List<String>> mapIdProprietaire = new HashMap<>();

        for (SerieByIdModelSwagger serie : seriesListModelSwaggerS) {
            if (!serie.getProprietaire().isEmpty()) {

                String proprietaireTemporaire = serie.getProprietaire().get(0);
                if (mapIdProprietaire.containsKey(serie.getId())) {
                    List<String> templist = mapIdProprietaire.get(serie.getId());
                    if (!templist.contains(proprietaireTemporaire)) {
                        templist.add(proprietaireTemporaire);
                        mapIdProprietaire.remove(serie.getId());
                        mapIdProprietaire.put(serie.getId(), templist);
                    }
                } else {
                    List<String> templist = new ArrayList<>();
                    templist.add(proprietaireTemporaire);
                    mapIdProprietaire.put(serie.getId(), templist);
                }
            }
        }
//        at this stage, we have each series with as many attributes as its number of proprietaires

        List <SerieByIdModelSwagger> serieASupprimer = new ArrayList<>();
        for (SerieByIdModelSwagger serie : seriesListModelSwaggerS){
            if (mapIdProprietaire.containsKey(serie.getId()) && (!serie.getProprietaire().isEmpty())){
                serie.setProprietaire(mapIdProprietaire.get(serie.getId()));
                mapIdProprietaire.remove(serie.getId());
            }
            else if (serie.getProprietaire().isEmpty()){
                mapIdProprietaire.remove(serie.getId());
            }
            else {
                serieASupprimer.add(serie);
            }
        }
//        we rebuild seriesListModelSwaggerS with, for each series, as many attributes as its number of proprietaires
        for (SerieByIdModelSwagger serie : serieASupprimer){
            seriesListModelSwaggerS.remove(serie);
        }

        return mapper.writeValueAsString(seriesListModelSwaggerS);

    }


    @Override
    public String getSerieById(String id) throws RmesException, IOException {
        Map<String, Object> params = initParams();

        params.put("ID", id);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);

        JSONArray serieId= repoGestion.getResponseAsArray(buildRequest(Constants.POGUES_QUERIES_PATH, "getSerieById.ftlh", params));
        if (!serieId.isEmpty()) {

        JSONObject serieIdwithOneProprietaire = serieId.getJSONObject(0);
        List <String> proprietaires = new ArrayList<>();
        for (int i = 0; i < serieId.length(); ++i){
            JSONObject jsonobjectOfSerieId = serieId.getJSONObject(i);
            if (jsonobjectOfSerieId.has("proprietaire")) {
                String proprietaire = jsonobjectOfSerieId.getString("proprietaire");
                proprietaires.add(proprietaire);
            }
        }

        JsonMapper jsonResponse = JsonMapper.builder().build();
        SerieById serieById = jsonResponse.readValue(serieIdwithOneProprietaire.toString(),SerieById.class);

        JsonMapper mapper = JsonMapper.builder().build();
        AltLabel altLabelSerie1 = new AltLabel(Config.LG1,serieById.getSeriesAltLabelLg1());
        AltLabel altLabelSerie2 = new AltLabel(Config.LG2, serieById.getSeriesAltLabelLg2());
                 List<AltLabel> altLabelSerie = new ArrayList<>();
                    altLabelSerie.add(altLabelSerie1);
                    altLabelSerie.add(altLabelSerie2);
        Label labelSerie1= new Label(Config.LG1, serieById.getSeriesLabelLg1());
        Label labelSerie2= new Label(Config.LG2, serieById.getSeriesLabelLg2());
                List<Label> label = new ArrayList<>();
                    label.add(labelSerie1);
                    label.add(labelSerie2);
        Label labelType1=new Label(Config.LG1, serieById.getTypeLabelLg1());
        Label labelType2=new Label(Config.LG2, serieById.getTypeLabelLg2());
                List<Label> labelType=new ArrayList<>();
                    labelType.add(labelType1);
                    labelType.add(labelType2);
                        Type typeSerie= new Type (serieById.getTypeId(),labelType,serieById.getType());
        Label labelFreq1=new Label(Config.LG1, serieById.getPeriodicityLabelLg1());
        Label labelFreq2=new Label(Config.LG2, serieById.getPeriodicityLabelLg2());
            List<Label> labelFreq=new ArrayList<>();
                    labelFreq.add(labelFreq1);
                    labelFreq.add(labelFreq2);
                        Frequence frequenceSerie= new Frequence (serieById.getPeriodicityId(),labelFreq,serieById.getPeriodicity());
        Label labelFamille1=new Label(Config.LG1, serieById.getFamilyLabelLg1());
        Label labelFamille2=new Label(Config.LG2, serieById.getFamilyLabelLg2());
            List<Label> labelFamille=new ArrayList<>();
                    labelFamille.add(labelFamille1);
                    labelFamille.add(labelFamille2);
                        Famille familleSerie= new Famille (serieById.getFamilyId(),labelFamille,serieById.getFamily());

        SerieByIdModelSwagger serieByIdModelSwagger= new SerieByIdModelSwagger(altLabelSerie,label,typeSerie,serieById.getSeries(),serieById.getId(),frequenceSerie,serieById.getNbOperation(),familleSerie,proprietaires);

        return mapper.writeValueAsString(serieByIdModelSwagger);
        }
        else {
            throw new RmesException(HttpStatus.NOT_FOUND, "Non existent series identifier", "The id " + id + " does not correspond to any series");
        }
    }

    @Override
    public String getOperationsBySerieId(String id) throws RmesException, IOException {
        Map<String, Object> params = initParams();
        params.put("ID", id);
        JSONArray operationsList = repoGestion.getResponseAsArray(buildRequest(Constants.POGUES_QUERIES_PATH, "getOperationsBySerie.ftlh", params));
        JsonMapper jsonResponse = JsonMapper.builder().build();
        OperationBySerieId[] operationBySerieId = jsonResponse.readValue(operationsList.toString(), OperationBySerieId[].class);

        JsonMapper mapper = JsonMapper.builder().build();
        List<OperationBySerieIdModelSwagger> operationsBySerieIdModelSwaggerS = new ArrayList<>();
        for (OperationBySerieId bySerieId : operationBySerieId) {
            Label labelSerie1 = new Label(Config.LG1, bySerieId.getSeriesLabelLg1());
            Label labelSerie2 = new Label(Config.LG2, bySerieId.getSeriesLabelLg2());
            List<Label> label = new ArrayList<>();
            label.add(labelSerie1);
            label.add(labelSerie2);

            Serie serie;
            if (bySerieId.getPeriodicityLabelLg1() != null && bySerieId.getPeriodicityLabelLg2() != null) {
                Label labelFreq1 = new Label(Config.LG1, bySerieId.getPeriodicityLabelLg1());
                Label labelFreq2 = new Label(Config.LG2, bySerieId.getPeriodicityLabelLg2());
                List<Label> labelFreq = new ArrayList<>();
                labelFreq.add(labelFreq1);
                labelFreq.add(labelFreq2);
                Frequence frequence = new Frequence(bySerieId.getPeriodicityId(), labelFreq, bySerieId.getPeriodicity());
                serie = new Serie(bySerieId.getSeriesId(), label, frequence, bySerieId.getSeries());
            } else {
                serie = new Serie(bySerieId.getSeriesId(), label, bySerieId.getSeries());
            }

            Label labelOperation1 = new Label(Config.LG1, bySerieId.getOperationLabelLg1());
            Label labelOperation2 = new Label(Config.LG2, bySerieId.getOperationLabelLg2());
            List<Label> labelOperation = new ArrayList<>();
            labelOperation.add(labelOperation1);
            labelOperation.add(labelOperation2);

            AltLabel altLabelOperation1 = new AltLabel(Config.LG1, bySerieId.getOperationAltLabelLg1());
            AltLabel altLabelOperation2 = new AltLabel(Config.LG2, bySerieId.getOperationAltLabelLg2());
            List<AltLabel> altLabelOperation = new ArrayList<>();
            altLabelOperation.add(altLabelOperation1);
            altLabelOperation.add(altLabelOperation2);

            OperationBySerieIdModelSwagger operationBySerieIdModelSwagger = new OperationBySerieIdModelSwagger(
                    altLabelOperation, labelOperation, bySerieId.getUri(), serie, bySerieId.getOperationId());

            if(bySerieId.getMillesime() != null){
                operationBySerieIdModelSwagger.setMillesime(bySerieId.getMillesime());
            }
            operationsBySerieIdModelSwaggerS.add(operationBySerieIdModelSwagger);
        }

        return mapper.writeValueAsString(operationsBySerieIdModelSwaggerS);
    }

    @Override
    public String getOperationByCode(String id) throws RmesException, IOException {
        Map<String, Object> params = initParams();
        params.put("ID", id);
        JSONObject operationId = repoGestion.getResponseAsObject(buildRequest(Constants.POGUES_QUERIES_PATH, "getOperationByCode.ftlh", params));
        if (operationId.has("operationId")) {
            JsonMapper jsonResponse = JsonMapper.builder().build();
            OperationById operationById = jsonResponse.readValue(operationId.toString(), OperationById.class);

            JsonMapper mapper = JsonMapper.builder().build();
            Label labelSerie1 = new Label(Config.LG1, operationById.getSeriesLabelLg1());
            Label labelSerie2 = new Label(Config.LG2, operationById.getSeriesLabelLg2());
            List<Label> label = new ArrayList<>();
            label.add(labelSerie1);
            label.add(labelSerie2);
            Serie serie = new Serie(operationById.getSeriesId(), label, operationById.getSeries());
            Label labelOperation1 = new Label(Config.LG1, operationById.getOperationLabelLg1());
            Label labelOperation2 = new Label(Config.LG2, operationById.getOperationLabelLg2());
            List<Label> labelOperation = new ArrayList<>();
            labelOperation.add(labelOperation1);
            labelOperation.add(labelOperation2);

            OperationByIdModelSwagger operationByIdModelSwagger = new OperationByIdModelSwagger(serie, operationById.getId(), labelOperation, operationById.getUri());
            if (operationById.getProprietaire() != null) {
                operationByIdModelSwagger.setProprietaire(operationById.getProprietaire());
            }
            if (operationById.getMillesime() != null) {
                operationByIdModelSwagger.setMillesime(operationById.getMillesime());
            }
            return mapper.writeValueAsString(operationByIdModelSwagger);
        }
        else throw new RmesException(HttpStatus.NOT_FOUND, "Non existent operation identifier", "The id " + id + " does not correspond to any operation");
    }


    public Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);
        return params;
    }




}
