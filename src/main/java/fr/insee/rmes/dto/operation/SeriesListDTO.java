package fr.insee.rmes.dto.operation;

import java.util.List;

public class SeriesListDTO {
    private List<SeriesListDTO> seriesListDTO= null;

    public SeriesListDTO() {}

    public SeriesListDTO(List<SeriesListDTO> seriesListDTO) {
        this.seriesListDTO=seriesListDTO;
    }

    public List<SeriesListDTO> getSeriesListDTO() {
        return seriesListDTO;
    }

}
