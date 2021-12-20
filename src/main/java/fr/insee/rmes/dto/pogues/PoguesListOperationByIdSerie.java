package fr.insee.rmes.dto.pogues;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class PoguesListOperationByIdSerie {
    @Schema
    public String idRapportQualite;
    @Schema
    public NodePogues.AltLabel altlabel;
    @Schema
    public NodePogues.Label label;
    @Schema
    public String uri;
    @Schema
    public NodePogues.Serie serie;
    @Schema
    public String id;
}
