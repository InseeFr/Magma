package fr.insee.rmes.magma.api;

import fr.insee.rmes.magma.api.*;
import fr.insee.rmes.magma.api.requestprocessor.RequestProcessorDiffusion;
import fr.insee.rmes.magma.model.BassinDeVie2022;
import fr.insee.rmes.magma.model.TerritoireTousAttributs;
import fr.insee.rmes.magma.model.TypeEnumDescendantsBassinDeVie;
import fr.insee.rmes.magma.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.magma.queries.parameters.TerritoireEtoileRequestParametizer;
import fr.insee.rmes.magma.queries.parameters.TerritoireRequestParametizer;
import fr.insee.rmes.magma.utils.TerritoriesFilterUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class GeoBassinDeVieEndpoints implements GeoBassinDeVieApi {

    private final RequestProcessorDiffusion requestProcessorDiffusion;
    private final TerritoriesFilterUtils territoriesFilterUtils;

    public GeoBassinDeVieEndpoints(RequestProcessorDiffusion requestProcessorDiffusion, TerritoriesFilterUtils territoriesFilterUtils) {
        this.requestProcessorDiffusion = requestProcessorDiffusion;
        this.territoriesFilterUtils = territoriesFilterUtils;
    }

    @Override
    public ResponseEntity<BassinDeVie2022> getcogbass(String code, LocalDate date) {
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireRequestParametizer(code, date, BassinDeVie2022.class, "none"))
                .executeQuery()
                .singleResult(BassinDeVie2022.class).toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>>  getcogbassdes (String code, LocalDate date, TypeEnumDescendantsBassinDeVie type) {
        String territoriesFilter = this.territoriesFilterUtils.defineTerritoriesFilter(type);
        return requestProcessorDiffusion.queryforFindAscendantsDescendants()
                .with(new AscendantsDescendantsRequestParametizer(code, date, territoriesFilter, BassinDeVie2022.class, false))
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
        return requestProcessorDiffusion.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, BassinDeVie2022.class, finalFiltreNom,"none", true))
                .executeQuery()
                .listResult(BassinDeVie2022.class)
                .toResponseEntity();

    }

}