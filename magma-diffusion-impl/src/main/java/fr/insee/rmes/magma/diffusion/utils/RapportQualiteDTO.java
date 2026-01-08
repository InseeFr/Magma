package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.*;
import fr.insee.rmes.magma.diffusion.queries.parameters.OperationsDocumentsRequestParametizer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static fr.insee.rmes.magma.diffusion.utils.EndpointsUtils.*;

@Slf4j

public class RapportQualiteDTO {


    private RequestProcessor requestProcessor;
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

    public RapportQualiteDTO(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
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

    public RapportQualiteDTO(RequestProcessor requestProcessor, String id, String uri, String labelLg1, String labelLg2, String idCible, String cible, String labelCibleLg1, String labelCibleLg2, List<RubriqueDTO> rubriqueDTOList) {
        this.requestProcessor = requestProcessor;
        this.id = id;
        this.uri = uri;
        this.labelLg1 = labelLg1;
        this.labelLg2 = labelLg2;
        this.idCible = idCible;
        this.cible = cible;
        this.labelCibleLg1 = labelCibleLg1;
        this.labelCibleLg2 = labelCibleLg2;
        this.rubriqueDTOList = rubriqueDTOList;
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

    public RapportQualite transformDTOenRapportQualite(RequestProcessor requestProcessor) {
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
        rapportQualite.setRubriques(null);

        if (this.rubriqueDTOList != null) {
            for (RubriqueDTO rub : this.rubriqueDTOList) {
                Rubrique rubrique = new Rubrique();
                rubrique.setId(rub.getId());
                rubrique.setUri(rub.getUri());
                rubrique.setIdParent(rub.getIdParent());
                rubrique.setType(rub.getType());
                rubrique.setLabel(null);//valorised later only if necessary
                rubrique.setContenus(null);//valorised later only if necessary
                rubrique.setCodes(null);//valorised later only if necessary
                if (rub.getTitreLg1() != null && rub.getTitreLg2() != null) {
                    List<LocalisedLabel> titre = createListLangueContenu(createLangueContenu(rub.getTitreLg1(), "fr"), createLangueContenu(rub.getTitreLg2(), "en"));
                    rubrique.setTitre(titre);
                }

                switch (rub.getType()) {
                    case "DATE":
                        rubrique.setDate(rub.getValeurSimple());
                        break;
                    case "CODE_LIST":
                        RubriqueTerritoireCodeListOrganisme rubriqueCodeList = new RubriqueTerritoireCodeListOrganisme();
                        rubriqueCodeList.setId(rub.getValeurSimple());
                        rubriqueCodeList.setUri(URI.create(rub.getCodeUri()));

                        if (rub.getLabelObjLg1() != null && rub.getLabelObjLg2() != null) {
                            List<LocalisedLabel> labelCodeList = createListLangueContenu(createLangueContenu(rub.getLabelObjLg1(), "fr"), createLangueContenu(rub.getLabelObjLg2(), "en"));
                            rubriqueCodeList.setLabel(labelCodeList);
                        }
                        if (rub.getLabelObjLg1() != null && rub.getLabelObjLg2() == null) {
                            LocalisedLabel labelCodeListLg1 = createLangueContenu(rub.getLabelObjLg1(), "fr");
                            List<LocalisedLabel> labelTerritoire = new ArrayList<>();
                            labelTerritoire.add(labelCodeListLg1);
                            rubriqueCodeList.setLabel(labelTerritoire);
                        }
                        boolean rubricExist = rapportQualite.getRubriques().stream()
                                .anyMatch(r ->  r.getId().equals(rub.getId()));

                        if (rub.getMaxOccurs() != null && rubricExist){
                            Rubrique rubriqueExistante = rapportQualite.getRubriques().stream()
                                    .filter(r -> r.getId().equals(rub.getId()))
                                    .findFirst()
                                    .orElse(null);
                            assert rubriqueExistante != null;
                            rubriqueExistante.addCodesItem(rubriqueCodeList);//add rubric in RapportQualite
                            
                        } else {
                            rubrique.addCodesItem(rubriqueCodeList);
                        }
                        break;
                    case "RICH_TEXT":
                        Contenu contenuLg1 = new Contenu();
                        contenuLg1.setTexte(rub.getLabelLg1());
                        contenuLg1.setLangue("fr");
                        if (rub.isHasDocLg1()) {
                            List<DocumentDTO> rubriqueDocuments = requestProcessor.queryToFindDocuments()
                                    .with(new OperationsDocumentsRequestParametizer(rapportQualite.getId(), rub.getId(),"fr"))
                                    .executeQuery()
                                    .listResult(DocumentDTO.class)
                                    .result();
                            for (DocumentDTO documentDTO : rubriqueDocuments) {
                                Document document = new Document();
                                List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(documentDTO.getLabelLg1(), "fr"), createLangueContenu(documentDTO.getLabelLg2(), "en"));
                                document.label(label);
                                document.setDateMiseAJour(documentDTO.getDateMiseAJour());
                                document.setLangue(documentDTO.getLangue());
                                document.setUrl(documentDTO.getUrl());
                                contenuLg1.addDocumentsItem(document);
                            }
                        }
                        rubrique.addContenusItem(contenuLg1);

                        if (StringUtils.isNotEmpty(rub.getLabelLg2())||rub.isHasDocLg2()){
                            Contenu contenuLg2 = new Contenu();
                            contenuLg2.setTexte(rub.getLabelLg2());
                            contenuLg2.setLangue("en");
                            if (rub.isHasDocLg2()) {
                                List<DocumentDTO> rubriqueDocuments = requestProcessor.queryToFindDocuments()
                                        .with(new OperationsDocumentsRequestParametizer(rapportQualite.getId(), rub.getId(),"en"))
                                        .executeQuery()
                                        .listResult(DocumentDTO.class)
                                        .result();
                                for (DocumentDTO documentDTO : rubriqueDocuments) {
                                    Document document = new Document();
                                    List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(documentDTO.getLabelLg1(), "fr"), createLangueContenu(documentDTO.getLabelLg2(), "en"));
                                    document.label(label);
                                    document.setDateMiseAJour(documentDTO.getDateMiseAJour());
                                    document.setLangue(documentDTO.getLangue());
                                    document.setUrl(documentDTO.getUrl());
                                    contenuLg2.addDocumentsItem(document);
                                }
                            }
                            rubrique.addContenusItem(contenuLg2);
                        }
                        break;
                    case "TEXT":
                        List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(rub.getLabelLg1(), "fr"), createLangueContenu(rub.getLabelLg2(), "en"));
                        rubrique.setLabel(label);
                        break;
                    case "GEOGRAPHY":
                        RubriqueTerritoireCodeListOrganisme rubriqueTerritoire = new RubriqueTerritoireCodeListOrganisme();
                        rubriqueTerritoire.setId(rub.getValeurSimple());
                        rubriqueTerritoire.setUri(URI.create(rub.getGeoUri()));

                        if (rub.getLabelObjLg1() != null && rub.getLabelObjLg2() != null) {
                            List<LocalisedLabel> labelTerritoire = createListLangueContenu(createLangueContenu(rub.getLabelObjLg1(), "fr"), createLangueContenu(rub.getLabelObjLg2(), "en"));
                            rubriqueTerritoire.setLabel(labelTerritoire);
                        }
                        if (rub.getLabelObjLg1() != null && rub.getLabelObjLg2() == null) {
                            LocalisedLabel labelTerritoireLg1 = createLangueContenu(rub.getLabelObjLg1(), "fr");
                            List<LocalisedLabel> labelTerritoire = new ArrayList<>();
                            labelTerritoire.add(labelTerritoireLg1);
                            rubriqueTerritoire.setLabel(labelTerritoire);
                        }
                        rubrique.setTerritoire(rubriqueTerritoire);
                        break;
                    case "ORGANIZATION":
                        RubriqueTerritoireCodeListOrganisme rubriqueOrganisme = new RubriqueTerritoireCodeListOrganisme();
                        rubriqueOrganisme.setId(rub.getValeurSimple());
                        rubriqueOrganisme.setUri(URI.create(rub.getGeoUri()));

                        if (rub.getLabelObjLg1() != null && rub.getLabelObjLg2() != null) {
                            List<LocalisedLabel> labelOrganisme = createListLangueContenu(createLangueContenu(rub.getLabelObjLg1(), "fr"), createLangueContenu(rub.getLabelObjLg2(), "en"));
                            rubriqueOrganisme.setLabel(labelOrganisme);
                        }
                        if (rub.getLabelObjLg1() != null && rub.getLabelObjLg2() == null) {
                            LocalisedLabel labelOrganismeLg1 = createLangueContenu(rub.getLabelObjLg1(), "fr");
                            List<LocalisedLabel> labelOrganisme = new ArrayList<>();
                            labelOrganisme.add(labelOrganismeLg1);
                            rubriqueOrganisme.setLabel(labelOrganisme);
                        }
                        rubrique.setTerritoire(rubriqueOrganisme);
                        break;
                    default:
                        break;
                }
                rapportQualite.addRubriquesItem(rubrique);
            }
        }

        ArrayList<Rubrique> rubriques = rapportQualite.getRubriques() == null ? new ArrayList<>() : new ArrayList<>(rapportQualite.getRubriques());

        rapportQualite.setRubriques(rubriques);



        return rapportQualite;
    }
}