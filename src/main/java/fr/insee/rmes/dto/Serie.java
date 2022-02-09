package fr.insee.rmes.dto;

import fr.insee.rmes.dto.pogues.NodePogues;
import io.swagger.v3.oas.annotations.media.Schema;

public class Serie {
    @Schema(description = "id ", required = true)
    public String id;
    @Schema(description = "label")
    public NodePogues.Label label;
    @Schema(description = "altlabel")
    public NodePogues.AltLabel altlabel;
    @Schema(description = "uri ", required = true)
    public String uri;
}
