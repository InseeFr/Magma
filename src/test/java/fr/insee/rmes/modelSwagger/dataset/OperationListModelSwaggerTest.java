package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OperationListModelSwaggerTest {

    @Test
    void shouldCheckConstructorInitialization(){

        OperationListModelSwagger firstOperationListModelSwagger = new OperationListModelSwagger();

        List<OperationListModelSwagger> operations = List.of(
                new OperationListModelSwagger(),
                new OperationListModelSwagger(),
                new OperationListModelSwagger()
        );

        OperationListModelSwagger secondOperationListModelSwagger = new OperationListModelSwagger(operations);

        assertTrue(!firstOperationListModelSwagger.equals(secondOperationListModelSwagger) && operations==secondOperationListModelSwagger.getOperationListModelSwaggerS());

    }

}