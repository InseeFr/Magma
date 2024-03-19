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
        "labelThemeLg1",
        "labelThemeLg2",
        "themeTaxonomy"
})
@Generated("jsonschema2pojo")
public class Theme implements Serializable  {
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("labelThemeLg1")
    private String labelThemeLg1;
    @JsonProperty("labelThemeLg2")
    private String labelThemeLg2;
    @JsonProperty("themeTaxonomy")
    private String themeTaxonomy;

    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Theme() {
    }

    public Theme(String uri, String labelThemeLg1, String labelThemeLg2, String themeTaxonomy) {
        this.uri = uri;
        this.labelThemeLg1 = labelThemeLg1;
        this.labelThemeLg2 = labelThemeLg2;
        this.themeTaxonomy = themeTaxonomy;
    }



    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }
    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }
    @JsonProperty("labelThemeLg1")
    public String getLabelThemeLg1() {
        return labelThemeLg1;
    }
    @JsonProperty("labelThemeLg1")
    public void setLabelThemeLg1(String labelThemeLg1) {
        this.labelThemeLg1 = labelThemeLg1;
    }
    @JsonProperty("labelThemeLg2")
    public String getLabelThemeLg2() {
        return labelThemeLg2;
    }
    @JsonProperty("labelThemeLg2")
    public void setLabelThemeLg2(String labelThemeLg2) {
        this.labelThemeLg2 = labelThemeLg2;
    }

    @JsonProperty("themeTaxonomy")
    public String getThemeTaxonomy() {
        return themeTaxonomy;
    }
    @JsonProperty("themeTaxonomy")
    public void setThemeTaxonomy(String themeTaxonomy) {
        this.themeTaxonomy = themeTaxonomy;
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
