package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.PrecedentsSuivantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.ProjetesRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.*;
import fr.insee.rmes.metadata.queries.parameters.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;


@Controller
public class GeoDepartementEndpoints implements GeoDepartementApi {

    private final RequestProcessor requestProcessor;

    public GeoDepartementEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogdepdesc(String code, LocalDate date, TypeEnumDescendantsDepartement type, String filtreNom) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, filtreNom, Departement.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogdepasc(String code, LocalDate date, TypeEnumAscendantsDepartement type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, Departement.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBaseChefLieu>>  getcogdepprec(String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Departement.class, true))
                .executeQuery()
                .listResult(TerritoireBaseChefLieu.class)
                .toResponseEntity();
    }



    @Override
    public ResponseEntity<List<TerritoireBaseChefLieu>>  getcogdepproj(String code, LocalDate dateProjection, LocalDate date) {
        //le booléen previous est calculé en fonction du paramètre dateProjection (paramètre obligatoire) et du paramètre date valorisé à la date du jour si absent
        // (facultatif). La valorisation de date à la date du jour dans ParameterValueDecoder n'est pas conservée en dehors de la méthode
        // => obligé de valoriser date ici aussi
        if (date == null) {
            date = LocalDate.now();
        }
        boolean previous = !dateProjection.isAfter(date);
        return requestProcessor.queryforFindProjetes()
                .with(new ProjetesRequestParametizer(code, dateProjection, date, Departement.class, previous))
                .executeQuery()
                .listResult(TerritoireBaseChefLieu.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBaseChefLieu>>  getcogdepsuiv(String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Departement.class, false))
                .executeQuery()
                .listResult(TerritoireBaseChefLieu.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<Departement> getcogdep(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, Departement.class,"prefecture"))
                .executeQuery()
                .singleResult(Departement.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBaseChefLieu>> getcogdepts(String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, Departement.class, "prefecture", true))
                .executeQuery()
                .listResult(TerritoireBaseChefLieu.class)
                .toResponseEntity();

    }
 
}
