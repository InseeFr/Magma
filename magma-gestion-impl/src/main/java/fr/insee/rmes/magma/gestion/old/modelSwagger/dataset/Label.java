package fr.insee.rmes.magma.gestion.old.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
