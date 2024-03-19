
package fr.insee.rmes.modelSwagger.codeList;

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
    "ordre",
    "id",
    "version",
    "statutValidation",
    "dateMiseAJour"
})
@Generated("jsonschema2pojo")
public class AllListCodeModelSwagger implements Serializable
{

    @JsonProperty("ordre")
    private String ordre;
    @JsonProperty("id")
    private String id;
    @JsonProperty("version")
    private String version;
    @JsonProperty("statutValidation")
    private String statutValidation;
    @JsonProperty("dateMiseAJour")
    private String dateMiseAJour;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 2246220713576937028L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AllListCodeModelSwagger() {
    }

    /**
     * 
     * @param dateMiseAJour
     * @param ordre
     * @param statutValidation
     * @param id
     * @param version
     */
    public AllListCodeModelSwagger(String ordre, String id, String version, String statutValidation, String dateMiseAJour) {
        super();
        this.ordre = ordre;
        this.id = id;
        this.version = version;
        this.statutValidation = statutValidation;
        this.dateMiseAJour = dateMiseAJour;
    }

    @JsonProperty("ordre")
    public String getOrdre() {
        return ordre;
    }

    @JsonProperty("ordre")
    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public AllListCodeModelSwagger withOrdre(String ordre) {
        this.ordre = ordre;
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

    public AllListCodeModelSwagger withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    public AllListCodeModelSwagger withVersion(String version) {
        this.version = version;
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

    public AllListCodeModelSwagger withStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
        return this;
    }

    @JsonProperty("dateMiseAJour")
    public String getDateMiseAJour() {
        return dateMiseAJour;
    }

    @JsonProperty("dateMiseAJour")
    public void setDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public AllListCodeModelSwagger withDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
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

    public AllListCodeModelSwagger withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
