package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OperationListModelSwaggerTest {

    @Test
    void shouldCheckConstructorInitialization(){

        OperationListModelSwagger firstOperationListModelSwagger = new OperationListModelSwagger();

        List<OperationListModelSwagger> OperationListModelSwaggerS = List.of(
                new OperationListModelSwagger(),
                new OperationListModelSwagger(),
                new OperationListModelSwagger()
        );

        OperationListModelSwagger secondOperationListModelSwagger = new OperationListModelSwagger(OperationListModelSwaggerS);

        assertTrue(!firstOperationListModelSwagger.equals(secondOperationListModelSwagger) && OperationListModelSwaggerS==secondOperationListModelSwagger.getOperationListModelSwaggerS());

    }

}