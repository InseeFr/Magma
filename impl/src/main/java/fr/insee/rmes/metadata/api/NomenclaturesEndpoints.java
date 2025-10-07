package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.Nomenclature;
import fr.insee.rmes.metadata.queries.parameters.ClassificationRequestParametizer;
import org.springframework.http.ResponseEntity;

public class NomenclaturesEndpoints implements NomenclaturesApi {

    private final RequestProcessor requestProcessor;

    public NomenclaturesEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<Nomenclature> getClassificationByCode(String classification, String level, String code){
        return requestProcessor.queryToFindClassification()
                .with(new ClassificationRequestParametizer(classification, level, code))
                .executeQuery()
                .singleResult(Nomenclature.class)
                .toResponseEntity();
    }
}
