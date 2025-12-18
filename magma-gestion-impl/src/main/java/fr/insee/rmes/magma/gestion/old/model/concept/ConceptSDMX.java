package fr.insee.rmes.magma.gestion.old.model.concept;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "agence",
        "id"
})
@Generated("jsonschema2pojo")
public class ConceptSDMX implements Serializable {
    @JsonProperty("agence")
    private String agence;
    @JsonProperty("id")
    private String id;
    /**
     * No args constructor for use in serialization
     *
     */
    public ConceptSDMX() {
    }

    public ConceptSDMX(String agence, String id) {
        this.agence = agence;
        this.id = id;
    }

    @JsonProperty("agence")
    public String getAgence() {
        return agence;
    }

    @JsonProperty("agence")
    public void setAgence(String agence) {
        this.agence = agence;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }
}
