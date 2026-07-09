package fr.insee.rmes.magma.services;

import fr.insee.rmes.magma.model.Indicateur;
import fr.insee.rmes.magma.model.Operation;
import fr.insee.rmes.magma.model.Serie;
import fr.insee.rmes.magma.utils.IndicateurDTO;
import fr.insee.rmes.magma.utils.OperationDTO;
import fr.insee.rmes.magma.utils.SeriesDTO;

import java.util.List;

public interface SeriesOperationsService {
    Serie transformSeriesDTOToSerieById(SeriesDTO seriesDTO);
    Operation transformOperationDTOToOperation(OperationDTO dto);
    List<Serie> transformSeriesDTOsToSeries(List<SeriesDTO> dtos);
    Indicateur transformIndicateurDTOToIndicateur(IndicateurDTO indicateurDTO);
}