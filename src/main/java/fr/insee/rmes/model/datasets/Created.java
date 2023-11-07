package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Created {
    private String created;
    public Created(String created) {
        this.created=created;
    }

    @Override
    public String toString() {
        return created;
    }
}

