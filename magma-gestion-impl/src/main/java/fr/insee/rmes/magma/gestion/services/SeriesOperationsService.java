package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.gestion.model.SerieById;
import fr.insee.rmes.magma.gestion.utils.SeriesDTO;

public interface SeriesOperationsService {
    SerieById transformSeriesDTOToSerieById(SeriesDTO seriesDTO);
}