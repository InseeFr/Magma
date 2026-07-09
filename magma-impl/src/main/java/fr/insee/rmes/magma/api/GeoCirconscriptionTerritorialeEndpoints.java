package fr.insee.rmes.magma.api;

import fr.insee.rmes.magma.api.*;
import fr.insee.rmes.magma.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.model.CirconscriptionTerritoriale;
import fr.insee.rmes.magma.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.model.TypeEnumAscendantsCirconscriptionTerritoriale;
import fr.insee.rmes.magma.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.queries.parameters.TerritoireRequestParametizer;
import fr.insee.rmes.magma.utils.TerritoriesFilterUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoCirconscriptionTerritorialeEndpoints implements GeoCirconscriptionTerritorialeApi{

    private final RequestProcessor requestProcessor;
    private final TerritoriesFilterUtils territoriesFilterUtils;

    public GeoCirconscriptionTerritorialeEndpoints(RequestProcessor requestProcessor, TerritoriesFilterUtils territoriesFilterUtils) {
        this.requestProcessor = requestProcessor;
        this.territoriesFilterUtils = territoriesFilterUtils;
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogcirasc (String code, LocalDate date, TypeEnumAscendantsCirconscriptionTerritoriale type) {
        String territoriesFilter = this.territoriesFilterUtils.defineTerritoriesFilter(type);
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