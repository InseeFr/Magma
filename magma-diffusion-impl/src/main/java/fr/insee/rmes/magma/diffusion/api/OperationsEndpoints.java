package fr.insee.rmes.magma.diffusion.api;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.RapportQualite;
import fr.insee.rmes.magma.diffusion.queries.parameters.OperationRequestParametizer;
import fr.insee.rmes.magma.diffusion.queries.parameters.OperationRubriquesRequestParametizer;
import fr.insee.rmes.magma.diffusion.services.RapportQualiteService;
import fr.insee.rmes.magma.diffusion.utils.EndpointsUtils;
import fr.insee.rmes.magma.diffusion.utils.RapportQualiteDTO;
import fr.insee.rmes.magma.diffusion.utils.RubriqueDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class OperationsEndpoints implements OperationsApi {

    private final RequestProcessor requestProcessor;
    private final RapportQualiteService rapportQualiteService;

    public OperationsEndpoints(RequestProcessor requestProcessor, RapportQualiteService rapportQualiteService) {
        this.requestProcessor = requestProcessor;
        this.rapportQualiteService = rapportQualiteService;
    }


    @Override
    public ResponseEntity<RapportQualite> getRapportQualiteByCode(String idSims) {
        RapportQualiteDTO rapportQualiteDTO = requestProcessor.queryToFindRapportQualite()
                .with(new OperationRequestParametizer(idSims))
                .executeQuery()
                .singleResult(RapportQualiteDTO.class)
                .result();

        if (rapportQualiteDTO.getUri() != null) {
            String LG1_CL = "http://id.insee.fr/codes/langue/fr";
            String LG2_CL = "http://id.insee.fr/codes/langue/en";

            List<RubriqueDTO> rubriqueList = requestProcessor.queryToFindRubriques()
                    .with(new OperationRubriquesRequestParametizer(rapportQualiteDTO.getId(), LG1_CL, LG2_CL))
                    .executeQuery()
                    .listResult(RubriqueDTO.class)
                    .result();
            rapportQualiteDTO.setRubriqueDTOList(rubriqueList);
        }


        RapportQualite rapportQualite = rapportQualiteService.transformDTOenRapportQualite(rapportQualiteDTO, requestProcessor);
//        RapportQualite rapportQualite = rapportQualiteDTO.transformDTOenRapportQualite(requestProcessor);

            return EndpointsUtils.toResponseEntity(rapportQualite);

        }

    }



