package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SerieListModelSwaggerTest {

    @Test
    void shouldCheckInitializationOfConstructors(){
        List<SerieListModelSwagger> serieListModelSwaggerS = List.of(
                new SerieListModelSwagger(),
                new SerieListModelSwagger(),
                new SerieListModelSwagger(),
                new SerieListModelSwagger()
        );

        SerieListModelSwagger firstSerieListModelSwagger = new SerieListModelSwagger();
        SerieListModelSwagger secondSerieListModelSwagger = new SerieListModelSwagger(serieListModelSwaggerS);

        assertTrue(!firstSerieListModelSwagger.equals(secondSerieListModelSwagger) &&
                secondSerieListModelSwagger.getSerieListModelSwaggerS()==serieListModelSwaggerS);
    }

}