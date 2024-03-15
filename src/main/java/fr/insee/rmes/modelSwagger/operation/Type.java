
package fr.insee.rmes.modelSwagger.operation;

import com.fasterxml.jackson.annotation.*;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "label",
    "uri"
})
@Generated("jsonschema2pojo")
public class Type implements Serializable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    @Valid
    private List<Label> label = null;
    @JsonProperty("uri")
    private String uri;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6356583899097583891L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Type() {
    }

    /**
     * 
     * @param id
     * @param label
     * @param uri
     */
    public Type(String id, List<Label> label, String uri) {
        super();
        this.id = id;
        this.label = label;
        this.uri = uri;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Type withId(String id) {
        this.id = id;
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

    public Type withLabel(List<Label> label) {
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

    public Type withUri(String uri) {
        this.uri = uri;
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

    public Type withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
