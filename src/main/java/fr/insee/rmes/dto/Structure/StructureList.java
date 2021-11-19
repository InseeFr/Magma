package fr.insee.rmes.dto.Structure;

import io.swagger.v3.oas.annotations.media.Schema;

public class StructureList {


    @Schema(description ="date of update" )
    public String dateMiseAJour ;
    @Schema(description ="validation statut" )
    public String statutValidation;
    @Schema(description ="ID" )
    public String id;
    /*
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
    @Schema(description = "Notation")
    public String notation;
    @Schema(description = "ConceptUri")
    public String conceptUri ;
    @Schema(description = "ConceptId")
    public String conceptId ;
    @Schema(description = "Representation")
    public String representation ;
    @Schema(description = "URI of code list")
    public String listeCodeUri ;
    @Schema(description = "Notation of code list")
    public String listeCodeNotation ;
    @Schema(description = "Order")
    public String ordre;
    @Schema(description = "attachement")
    public String attachement;
    @Schema(description = "required")
    public String obligatoire;*/

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
/*
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

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public String getConceptUri() {
        return conceptUri;
    }

    public void setConceptUri(String conceptUri) {
        this.conceptUri = conceptUri;
    }

    public String getConceptId() {
        return conceptId;
    }

    public void setConceptId(String conceptId) {
        this.conceptId = conceptId;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public String getListeCodeUri() {
        return listeCodeUri;
    }

    public void setListeCodeUri(String listeCodeUri) {
        this.listeCodeUri = listeCodeUri;
    }

    public String getListeCodeNotation() {
        return listeCodeNotation;
    }

    public void setListeCodeNotation(String listeCodeNotation) {
        this.listeCodeNotation = listeCodeNotation;
    }

    public String getOrdre() {
        return ordre;
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public String getAttachement() {
        return attachement;
    }

    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }

    public String getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(String obligatoire) {
        this.obligatoire = obligatoire;
    }
    */

}
