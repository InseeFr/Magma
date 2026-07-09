package fr.insee.rmes.magma.services;

import fr.insee.rmes.magma.model.Dataset;
import fr.insee.rmes.magma.model.Distribution;
import fr.insee.rmes.magma.utils.DatasetByIdDTO;
import fr.insee.rmes.magma.utils.DatasetDTO;
import fr.insee.rmes.magma.utils.DistributionDTO;

import java.util.List;

public interface DatasetsService {

    List<Dataset> transformDatasetDTOsToDataSets(List<DatasetDTO> dtos);

    Dataset transformDatasetByIdDTOToDataSet(DatasetByIdDTO dto);

    List<Distribution> transformDistributionDTOsToDistributions(List<DistributionDTO> dtos);

}