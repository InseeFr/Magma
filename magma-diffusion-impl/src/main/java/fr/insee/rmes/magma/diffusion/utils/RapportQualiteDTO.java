package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.LangueContenu;
import fr.insee.rmes.magma.diffusion.model.LocalisedLabel;
import fr.insee.rmes.magma.diffusion.model.RapportQualite;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.List;

import static fr.insee.rmes.magma.diffusion.utils.EndpointsUtils.createLangueContenu;
import static fr.insee.rmes.magma.diffusion.utils.EndpointsUtils.createListLangueContenu;

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

    public RapportQualite transformDTOenRapportQualite() {
        RapportQualite rapportQualite = new RapportQualite();
        rapportQualite.setId(this.id);
        rapportQualite.setUri(URI.create(this.uri));
        if (this.labelLg1 != null && this.labelLg2 != null) {
            List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(labelLg1, "fr"), createLangueContenu(labelLg2, "en"));
            rapportQualite.setLabel(label);
        }
        if (this.labelLg1 != null && this.labelLg2 == null) {
            List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(labelLg1, "fr"), createLangueContenu("", "en"));
            rapportQualite.setLabel(label);
        }
        if (this.rubriqueDTOList != null) {
            for (RubriqueDTO r : this.rubriqueDTOList) {
                //  private List<@Valid Rubrique> rubriques = new ArrayList<>();
//                Rubrique rubrique = new Rubrique();
//                rubrique.setId(r.getId());
//                rubrique.setUri(URI.create(this.uri));
//                rubrique.setIdParent(r.getIdParent());
//                rubrique.setType(r.getType());
//                if  (r.getTitreLg1() != null && r.getTitreLg2() != null) {
//                    List<Object> titre = createListLangueContenu(createLangueContenu(labelLg1,"fr"),createLangueContenu(labelLg2,"en"));
//                    rapportQualite.setLabel(label);
//                }
//
//                switch (r.getType()){
//                    case "TEXT":
//                        List<ConceptIntituleInner> label = createListLangueContenu(createLangueContenu(labelLg1,"fr"),createLangueContenu("","en"));
//                        rubrique.setLabel(label);
//                }
//            }
//        };
//
//            }
//
            }
        }
        return rapportQualite;
    }
}