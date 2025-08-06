package fr.insee.rmes.modelSwagger.operation;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OperationByIdModelSwaggerTest {

    @Test
    void shouldInitializeConstructor(){

        Serie serie = new Serie();
        String operationId = "mockedOperationId";
        List<Label> labelOperation = List.of(new Label(),new Label());
        String uri = "mockedUri";
        String proprietaire = "proprietaire";

        OperationByIdModelSwagger firstOperation = new OperationByIdModelSwagger();
        OperationByIdModelSwagger secondOperation = new OperationByIdModelSwagger(
                serie,
                operationId,
                labelOperation,
                uri);

        firstOperation.setProprietaire(proprietaire);

        assertNotEquals(firstOperation,secondOperation);

    }

}