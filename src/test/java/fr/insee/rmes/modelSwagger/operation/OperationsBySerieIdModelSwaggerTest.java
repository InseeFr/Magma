package fr.insee.rmes.modelSwagger.operation;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OperationsBySerieIdModelSwaggerTest {

    @Test
    void shouldCheckOperationsBySerieIdModelSwagger(){

        List<OperationsBySerieIdModelSwagger> operations = List.of(
                new OperationsBySerieIdModelSwagger(),
                new OperationsBySerieIdModelSwagger(),
                new OperationsBySerieIdModelSwagger()
        );

        OperationsBySerieIdModelSwagger firstOperation = new OperationsBySerieIdModelSwagger();
        OperationsBySerieIdModelSwagger secondOperation = new OperationsBySerieIdModelSwagger(operations);

        assertNotEquals(firstOperation.getListOperationsBySerieIdModelSwagger(),secondOperation.getListOperationsBySerieIdModelSwagger());

    }

}