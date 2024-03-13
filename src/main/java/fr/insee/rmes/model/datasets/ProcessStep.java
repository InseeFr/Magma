package fr.insee.rmes.model.datasets;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.insee.rmes.modelSwagger.dataset.Label;
public class ProcessStep {
    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    private Label label;

    public ProcessStep(String id,  Label label) {
        this.id = id;
        this.label = label;
    }
}
