package fr.insee.rmes.dto.component;
import io.swagger.v3.oas.annotations.media.Schema;
public class Components {

    @Schema(description = "date of update")
    public String dateMiseAJour;

    @Schema(description = "statut of validation")
    public String statutValidation;

    @Schema(description="Component id")
    public String id;
}
