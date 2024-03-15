
package fr.insee.rmes.modelSwagger.structure;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
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
    "notation",
    "label",
    "uri",
    "representation"
})
@Generated("jsonschema2pojo")
public class Mesure implements Serializable
{

    @JsonProperty("ordre")
    private String ordre;
    @JsonProperty("notation")
    private String notation;
    @JsonProperty("label")
    @Valid
    private List<Label> label = null;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("representation")
    private String representation;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8529450967066590683L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Mesure() {
    }

    /**
     * 
     * @param ordre
     * @param notation
     * @param label
     * @param uri
     * @param representation
     */
    public Mesure(String ordre, String notation, List<Label> label, String uri, String representation) {
        super();
        this.ordre = ordre;
        this.notation = notation;
        this.label = label;
        this.uri = uri;
        this.representation = representation;
    }

    @JsonProperty("ordre")
    public String getOrdre() {
        return ordre;
    }

    @JsonProperty("ordre")
    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public Mesure withOrdre(String ordre) {
        this.ordre = ordre;
        return this;
    }

    @JsonProperty("notation")
    public String getNotation() {
        return notation;
    }

    @JsonProperty("notation")
    public void setNotation(String notation) {
        this.notation = notation;
    }

    public Mesure withNotation(String notation) {
        this.notation = notation;
        return this;
    }

    @JsonProperty("label")
    public List<Label> getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(List<Label> label) {
        this.label = label;
    }

    public Mesure withLabel(List<Label> label) {
        this.label = label;
        return this;
    }

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    public Mesure withUri(String uri) {
        this.uri = uri;
        return this;
    }

    @JsonProperty("representation")
    public String getRepresentation() {
        return representation;
    }

    @JsonProperty("representation")
    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public Mesure withRepresentation(String representation) {
        this.representation = representation;
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

    public Mesure withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
