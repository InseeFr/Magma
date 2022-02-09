package fr.insee.rmes.dto;

import fr.insee.rmes.dto.pogues.NodePogues;
import io.swagger.v3.oas.annotations.media.Schema;

public class Operation {

    @Schema(description = "idRapportQualite")
    public String idRapportQualite;
    @Schema(description = "label")
    public NodePogues.Label label;
    @Schema(description = "uri ", required = true)
    public String uri;
    @Schema(description = "serie")
    public Serie serie;
    @Schema(description = "id ", required = true)
    public String id;
}
