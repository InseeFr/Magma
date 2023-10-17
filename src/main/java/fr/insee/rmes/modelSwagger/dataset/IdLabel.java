package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.List;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "type",
        "label"
})
@Generated("jsonschema2pojo")
public class IdLabel implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    private List<Title> label;

    @JsonProperty("type")
    private String type;
    public IdLabel() {
    }

    public IdLabel(String id, List<Title> label) {
        this.id = id;
        this.label = label;
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
    public List<Title> getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(List<Title> label) {
        this.label = label;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }
}
