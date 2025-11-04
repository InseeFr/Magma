package fr.insee.rmes.magma.diffusion.api.requestprocessor;

import fr.insee.rmes.magma.diffusion.api.GeoQuartierPrioritaireDeLaPolitiqueDeLaVilleApi;
import fr.insee.rmes.magma.diffusion.model.QuartierPrioritaireDeLaPolitiqueDeLaVille2024;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class GeoQuartierPrioritaireDeLaPolitiqueDeLaVilleEndpoints implements GeoQuartierPrioritaireDeLaPolitiqueDeLaVilleApi {

    private final RequestProcessor requestProcessor;

    public GeoQuartierPrioritaireDeLaPolitiqueDeLaVilleEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<QuartierPrioritaireDeLaPolitiqueDeLaVille2024> getcogqpv (String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, QuartierPrioritaireDeLaPolitiqueDeLaVille2024.class, "none"))
                .executeQuery()
                .singleResult(QuartierPrioritaireDeLaPolitiqueDeLaVille2024.class).toResponseEntity();
    }
}
