package fr.insee.rmes.dto.operation;

import java.util.List;

public class OperationsBySerieIdDTO {
private List<OperationsBySerieIdDTO> operationsBySerieIdDTO= null;

public OperationsBySerieIdDTO() {}

public OperationsBySerieIdDTO(List<OperationsBySerieIdDTO> operationsBySerieIdDTO) {
    this.operationsBySerieIdDTO=operationsBySerieIdDTO;
    }

    public List<OperationsBySerieIdDTO> getOperationsBySerieIdDTO() {
    return operationsBySerieIdDTO;
    }


}
