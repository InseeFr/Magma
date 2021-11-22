package fr.insee.rmes.dto.concept;

import fr.insee.rmes.dto.LabelTwoLangs;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ConceptListId {


    @Schema(description ="date of creation" )
    public String dateCreation;
    @Schema(description ="date of update" )
    public String dateMiseAJour ;
    @Schema(description ="statut of validation" )
    public String statutValidation;
    @Schema(description ="ID" )
    public String id;
    @Schema(description="Label")
    public List<LabelTwoLangs> label;
    @Schema(description ="dateFinValidite" )
    public String dateFinValidite;
    @Schema(description ="uri" )
    public String uri;
    @Schema(description ="version" )
    public String version;

    public ConceptListId() {
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

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateFinValidite() {
        return dateFinValidite;
    }

    public void setDateFinValidite(String dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }



}
