package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.ArrondissementMunicipal;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.model.TypeEnumAscendantsArrondissementMunicipal;
import fr.insee.rmes.metadata.queries.parameters.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoArrondissementMunipalEndpoints implements GeoArrondissementMunicipalApi {

    private final RequestProcessor requestProcessor;

    public GeoArrondissementMunipalEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogarrmuasc(String code, LocalDate date, TypeEnumAscendantsArrondissementMunicipal type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, ArrondissementMunicipal.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<ArrondissementMunicipal> getcogarrmu(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, ArrondissementMunicipal.class, "none"))
                .executeQuery()
                .singleResult(ArrondissementMunicipal.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<ArrondissementMunicipal>> getcogarrmuliste(String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, ArrondissementMunicipal.class, "none"))
                .executeQuery()
                .listResult(ArrondissementMunicipal.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogarrmuprec(String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, ArrondissementMunicipal.class, true))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogarrmuproj(String code, LocalDate dateProjection, LocalDate date) {
        //le booléen previous est calculé en fonction du paramètre dateProjection (paramètre obligatoire) et du paramètre date valorisé à la date du jour si absent
        // (facultatif). La valorisation de date à la date du jour dans ParameterValueDecoder n'est pas conservée en dehors de la méthode
        // => obligé de valoriser date ici aussi
        if (date == null) {
            date = LocalDate.now();
        }
        boolean previous = !dateProjection.isAfter(date);
        return requestProcessor.queryforFindProjetes()
                .with(new ProjetesRequestParametizer(code, dateProjection, date, ArrondissementMunicipal.class, previous))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogarrmusuiv(String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, ArrondissementMunicipal.class, false))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

}

