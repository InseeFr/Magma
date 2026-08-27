package fr.insee.rmes.magma.api;

import fr.insee.rmes.magma.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.model.RapportQualite;
import fr.insee.rmes.magma.queries.parameters.OperationRequestParametizer;
import fr.insee.rmes.magma.queries.parameters.OperationRubriquesRequestParametizer;
import fr.insee.rmes.magma.services.RapportQualiteService;
import fr.insee.rmes.magma.utils.RapportQualiteDTO;
import fr.insee.rmes.magma.utils.RubriqueDTO;
import fr.insee.rmes.magma.utils.EndpointsUtils;
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

        if (rapportQualiteDTO == null){
            return ResponseEntity.notFound().build();
        }

        String LG1_CL = "http://id.insee.fr/codes/langue/fr";
        String LG2_CL = "http://id.insee.fr/codes/langue/en";

        List<RubriqueDTO> rubriqueList = requestProcessor.queryToFindRubriques()
                .with(new OperationRubriquesRequestParametizer(rapportQualiteDTO.id(), LG1_CL, LG2_CL))
                .executeQuery()
                .listResult(RubriqueDTO.class)
                .result();
        rapportQualiteDTO = rapportQualiteDTO.withRubriqueDTOList(rubriqueList);



        RapportQualite rapportQualite = rapportQualiteService.transformDTOintoRapportQualite(rapportQualiteDTO);

        return EndpointsUtils.toResponseEntity(rapportQualite);

        }

    }



