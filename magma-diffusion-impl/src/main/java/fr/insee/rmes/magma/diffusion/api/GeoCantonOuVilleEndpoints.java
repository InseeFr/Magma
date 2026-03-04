package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessorDiffusion;
import fr.insee.rmes.magma.diffusion.queries.parameters.*;
import fr.insee.rmes.magma.diffusion.model.CantonOuVille;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsCantonOuVille;
import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsCantonOuVille;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoCantonOuVilleEndpoints implements GeoCantonEtVilleApi {

    private final RequestProcessorDiffusion requestProcessorDiffusion;

    public GeoCantonOuVilleEndpoints(RequestProcessorDiffusion requestProcessorDiffusion) {
        this.requestProcessorDiffusion = requestProcessorDiffusion;
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogcanvilasc(String code, LocalDate date, TypeEnumAscendantsCantonOuVille type) {
        return requestProcessorDiffusion.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, CantonOuVille.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<CantonOuVille> getcogcanvil(String code, LocalDate date) {
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, CantonOuVille.class, "none"))
                .executeQuery()
                .singleResult(CantonOuVille.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogcanvildes(String code, LocalDate date, TypeEnumDescendantsCantonOuVille type, String filtreNom) {
        return requestProcessorDiffusion.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, filtreNom, CantonOuVille.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<CantonOuVille>> getcogcanvilliste(String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, CantonOuVille.class, "none"))
                .executeQuery()
                .listResult(CantonOuVille.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogcanvilprec(String code, LocalDate date) {
        return requestProcessorDiffusion.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, CantonOuVille.class, true))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogcanvilproj(String code, LocalDate dateProjection, LocalDate date) {
        //The Boolean previous is based on the dateProjection parameter (required parameter) and on the date parameter set to today's date if absent
        // (optional). Setting the date to today's date in ParameterValueDecoder is not retained outside the method
        // => must set the date here as well
        if (date == null) {
            date = LocalDate.now();
        }
        boolean previous = !dateProjection.isAfter(date);
        return requestProcessorDiffusion.queryforFindProjetes()
                .with(new ProjetesRequestParametizer(code, dateProjection, date, CantonOuVille.class, previous))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogcanvilsuiv(String code, LocalDate date) {
        return requestProcessorDiffusion.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, CantonOuVille.class, false))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }
}
