package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessorDiffusion;
import fr.insee.rmes.magma.diffusion.model.Nomenclature;
import fr.insee.rmes.magma.diffusion.queries.parameters.ClassificationRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NomenclaturesEndpoints implements NomenclaturesApi {

    private final RequestProcessorDiffusion requestProcessorDiffusion;

    public NomenclaturesEndpoints(RequestProcessorDiffusion requestProcessorDiffusion) {
        this.requestProcessorDiffusion = requestProcessorDiffusion;
    }

    @Override
    public ResponseEntity<Nomenclature> getClassificationByCode(String nomenclature, String niveau, String code){
        return requestProcessorDiffusion.queryToFindClassification()
                .with(new ClassificationRequestParametizer(nomenclature, niveau, code))
                .executeQuery()
                .singleResult(Nomenclature.class)
                .toResponseEntity();
    }
}
