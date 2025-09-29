package fr.insee.rmes.metadata.api;


import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.*;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GeoCommuneDelegueeEndpoints implements GeoCommuneDelegueeApi{

    private final RequestProcessor requestProcessor;

    public GeoCommuneDelegueeEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogcomdasc (String code, LocalDate date, TypeEnumAscendantsCommuneDeleguee type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, CommuneDeleguee.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<CommuneDeleguee> getcogcomd (String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, CommuneDeleguee.class, "none"))
                .executeQuery()
                .singleResult(CommuneDeleguee.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<CommuneDeleguee>> getcogcomdliste (String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, CommuneDeleguee.class, "none"))
                .executeQuery()
                .listResult(CommuneDeleguee.class)
                .toResponseEntity();

    }


}
