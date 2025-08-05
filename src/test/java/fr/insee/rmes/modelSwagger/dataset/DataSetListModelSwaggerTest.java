package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DataSetListModelSwaggerTest {

    @Test
    void shouldCheckAttributesValue(){
        DataSetListModelSwagger firstDataSetListModelSwagger = new DataSetListModelSwagger();

        List<DataSetListModelSwagger> list = List.of(
                new DataSetListModelSwagger(),
                new DataSetListModelSwagger(),
                new DataSetListModelSwagger());

        DataSetListModelSwagger secondDataSetListModelSwagger = new DataSetListModelSwagger(list);

        assertEquals(list,secondDataSetListModelSwagger.getDataSetListModelSwaggerS());
    }
}