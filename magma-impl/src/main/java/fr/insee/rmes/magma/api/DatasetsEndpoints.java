package fr.insee.rmes.magma.api;

import fr.insee.rmes.magma.api.DatasetsApi;
import fr.insee.rmes.magma.api.requestprocessor.RequestProcessorGestion;
import fr.insee.rmes.magma.model.Dataset;
import fr.insee.rmes.magma.model.Distribution;
import fr.insee.rmes.magma.queries.parameters.DatasetsRequestParametizer;
import fr.insee.rmes.magma.services.DatasetsService;
import fr.insee.rmes.magma.utils.DatasetByIdDTO;
import fr.insee.rmes.magma.utils.DatasetDTO;
import fr.insee.rmes.magma.utils.DistributionDTO;
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
    public ResponseEntity<List<Dataset>> getListDatasets(@Nullable String dateMiseAJour) {
        List<DatasetDTO> dtos = requestProcessor.queryToFindAllDatasets()
                .with(new DatasetsRequestParametizer(dateMiseAJour))
                .executeQuery()
                .listResult(DatasetDTO.class)
                .result();

        List<Dataset> dataSets = datasetsService.transformDatasetDTOsToDataSets(dtos);
        return ResponseEntity.ok(dataSets);
    }

    @Override
    public ResponseEntity<Dataset> getDataSetById(String id) {
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

    @Override
    public ResponseEntity<List<Distribution>> getDataSetDistributionsById(String id) {
        List<DistributionDTO> dtos = requestProcessor.queryToFindDistributionsByDatasetId()
                .with(new DatasetsRequestParametizer(id, null))
                .executeQuery()
                .listResult(DistributionDTO.class)
                .result();
        return ResponseEntity.ok(datasetsService.transformDistributionDTOsToDistributions(dtos));
    }
}
