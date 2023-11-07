package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Contributor {
    private String contributor;
    public Contributor(String contributor) {
        this.contributor=contributor;
    }

    @Override
    public String toString() {
        return contributor ;
    }
}
