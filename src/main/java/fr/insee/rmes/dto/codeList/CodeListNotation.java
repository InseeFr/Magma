package fr.insee.rmes.dto.codeList;

import fr.insee.rmes.dto.LabelTwoLangs;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class CodeListNotation {

    @Schema(description = "date of update")
    public String dateMiseAJour;
    @Schema(description = "list of codes")
    public List<CodeListDetails> codes;
    @Schema(description ="date of creation" )
    public String dateCreation;
    @Schema(description = "Id")
    public String id;
    @Schema(description = "label")
    public List <LabelTwoLangs> label;
    @Schema(description = "Uri")
    public String uri;

}
