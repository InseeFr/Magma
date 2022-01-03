package fr.insee.rmes.dto.component;

import fr.insee.rmes.dto.LabelTwoLangs;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ComponentID {

    @Schema(description = "Date of update")
    public String dateMiseAJour;
    @Schema(description="Component notation")
    public String notation;
    @Schema(description="Concept")
    public List<ConceptInfo> concept;
    @Schema(description = "Statut of validation")
    public String statutValidation;
    @Schema(description="Component Id")
    public String id;
    @Schema(description="Label")
    public List<LabelTwoLangs> label;
    @Schema(description="Type")
    public String type;
    @Schema(description="Uri")
    public String uri;
    @Schema(description="Representation")
    public String representation;

}
