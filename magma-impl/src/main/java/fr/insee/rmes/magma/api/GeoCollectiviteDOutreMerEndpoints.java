package fr.insee.rmes.magma.api;

import fr.insee.rmes.magma.api.*;
import fr.insee.rmes.magma.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.model.CollectiviteDOutreMer;
import fr.insee.rmes.magma.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.model.TypeEnumDescendantsCollectiviteDOutreMer;
import fr.insee.rmes.magma.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.magma.queries.parameters.TerritoireRequestParametizer;
import fr.insee.rmes.magma.utils.TerritoriesFilterUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoCollectiviteDOutreMerEndpoints implements GeoCollectiviteDOutreMerApi {

    private final RequestProcessor requestProcessor;
    private final TerritoriesFilterUtils territoriesFilterUtils;

    public GeoCollectiviteDOutreMerEndpoints(RequestProcessor requestProcessor, TerritoriesFilterUtils territoriesFilterUtils) {
        this.requestProcessor = requestProcessor;
        this.territoriesFilterUtils = territoriesFilterUtils;
    }

    @Override
    public ResponseEntity<CollectiviteDOutreMer> getcogcoll(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, CollectiviteDOutreMer.class, "none"))
                .executeQuery()
                .singleResult(CollectiviteDOutreMer.class)
                .toResponseEntity();
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogcolldes(String code, LocalDate date, TypeEnumDescendantsCollectiviteDOutreMer type, String filtreNom) {
        String territoriesFilter = this.territoriesFilterUtils.defineTerritoriesFilter(type);
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, filtreNom, territoriesFilter, CollectiviteDOutreMer.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }


    @Override
    public ResponseEntity<List<CollectiviteDOutreMer>> getcogcollliste(String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, CollectiviteDOutreMer.class, "none"))
                .executeQuery()
                .listResult(CollectiviteDOutreMer.class)
                .toResponseEntity();

    }


}