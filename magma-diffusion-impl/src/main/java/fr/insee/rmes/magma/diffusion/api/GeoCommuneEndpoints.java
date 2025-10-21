package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.queries.parameters.*;
import fr.insee.rmes.magma.diffusion.model.*;
import org.springframework.http.ResponseEntity;
import fr.insee.rmes.magma.diffusion.queries.parameters.AscendantsDescendantsRequestParametizer;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
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
    public ResponseEntity<List<Canton>> getcogcomcan (String code, LocalDate date) {
        return requestProcessor.queryToFindCantonsOfCommune()
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
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, Commune.class, finalFiltreNom, "none", finalcom))
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



