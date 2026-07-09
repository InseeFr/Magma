package fr.insee.rmes.magma.api;

import fr.insee.rmes.magma.api.SeriesOperationsApi;
import fr.insee.rmes.magma.api.requestprocessor.RequestProcessorGestion;
import fr.insee.rmes.magma.model.Indicateur;
import fr.insee.rmes.magma.model.Operation;
import fr.insee.rmes.magma.model.Serie;
import fr.insee.rmes.magma.queries.parameters.IndicateurRequestParametizer;
import fr.insee.rmes.magma.queries.parameters.SeriesOperationsRequestParametizer;
import fr.insee.rmes.magma.services.SeriesOperationsService;
import fr.insee.rmes.magma.utils.IndicateurDTO;
import fr.insee.rmes.magma.utils.OperationDTO;
import fr.insee.rmes.magma.utils.SeriesDTO;
import fr.insee.rmes.magma.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeriesOperationsEndpoints implements SeriesOperationsApi {

    private final RequestProcessorGestion requestProcessor;
    private final SeriesOperationsService seriesOperationsService;

    public SeriesOperationsEndpoints(RequestProcessorGestion requestProcessor, SeriesOperationsService seriesOperationsService) {
        this.requestProcessor = requestProcessor;
        this.seriesOperationsService = seriesOperationsService;
    }

    @Override
    public ResponseEntity<List<Serie>> getAllSeries(String dateMiseAJour) {
        String date = dateMiseAJour != null ? dateMiseAJour : "none";
        List<SeriesDTO> dtos = requestProcessor.queryToFindAllSeries()
                .with(new SeriesOperationsRequestParametizer(null, null, date))
                .executeQuery()
                .listResult(SeriesDTO.class)
                .result();
        List<Serie> series = seriesOperationsService.transformSeriesDTOsToSeries(dtos);
        return ResponseEntity.ok(series);
    }

    @Override
    public ResponseEntity<Serie> getSerieById(String id) {
        SeriesDTO seriesDTO = requestProcessor.queryToFindSerieById()
                .with(new SeriesOperationsRequestParametizer(id, null))
                .executeQuery()
                .singleResult(SeriesDTO.class)
                .result();
        if (seriesDTO == null) {
            return ResponseEntity.notFound().build();
        }
        Serie serieById = seriesOperationsService.transformSeriesDTOToSerieById(seriesDTO);
        return EndpointsUtils.toResponseEntity(serieById);
    }

    @Override
    public ResponseEntity<Operation> getOperationByCode(String id) {
        OperationDTO operationDTO = requestProcessor.queryToFindOperationByCode()
                .with(new SeriesOperationsRequestParametizer(null, id))
                .executeQuery()
                .singleResult(OperationDTO.class)
                .result();
        if (operationDTO == null) {
            return ResponseEntity.notFound().build();
        }
        Operation operation = seriesOperationsService.transformOperationDTOToOperation(operationDTO);
        return EndpointsUtils.toResponseEntity(operation);
    }

    @Override
    public ResponseEntity<Indicateur> getIndicatorById(String id) {
        IndicateurDTO indicateurDTO = requestProcessor.queryToFindIndicatorById()
                .with(new IndicateurRequestParametizer(id))
                .executeQuery()
                .singleResult(IndicateurDTO.class)
                .result();
        if (indicateurDTO == null) {
            return ResponseEntity.notFound().build();
        }
        Indicateur indicateur = seriesOperationsService.transformIndicateurDTOToIndicateur(indicateurDTO);
        return EndpointsUtils.toResponseEntity(indicateur);
    }

}