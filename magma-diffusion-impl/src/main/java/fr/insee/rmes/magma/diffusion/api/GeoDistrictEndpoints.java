package fr.insee.rmes.magma.diffusion.api;


import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.*;
import fr.insee.rmes.magma.diffusion.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class GeoDistrictEndpoints implements GeoDistrictApi {

    private final RequestProcessor requestProcessor;

    public GeoDistrictEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogdisasc (String code, LocalDate date, TypeEnumAscendantsDistrict type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, District.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<District> getcogdis(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, District.class, "none"))
                .executeQuery()
                .singleResult(District.class).toResponseEntity();
    }
}

