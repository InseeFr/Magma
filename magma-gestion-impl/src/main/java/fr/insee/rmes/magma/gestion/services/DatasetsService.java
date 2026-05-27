package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.gestion.model.DataSet;
import fr.insee.rmes.magma.gestion.model.Distribution;
import fr.insee.rmes.magma.gestion.utils.DatasetByIdDTO;
import fr.insee.rmes.magma.gestion.utils.DatasetByIdSummaryDTO;
import fr.insee.rmes.magma.gestion.utils.DatasetDTO;
import fr.insee.rmes.magma.gestion.utils.DistributionDTO;

import java.util.List;

public interface DatasetsService {

    List<DataSet> transformDatasetDTOsToDataSets(List<DatasetDTO> dtos);

    DataSet transformDatasetByIdDTOToDataSet(DatasetByIdDTO dto);

    List<Distribution> transformDistributionDTOsToDistributions(List<DistributionDTO> dtos);

}