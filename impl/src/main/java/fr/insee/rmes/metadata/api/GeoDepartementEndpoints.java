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
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogdepprec(String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Departement.class, true))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }



    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogdepproj(String code, LocalDate dateProjection, LocalDate date) {
        //The Boolean previous is based on the dateProjection parameter (required parameter) and on the date parameter set to today's date if absent
        // (optional). Setting the date to today's date in ParameterValueDecoder is not retained outside the method
        // => must set the date here as well
        if (date == null) {
            date = LocalDate.now();
        }
        boolean previous = !dateProjection.isAfter(date);
        return requestProcessor.queryforFindProjetes()
                .with(new ProjetesRequestParametizer(code, dateProjection, date, Departement.class, previous))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogdepsuiv(String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Departement.class, false))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
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
    public ResponseEntity<List<TerritoireTousAttributs>> getcogdepts(String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, Departement.class, "prefecture", true))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();

    }
 
}
