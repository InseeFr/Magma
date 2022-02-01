package fr.insee.rmes.dto.codeList;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class CodeList {

    @Schema(description="Code list's id")
    public String id;

    @Schema(description = "date of update")
    public String dateMiseAJour;

    @Schema(description = "statut of validation")
    public String statutValidation;

    @Schema(description = "order")
    public String ordre;

    @Schema(description = "version")
    public String version;



}
