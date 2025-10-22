package fr.insee.rmes.magma.gestion.old.modelSwagger.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "composants",
        "label",
        "type"
})

@Generated("jsonschema2pojo")
@Getter
@Setter
public class StructureSliceKeysModelSwagger {
    @JsonProperty("composants")
    @Valid
    private List<String> composantsId = null;
    @JsonProperty("label")
    @Valid
    private List<Label> label = null;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 59690530118419605L;

    public StructureSliceKeysModelSwagger() {
    }

}
