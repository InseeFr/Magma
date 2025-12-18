
package fr.insee.rmes.magma.gestion.old.modelSwagger.component;

import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "codes",
    "id",
    "uri"
})
@Generated("jsonschema2pojo")
public class ListeCode implements Serializable
{

    @JsonProperty("codes")
    @Valid
    private List<Code> codes = null;
    @JsonProperty("id")
    private String id;
    @JsonProperty("uri")
    private String uri;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2665224137207209788L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListeCode() {
    }

    /**
     * 
     * @param codes
     * @param id
     * @param uri
     */
    public ListeCode(List<Code> codes, String id, String uri) {
        super();
        this.codes = codes;
        this.id = id;
        this.uri = uri;
    }

    @JsonProperty("codes")
    public List<Code> getCodes() {
        return codes;
    }

    @JsonProperty("codes")
    public void setCodes(List<Code> codes) {
        this.codes = codes;
    }

    public ListeCode withCodes(List<Code> codes) {
        this.codes = codes;
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

    public ListeCode withId(String id) {
        this.id = id;
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

    public ListeCode withUri(String uri) {
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

    public ListeCode withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
