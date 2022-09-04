
package fr.insee.rmes.modelSwagger.component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "label",
    "uri"
})
@Generated("jsonschema2pojo")
public class Code implements Serializable
{

    @JsonProperty("code")
    private String code;
    @JsonProperty("label")
    @Valid
    private List<Label__1> label = null;
    @JsonProperty("uri")
    private String uri;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5079446284551586511L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Code() {
    }

    /**
     * 
     * @param code
     * @param label
     * @param uri
     */
    public Code(String code, List<Label__1> label, String uri) {
        super();
        this.code = code;
        this.label = label;
        this.uri = uri;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    public Code withCode(String code) {
        this.code = code;
        return this;
    }

    @JsonProperty("label")
    public List<Label__1> getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(List<Label__1> label) {
        this.label = label;
    }

    public Code withLabel(List<Label__1> label) {
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

    public Code withUri(String uri) {
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

    public Code withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
