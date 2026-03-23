package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.CirconscriptionTerritoriale;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsCirconscriptionTerritoriale;
import fr.insee.rmes.magma.diffusion.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import fr.insee.rmes.magma.diffusion.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoCirconscriptionTerritorialeEndpoints implements GeoCirconscriptionTerritorialeApi{

    private final RequestProcessor requestProcessor;
    private final EndpointsUtils endpointsUtils;

    public GeoCirconscriptionTerritorialeEndpoints(RequestProcessor requestProcessor, EndpointsUtils endpointsUtils) {
        this.requestProcessor = requestProcessor;
        this.endpointsUtils = endpointsUtils;
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogcirasc (String code, LocalDate date, TypeEnumAscendantsCirconscriptionTerritoriale type) {
        String territoriesFilter = this.endpointsUtils.defineTerritoriesFilter(type == null ? null : type.getValue());
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, territoriesFilter, CirconscriptionTerritoriale.class, true))
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