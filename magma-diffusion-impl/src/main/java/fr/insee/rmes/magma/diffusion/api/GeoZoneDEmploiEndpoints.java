package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessorDiffusion;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsZoneDEmploi;
import fr.insee.rmes.magma.diffusion.model.ZoneDEmploi2020;
import fr.insee.rmes.magma.diffusion.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoZoneDEmploiEndpoints implements GeoZoneDEmploiApi{

    private final RequestProcessorDiffusion requestProcessorDiffusion;

    public GeoZoneDEmploiEndpoints(RequestProcessorDiffusion requestProcessorDiffusion) {
        this.requestProcessorDiffusion = requestProcessorDiffusion;
    }

    @Override
    public ResponseEntity<ZoneDEmploi2020> getcogze(String code, LocalDate date) {
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, ZoneDEmploi2020.class, "none"))
                .executeQuery()
                .singleResult(ZoneDEmploi2020.class)
                .toResponseEntity();

    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogzedesc(String code, LocalDate date, TypeEnumDescendantsZoneDEmploi type) {
        return requestProcessorDiffusion.queryforFindAscendantsDescendants()
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
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, ZoneDEmploi2020.class, "none"))
                .executeQuery()
                .listResult(ZoneDEmploi2020.class)
                .toResponseEntity();

    }



}
