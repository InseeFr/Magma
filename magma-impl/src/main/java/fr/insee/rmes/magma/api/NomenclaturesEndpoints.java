package fr.insee.rmes.magma.api;

import fr.insee.rmes.magma.api.*;
import fr.insee.rmes.magma.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.model.Nomenclature;
import fr.insee.rmes.magma.queries.parameters.ClassificationRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NomenclaturesEndpoints implements NomenclaturesApi {

    private final RequestProcessor requestProcessor;

    public NomenclaturesEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<Nomenclature> getClassificationByCode(String nomenclature, String niveau, String code){
        return requestProcessor.queryToFindClassification()
                .with(new ClassificationRequestParametizer(nomenclature, niveau, code))
                .executeQuery()
                .singleResult(Nomenclature.class)
                .toResponseEntity();
    }
}
