package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.*;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.PrecedentsSuivantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.ProjetesRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GeoCommuneEndpoints implements GeoCommuneApi {

    private final RequestProcessor requestProcessor;

    public GeoCommuneEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }


    @Override
    public ResponseEntity<Commune> getcogcom(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, Commune.class, "none"))
                .executeQuery()
                .singleResult(Commune.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBase>> getcogcomliste(LocalDate date, String filtreNom, Boolean com) {
        String finalFiltreNom = filtreNom == null ? "*" : filtreNom;
        boolean finalcom = (com != null) && com;
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(date, Commune.class, finalFiltreNom, "none", finalcom))
                .executeQuery()
                .listResult(TerritoireBase.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogcomdesc( String code, LocalDate date, TypeEnumDescendantsCommune type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, Commune.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogcomasc( String code, LocalDate date, TypeEnumAscendantsCommune type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, Commune.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBase>> getcogcomprec( String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Commune.class, true))
                .executeQuery()
                .listResult(TerritoireBase.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBase>> getcogcomproj( String code, LocalDate dateProjection, LocalDate date) {
        //le booléen previous est calculé en fonction du paramètre dateProjection (paramètre obligatoire) et du paramètre date valorisé à la date du jour si absent
        // (facultatif). La valorisation de date à la date du jour dans ParameterValueDecoder n'est pas conservée en dehors de la méthode
        // => obligé de valoriser date ici aussi
        if (date == null) {
            date = LocalDate.now();
        }
        boolean previous = !dateProjection.isAfter(date);
        return requestProcessor.queryforFindProjetes()
                .with(new ProjetesRequestParametizer(code, dateProjection, date, Commune.class, previous))
                .executeQuery()
                .listResult(TerritoireBase.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireBase>>  getcogcomsuiv(String code, LocalDate date) {
        return requestProcessor.queryforFindPrecedentsSuivants()
                .with(new PrecedentsSuivantsRequestParametizer(code, date, Commune.class, false))
                .executeQuery()
                .listResult(TerritoireBase.class)
                .toResponseEntity();
    }
}



