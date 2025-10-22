package fr.insee.rmes.magma.gestion.old.model.CodeList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.insee.rmes.magma.gestion.old.modelSwagger.dataset.Label;
import jakarta.annotation.Generated;
import lombok.Getter;
import lombok.Setter;

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
