package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SerieListModelSwagger {
    private List<SerieListModelSwagger> serieListModelSwaggerS= null;

    public SerieListModelSwagger() {}

    public SerieListModelSwagger(List<SerieListModelSwagger> serieListModelSwaggerS) {
        this.serieListModelSwaggerS=serieListModelSwaggerS;
    }

    public List<SerieListModelSwagger> getSerieListModelSwaggerS () {
        return serieListModelSwaggerS;
    }
}
