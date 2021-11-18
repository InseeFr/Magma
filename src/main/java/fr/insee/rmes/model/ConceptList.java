package fr.insee.rmes.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class ConceptList {
    /*?id ?dateMiseAJour ?statutValidation ?agence
    *  ?id ?prefLabelLg1 ?prefLabelLg2 ?namingHint ?dateCreation ?dateMiseAjour ?dateFinValidite ?statutValidation ?uri (max(?numVersion) as?version)*/

    @Schema(description ="ID" )
    public String id;
    @Schema(description ="date of update" )
    public String dateMiseAJour ;
    @Schema(description ="statut of validation" )
    public String statutValidation;
    @Schema(description ="agency" )
    public String agence;
    @Schema(description ="prefLabelLg1" )
    public String prefLabelLg1;
    @Schema(description ="prefLabelLg2" )
    public String prefLabelLg2;
    @Schema(description ="namingHint" )
    public String namingHint ;
    @Schema(description ="date of creation" )
    public String dateCreation;
    @Schema(description ="dateFinValidite" )
    public String dateFinValidite;
    @Schema(description ="uri" )
    public String uri;
    @Schema(description ="version" )
    public String version;

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

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getPrefLabelLg1() {
        return prefLabelLg1;
    }

    public void setPrefLabelLg1(String prefLabelLg1) {
        this.prefLabelLg1 = prefLabelLg1;
    }

    public String getPrefLabelLg2() {
        return prefLabelLg2;
    }

    public void setPrefLabelLg2(String prefLabelLg2) {
        this.prefLabelLg2 = prefLabelLg2;
    }

    public String getNamingHint() {
        return namingHint;
    }

    public void setNamingHint(String namingHint) {
        this.namingHint = namingHint;
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
