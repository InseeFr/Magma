package fr.insee.rmes.dto.pogues;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class PoguesListId {
    @Schema
    public String id;
    @Schema
    public NodePogues.Label label;
    @Schema
    public String serie;
    @Schema
    public NodePogues.AltLabel altLabel;
    @Schema
    public NodePogues.Type type;
    @Schema
    public String nbOperations;
    @Schema
    public NodePogues.Famille Famille;

}
