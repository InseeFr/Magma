package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.gestion.model.DataSet;
import fr.insee.rmes.magma.gestion.utils.DatasetDTO;

import java.util.List;

public interface DatasetsService {

    List<DataSet> transformDatasetDTOsToDataSets(List<DatasetDTO> dtos);
}