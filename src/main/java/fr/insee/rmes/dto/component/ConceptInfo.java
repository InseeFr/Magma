package fr.insee.rmes.dto.component;

import io.swagger.v3.oas.annotations.media.Schema;

public class ConceptInfo {
    @Schema(description="Concept Id")
    public String id;
    @Schema(description="Uri")
    public String uri;
}
