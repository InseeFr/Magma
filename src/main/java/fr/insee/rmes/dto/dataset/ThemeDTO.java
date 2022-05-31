package fr.insee.rmes.dto.dataset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uri",
        "label",
})
@Generated("jsonschema2pojo")
public class ThemeDTO  implements Serializable{
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("label")
    @Valid
    private List<LabelDataSet> labelDataSet = null;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ThemeDTO(String uri, List<LabelDataSet> labelDataSet) {
        this.uri = uri;
        this.labelDataSet = labelDataSet;
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
}
