package fr.insee.rmes.magma.api;

import fr.insee.rmes.magma.api.*;
import fr.insee.rmes.magma.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.model.TypeEnumDescendantsZoneDEmploi;
import fr.insee.rmes.magma.model.ZoneDEmploi2020;
import fr.insee.rmes.magma.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.magma.queries.parameters.TerritoireRequestParametizer;
import fr.insee.rmes.magma.utils.TerritoriesFilterUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoZoneDEmploiEndpoints implements GeoZoneDEmploiApi{

    private final RequestProcessor requestProcessor;
    private final TerritoriesFilterUtils territoriesFilterUtils;

    public GeoZoneDEmploiEndpoints(RequestProcessor requestProcessor, TerritoriesFilterUtils territoriesFilterUtils) {
        this.requestProcessor = requestProcessor;
        this.territoriesFilterUtils = territoriesFilterUtils;
    }

    @Override
    public ResponseEntity<ZoneDEmploi2020> getcogze(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, ZoneDEmploi2020.class, "none"))
                .executeQuery()
                .singleResult(ZoneDEmploi2020.class)
                .toResponseEntity();

    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogzedesc(String code, LocalDate date, TypeEnumDescendantsZoneDEmploi type) {
        String territoriesFilter = this.territoriesFilterUtils.defineTerritoriesFilter(type);
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, territoriesFilter, ZoneDEmploi2020.class, false))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<ZoneDEmploi2020>> getcogzeliste (String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, ZoneDEmploi2020.class, "none"))
                .executeQuery()
                .listResult(ZoneDEmploi2020.class)
                .toResponseEntity();

    }



}
