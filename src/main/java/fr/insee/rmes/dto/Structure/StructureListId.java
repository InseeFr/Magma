package fr.insee.rmes.dto.Structure;

import fr.insee.rmes.dto.LabelTwoLangs;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class StructureListId {

    @Schema(description = "date of update")
    public String dateMiseAJour;
    @Schema(description = "Notation")
    public String notation;
    @Schema(description = "date of creation")
    public String dateCreation;
    @Schema(description = "validation statut")
    public String statutValidation;
    @Schema (description = "measure")
    public List <StructureListIdMesure> mesure;
    @Schema(description = "Id")
    public String id;
    @Schema (description = "label")
    public List <LabelTwoLangs> label;
    @Schema (description = "attribut")
    public List <StructureAttributs> attributs;
    @Schema (description = "dimensions")
    public List <StructureDimensions> dimensions;
}
