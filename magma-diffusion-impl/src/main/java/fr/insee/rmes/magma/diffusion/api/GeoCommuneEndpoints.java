package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessorDiffusion;
import fr.insee.rmes.magma.diffusion.queries.parameters.*;
import fr.insee.rmes.magma.diffusion.model.*;
import fr.insee.rmes.magma.diffusion.queries.parameters.*;
import fr.insee.rmes.magma.diffusion.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class GeoCommuneEndpoints implements GeoCommuneApi {

    private final RequestProcessorDiffusion requestProcessorDiffusion;
    private final EndpointsUtils endpointsUtils;

    public GeoCommuneEndpoints(RequestProcessorDiffusion requestProcessorDiffusion, EndpointsUtils endpointsUtils) {
        this.requestProcessorDiffusion = requestProcessorDiffusion;
        this.endpointsUtils = endpointsUtils;
    }


    @Override
    public ResponseEntity<Commune> getcogcom(String code, LocalDate date) {
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, Commune.class, "none"))
                .executeQuery()
                .singleResult(Commune.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<Canton>> getcogcomcan (String code, LocalDate date) {
        return requestProcessorDiffusion.queryToFindCantonsOfCommune()
                .with(new TerritoireRequestParametizer(code, date, Commune.class, "none"))
                .executeQuery()
                .listResult(Canton.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBase>> getcogcomliste(String date, String filtreNom, Boolean com) {
        String finalFiltreNom = filtreNom == null ? "*" : filtreNom;
        boolean finalcom = (com != null) && com;
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, Commune.class, finalFiltreNom, "none", finalcom))
                .executeQuery()
                .listResult(TerritoireBase.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogcomdesc( String code, LocalDate date, TypeEnumDescendantsCommune type) {
        String territoriesFilter = this.endpointsUtils.defineTerritoriesFilter(type);
        return requestProcessorDiffusion.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, territoriesFilter, Commune.class, false))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogcomasc( String code, LocalDate date, TypeEnumAscendantsCommune type) {
        String territoriesFilter = this.endpointsUtils.defineTerritoriesFilter(type);
        return requestProcessorDiffusion.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, territoriesFilter, Commune.class, true))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBase>> getcogcomprec( String code, LocalDate date) {
        return requestProcessorDiffusion.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Commune.class, true))
                .executeQuery()
                .listResult(TerritoireBase.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBase>> getcogcomproj( String code, LocalDate dateProjection, LocalDate date) {
        //The Boolean previous is based on the dateProjection parameter (required parameter) and on the date parameter set to today's date if absent
        // (optional). Setting the date to today's date in ParameterValueDecoder is not retained outside the method
        // => must set the date here as well//
        if (date == null) {
            date = LocalDate.now();
        }
        boolean previous = !dateProjection.isAfter(date);
        return requestProcessorDiffusion.queryforFindProjetes()
                .with(new ProjetesRequestParametizer(code, dateProjection, date, Commune.class, previous))
                .executeQuery()
                .listResult(TerritoireBase.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBase>>  getcogcomsuiv(String code, LocalDate date) {
        return requestProcessorDiffusion.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Commune.class, false))
                .executeQuery()
                .listResult(TerritoireBase.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBaseRelation>>  getcogcomintersect (String code, LocalDate date, TypeEnum type) {
        String territoriesFilter = this.endpointsUtils.defineTerritoriesFilter(type);
        return requestProcessorDiffusion.queryToFindIntersections()
                .with(new TerritoiresLiesRequestParametizer(code, date, territoriesFilter, Commune.class))
                .executeQuery()
                .listResult(TerritoireBaseRelation.class)
                .toResponseEntity();
    }
}



