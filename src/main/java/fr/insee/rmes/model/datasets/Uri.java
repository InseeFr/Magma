package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Uri {
    private String uri;
    public Uri(String uri) {
        this.uri=uri;
    }

    @Override
    public String toString() {
        return uri ;
    }
}
