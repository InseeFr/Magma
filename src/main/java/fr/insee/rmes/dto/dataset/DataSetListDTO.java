package fr.insee.rmes.dto.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class DataSetListDTO extends SerieListDTO {
    private List<DataSetListDTO> dataSetListDTOS= null;

    public DataSetListDTO() {}

    public DataSetListDTO(List<DataSetListDTO> dataSetListDTOS) {
        this.dataSetListDTOS=dataSetListDTOS;
    }

    public List<DataSetListDTO> getDataSetListDTOS () {
        return dataSetListDTOS;
    }

}
