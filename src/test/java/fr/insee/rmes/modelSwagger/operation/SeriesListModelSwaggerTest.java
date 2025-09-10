package fr.insee.rmes.modelSwagger.operation;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SeriesListModelSwaggerTest {

    @Test
    void shouldCheckConstructoInitialization() {
        List<SeriesListModelSwagger> listSeriesListModelSwagger = List.of(
                new SeriesListModelSwagger(),
                new SeriesListModelSwagger(),
                new SeriesListModelSwagger(),
                new SeriesListModelSwagger()
        );

        SeriesListModelSwagger firstModel = new SeriesListModelSwagger();
        SeriesListModelSwagger secondModel = new SeriesListModelSwagger(listSeriesListModelSwagger);

        assertTrue(firstModel.getListSeriesListModelSwagger()!=secondModel.getListSeriesListModelSwagger() &&
                listSeriesListModelSwagger==secondModel.getListSeriesListModelSwagger());

    }



}