package fr.insee.rmes.dto.codeList;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class CodeList {

    @Schema(description="Code list's notation")
    public String notation;

    @Schema(description = "date of update")
    public String dateMiseAJour;

    @Schema(description = "statut of validation")
    public String statutValidation;

    @Schema(description = "order")
    public String ordre;
/*
    @Schema(description="Uri")
    public String Uri;

    @Schema(description = "Id")
    public String Id;

    @Schema (description = "prefLabelLg1")
    public String prefLabelLg1;

    @Schema (description = "prefLabelLg2")
    public String prefLabelLg2;

    @Schema(description = "date of creation")
    public String dateCreation;

    @Schema(description = "End of validity")
    public String dateFinValidite;

    @Schema(description =  "codes")
    public String codes;

    @Schema(description= "label")
    public String label;

    @Schema(description= "langue")
    public String langue;

    @Schema(description= "contenu")
    public String contenu;

    @Schema(description = "parents")
    public String parents;

    @Schema(description = "enfants")
    public String enfants;*/

        public CodeList(String notation, String dateMiseAJour, String statutValidation, String ordre) {
        this.notation = notation;
        this.dateMiseAJour = dateMiseAJour;
        this.statutValidation = statutValidation;
        this.ordre = ordre;
    }

    public CodeList() {};

    public CodeList(String notation, String ordre) {
        this.notation = notation;
        this.ordre = ordre;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
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

    public String getOrdre() {
        return ordre;
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }
/*
    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getEnfants() {
        return enfants;
    }

    public void setEnfants(String enfants) {
        this.enfants = enfants;
    } */
}
