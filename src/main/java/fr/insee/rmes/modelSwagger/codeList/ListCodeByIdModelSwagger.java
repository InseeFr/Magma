
package fr.insee.rmes.modelSwagger.codeList;

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
    "codes",
    "id",
    "label",
    "uri",
    "version",
    "dateMiseAJour"
})
@Generated("jsonschema2pojo")
public class ListCodeByIdModelSwagger implements Serializable
{

    @JsonProperty("codes")
    @Valid
    private List<Code> codes = null;
    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    @Valid
    private List<Label__1> label = null;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("version")
    private String version;
    @JsonProperty("dateMiseAJour")
    private String dateMiseAJour;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5900782437168503916L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListCodeByIdModelSwagger() {
    }

    /**
     * 
     * @param codes
     * @param id
     * @param label
     * @param uri
     * @param version
     * @param dateMiseAJour
     */
    public ListCodeByIdModelSwagger(List<Code> codes, String id, List<Label__1> label, String uri, String version, String dateMiseAJour) {
        super();
        this.codes = codes;
        this.id = id;
        this.label = label;
        this.uri = uri;
        this.version = version;
        this.dateMiseAJour = dateMiseAJour;
    }

    @JsonProperty("codes")
    public List<Code> getCodes() {
        return codes;
    }

    @JsonProperty("codes")
    public void setCodes(List<Code> codes) {
        this.codes = codes;
    }

    public ListCodeByIdModelSwagger withCodes(List<Code> codes) {
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

    public ListCodeByIdModelSwagger withId(String id) {
        this.id = id;
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

    public ListCodeByIdModelSwagger withLabel(List<Label__1> label) {
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

    public ListCodeByIdModelSwagger withUri(String uri) {
        this.uri = uri;
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

    @JsonProperty("dateMiseAJour")
    public String getDateMiseAJour() {
        return dateMiseAJour;
    }

    @JsonProperty("dateMiseAJour")
    public void setDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public ListCodeByIdModelSwagger withVersion(String version) {
        this.version = version;
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

    public ListCodeByIdModelSwagger withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
