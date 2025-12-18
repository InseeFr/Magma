package fr.insee.rmes.magma.gestion.old.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Modified {
    private String modified;
    public Modified(String modified) {
        this.modified=modified;
    }

    @Override
    public String toString() {
        return modified ;
    }
}
