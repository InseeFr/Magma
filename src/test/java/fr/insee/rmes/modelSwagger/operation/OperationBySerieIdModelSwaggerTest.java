package fr.insee.rmes.modelSwagger.operation;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OperationBySerieIdModelSwaggerTest {

    @Test
    void shouldCheckOperationBySerieIdModelSwagger(){

        List<AltLabel> altLabel = List.of(new AltLabel(),new AltLabel());
        List<Label> label = List.of(new Label(),new Label());

        OperationBySerieIdModelSwagger firstOperation = new OperationBySerieIdModelSwagger(
                altLabel,
                label,
                "mockedUri",
                new Serie(),
                "mockedId");

        OperationBySerieIdModelSwagger  secondOperation = new OperationBySerieIdModelSwagger();

        assertNotEquals(firstOperation,secondOperation);

    }

}