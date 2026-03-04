package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessorDiffusion;
import fr.insee.rmes.magma.diffusion.model.*;
import fr.insee.rmes.magma.diffusion.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoCommuneAssocieeEndpoints implements GeoCommuneAssocieeApi{

    private final RequestProcessorDiffusion requestProcessorDiffusion;

    public GeoCommuneAssocieeEndpoints(RequestProcessorDiffusion requestProcessorDiffusion) {
        this.requestProcessorDiffusion = requestProcessorDiffusion;
    }


    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogcomaasc (String code, LocalDate date, TypeEnumAscendantsCommuneAssociee type) {
        return requestProcessorDiffusion.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, type, CommuneAssociee.class))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<CommuneAssociee> getcogcoma(String code, LocalDate date) {
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, CommuneAssociee.class, "none"))
                .executeQuery()
                .singleResult(CommuneAssociee.class)
                .toResponseEntity();

    }

    @Override
    public ResponseEntity<List<CommuneAssociee>> getcogcomaliste (String date) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, CommuneAssociee.class, "none"))
                .executeQuery()
                .listResult(CommuneAssociee.class)
                .toResponseEntity();

    }



}