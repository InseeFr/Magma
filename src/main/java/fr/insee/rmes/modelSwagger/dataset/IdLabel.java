package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
public class IdLabel implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    private List<LangContent> label;

    @JsonProperty("type")
    private String type;
    public IdLabel() {
    }

    public IdLabel(String id, List<LangContent> label) {
        this.id = id;
        this.label = label;
    }

}
