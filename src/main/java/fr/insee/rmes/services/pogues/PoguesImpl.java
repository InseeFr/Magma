package fr.insee.rmes.services.pogues;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.model.operation.OperationById;
import fr.insee.rmes.model.operation.OperationBySerieId;
import fr.insee.rmes.model.operation.SerieById;
import fr.insee.rmes.model.operation.SerieModel;
import fr.insee.rmes.modelSwagger.operation.*;
import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PoguesImpl extends RdfService implements PoguesServices {

    @Override
    public String getAllSeriesLists(Boolean survey) throws RmesException, IOException {
        Map<String, Object> params = initParams();
        JSONArray seriesList ;

        if ( survey) {
            seriesList = repoGestion.getResponseAsArray(buildRequest(Constants.POGUES_QUERIES_PATH, "getAllCodesListsSurvey.ftlh", params));
        }
        else {
            seriesList = repoGestion.getResponseAsArray(buildRequest(Constants.POGUES_QUERIES_PATH, "getAllCodesLists.ftlh", params));
        }

        ObjectMapper jsonResponse = new ObjectMapper();
        SerieModel[] listSeries = jsonResponse.readValue(seriesList.toString(), SerieModel[].class);

        ObjectMapper mapper = new ObjectMapper();
        List<SerieByIdModelSwagger> seriesListModelSwaggerS= new ArrayList<>();

        for (SerieModel bySerie : listSeries) {
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
            SerieByIdModelSwagger serieByIdModelSwagger= new SerieByIdModelSwagger(altLabelSerie,label,typeSerie,bySerie.getSeries(),bySerie.getId(),frequenceSerie,bySerie.getNbOperation(),familleSerie);
            seriesListModelSwaggerS.add(serieByIdModelSwagger);

        }
        return mapper.writeValueAsString(seriesListModelSwaggerS);

    }

    @Override
    public String getSerieById(String id) throws RmesException, IOException {
        Map<String, Object> params = initParams();

        params.put("ID", id);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);

        JSONObject serieId= repoGestion.getResponseAsObject(buildRequest(Constants.POGUES_QUERIES_PATH, "getCodesList.ftlh", params));

        ObjectMapper jsonResponse =new ObjectMapper();
        SerieById serieById = jsonResponse.readValue(serieId.toString(),SerieById.class);

        ObjectMapper mapper = new ObjectMapper();
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
        Label labelType2=new Label(Config.LG1, serieById.getTypeLabelLg2());
                List<Label> labelType=new ArrayList<>();
                    labelType.add(labelType1);
                    labelType.add(labelType2);
                        Type typeSerie= new Type (serieById.getTypeId(),labelType,serieById.getType());
        Label labelFreq1=new Label(Config.LG1, serieById.getPeriodicityLabelLg1());
        Label labelFreq2=new Label(Config.LG1, serieById.getPeriodicityLabelLg2());
            List<Label> labelFreq=new ArrayList<>();
                    labelFreq.add(labelFreq1);
                    labelFreq.add(labelFreq2);
                        Frequence frequenceSerie= new Frequence (serieById.getPeriodicityId(),labelFreq,serieById.getPeriodicity());
        Label labelFamille1=new Label(Config.LG1, serieById.getFamilyLabelLg1());
        Label labelFamille2=new Label(Config.LG1, serieById.getFamilyLabelLg2());
            List<Label> labelFamille=new ArrayList<>();
                    labelFamille.add(labelFamille1);
                    labelFamille.add(labelFamille2);
                        Famille familleSerie= new Famille (serieById.getFamilyId(),labelFamille,serieById.getFamily());

        SerieByIdModelSwagger serieByIdModelSwagger= new SerieByIdModelSwagger(altLabelSerie,label,typeSerie,serieById.getSeries(),serieById.getId(),frequenceSerie,serieById.getNbOperation(),familleSerie);

        return mapper.writeValueAsString(serieByIdModelSwagger);

    }

    @Override
    public String getOperationsBySerieId(String id) throws RmesException, IOException {
        Map<String, Object> params = initParams();
        params.put("ID", id);
        JSONArray operationsList = repoGestion.getResponseAsArray(buildRequest(Constants.POGUES_QUERIES_PATH, "getOperationsBySerie.ftlh", params));
        ObjectMapper jsonResponse = new ObjectMapper();
        OperationBySerieId[] operationBySerieId = jsonResponse.readValue(operationsList.toString(), OperationBySerieId[].class);

        ObjectMapper mapper = new ObjectMapper();
        List<OperationBySerieIdModelSwagger> operationsBySerieIdModelSwaggerS= new ArrayList<>();
        for (OperationBySerieId bySerieId : operationBySerieId) {
            Label labelSerie1 = new Label(Config.LG1, bySerieId.getSeriesLabelLg1());
            Label labelSerie2 = new Label(Config.LG2, bySerieId.getSeriesLabelLg2());
                List<Label> label = new ArrayList<>();
                    label.add(labelSerie1);
                    label.add(labelSerie2);
                        Serie serie = new Serie(bySerieId.getSeriesId(), label, bySerieId.getSeries());
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
                        OperationBySerieIdModelSwagger operationBySerieIdModelSwagger = new OperationBySerieIdModelSwagger(altLabelOperation, labelOperation, bySerieId.getUri(), serie, bySerieId.getOperationId());
            operationsBySerieIdModelSwaggerS.add(operationBySerieIdModelSwagger);
        }

        return mapper.writeValueAsString(operationsBySerieIdModelSwaggerS);

    }

    @Override
    public String getOperationByCode(String id) throws RmesException, IOException {
        Map<String, Object> params = initParams();
        params.put("ID", id);
        JSONObject operationId = repoGestion.getResponseAsObject(buildRequest(Constants.POGUES_QUERIES_PATH, "getOperationByCode.ftlh", params));

        ObjectMapper jsonResponse =new ObjectMapper();
        OperationById operationById = jsonResponse.readValue(operationId.toString(),OperationById.class);

        ObjectMapper mapper = new ObjectMapper();
        Label labelSerie1= new Label(Config.LG1, operationById.getSeriesLabelLg1());
        Label labelSerie2= new Label(Config.LG2, operationById.getSeriesLabelLg2());
            List<Label> label = new ArrayList<>();
                label.add(labelSerie1);
                label.add(labelSerie2);
        Serie serie= new Serie(operationById.getSeriesId(), label, operationById.getSeries());
        Label labelOperation1=new Label(Config.LG1,operationById.getOperationLabelLg1());
        Label labelOperation2=new Label(Config.LG2,operationById.getOperationLabelLg2());
            List<Label> labelOperation = new ArrayList<>();
                labelOperation.add(labelOperation1);
                labelOperation.add(labelOperation2);
        OperationByIdModelSwagger operationByIdModelSwagger= new OperationByIdModelSwagger(serie,operationById.getId(),labelOperation, operationById.getUri());

        return mapper.writeValueAsString(operationByIdModelSwagger);

    }


    public Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);


        return params;
    }




}
