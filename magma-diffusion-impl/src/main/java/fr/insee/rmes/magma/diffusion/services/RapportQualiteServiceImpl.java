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

import static fr.insee.rmes.magma.diffusion.utils.LocalisedLabelUtils.createLangueContenu;
import static fr.insee.rmes.magma.diffusion.utils.LocalisedLabelUtils.createListLangueContenu;

@Service
public class RapportQualiteServiceImpl implements RapportQualiteService {

    @Override
    public RapportQualite transformDTOenRapportQualite(RapportQualiteDTO rapportQualiteDTO, RequestProcessor requestProcessor) {
        RapportQualite rapportQualite = new RapportQualite();
        rapportQualite.setId(rapportQualiteDTO.id());
        rapportQualite.setUri(URI.create(rapportQualiteDTO.uri()));
        if (rapportQualiteDTO.labelLg1() != null && rapportQualiteDTO.labelLg2() != null) {
            List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(rapportQualiteDTO.labelLg1(), "fr"), createLangueContenu(rapportQualiteDTO.labelLg2(), "en"));
            rapportQualite.setLabel(label);
        }
        if (rapportQualiteDTO.labelLg1() != null && rapportQualiteDTO.labelLg2() == null) {
            List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(rapportQualiteDTO.labelLg1(), "fr"), createLangueContenu("", "en"));
            rapportQualite.setLabel(label);
        }
        rapportQualite.setRubriques(null);

        if (rapportQualiteDTO.rubriqueDTOList() != null) {

            for (RubriqueDTO rubDTO : rapportQualiteDTO.rubriqueDTOList()) {
                Rubrique rubrique = transformRubrique(rubDTO, rapportQualite, requestProcessor);
                if (rubrique != null) { //rubric can be null : case of a CODE_LIST rubric with several codes and return null for addCodeList when rubric has been yet added with another code (it's the case when maxOccurs not null and rubricExist is true)
                    rapportQualite.addRubriquesItem(rubrique);
                }
            }
        }

            return rapportQualite;
    }

    private Rubrique transformRubrique(RubriqueDTO rubriqueDTO, RapportQualite rapportQualite, RequestProcessor requestProcessor) {

        Rubrique rubrique = createRubrique(rubriqueDTO);

        if (rubriqueDTO.titreLg1() != null && rubriqueDTO.titreLg2() != null) {
            List<LocalisedLabel> titre = createListLangueContenu(createLangueContenu(rubriqueDTO.titreLg1(), "fr"), createLangueContenu(rubriqueDTO.titreLg2(), "en"));
            rubrique.setTitre(titre);
        }

        switch (rubriqueDTO.type()) {
            case "DATE":
                rubrique.setDate(rubriqueDTO.valeurSimple());
                break;
            case "CODE_LIST":
                rubrique = addCodeList(rubriqueDTO, rubrique, rapportQualite);
                break;
            case "RICH_TEXT":
                addRichText(rubriqueDTO, rubrique, rapportQualite, requestProcessor);
                break;
            case "TEXT":
                List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(rubriqueDTO.labelLg1(), "fr"), createLangueContenu(rubriqueDTO.labelLg2(), "en"));
                rubrique.setLabel(label);
                break;
            case "GEOGRAPHY":
                rubrique.setTerritoire(createUriLabel(rubriqueDTO, rubriqueDTO.geoUri()));
                break;
            case "ORGANIZATION":
                rubrique.setOrganisme(createUriLabel(rubriqueDTO, rubriqueDTO.organisationUri()));
                break;
            default:
                break;
        }

        return rubrique;

    }

    private static Rubrique createRubrique(RubriqueDTO rubriqueDTO) {
        Rubrique rubrique = new Rubrique();
        rubrique.setId(rubriqueDTO.id());
        rubrique.setUri(rubriqueDTO.uri());
        rubrique.setIdParent(rubriqueDTO.idParent());
        rubrique.setType(rubriqueDTO.type());
        rubrique.setLabel(null);//valued only if exists
        rubrique.setContenus(null);//valued only if exists
        rubrique.setCodes(null);//valued only if exists
        return rubrique;
    }


    private IdUriLabel createUriLabel(RubriqueDTO rubriqueDTO, String uri) {
        IdUriLabel rubriqueWithIdUriLabel = new IdUriLabel();
        rubriqueWithIdUriLabel.setId(rubriqueDTO.valeurSimple());
        rubriqueWithIdUriLabel.setUri(URI.create(uri));

        if (rubriqueDTO.labelObjLg1() != null && rubriqueDTO.labelObjLg2() != null) {
            List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(rubriqueDTO.labelObjLg1(), "fr"), createLangueContenu(rubriqueDTO.labelObjLg2(), "en"));
            rubriqueWithIdUriLabel.setLabel(label);
        }
        if (rubriqueDTO.labelObjLg1() != null && rubriqueDTO.labelObjLg2() == null) {
            LocalisedLabel labelLg1 = createLangueContenu(rubriqueDTO.labelObjLg1(), "fr");
            List<LocalisedLabel> label = new ArrayList<>();
            label.add(labelLg1);
            rubriqueWithIdUriLabel.setLabel(label);
        }
        return rubriqueWithIdUriLabel;

    }

    private void addRichText(RubriqueDTO rubriqueDTO, Rubrique rubrique, RapportQualite rapportQualite, RequestProcessor requestProcessor) {
        Contenu contenuLg1 = new Contenu();
        contenuLg1.setDocuments(null);// will be valued only if a document exists
        if (StringUtils.isNotEmpty(rubriqueDTO.labelLg1())) {
            contenuLg1.setTexte(rubriqueDTO.labelLg1());
        } else {
            contenuLg1.setTexte("");
        }
        contenuLg1.setLangue("fr");
        if (Boolean.TRUE.equals(rubriqueDTO.hasDocLg1())) {
            contenuLg1.addDocumentsItem(findDocument(requestProcessor, rapportQualite.getId(), rubriqueDTO.id(), "fr"));
        }
        rubrique.addContenusItem(contenuLg1);

        if (rubriqueDTO.isDocLg2NotEmpty() && (StringUtils.isNotEmpty(rubriqueDTO.labelLg2())||rubriqueDTO.hasDocLg2())){
            Contenu contenuLg2 = new Contenu();
            contenuLg2.setDocuments(null);// will be valued only if a document exists
            if (StringUtils.isNotEmpty(rubriqueDTO.labelLg2())) {
                contenuLg2.setTexte(rubriqueDTO.labelLg2());
            } else {
                contenuLg2.setTexte("");
            }
            contenuLg2.setLangue("en");
            if (Boolean.TRUE.equals(rubriqueDTO.hasDocLg2())) {
                contenuLg2.addDocumentsItem(findDocument(requestProcessor,rapportQualite.getId(), rubriqueDTO.id(), "en"));
            }
            rubrique.addContenusItem(contenuLg2);
        }

    }
    
    private static Document findDocument(RequestProcessor requestProcessor, String rapportQualiteId, String rubriqueDTOId, String lang) {
        DocumentDTO documentDTO = requestProcessor.queryToFindDocuments()
                .with(new OperationsDocumentsRequestParametizer(rapportQualiteId, rubriqueDTOId, lang))
                .executeQuery()
                .singleResult(DocumentDTO.class)
                .result();
        Document document = new Document();
        if (documentDTO.labelLg1() != null && documentDTO.labelLg2() != null) {
            List<LocalisedLabel> label = createListLangueContenu(createLangueContenu(documentDTO.labelLg1(), "fr"), createLangueContenu(documentDTO.labelLg2(), "en"));
            document.label(label);
        }
        if (documentDTO.labelLg1() != null && documentDTO.labelLg2() == null) {
            LocalisedLabel labelsLg1 = createLangueContenu(documentDTO.labelLg1(), "fr");
            List<LocalisedLabel> label = createListLangueContenu(labelsLg1, null);
            document.label(label);
        }

        document.setDateMiseAJour(documentDTO.dateMiseAJour());
        document.setLangue(documentDTO.langue());
        document.setUrl(documentDTO.url());
        return document;
    }

    private Rubrique addCodeList (RubriqueDTO rubriqueDTO, Rubrique rubrique, RapportQualite rapportQualite) {
        IdUriLabel rubriqueCodeList = new IdUriLabel();
        rubriqueCodeList.setId(rubriqueDTO.valeurSimple());
        rubriqueCodeList.setUri(URI.create(rubriqueDTO.codeUri()));
        if (rubriqueDTO.labelObjLg1() != null) {
             LocalisedLabel labelLg1 = createLangueContenu(rubriqueDTO.labelObjLg1(), "fr");
            LocalisedLabel labelLg2 = rubriqueDTO.labelObjLg2() != null ? createLangueContenu(rubriqueDTO.labelObjLg2(), "en") : null;

            rubriqueCodeList.setLabel(createListLangueContenu(labelLg1, labelLg2));
        }

        if (rapportQualite.getRubriques() != null) {//is null if the first rubric is a CODE_LIST type rubric

            boolean rubricExist = rapportQualite.getRubriques().stream()
                    .filter(Objects::nonNull) // We keep only not null rubrics, otherwise NullPointer Exception when r.getId()
                    .anyMatch(r -> r.getId().equals(rubriqueDTO.id()));

            if (rubriqueDTO.maxOccurs() != null && rubricExist) {
                Rubrique rubriqueExistante = rapportQualite.getRubriques().stream()
                        .filter(r -> r.getId().equals(rubriqueDTO.id()))
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
