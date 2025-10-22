package fr.insee.rmes.magma.gestion.old.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uri",
        "label",
        "themeTaxonomy"
})
@Generated("jsonschema2pojo")
@ToString
public class ThemeModelSwagger  implements Serializable{
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("label")
    @Valid
    private List<LabelDataSet> labelDataSet = null;

    @JsonProperty("themeTaxonomy")
    private String themeTaxonomy;

    public ThemeModelSwagger(String uri, List<LabelDataSet> labelDataSet, String themeTaxonomy) {
        this.uri = uri;
        this.labelDataSet = labelDataSet;
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

    @JsonProperty("label")
    public List<LabelDataSet> getLabel() {
        return labelDataSet;
    }

    @JsonProperty("label")
    public void setLabel(List<LabelDataSet> labelDataSet) {
        this.labelDataSet = labelDataSet;
    }

    @JsonProperty("themeTaxonomy")
    public String getThemeTaxonomy() {
        return themeTaxonomy;
    }
    @JsonProperty("themeTaxonomy")
    public void setThemeTaxonomy(String themeTaxonomy) {
        this.themeTaxonomy = themeTaxonomy;
    }
}
