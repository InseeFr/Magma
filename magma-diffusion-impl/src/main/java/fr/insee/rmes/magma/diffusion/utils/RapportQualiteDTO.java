package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j

public class RapportQualiteDTO {

    private String id;
    private String uri;
    private String labelLg1;
    private String labelLg2;

    public String getValeurSimple() {
        return valeurSimple;
    }

    public void setValeurSimple(String valeurSimple) {
        this.valeurSimple = valeurSimple;
    }

    private String valeurSimple;
    @Getter
    private List<Rubrique> rubriques;


    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getLabelLg1() {
        return labelLg1;
    }

    public String getLabelLg2() {
        return labelLg2;
    }

    public List<Rubrique> getRubriques() {
        return rubriques;
    }
    public void setRubriques(List<Rubrique> rubriques) {
        this.rubriques = rubriques;
    }


    public RapportQualite transformDTOenRapportQualite(){
        RapportQualite rapportQualite = new RapportQualite();
        rapportQualite.setId(this.id);
        rapportQualite.setUri(URI.create(this.uri));
        if  (this.labelLg1 != null && this.labelLg2 != null) {
            List<ConceptIntituleInner> label = createListLangueContenu(createLangueContenu(labelLg1,"fr"),createLangueContenu(labelLg2,"en"));
            rapportQualite.setLabel(label);
        }
        if  (this.labelLg1 != null && this.labelLg2 == null) {
            List<ConceptIntituleInner> label = createListLangueContenu(createLangueContenu(labelLg1,"fr"),createLangueContenu("","en"));
            rapportQualite.setLabel(label);
        }
        if (this.rubriques != null) {
            List<Rubrique> rubriquesList = new ArrayList<>();
            for (Rubrique r : this.rubriques) {
                Rubrique inner = new Rubrique();
                inner.setId(r.getId());
                inner.setUri(r.getUri());
                inner.setIdParent(r.getIdParent());
                inner.setType(r.getType());
                switch (r.getType()){
                    case "DATE":

                }
            }
        };
        return rapportQualite;
    }

    public List<ConceptIntituleInner> createListLangueContenu(ConceptIntituleInner conceptIntituleInner1, ConceptIntituleInner conceptIntituleInner2) {
        List<ConceptIntituleInner> list = new ArrayList<>();
        list.add(conceptIntituleInner1);
        list.add(conceptIntituleInner2);
        return list;
    }

    public ConceptIntituleInner createLangueContenu(String contenu, String langue) {
        ConceptIntituleInner conceptIntituleInner = new ConceptIntituleInner();
        conceptIntituleInner.setContenu(contenu);
        conceptIntituleInner.setLangue(langue);
        return conceptIntituleInner;
    }

}
