package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Id {
    private String id;

    public Id(String id) {
        this.id=id;
    }
}
