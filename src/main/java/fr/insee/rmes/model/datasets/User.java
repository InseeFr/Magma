package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String Id;
    private String email;

    public User(String id, String email) {
        Id = id;
        this.email = email;
    }
}
