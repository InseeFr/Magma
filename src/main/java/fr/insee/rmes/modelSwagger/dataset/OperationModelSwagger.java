package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uri",
        "id",
        "label",
})
@Generated("jsonschema2pojo")
public class OperationModelSwagger  implements Serializable {
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    @Valid
    private List<LabelDataSet> labelDataSet = null;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public OperationModelSwagger(String uri, String id, List<LabelDataSet> labelDataSet) {
        this.uri = uri;
        this.id=id;
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

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
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
