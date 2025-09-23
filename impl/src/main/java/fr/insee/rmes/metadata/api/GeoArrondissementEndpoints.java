package fr.insee.rmes.metadata.api;


import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.Arrondissement;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.model.TypeEnumAscendantsArrondissement;
import fr.insee.rmes.metadata.model.TypeEnumDescendantsArrondissement;
import fr.insee.rmes.metadata.queries.parameters.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GeoArrondissementEndpoints implements GeoArrondissementApi {

    private final RequestProcessor requestProcessor;

    public GeoArrondissementEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogarrasc(String code, LocalDate date, TypeEnumAscendantsArrondissement type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, Arrondissement.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<Arrondissement> getcogarr(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, Arrondissement.class, "sousPrefecture"))
                .executeQuery()
                .singleResult(Arrondissement.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogarrdes(String code, LocalDate date, TypeEnumDescendantsArrondissement type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, Arrondissement.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<Arrondissement>> getcogarrliste(String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, Arrondissement.class, "sousPrefecture"))
                .executeQuery()
                .listResult(Arrondissement.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogarrprec(String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Arrondissement.class, true))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogarrproj(String code, LocalDate dateProjection, LocalDate date) {
        //le booléen previous est calculé en fonction du paramètre dateProjection (paramètre obligatoire) et du paramètre date valorisé à la date du jour si absent
        // (facultatif). La valorisation de date à la date du jour dans ParameterValueDecoder n'est pas conservée en dehors de la méthode
        // => obligé de valoriser date ici aussi
        if (date == null) {
            date = LocalDate.now();
        }
        boolean previous = !dateProjection.isAfter(date);
        return requestProcessor.queryforFindProjetes()
                .with(new ProjetesRequestParametizer(code, dateProjection, date, Arrondissement.class, previous))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogarrsuiv(String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Arrondissement.class, false))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

}

