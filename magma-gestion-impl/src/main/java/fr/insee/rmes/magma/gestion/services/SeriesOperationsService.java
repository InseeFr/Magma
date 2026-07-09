package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.model.Indicateur;
import fr.insee.rmes.magma.model.Operation;
import fr.insee.rmes.magma.model.Serie;
import fr.insee.rmes.magma.gestion.utils.IndicateurDTO;
import fr.insee.rmes.magma.gestion.utils.OperationDTO;
import fr.insee.rmes.magma.gestion.utils.SeriesDTO;

import java.util.List;

public interface SeriesOperationsService {
    Serie transformSeriesDTOToSerieById(SeriesDTO seriesDTO);
    Operation transformOperationDTOToOperation(OperationDTO dto);
    List<Serie> transformSeriesDTOsToSeries(List<SeriesDTO> dtos);
    Indicateur transformIndicateurDTOToIndicateur(IndicateurDTO indicateurDTO);
}