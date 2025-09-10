package fr.insee.rmes.metadata.api;


import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.model.TypeEnumDescendantsUniteUrbaine;
import fr.insee.rmes.metadata.model.UniteUrbaine2020;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GeoUniteUrbaineEndpoints implements GeoUniteUrbaineApi {

    private final RequestProcessor requestProcessor;

    public GeoUniteUrbaineEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<UniteUrbaine2020> getcoguu(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, UniteUrbaine2020.class, "none"))
                .executeQuery()
                .singleResult(UniteUrbaine2020.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcoguudes (String code, LocalDate date, TypeEnumDescendantsUniteUrbaine type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, UniteUrbaine2020.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<UniteUrbaine2020>> getcoguuliste (String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, UniteUrbaine2020.class, "none"))
                .executeQuery()
                .listResult(UniteUrbaine2020.class)
                .toResponseEntity();
    }

}

