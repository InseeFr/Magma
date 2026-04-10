package fr.insee.rmes.magma.gestion.api;

import fr.insee.rmes.magma.gestion.api.requestprocessor.RequestProcessorGestion;
import fr.insee.rmes.magma.gestion.model.DataSet;
import fr.insee.rmes.magma.gestion.queries.parameters.DatasetsRequestParametizer;
import fr.insee.rmes.magma.gestion.services.DatasetsService;
import fr.insee.rmes.magma.gestion.utils.DatasetByIdDTO;
import fr.insee.rmes.magma.gestion.utils.DatasetByIdSummaryDTO;
import fr.insee.rmes.magma.gestion.utils.DatasetDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DatasetsEndpoints implements DatasetsApi {

    private final RequestProcessorGestion requestProcessor;
    private final DatasetsService datasetsService;

    public DatasetsEndpoints(RequestProcessorGestion requestProcessor, DatasetsService datasetsService) {
        this.requestProcessor = requestProcessor;
        this.datasetsService = datasetsService;
    }

    @Override
    public ResponseEntity<List<DataSet>> getListDatasets(@Nullable String dateMiseAJour) {
        List<DatasetDTO> dtos;
        if (dateMiseAJour == null || dateMiseAJour.isBlank()) {
            dtos = requestProcessor.queryToFindAllDatasets()
                    .with(new DatasetsRequestParametizer(null))
                    .executeQuery()
                    .listResult(DatasetDTO.class)
                    .result();
        } else {
            dtos = requestProcessor.queryToFindAllDatasetsByDate()
                    .with(new DatasetsRequestParametizer(dateMiseAJour))
                    .executeQuery()
                    .listResult(DatasetDTO.class)
                    .result();
        }
        List<DataSet> dataSets = datasetsService.transformDatasetDTOsToDataSets(dtos);
        return ResponseEntity.ok(dataSets);
    }

    @Override
    public ResponseEntity<DataSet> getDataSetById(String id, Boolean dateMiseAJour) {
        if (Boolean.TRUE.equals(dateMiseAJour)) {
            DatasetByIdSummaryDTO summaryDTO = requestProcessor.queryToFindDatasetByIdSummary()
                    .with(new DatasetsRequestParametizer(id, null))
                    .executeQuery()
                    .singleResult(DatasetByIdSummaryDTO.class)
                    .result();
            if (summaryDTO == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(datasetsService.transformDatasetByIdSummaryDTOToDataSet(summaryDTO));
        } else {
            DatasetByIdDTO dto = requestProcessor.queryToFindDatasetById()
                    .with(new DatasetsRequestParametizer(id, null))
                    .executeQuery()
                    .singleResult(DatasetByIdDTO.class)
                    .result();
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(datasetsService.transformDatasetByIdDTOToDataSet(dto));
        }
    }
}