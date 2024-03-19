package fr.insee.rmes.model.datasets;

import com.fasterxml.jackson.annotation.*;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uri",
        "id",
        "labelSerieLg1",
        "labelSerieLg2"
})
@Generated("jsonschema2pojo")
public class Serie implements Serializable  {
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("id")
    private String id;
    @JsonProperty("labelSerieLg1")
    private String labelSerieLg1;
    @JsonProperty("labelSerieLg2")
    private String labelSerieLg2;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Serie() {
    }

    public Serie(String uri, String id, String labelSerieLg1, String labelSerieLg2) {
        super();
        this.uri = uri;
        this.id= id;
        this.labelSerieLg1 = labelSerieLg1;
        this.labelSerieLg2 = labelSerieLg2;
    }
    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }
    @JsonProperty("uri")
    public void setUri(String uri) {
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

    @JsonProperty("labelSerieLg1")
    public String getLabelSerieLg1() {
        return labelSerieLg1;
    }
    @JsonProperty("labelSerieLg1")
    public void setLabelSerieLg1(String labelSerieLg1) {
        this.labelSerieLg1 = labelSerieLg1;
    }
    @JsonProperty("labelSerieLg2")
    public String getLabelSerieLg2() {
        return labelSerieLg2;
    }
    @JsonProperty("labelSerieLg2")
    public void setLabelSerieLg2(String labelSerieLg2) {
        this.labelSerieLg2 = labelSerieLg2;
    }
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
    @JsonAnySetter
    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
