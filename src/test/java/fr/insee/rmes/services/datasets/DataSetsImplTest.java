package fr.insee.rmes.services.datasets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.model.datasets.DataSet;
import fr.insee.rmes.model.datasets.Operation;
import fr.insee.rmes.model.datasets.Serie;
import fr.insee.rmes.model.datasets.Theme;
import fr.insee.rmes.modelSwagger.dataset.*;
import fr.insee.rmes.utils.config.Config;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Disabled("Aim of these tests ?")
class DataSetsImplTest {

    @Test
    void getListDataSetsTest() throws JsonProcessingException {

        DataSet dataSet1 = new DataSet("IdDataset1","uriDataset1","titreFrDataset1","titreEnDataset1","01/01/2022","publié","01/01/2021");
        DataSet dataSet2 = new DataSet("IdDataset2","uriDataset2","titreFrDataset2","titreEnDataset2","01/01/2022","provisoire","01/01/2021");
        List<DataSet> dataSets= new ArrayList<>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);
        ObjectMapper mapper = new ObjectMapper();
        List<DataSetModelSwagger> dataSetListDTOS= new ArrayList<>();

        for (DataSet byDataSet : dataSets) {
            Title titre1 = new Title(Config.LG1, byDataSet.getTitreLg1());
            Title titre2 = new Title(Config.LG2, byDataSet.getTitreLg2());
            List<Title> titres = new ArrayList<>();
            titres.add(titre1);
            titres.add(titre2);
            DataSetModelSwagger dataSetSwagger = new DataSetModelSwagger(byDataSet.getId(),titres,byDataSet.getUri(),byDataSet.getDateMiseAJour(),byDataSet.getStatutValidation());
            dataSetListDTOS.add(dataSetSwagger);
        }

        mapper.writeValueAsString(dataSetListDTOS);



    }

    @Test
    void getDataSetByIDTest() {
        DataSet dataSet =new DataSet("publie","01/01/2021","themeUn,ThemeDeux","01/01/2022","antecedent1/serie/1,antecedent1/serie/2,antecedent2/operation/1,antecedent2/operation/2","nomFrDataset","iddatset","nomDataset","UriDataset");
        Title titre1 = new Title("fr", dataSet.getTitreLg1());
        Title titre2 = new Title("en", dataSet.getTitreLg2());
        List<Title> titres = new ArrayList<>();
        titres.add(titre1);
        titres.add(titre2);

        String[] parts = dataSet.getNames().split(",");
        List<ThemeModelSwagger> themeListDTOS = new ArrayList<>();

        if (dataSet.getNames().endsWith("[]")) {
            themeListDTOS=themeListDTOS;
        } else {
            for (int i = 0; i < Arrays.stream(parts).count(); i++) {
                Theme theme1 = new Theme("uriTheme","labelThemeFR","labelThemeEn");
                LabelDataSet labelDataSet1 = new LabelDataSet(Config.LG1, theme1.getLabelThemeLg1());
                LabelDataSet labelDataSet2 = new LabelDataSet(Config.LG2, theme1.getLabelThemeLg2());
                List<LabelDataSet> labelDataSets = new ArrayList<>();
                labelDataSets.add(labelDataSet1);
                labelDataSets.add(labelDataSet2);
                ThemeModelSwagger themeSwagger = new ThemeModelSwagger(theme1.getUri(), labelDataSets);
                themeListDTOS.add(themeSwagger);

            }
                    }
        List<String> operationStat = List.of(dataSet.getOperationStat().split(","));

        Stream<String> streamSerie = operationStat.stream();
        List<String> serieUri = streamSerie.filter(v -> v.contains("/serie/"))
                .collect(Collectors.toList());


        List<SerieModelSwagger> serieListSwaggerS = new ArrayList<>();
        for (String value : serieUri) {
            Serie serie1 =new Serie("uriSerie","idSerie","labelSerieFr","labelSerieEn");
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
            Operation operation1 = new Operation("uriOp1","idOp1","labelOp1","labelOp1");
            LabelDataSet labelDataSet1 = new LabelDataSet(Config.LG1, operation1.getlabelOperationLg1());
            LabelDataSet labelDataSet2 = new LabelDataSet(Config.LG2, operation1.getlabelOperationLg2());
            List<LabelDataSet> labelDataSets = new ArrayList<>();
            labelDataSets.add(labelDataSet1);
            labelDataSets.add(labelDataSet2);
            OperationModelSwagger operationSwagger = new OperationModelSwagger(operation1.getUri(), operation1.getId(), labelDataSets);
            operationListSwaggerS.add(operationSwagger);
        }
        List<OperationModelSwagger> operationListDTOS =operationListSwaggerS;

        DataSetModelSwagger dataSetSwagger = new DataSetModelSwagger(dataSet.getId(), titres, dataSet.getUri(), dataSet.getDateMiseAJour(), dataSet.getDateCreation(), dataSet.getStatutValidation(),themeListDTOS, serieListDTOS, operationListDTOS);
        ObjectMapper dataSetFinal = new ObjectMapper();
        JsonNode dataSetFinalNode=dataSetFinal.valueToTree(dataSetSwagger);
        Iterator<JsonNode> it= dataSetFinalNode.iterator();

        while (it.hasNext()){
            JsonNode node=it.next();
            if (node.isContainerNode() && node.isEmpty()){
                it.remove();
            }
    }
}
}