package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.BassinDeVie2022;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsBassinDeVie;
import fr.insee.rmes.magma.diffusion.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoBassinDeVieEndpoints implements GeoBassinDeVieApi {

    private final RequestProcessor requestProcessor;

    public GeoBassinDeVieEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<BassinDeVie2022> getcogbass(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, BassinDeVie2022.class, "none"))
                .executeQuery()
                .singleResult(BassinDeVie2022.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogbassdes (String code, LocalDate date, TypeEnumDescendantsBassinDeVie type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, BassinDeVie2022.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<BassinDeVie2022>> getcogbassliste (String date, String filtreNom) {
        String finalFiltreNom = filtreNom == null ? "*" : filtreNom;
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, BassinDeVie2022.class, finalFiltreNom,"none", true))
                .executeQuery()
                .listResult(BassinDeVie2022.class)
                .toResponseEntity();

    }

}