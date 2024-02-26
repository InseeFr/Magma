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
        "code",
        "label",
        "uri"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
public class Code {
    @JsonProperty("code")
    private String code;
    @JsonProperty("label")
    private Label label;
    @JsonProperty("uri")
    private String uri;

    public Code(String code, Label label, String uri) {
        this.code = code;
        this.label = label;
        this.uri = uri;
    }
}
