package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.Iris;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.queries.parameters.IrisRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Controller
public class GeoIrisEndpoints implements GeoIrisApi {

    private final RequestProcessor requestProcessor;

    public GeoIrisEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }


    @Override
    public ResponseEntity<Iris> getcogiris(String code, LocalDate date) {
        String codeCommune=code.substring(0, 5);
        var queryResult = requestProcessor.queryToFindIrisDescendantsCommune()
                .with(new IrisRequestParametizer(codeCommune, date))
                .executeAskQuery();



//bloc de fin pour que ça compile mais à revoir
        return requestProcessor.queryforFindIris()
                .with(new TerritoireRequestParametizer(code, date, Iris.class, "none"))
                .executeQuery()
                .singleResult(Iris.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogirislist (LocalDate date, Boolean com) {
        boolean finalcom = (com != null) && com;
        return requestProcessor.queryToFindIrisList()
                .with(new IrisRequestParametizer(date, finalcom))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();

    }
}
