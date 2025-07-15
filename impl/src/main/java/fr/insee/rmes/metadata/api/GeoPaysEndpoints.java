package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.Pays;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.model.TypeEnumDescendantsPays;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.PrecedentsSuivantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;


@Controller
public class GeoPaysEndpoints implements GeoPaysApi {

    private final RequestProcessor requestProcessor;

    public GeoPaysEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<Pays> getcogpays(String code, LocalDate date) {
        return requestProcessor.queryforFindPays()
                .with(new TerritoireRequestParametizer(code, date, Pays.class, "none"))
                .executeQuery()
                .singleResult(Pays.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogpaysdesc(String code, LocalDate date, TypeEnumDescendantsPays type) {
        return requestProcessor.queryforFindDescendantsPays()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, Pays.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }


    @Override
    public ResponseEntity<List<Pays>> getcogpayslist(LocalDate date) {
        return requestProcessor.queryforFindPays()
                .with(new TerritoireRequestParametizer(date, Pays.class, "none"))
                .executeQuery()
                .listResult(Pays.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<Pays>> getcogpaysprec(String code, LocalDate date) {
        return requestProcessor.queryforFindPaysPrecedents()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Pays.class, true))
                .executeQuery()
                .listResult(Pays.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<Pays>> getcogpayssuiv(String code, LocalDate date) {
        return requestProcessor.queryforFindPaysSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Pays.class, false))
                .executeQuery()
                .listResult(Pays.class)
                .toResponseEntity();
    }
}