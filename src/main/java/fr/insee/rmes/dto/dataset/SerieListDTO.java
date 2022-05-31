package fr.insee.rmes.dto.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SerieListDTO {
    private List<SerieListDTO> serieListDTOS= null;

    public SerieListDTO() {}

    public SerieListDTO(List<SerieListDTO> serieListDTOS) {
        this.serieListDTOS=serieListDTOS;
    }

    public List<SerieListDTO> getSerieListDTOS () {
        return serieListDTOS;
    }
}
