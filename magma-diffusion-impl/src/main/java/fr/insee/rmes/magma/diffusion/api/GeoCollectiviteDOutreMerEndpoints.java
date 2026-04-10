package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessorDiffusion;
import fr.insee.rmes.magma.diffusion.model.CollectiviteDOutreMer;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsCollectiviteDOutreMer;
import fr.insee.rmes.magma.diffusion.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import fr.insee.rmes.magma.diffusion.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoCollectiviteDOutreMerEndpoints implements GeoCollectiviteDOutreMerApi {

    private final RequestProcessorDiffusion requestProcessorDiffusion;
    private final EndpointsUtils endpointsUtils;

    public GeoCollectiviteDOutreMerEndpoints(RequestProcessorDiffusion requestProcessorDiffusion, EndpointsUtils endpointsUtils) {
        this.requestProcessorDiffusion = requestProcessorDiffusion;
        this.endpointsUtils = endpointsUtils;
    }

    @Override
    public ResponseEntity<CollectiviteDOutreMer> getcogcoll(String code, LocalDate date) {
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, CollectiviteDOutreMer.class, "none"))
                .executeQuery()
                .singleResult(CollectiviteDOutreMer.class)
                .toResponseEntity();
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogcolldes(String code, LocalDate date, TypeEnumDescendantsCollectiviteDOutreMer type, String filtreNom) {
        String territoriesFilter = this.endpointsUtils.defineTerritoriesFilter(type);
        return requestProcessorDiffusion.queryforFindAscendantsDescendants()
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
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, CollectiviteDOutreMer.class, "none"))
                .executeQuery()
                .listResult(CollectiviteDOutreMer.class)
                .toResponseEntity();

    }


}