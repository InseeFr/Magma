package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.Intercommunalite;
import fr.insee.rmes.metadata.model.TerritoireTousAttributs;
import fr.insee.rmes.metadata.model.TypeEnumDescendantsIntercommunalite;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.PrecedentsSuivantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.ProjetesRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;


@Controller
public class GeoIntercommunaliteEndpoints implements GeoIntercommunaliteApi {

    private final RequestProcessor requestProcessor;

    public GeoIntercommunaliteEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<Intercommunalite> getcoginterco(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, Intercommunalite.class, "none"))
                .executeQuery()
                .singleResult(Intercommunalite.class).toResponseEntity();
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogintercodes(String code, LocalDate date, TypeEnumDescendantsIntercommunalite type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, Intercommunalite.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }


    @Override
    public ResponseEntity<List<Intercommunalite>> getcogintercoliste(LocalDate date, String filtreNom) {
        String finalFiltreNom = filtreNom == null ? "*" : filtreNom;

        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(date, Intercommunalite.class, finalFiltreNom, "none", true))
                .executeQuery()
                .listResult(Intercommunalite.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogintercoprec(String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Intercommunalite.class, true))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogintercoproj(String code, LocalDate dateProjection, LocalDate date) {
        //le booléen previous est calculé en fonction du paramètre dateProjection (paramètre obligatoire) et du paramètre date valorisé à la date du jour si absent
        // (facultatif). La valorisation de date à la date du jour dans ParameterValueDecoder n'est pas conservée en dehors de la méthode
        // => obligé de valoriser date ici aussi
        if (date == null) {
            date = LocalDate.now();
        }
        boolean previous = !dateProjection.isAfter(date);
        return requestProcessor.queryforFindProjetes()
                .with(new ProjetesRequestParametizer(code, dateProjection, date, Intercommunalite.class, previous))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogintercosuiv(String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Intercommunalite.class, false))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

}

