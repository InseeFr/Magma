package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessorDiffusion;
import fr.insee.rmes.magma.diffusion.model.Commune;
import fr.insee.rmes.magma.diffusion.model.Iris;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsIris;
import fr.insee.rmes.magma.diffusion.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import fr.insee.rmes.magma.diffusion.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static fr.insee.rmes.magma.utils.EndpointsUtils.toResponseEntity;

@RestController
public class GeoIrisEndpoints implements GeoIrisApi {


    private final RequestProcessorDiffusion requestProcessorDiffusion;
    private final EndpointsUtils endpointsUtils;

    public GeoIrisEndpoints(RequestProcessorDiffusion requestProcessorDiffusion, EndpointsUtils endpointsUtils) {
        this.requestProcessorDiffusion = requestProcessorDiffusion;
        this.endpointsUtils = endpointsUtils;
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogirisasc (String code, LocalDate date, TypeEnumAscendantsIris type) {
        String territoriesFilter = this.endpointsUtils.defineTerritoriesFilter(type);
        if (code.matches("^.{5}0000$")) {
            return requestProcessorDiffusion.queryToFindAscendantsFauxIris()
                    .with(new AscendantsDescendantsRequestParametizer(code, date, territoriesFilter, Iris.class, true))
                    .executeQuery()
                    .listResult(TerritoireTousAttributs.class)
                    .toResponseEntity();
        }
        else {
            return requestProcessorDiffusion.queryforFindAscendantsDescendants()
                    .with(new AscendantsDescendantsRequestParametizer(code, date, territoriesFilter, Iris.class, true))
                    .executeQuery()
                    .listResult(TerritoireTousAttributs.class)
                    .toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<Iris> getcogiris(String code, LocalDate date) {
        String codeCommune = code.substring(0, 5);
        boolean comHasIrisDescendant = requestProcessorDiffusion.queryToFindIrisDescendantsCommune()
                .with(new TerritoireRequestParametizer(codeCommune, date))
                .executeAskQuery();

        if (comHasIrisDescendant){

            if (!code.endsWith("0000")) {
            Iris iris = requestProcessorDiffusion.queryToFindIrisAndFauxIris()
                    .with(new TerritoireRequestParametizer(code, date))
                    .executeQuery()
                    .singleResult(Iris.class).result();
            return toResponseEntity(iris);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        else { //it is a false-Iris
            if (!code.endsWith("0000")) {
                return ResponseEntity.notFound().build();
            }
            else {//return the COMMUNE
                Iris iris = requestProcessorDiffusion.queryforFindTerritoire()
                        .with(new TerritoireRequestParametizer(codeCommune, date, Commune.class, "none"))
                        .executeQuery()
                        .singleResult(Iris.class).result();
                iris.setCode(code); //modify code property to have in output the Iris code and not the commune code
            return toResponseEntity(iris);
            }
        }
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogirislist (LocalDate date, Boolean com) {
        boolean finalcom = (com != null) && com;
        return requestProcessorDiffusion.queryToFindIrisList()
                .with(new TerritoireRequestParametizer(date, finalcom))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();

    }
}
