package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsZoneDEmploi;
import fr.insee.rmes.magma.diffusion.model.ZoneDEmploi2020;
import fr.insee.rmes.magma.diffusion.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;


@Controller
public class GeoZoneDEmploiEndpoints implements GeoZoneDEmploiApi{

    private final RequestProcessor requestProcessor;

    public GeoZoneDEmploiEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<ZoneDEmploi2020> getcogze(String code, LocalDate date) {
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, ZoneDEmploi2020.class, "none"))
                .executeQuery()
                .singleResult(ZoneDEmploi2020.class)
                .toResponseEntity();

    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogzedesc(String code, LocalDate date, TypeEnumDescendantsZoneDEmploi type) {
        return requestProcessor.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, ZoneDEmploi2020.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<ZoneDEmploi2020>> getcogzeliste (String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, ZoneDEmploi2020.class, "none"))
                .executeQuery()
                .listResult(ZoneDEmploi2020.class)
                .toResponseEntity();

    }



}
