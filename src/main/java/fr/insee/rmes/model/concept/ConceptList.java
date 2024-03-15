package fr.insee.rmes.model.concept;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "dateMiseAJour",
        "statutValidation",
        "id"
})
@Generated("jsonschema2pojo")
public class ConceptList implements Serializable{
    @JsonProperty("dateMiseAJour")
    private String dateMiseAJour;
    @JsonProperty("statutValidation")
    private String statutValidation;
    @JsonProperty("id")
    private String id;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4994025485354755729L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ConceptList() {
    }

    /**
     *
     * @param dateMiseAJour
     * @param statutValidation
     * @param id
     */
    public ConceptList(String dateMiseAJour, String statutValidation, String id) {
        super();
        this.dateMiseAJour = dateMiseAJour;
        this.statutValidation = statutValidation;
        this.id = id;
    }

    @JsonProperty("dateMiseAJour")
    public String getDateMiseAJour() {
        return dateMiseAJour;
    }

    @JsonProperty("dateMiseAJour")
    public void setDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public ConceptList withDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
        return this;
    }

    @JsonProperty("statutValidation")
    public String getStatutValidation() {
        return statutValidation;
    }

    @JsonProperty("statutValidation")
    public void setStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
    }

    public ConceptList withStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ConceptList withId(String id) {
        this.id = id;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ConceptList withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
