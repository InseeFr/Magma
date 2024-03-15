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
        "labelOperationLg1",
        "labelOperationLg2"
})
@Generated("jsonschema2pojo")
public class Operation implements Serializable {
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("id")
    private String id;
    @JsonProperty("labelOperationLg1")
    private String labelOperationLg1;
    @JsonProperty("labelOperationLg2")
    private String labelOperationLg2;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Operation() {
    }

    public Operation(String uri, String id, String labelOperationLg1, String labelOperationLg2) {
        super();
        this.uri = uri;
        this.id= id;
        this.labelOperationLg1 = labelOperationLg1;
        this.labelOperationLg2 = labelOperationLg2;
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

    @JsonProperty("labelOperationLg1")
    public String getlabelOperationLg1() {
        return labelOperationLg1;
    }
    @JsonProperty("labelOperationLg1")
    public void setlabelOperationLg1(String labelOperationLg1) {
        this.labelOperationLg1 = labelOperationLg1;
    }
    @JsonProperty("labelOperationLg2")
    public String getlabelOperationLg2() {
        return labelOperationLg2;
    }
    @JsonProperty("labelOperationLg2")
    public void setlabelOperationLg2(String labelOperationLg2) {
        this.labelOperationLg2 = labelOperationLg2;
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
