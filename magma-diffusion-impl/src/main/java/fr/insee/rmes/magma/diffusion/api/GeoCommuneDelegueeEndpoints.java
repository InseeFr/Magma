package fr.insee.rmes.magma.diffusion.api;


import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.CommuneDeleguee;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsCommuneDeleguee;
import fr.insee.rmes.magma.diffusion.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import fr.insee.rmes.magma.diffusion.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class GeoCommuneDelegueeEndpoints implements GeoCommuneDelegueeApi{

    private final RequestProcessor requestProcessor;
    private final EndpointsUtils endpointsUtils;

    public GeoCommuneDelegueeEndpoints(RequestProcessor requestProcessor, EndpointsUtils endpointsUtils) {
        this.requestProcessor = requestProcessor;
        this.endpointsUtils = endpointsUtils;
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogcomdasc (String code, LocalDate date, TypeEnumAscendantsCommuneDeleguee type) {
        String territoriesFilter = this.endpointsUtils.defineTerritoriesFilter(type == null ? null : type.getValue());
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, territoriesFilter, CommuneDeleguee.class, true))
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
