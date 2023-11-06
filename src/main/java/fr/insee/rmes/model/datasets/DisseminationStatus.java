package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class DisseminationStatus {
    private String disseminationStatus;
    public DisseminationStatus(String disseminationStatus) {
        this.disseminationStatus=disseminationStatus;
    }
}
