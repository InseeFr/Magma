package fr.insee.rmes.magma.diffusion.api.requestprocessor;

import fr.insee.rmes.magma.diffusion.api.GeoQuartierPrioritaireDeLaPolitiqueDeLaVilleApi;
import fr.insee.rmes.magma.diffusion.model.QuartierPrioritaireDeLaPolitiqueDeLaVille2024;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public ResponseEntity<List<QuartierPrioritaireDeLaPolitiqueDeLaVille2024>> getcogqpvliste (String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, QuartierPrioritaireDeLaPolitiqueDeLaVille2024.class, "none"))
                .executeQuery()
                .listResult(QuartierPrioritaireDeLaPolitiqueDeLaVille2024.class)
                .toResponseEntity();
    }


}
