package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessorDiffusion;
import fr.insee.rmes.magma.diffusion.model.RapportQualite;
import fr.insee.rmes.magma.diffusion.queries.parameters.OperationRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.OperationRubriquesRequestParametizer;
import fr.insee.rmes.magma.diffusion.services.RapportQualiteService;
import fr.insee.rmes.magma.diffusion.utils.RapportQualiteDTO;
import fr.insee.rmes.magma.diffusion.utils.RubriqueDTO;
import fr.insee.rmes.magma.utils.EndpointsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class OperationsEndpoints implements OperationsApi {

    private final RequestProcessorDiffusion requestProcessorDiffusion;
    private final RapportQualiteService rapportQualiteService;

    public OperationsEndpoints(RequestProcessorDiffusion requestProcessorDiffusion, RapportQualiteService rapportQualiteService) {
        this.requestProcessorDiffusion = requestProcessorDiffusion;
        this.rapportQualiteService = rapportQualiteService;
    }

    @Value("${fr.insee.rmes.magma.uri}")
    private String magmaUri;
    @Value("${fr.insee.rmes.magma.lg1}")
    private String magmaLg1;
    @Value("${fr.insee.rmes.magma.lg2}")
    private String magmaLg2;

    private String getLg1Cl() {
        return "http://" + magmaUri + "/codes/langue/" + magmaLg1 ;
    }
    private String getLg2Cl() {
        return "http://" + magmaUri + "/codes/langue/" + magmaLg2;
    }
    @Override
    public ResponseEntity<RapportQualite> getRapportQualiteByCode(String idSims) {
        RapportQualiteDTO rapportQualiteDTO = requestProcessorDiffusion.queryToFindRapportQualite()
                .with(new OperationRequestParametizer(idSims))
                .executeQuery()
                .singleResult(RapportQualiteDTO.class)
                .result();

        if (rapportQualiteDTO == null){
            return ResponseEntity.notFound().build();
        }

        List<RubriqueDTO> rubriqueList = requestProcessorDiffusion.queryToFindRubriques()
                .with(new OperationRubriquesRequestParametizer(rapportQualiteDTO.id(), getLg1Cl(), getLg2Cl()))
                .executeQuery()
                .listResult(RubriqueDTO.class)
                .result();
        rapportQualiteDTO = rapportQualiteDTO.withRubriqueDTOList(rubriqueList);



        RapportQualite rapportQualite = rapportQualiteService.transformDTOenRapportQualite(rapportQualiteDTO);

        return EndpointsUtils.toResponseEntity(rapportQualite);

        }

    }



