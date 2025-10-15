package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.*;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoCirconscriptionTerritorialeEndpoints implements GeoCirconscriptionTerritorialeApi{

    private final RequestProcessor requestProcessor;

    public GeoCirconscriptionTerritorialeEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogcirasc (String code, LocalDate date, TypeEnumAscendantsCirconscriptionTerritoriale type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, CirconscriptionTerritoriale.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<CirconscriptionTerritoriale> getcogcir(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, CirconscriptionTerritoriale.class, "none"))
                .executeQuery()
                .singleResult(CirconscriptionTerritoriale.class)
                .toResponseEntity();

    }


}