package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.AireDAttractionDesVilles2020;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsAireDAttractionDesVilles;
import fr.insee.rmes.magma.diffusion.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoAireDAttractionDesVillesEndpoints implements GeoAireDAttractionDesVillesApi {

    private final RequestProcessor requestProcessor;

    public GeoAireDAttractionDesVillesEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<AireDAttractionDesVilles2020> getcogaav (String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, AireDAttractionDesVilles2020.class, "none"))
                .executeQuery()
                .singleResult(AireDAttractionDesVilles2020.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogaavdesc (String code, LocalDate date, TypeEnumDescendantsAireDAttractionDesVilles type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, AireDAttractionDesVilles2020.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<AireDAttractionDesVilles2020>> getcogaavliste (String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, AireDAttractionDesVilles2020.class, "none"))
                .executeQuery()
                .listResult(AireDAttractionDesVilles2020.class)
                .toResponseEntity();

    }

}