package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Modified {
    private String modified;
    public Modified(String modified) {
        this.modified=modified;
    }
}
