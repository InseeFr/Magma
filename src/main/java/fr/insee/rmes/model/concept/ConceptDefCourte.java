package fr.insee.rmes.model.concept;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.annotation.Generated;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "content",
        "lang"
})
@Generated("jsonschema2pojo")
public class ConceptDefCourte implements Serializable  {

    @JsonProperty("content")
    private String contenu;
    @JsonProperty("lang")
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

    @JsonProperty("content")
    public String getContenu() {
        return contenu;
    }

    @JsonProperty("content")
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @JsonProperty("lang")
    public String getLangue() {
        return langue;
    }

    @JsonProperty("lang")
    public void setLangue(String langue) {
        this.langue = langue;
    }
}
