package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Creator {
    private String creator;
    public Creator(String creator) {
        this.creator=creator;
    }

    @Override
    public String toString() {
        return creator;
    }
}
