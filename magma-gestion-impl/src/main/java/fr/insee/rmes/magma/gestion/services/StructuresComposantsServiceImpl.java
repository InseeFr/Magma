package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.gestion.api.requestprocessor.RequestProcessorGestion;
import fr.insee.rmes.magma.gestion.model.*;
import fr.insee.rmes.magma.gestion.queries.parameters.StructureComponentsRequestParametizer;
import fr.insee.rmes.magma.gestion.utils.ComponentByIdDTO;
import fr.insee.rmes.magma.gestion.utils.StructureComponentDTO;
import fr.insee.rmes.magma.gestion.utils.StructureDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static fr.insee.rmes.magma.gestion.utils.LocalisedLabelUtils.createLangueContenu;
import static fr.insee.rmes.magma.gestion.utils.LocalisedLabelUtils.createListLangueContenu;

@Service
public class StructuresComposantsServiceImpl implements StructuresComposantsService {

    private final RequestProcessorGestion requestProcessor;
    public StructuresComposantsServiceImpl(RequestProcessorGestion requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public StructureById transformStructureDTOToStructureById(StructureDTO structureDTO, List<StructureComponentDTO> componentDTOList){
        StructureById structureById = new StructureById();
        structureById.setId(structureDTO.id());
        structureById.setUri(structureDTO.uri());
        structureById.setNotation(structureDTO.notation());
        structureById.setDateCreation(structureDTO.dateCreation());
        structureById.setDateMiseAJour(structureDTO.dateMiseAJour());
        structureById.setStatutValidation(structureDTO.statutValidation());
        if (structureDTO.prefLabelLg1() != null && structureDTO.prefLabelLg2() != null){
            List<StructureByIdMesuresInnerLabelInner> label = createListLangueContenu(createLangueContenu(structureDTO.prefLabelLg1(),"fr"), createLangueContenu(structureDTO.prefLabelLg2(),"en"));
            structureById.setLabel(label);
        }
        if (structureDTO.prefLabelLg1() != null && structureDTO.prefLabelLg2() == null){
            List<StructureByIdMesuresInnerLabelInner> label = createListLangueContenu(createLangueContenu(structureDTO.prefLabelLg1(),"fr"), createLangueContenu("", "en"));
            structureById.setLabel(label);
        }

        //en attendant qu'il y ait des composants en base, on met certains champs à vide
        structureById.setMesures(null);
        structureById.setAttributs(null);
        structureById.setDimensions(null);

//        if (componentDTOList != null){
//            List<StructureByIdMesuresInner> mesures = new ArrayList<>();
//            List<StructureByIdAttributsInner> attributs = new ArrayList<>();
//            List<StructureByIdDimensionsInner> dimensions = new ArrayList<>();
//            for (StructureComponentDTO structureComponentDTO : componentDTOList) {
//                ComponentByIdDTO componentByIdDTO = requestProcessor.queryToFindComponent()
//                        .with(new StructureComponentsRequestParametizer(structureComponentDTO.id()))
//                        .executeQuery()
//                        .singleResult(ComponentByIdDTO.class)
//                        .result();
//                if (componentByIdDTO.id() != null){
//                    List<StructureByIdMesuresInnerLabelInner> label = createListLangueContenu(createLangueContenu(componentByIdDTO.prefLabelLg1(),"fr"), createLangueContenu(componentByIdDTO.prefLabelLg2(),"en"));
//                //à continuer
//                }
//
//                if (structureComponentDTO.id().startsWith("m")){
//                    StructureByIdMesuresInner structureByIdMesuresInner = new StructureByIdMesuresInner();
//                          //etc
//                }
//            }
//        }



        return structureById;


    }

    public ComponentById transformComponentDTOToComponentById(ComponentByIdDTO componentByIdDTO) {
        ComponentById componentById = new ComponentById();
        componentById.setId(componentByIdDTO.id());
        componentById.setUri(componentByIdDTO.uri());
        componentById.setDateCreation(componentByIdDTO.dateCreation());
        componentById.setDateMiseAJour(componentByIdDTO.dateMiseAJour());
        componentById.setStatutValidation(componentByIdDTO.statutValidation());
        if (componentByIdDTO.prefLabelLg1() != null && componentByIdDTO.prefLabelLg2() != null) {
            List<StructureByIdMesuresInnerLabelInner> label = createListLangueContenu(createLangueContenu(componentByIdDTO.prefLabelLg1(), "fr"), createLangueContenu(componentByIdDTO.prefLabelLg2(), "en"));
            componentById.setLabel(label);
        }
        if (componentByIdDTO.prefLabelLg1() != null && componentByIdDTO.prefLabelLg2() == null) {
            List<StructureByIdMesuresInnerLabelInner> label = createListLangueContenu(createLangueContenu(componentByIdDTO.prefLabelLg1(), "fr"), createLangueContenu("", "en"));
            componentById.setLabel(label);
        }
//        if (componentByIdDTO.altLabelLg1() != null && componentByIdDTO.altLabelLg2() != null){
//            List<StructureByIdMesuresInnerLabelInner> nom = createListLangueContenu(createLangueContenu(componentByIdDTO.altLabelLg1(),"fr"), createLangueContenu(componentByIdDTO.altLabelLg2(),"en"));
//            componentById.setLabel(nom);dans StructuresImpl (Magma historique, ligne 272, on crée l'attribut nom pour les components, mais il n'y a pas nom dans openapi-gestion. Je ne comprends pas.
        // il n'y a pas de composants en dev gestion ni dev diffusion ni dans Magma en prod donc je ne sais pas si on attend vraiment una ttribut "nom"
//        }
//        if (componentByIdDTO.prefLabelLg1() != null && componentByIdDTO.prefLabelLg2() == null){
//            List<StructureByIdMesuresInnerLabelInner> label = createListLangueContenu(createLangueContenu(componentByIdDTO.prefLabelLg1(),"fr"), createLangueContenu("", "en"));
//            componentById.setLabel(label);
//        }

        if (componentByIdDTO.uriComponentParentId() != null){

        }
        return componentById;

    }
}
