package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.annotation.Generated;
import java.util.List;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "label"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
public class Label {

    @JsonProperty("label")
    private List<LangContent> label;

    public Label(List<LangContent> label) {
        this.label = label;
    }

}
