package fr.insee.rmes.dto.concept;

import io.swagger.v3.oas.annotations.media.Schema;

public class ConceptList {

    @Schema(description ="ID" )
    public String id;
    @Schema(description ="date of update" )
    public String dateMiseAJour ;
    @Schema(description ="statut of validation" )
    public String statutValidation;

    public ConceptList() {
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
