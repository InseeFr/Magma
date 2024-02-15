package fr.insee.rmes.model.CodeList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.insee.rmes.modelSwagger.dataset.Label;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "codes",
        "label"
})
@Generated("jsonschema2pojo")
@Setter
@Getter
public class CodeList {
    @JsonProperty("id")
    private String id;
    @JsonProperty("codes")
    private List<Code> codes;
    @JsonProperty("label")
    private Label label;

    public CodeList(String id, List<Code> codes, Label label) {
        this.id = id;
        this.codes = codes;
        this.label = label;
    }
}
