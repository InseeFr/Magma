package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class DataSetListModelSwagger extends SerieListModelSwagger {
    private List<DataSetListModelSwagger> dataSetListModelSwaggerS= null;

    public DataSetListModelSwagger() {}

    public DataSetListModelSwagger(List<DataSetListModelSwagger> dataSetListModelSwaggerS) {
        this.dataSetListModelSwaggerS=dataSetListModelSwaggerS;
    }

    public List<DataSetListModelSwagger> getDataSetListModelSwaggerS () {
        return dataSetListModelSwaggerS;
    }

}
