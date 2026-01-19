package fr.insee.rmes.magma.diffusion.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j

public class RapportQualiteDTO {


    private String id;
    private String uri;
    private String labelLg1;
    private String labelLg2;
    private String idCible;
    private String cible;
    private String labelCibleLg1;
    private String labelCibleLg2;
    private List<RubriqueDTO> rubriqueDTOList;

    public RapportQualiteDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabelLg1() {
        return labelLg1;
    }

    public void setLabelLg1(String labelLg1) {
        this.labelLg1 = labelLg1;
    }

    public String getLabelLg2() {
        return labelLg2;
    }

    public void setLabelLg2(String labelLg2) {
        this.labelLg2 = labelLg2;
    }

    public String getIdCible() {
        return idCible;
    }

    public void setIdCible(String idCible) {
        this.idCible = idCible;
    }

    public String getCible() {
        return cible;
    }

    public void setCible(String cible) {
        this.cible = cible;
    }

    public String getLabelCibleLg1() {
        return labelCibleLg1;
    }

    public void setLabelCibleLg1(String labelCibleLg1) {
        this.labelCibleLg1 = labelCibleLg1;
    }

    public String getLabelCibleLg2() {
        return labelCibleLg2;
    }

    public void setLabelCibleLg2(String labelCibleLg2) {
        this.labelCibleLg2 = labelCibleLg2;
    }


    public List<RubriqueDTO> getRubriqueDTOList() {
        return rubriqueDTOList;
    }

    public void setRubriqueDTOList(List<RubriqueDTO> rubriqueDTOList) {
        this.rubriqueDTOList = rubriqueDTOList;
    }

}