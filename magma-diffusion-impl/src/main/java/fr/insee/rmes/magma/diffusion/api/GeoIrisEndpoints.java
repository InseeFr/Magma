package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.Commune;
import fr.insee.rmes.magma.diffusion.model.Iris;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsIris;
import fr.insee.rmes.magma.diffusion.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import fr.insee.rmes.magma.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class GeoIrisEndpoints implements GeoIrisApi {

    private final RequestProcessor requestProcessor;

    public GeoIrisEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogirisasc (String code, LocalDate date, TypeEnumAscendantsIris type) {
        if (code.matches("^.{5}0000$")) {
            return requestProcessor.queryToFindAscendantsFauxIris()
                    .with(new AscendantsDescendantsRequestParametizer(code, date, type, Iris.class))
                    .executeQuery()
                    .listResult(TerritoireTousAttributs.class)
                    .toResponseEntity();
        }
        else {
            return requestProcessor.queryforFindAscendantsDescendants()
                    .with(new AscendantsDescendantsRequestParametizer(code, date, type, Iris.class))
                    .executeQuery()
                    .listResult(TerritoireTousAttributs.class)
                    .toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<Iris> getcogiris(String code, LocalDate date) {
        String codeCommune = code.substring(0, 5);
        boolean comHasIrisDescendant = requestProcessor.queryToFindIrisDescendantsCommune()
                .with(new TerritoireRequestParametizer(codeCommune, date))
                .executeAskQuery();

        if (comHasIrisDescendant){

            if (!code.endsWith("0000")) {
            Iris iris = requestProcessor.queryToFindIrisAndFauxIris()
                    .with(new TerritoireRequestParametizer(code, date))
                    .executeQuery()
                    .singleResult(Iris.class).result();
            return EndpointsUtils.toResponseEntity(iris);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        else { //it is a false-Iris
            if (!code.endsWith("0000")) {
                return ResponseEntity.notFound().build();
            }
            else {//return the COMMUNE
                Iris iris = requestProcessor.queryforFindTerritoire()
                        .with(new TerritoireRequestParametizer(codeCommune, date, Commune.class, "none"))
                        .executeQuery()
                        .singleResult(Iris.class).result();
                iris.setCode(code); //modify code property to have in output the Iris code and not the commune code
            return EndpointsUtils.toResponseEntity(iris);
            }
        }
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogirislist (LocalDate date, Boolean com) {
        boolean finalcom = (com != null) && com;
        return requestProcessor.queryToFindIrisList()
                .with(new TerritoireRequestParametizer(date, finalcom))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();

    }
}
