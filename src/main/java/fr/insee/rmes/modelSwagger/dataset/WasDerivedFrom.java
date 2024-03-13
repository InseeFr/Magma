package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonPropertyOrder({
        "datasets",
        "description"
})
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter


public class WasDerivedFrom {
    @JsonProperty("datasets")
    private List<String> datasets;


    @JsonProperty("description")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<LangContent> description;

    public WasDerivedFrom(List<String> datasets, List<LangContent> description) {
        this.datasets = datasets;
        this.description = description;
    }
    public WasDerivedFrom(List<String> datasets) {
        this.datasets = datasets;
    }

}
