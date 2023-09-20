package fr.insee.rmes.services.pogues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.model.operation.OperationById;
import fr.insee.rmes.model.operation.OperationBySerieId;
import fr.insee.rmes.model.operation.SerieById;
import fr.insee.rmes.model.operation.SerieModel;
import fr.insee.rmes.modelSwagger.operation.*;
import fr.insee.rmes.utils.config.Config;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Disabled("aim of these tests ?")
class PoguesImplTest {

    @Test
    void getOperationByCodeTest() throws JsonProcessingException {

        OperationById operationById= new OperationById("serie","idoperation","operationLabelLg1","operationLabelLg2","serieLabelLg1","uri","serieId","serieLabelLg2","proprietaire");
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
        OperationByIdModelSwagger operationByIdSwagger = new OperationByIdModelSwagger(serie,operationById.getId(),labelOperation, operationById.getUri());
        mapper.writeValueAsString(operationByIdSwagger);
    }

    @Test
    void getOperationsBySerieIdTest () throws JsonProcessingException {

        OperationBySerieId operation1BySerieId= new OperationBySerieId("serie","typelabelLg1serie","typelabelLg2serie","oerationId1Serie","operation1LabelLg1Serie","operation1LabelLg2Serie","operation1AltLabelLg2Serie","serieLabelLg1","operation1AltLabelLg1Serie","uriOperation1","serieId","serieLabelLg2");
        OperationBySerieId operation2BySerieId= new OperationBySerieId("serie","typelabelLg1serie","typelabelLg2serie","oerationId2Serie","operation2LabelLg1Serie","operation2LabelLg2Serie","operation2AltLabelLg2Serie","serieLabelLg1","operation2AltLabelLg1Serie","uriOperation2","serieId","serieLabelLg2");
        ArrayList<OperationBySerieId> op= new ArrayList <OperationBySerieId>();
        op.add(operation1BySerieId);
        op.add(operation2BySerieId);

        ObjectMapper mapper = new ObjectMapper();
        List<OperationBySerieIdModelSwagger> operationsBySerieIdSwaggers= new ArrayList<>();
        for (OperationBySerieId bySerieId : op) {
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
            OperationBySerieIdModelSwagger operationBySerieIdSwagger = new OperationBySerieIdModelSwagger(altLabelOperation, labelOperation, bySerieId.getUri(), serie, bySerieId.getOperationId());
            operationsBySerieIdSwaggers.add(operationBySerieIdSwagger);
        }

        mapper.writeValueAsString(operationsBySerieIdSwaggers);
            }

    @Test
    void getSerieByIdTest () throws JsonProcessingException {

        SerieById serieById = new SerieById("type","familyId","periodicityId","periodicityLabelLg2","periodicityLabelLg1","serieUri","typeLabelLg1","typeLabelLg2","frequence","typeId","idserie","serieLabelLg1","serieAltLabelLg1","un nombre","famille","familyLabelLg1","serieAltLabelLg2","familyLabelLg2","serieLabelLg2");

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

        SerieByIdModelSwagger serieByIdSwagger = new SerieByIdModelSwagger(altLabelSerie,label,typeSerie,serieById.getSeries(),serieById.getId(),frequenceSerie,serieById.getNbOperation(),familleSerie);

        mapper.writeValueAsString(serieByIdSwagger);
    }

    @Test
    void getAllSeriesListsTest() throws JsonProcessingException {
        SerieModel Series1 = new SerieModel("type","famille","frequenceId","periodicityLabelLg2","periodicityLabelLg1","serieUri","typeLabelLg1","typeLabelLg2","frequence","typeId","idserie","serieLabelLg1","serieAltLabelLg1","un nombre","famille","familyLabelLg1","serieAltLabelLg2","familyLabelLg2","serieLabelLg2","proprietaire");
        SerieModel Series2 = new SerieModel("type","famille","frequenceId","periodicityLabelLg2","periodicityLabelLg1","serieUri","typeLabelLg1","typeLabelLg2","frequence","typeId","idserie","serieLabelLg1","serieAltLabelLg1","un nombre","famille","familyLabelLg1","serieAltLabelLg2","familyLabelLg2","serieLabelLg2","proprietaire");
        ArrayList<SerieModel> listSeries= new ArrayList <SerieModel>();
        listSeries.add(Series1);
        listSeries.add(Series2);
        ObjectMapper mapper = new ObjectMapper();
        List<SerieByIdModelSwagger> SerieByIdSwaggerS= new ArrayList<>();

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
            SerieByIdModelSwagger serieByIdSwagger = new SerieByIdModelSwagger(altLabelSerie,label,typeSerie,bySerie.getSeries(),bySerie.getId(),frequenceSerie,bySerie.getNbOperation(),familleSerie);
            SerieByIdSwaggerS.add(serieByIdSwagger);

        }
        mapper.writeValueAsString(SerieByIdSwaggerS);
    }

}