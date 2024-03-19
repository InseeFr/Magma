
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
    "listCode",
    "label",
    "uri",
    "representation"
})
@Generated("jsonschema2pojo")
public class Dimension implements Serializable
{

    @JsonProperty("ordre")
    private String ordre;
    @JsonProperty("notation")
    private String notation;
    @JsonProperty("listCode")
    @Valid
    private ListCode__1 listCode;
    @JsonProperty("label")
    @Valid
    private List<Label__3> label = null;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("representation")
    private String representation;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8415218908620772820L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Dimension() {
    }

    /**
     * 
     * @param ordre
     * @param notation
     * @param listCode
     * @param label
     * @param uri
     * @param representation
     */
    public Dimension(String ordre, String notation, ListCode__1 listCode, List<Label__3> label, String uri, String representation) {
        super();
        this.ordre = ordre;
        this.notation = notation;
        this.listCode = listCode;
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

    public Dimension withOrdre(String ordre) {
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

    public Dimension withNotation(String notation) {
        this.notation = notation;
        return this;
    }

    @JsonProperty("listCode")
    public ListCode__1 getListCode() {
        return listCode;
    }

    @JsonProperty("listCode")
    public void setListCode(ListCode__1 listCode) {
        this.listCode = listCode;
    }

    public Dimension withListCode(ListCode__1 listCode) {
        this.listCode = listCode;
        return this;
    }

    @JsonProperty("label")
    public List<Label__3> getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(List<Label__3> label) {
        this.label = label;
    }

    public Dimension withLabel(List<Label__3> label) {
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

    public Dimension withUri(String uri) {
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

    public Dimension withRepresentation(String representation) {
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

    public Dimension withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
