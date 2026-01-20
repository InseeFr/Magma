package fr.insee.rmes.magma.diffusion.services;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.*;
import fr.insee.rmes.magma.diffusion.queries.parameters.OperationsDocumentsRequestParametizer;
import fr.insee.rmes.magma.diffusion.utils.DocumentDTO;
import fr.insee.rmes.magma.diffusion.utils.RapportQualiteDTO;
import fr.insee.rmes.magma.diffusion.utils.RubriqueDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static fr.insee.rmes.magma.diffusion.utils.LocalisedLabelUtils.createLangueContenu;
import static fr.insee.rmes.magma.diffusion.utils.LocalisedLabelUtils.createListLangueContenu;

@Service
public class RapportQualiteServiceImpl implements RapportQualiteService {

    @Override
    public RapportQualite transformDTOenRapportQualite(RapportQualiteDTO rapportQualiteDTO, RequestProcessor requestProcessor) {
        RapportQualite rapportQualite = new RapportQualite();
        rapportQualite.setId(rapportQualiteDTO.getId());
        rapportQualite.setUri(URI.create(rapportQualiteDTO.getUri()));
        if (rapportQualiteDTO.getLabelLg1() != null && rapportQualiteDTO.getLabelLg2() != null) {
            List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(rapportQualiteDTO.getLabelLg1(), "fr"), createLangueContenu(rapportQualiteDTO.getLabelLg2(), "en"));
            rapportQualite.setLabel(label);
        }
        if (rapportQualiteDTO.getLabelLg1() != null && rapportQualiteDTO.getLabelLg2() == null) {
            List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(rapportQualiteDTO.getLabelLg1(), "fr"), createLangueContenu("", "en"));
            rapportQualite.setLabel(label);
        }
        rapportQualite.setRubriques(null);

        if (rapportQualiteDTO.getRubriqueDTOList() != null) {

            for (RubriqueDTO rubDTO : rapportQualiteDTO.getRubriqueDTOList()) {
                Rubrique rubrique = transformRubrique(rubDTO, rapportQualite, requestProcessor);
                if (rubrique != null) { //rubric can be null : case of a CODE_LIST rubric with several codes and return null for addCodeList when rubric has been yet added with another code (it's the case when maxOccurs not null and rubricExist is true)
                    rapportQualite.addRubriquesItem(rubrique);
                }
            }
        }

            return rapportQualite;
    }

    private Rubrique transformRubrique(RubriqueDTO rubriqueDTO, RapportQualite rapportQualite, RequestProcessor requestProcessor) {

        Rubrique rubrique = new Rubrique();
        rubrique.setId(rubriqueDTO.getId());
        rubrique.setUri(rubriqueDTO.getUri());
        rubrique.setIdParent(rubriqueDTO.getIdParent());
        rubrique.setType(rubriqueDTO.getType());
        rubrique.setLabel(null);//valued later only if exists
        rubrique.setContenus(null);//valued later only if exists
        rubrique.setCodes(null);//valued later only if exists

        if (rubriqueDTO.getTitreLg1() != null && rubriqueDTO.getTitreLg2() != null) {
            List<LocalisedLabel> titre = createListLangueContenu(createLangueContenu(rubriqueDTO.getTitreLg1(), "fr"), createLangueContenu(rubriqueDTO.getTitreLg2(), "en"));
            rubrique.setTitre(titre);
        }

        switch (rubriqueDTO.getType()) {
            case "DATE":
                rubrique.setDate(rubriqueDTO.getValeurSimple());
                break;
            case "CODE_LIST":
                rubrique = addCodeList(rubriqueDTO, rubrique, rapportQualite);
                break;
            case "RICH_TEXT":
                addRichText(rubriqueDTO, rubrique, rapportQualite, requestProcessor);
                break;
            case "TEXT":
                List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(rubriqueDTO.getLabelLg1(), "fr"), createLangueContenu(rubriqueDTO.getLabelLg2(), "en"));
                rubrique.setLabel(label);
                break;
            case "GEOGRAPHY":
                rubrique.setTerritoire(createIdUriLabel(rubriqueDTO, rubriqueDTO.getGeoUri()));
                break;
            case "ORGANIZATION":
                rubrique.setOrganisme(createIdUriLabel(rubriqueDTO, rubriqueDTO.getOrganisationUri()));
                break;
            default:
                break;
        }

        return rubrique;

    }


    private IdUriLabel createIdUriLabel(RubriqueDTO rubriqueDTO, String uri) {
        IdUriLabel rubriqueWithIdUriLabel = new IdUriLabel();
        rubriqueWithIdUriLabel.setId(rubriqueDTO.getValeurSimple());
        rubriqueWithIdUriLabel.setUri(URI.create(uri));

        if (rubriqueDTO.getLabelObjLg1() != null && rubriqueDTO.getLabelObjLg2() != null) {
            List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(rubriqueDTO.getLabelObjLg1(), "fr"), createLangueContenu(rubriqueDTO.getLabelObjLg2(), "en"));
            rubriqueWithIdUriLabel.setLabel(label);
        }
        if (rubriqueDTO.getLabelObjLg1() != null && rubriqueDTO.getLabelObjLg2() == null) {
            LocalisedLabel labelLg1 = createLangueContenu(rubriqueDTO.getLabelObjLg1(), "fr");
            List<LocalisedLabel> label = new ArrayList<>();
            label.add(labelLg1);
            rubriqueWithIdUriLabel.setLabel(label);
        }
        return rubriqueWithIdUriLabel;

    }

    private Rubrique addRichText(RubriqueDTO rubriqueDTO, Rubrique rubrique, RapportQualite rapportQualite, RequestProcessor requestProcessor) {
        Contenu contenuLg1 = new Contenu();
        contenuLg1.setDocuments(null);// will be valued only if a document exists
        if (StringUtils.isNotEmpty(rubriqueDTO.getLabelLg1())) {
            contenuLg1.setTexte(rubriqueDTO.getLabelLg1());
        } else {
            contenuLg1.setTexte("");
        }
        contenuLg1.setLangue("fr");
        if (rubriqueDTO.isHasDocLg1()) {
            List<DocumentDTO> rubriqueDocuments = requestProcessor.queryToFindDocuments()
                    .with(new OperationsDocumentsRequestParametizer(rapportQualite.getId(), rubriqueDTO.getId(),"fr"))
                    .executeQuery()
                    .listResult(DocumentDTO.class)
                    .result();
            for (DocumentDTO documentDTO : rubriqueDocuments) {
                Document document = new Document();
                if (documentDTO.getLabelLg1() != null && documentDTO.getLabelLg2() != null){
                    List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(documentDTO.getLabelLg1(), "fr"), createLangueContenu(documentDTO.getLabelLg2(), "en"));
                    document.label(label);
                }
                if (documentDTO.getLabelLg1() != null && documentDTO.getLabelLg2() == null) {
                    LocalisedLabel labelLg1 = createLangueContenu(documentDTO.getLabelLg1(), "fr");
                    List<LocalisedLabel> label = createListLangueContenu(labelLg1,null);
                    document.label(label);
                }

                document.setDateMiseAJour(documentDTO.getDateMiseAJour());
                document.setLangue(documentDTO.getLangue());
                document.setUrl(documentDTO.getUrl());
                contenuLg1.addDocumentsItem(document);
            }

        }
        rubrique.addContenusItem(contenuLg1);

        if (StringUtils.isNotEmpty(rubriqueDTO.getLabelLg2())||rubriqueDTO.isHasDocLg2()){
            Contenu contenuLg2 = new Contenu();
            contenuLg2.setDocuments(null);// will be valued only if a document exists
            if (StringUtils.isNotEmpty(rubriqueDTO.getLabelLg2())) {
                contenuLg2.setTexte(rubriqueDTO.getLabelLg2());
            } else {
                contenuLg2.setTexte("");
            }
            contenuLg2.setLangue("en");

            if (rubriqueDTO.isHasDocLg2()) {
                List<DocumentDTO> rubriqueDocuments = requestProcessor.queryToFindDocuments()
                        .with(new OperationsDocumentsRequestParametizer(rapportQualite.getId(), rubriqueDTO.getId(),"en"))
                        .executeQuery()
                        .listResult(DocumentDTO.class)
                        .result();
                for (DocumentDTO documentDTO : rubriqueDocuments) {
                    Document document = new Document();
                    if (documentDTO.getLabelLg1() != null && documentDTO.getLabelLg2() != null){
                        List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(documentDTO.getLabelLg1(), "fr"), createLangueContenu(documentDTO.getLabelLg2(), "en"));
                        document.label(label);
                    }
                    if (documentDTO.getLabelLg1() != null && documentDTO.getLabelLg2() == null) {
                        LocalisedLabel labelFr = createLangueContenu(documentDTO.getLabelLg1(), "fr");
                        List<LocalisedLabel> label = createListLangueContenu(labelFr,null);
                        document.label(label);
                    }

                    document.setDateMiseAJour(documentDTO.getDateMiseAJour());
                    document.setLangue(documentDTO.getLangue());
                    document.setUrl(documentDTO.getUrl());
                    contenuLg2.addDocumentsItem(document);
                }
            }
            rubrique.addContenusItem(contenuLg2);
        }
        return rubrique;
    }

    private Rubrique addCodeList (RubriqueDTO rubriqueDTO, Rubrique rubrique, RapportQualite rapportQualite) {
        IdUriLabel rubriqueCodeList = new IdUriLabel();
        rubriqueCodeList.setId(rubriqueDTO.getValeurSimple());
        rubriqueCodeList.setUri(URI.create(rubriqueDTO.getCodeUri()));
        if (rubriqueDTO.getLabelObjLg1() != null && rubriqueDTO.getLabelObjLg2() != null) {
            List<LocalisedLabel> labelCodeList = createListLangueContenu(createLangueContenu(rubriqueDTO.getLabelObjLg1(), "fr"), createLangueContenu(rubriqueDTO.getLabelObjLg2(), "en"));
            rubriqueCodeList.setLabel(labelCodeList);
        }
        if (rubriqueDTO.getLabelObjLg1() != null && rubriqueDTO.getLabelObjLg2() == null) {
            LocalisedLabel labelCodeListLg1 = createLangueContenu(rubriqueDTO.getLabelObjLg1(), "fr");
            List<LocalisedLabel> label = new ArrayList<>();
            label.add(labelCodeListLg1);
            rubriqueCodeList.setLabel(label);
        }

        if (rapportQualite.getRubriques() != null) {//is null if the first rubrique is a CODE_LIST type rubrique

            boolean rubricExist = rapportQualite.getRubriques().stream()
                    .filter(Objects::nonNull) // We keep only not null rubrics, otherwise NullPointer Exception when r.getId()
                    .anyMatch(r -> r.getId().equals(rubriqueDTO.getId()));

            if (rubriqueDTO.getMaxOccurs() != null && rubricExist) {
                Rubrique rubriqueExistante = rapportQualite.getRubriques().stream()
                        .filter(r -> r.getId().equals(rubriqueDTO.getId()))
                        .findFirst()
                        .orElseThrow();
                rubriqueExistante.addCodesItem(rubriqueCodeList);//add rubric in RapportQualite
                return null;
            }
        }
        rubrique.addCodesItem(rubriqueCodeList);
        return rubrique;
    }

}
