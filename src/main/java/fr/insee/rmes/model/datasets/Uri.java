package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class Uri {
    private String uri;
    public Uri(String uri) {
        this.uri=uri;
    }
}
