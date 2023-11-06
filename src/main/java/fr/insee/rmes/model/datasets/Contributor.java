package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Contributor {
    private String contributor;
    public Contributor(String contributor) {
        this.contributor=contributor;
    }
}
