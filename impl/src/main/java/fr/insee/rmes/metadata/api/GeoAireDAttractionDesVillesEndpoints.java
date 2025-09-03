package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.AireDAttractionDesVilles2020;
import fr.insee.rmes.metadata.model.Pays;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.model.TypeEnumDescendantsAireDAttractionDesVilles;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;


@Controller
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