package fr.insee.rmes.model.concept;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "contenu",
        "langue"
})
@Generated("jsonschema2pojo")
public class ConceptDefCourte implements Serializable  {

    @JsonProperty("contenu")
    private String contenu;
    @JsonProperty("langue")
    private String langue;
    /**
     * No args constructor for use in serialization
     *
     */

    public ConceptDefCourte() {
    }

    public ConceptDefCourte(String contenu, String langue) {
        this.contenu = contenu;
        this.langue = langue;
    }

    @JsonProperty("contenu")
    public String getContenu() {
        return contenu;
    }

    @JsonProperty("contenu")
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @JsonProperty("langue")
    public String getLangue() {
        return langue;
    }

    @JsonProperty("langue")
    public void setLangue(String langue) {
        this.langue = langue;
    }
}
