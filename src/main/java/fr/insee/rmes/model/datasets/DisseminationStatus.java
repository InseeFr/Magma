package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class DisseminationStatus {
    private String disseminationStatus;
    public DisseminationStatus(String disseminationStatus) {
        this.disseminationStatus=disseminationStatus;
    }

    @Override
    public String toString() {
        return disseminationStatus ;
    }
}
