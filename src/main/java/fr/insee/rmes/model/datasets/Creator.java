package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Creator {
    private String creator;
    public Creator(String creator) {
        this.creator=creator;
    }
}
