package fr.insee.rmes.dto.dataset;

import java.util.List;

public class OperationListDTO {

    private List<OperationListDTO> OperationListDTOS= null;

    public OperationListDTO() {}

    public OperationListDTO(List<OperationListDTO> OperationListDTOS) {
        this.OperationListDTOS=OperationListDTOS;
    }

    public List<OperationListDTO> getOperationListDTOS () {
        return OperationListDTOS;
    }
}
