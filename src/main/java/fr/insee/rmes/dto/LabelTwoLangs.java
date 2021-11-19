package fr.insee.rmes.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class LabelTwoLangs {

    @Schema(description = "Label language ", required = true)
    public String labelLg;
    @Schema(description = "contenu")
    public String contenu;

}
