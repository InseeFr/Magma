package fr.insee.rmes.dto.Structure;

import io.swagger.v3.oas.annotations.media.Schema;

public class StructureList {


    @Schema(description ="date of update" )
    public String dateMiseAJour ;
    @Schema(description ="validation statut" )
    public String statutValidation;
    @Schema(description ="ID" )
    public String id;

    public StructureList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateMiseAJour() {
        return dateMiseAJour;
    }

    public void setDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public String getStatutValidation() {
        return statutValidation;
    }

    public void setStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
    }


}
