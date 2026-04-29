package fr.insee.rmes.magma.gestion.api;

import fr.insee.rmes.magma.gestion.api.requestprocessor.RequestProcessorGestion;
import fr.insee.rmes.magma.gestion.model.OperationById;
import fr.insee.rmes.magma.gestion.model.SerieById;
import fr.insee.rmes.magma.gestion.queries.parameters.SeriesOperationsRequestParametizer;
import fr.insee.rmes.magma.gestion.services.SeriesOperationsService;
import fr.insee.rmes.magma.gestion.utils.OperationDTO;
import fr.insee.rmes.magma.gestion.utils.SeriesDTO;
import fr.insee.rmes.magma.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeriesOperationsEndpoints implements SeriesOperationsApi {

    private final RequestProcessorGestion requestProcessor;
    private final SeriesOperationsService seriesOperationsService;

    public SeriesOperationsEndpoints(RequestProcessorGestion requestProcessor, SeriesOperationsService seriesOperationsService) {
        this.requestProcessor = requestProcessor;
        this.seriesOperationsService = seriesOperationsService;
    }

    @Override
    public ResponseEntity<SerieById> getSerieById(String id) {
        SeriesDTO seriesDTO = requestProcessor.queryToFindSerieById()
                .with(new SeriesOperationsRequestParametizer(id, null))
                .executeQuery()
                .singleResult(SeriesDTO.class)
                .result();
        if (seriesDTO == null) {
            return ResponseEntity.notFound().build();
        }
        SerieById serieById = seriesOperationsService.transformSeriesDTOToSerieById(seriesDTO);
        return EndpointsUtils.toResponseEntity(serieById);
    }

    @Override
    public ResponseEntity<OperationById> getOperationByCode(String id) {
        OperationDTO operationDTO = requestProcessor.queryToFindOperationByCode()
                .with(new SeriesOperationsRequestParametizer(null, id))
                .executeQuery()
                .singleResult(OperationDTO.class)
                .result();
        if (operationDTO == null) {
            return ResponseEntity.notFound().build();
        }
        OperationById operationById = seriesOperationsService.transformOperationDTOToOperationById(operationDTO);
        return EndpointsUtils.toResponseEntity(operationById);
    }
}