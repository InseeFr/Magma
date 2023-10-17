package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import javax.annotation.Generated;
import java.util.List;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "label"
})
@Generated("jsonschema2pojo")
public class Label {

    @JsonProperty("label")
    private List<Title> label;

    public Label() {
    }

    public Label(List<Title> label) {
        this.label = label;
    }

    @JsonProperty("label")
    public List<Title> getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(List<Title> label) {
        this.label = label;
    }
}
