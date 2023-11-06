package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class Created {
    private String created;
    public Created(String created) {
        this.created=created;
    }
}
