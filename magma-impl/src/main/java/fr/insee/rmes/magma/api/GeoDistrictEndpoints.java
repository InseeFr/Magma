package fr.insee.rmes.magma.api;

import fr.insee.rmes.magma.api.*;

import fr.insee.rmes.magma.api.requestprocessor.RequestProcessorDiffusion;
import fr.insee.rmes.magma.model.*;
import fr.insee.rmes.magma.model.District;
import fr.insee.rmes.magma.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.model.TypeEnumAscendantsDistrict;
import fr.insee.rmes.magma.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.queries.parameters.TerritoireRequestParametizer;
import fr.insee.rmes.magma.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class GeoDistrictEndpoints implements GeoDistrictApi {

    private final RequestProcessorDiffusion requestProcessorDiffusion;
    private final EndpointsUtils endpointsUtils;

     public GeoDistrictEndpoints(RequestProcessorDiffusion requestProcessorDiffusion, EndpointsUtils endpointsUtils) {
        this.requestProcessorDiffusion = requestProcessorDiffusion;
         this.endpointsUtils = endpointsUtils;
     }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogdisasc (String code, LocalDate date, TypeEnumAscendantsDistrict type) {
        String territoriesFilter = this.endpointsUtils.defineTerritoriesFilter(type);
        return requestProcessorDiffusion.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, territoriesFilter, District.class, true))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<District> getcogdis(String code, LocalDate date) {
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, District.class, "none"))
                .executeQuery()
                .singleResult(District.class).toResponseEntity();
    }
}

