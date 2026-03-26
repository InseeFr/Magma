package fr.insee.rmes.magma.gestion.api;

import fr.insee.rmes.magma.gestion.api.requestprocessor.RequestProcessorGestion;
import fr.insee.rmes.magma.gestion.model.*;
import fr.insee.rmes.magma.gestion.queries.parameters.StructureComponentsRequestParametizer;
import fr.insee.rmes.magma.gestion.services.StructuresComposantsService;
import fr.insee.rmes.magma.gestion.utils.ComponentByIdDTO;
import fr.insee.rmes.magma.gestion.utils.StructureComponentDTO;
import fr.insee.rmes.magma.gestion.utils.StructureDTO;
import fr.insee.rmes.magma.utils.EndpointsUtils;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StructuresComposantsEndpoints implements StructuresComposantsApi {
    private final RequestProcessorGestion requestProcessor;
    private final StructuresComposantsService structuresComposantsService;

	public StructuresComposantsEndpoints(RequestProcessorGestion requestProcessor, StructuresComposantsService structuresComposantsService) {
		this.requestProcessor = requestProcessor;
        this.structuresComposantsService = structuresComposantsService;
    }

	@Override
    public ResponseEntity<List<AllComponent>> getAllComponents(LocalDate dateMiseAJour) {
        if (dateMiseAJour == null){
            dateMiseAJour = LocalDate.parse("");
        }
        return requestProcessor.queryToFindStructuresComponents()
                .with(new StructureComponentsRequestParametizer(dateMiseAJour))
                .executeQuery()
                .listResult(AllComponent.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<AllStructure>> getAllStructures(LocalDate date) {
        if (date == null) {
            return requestProcessor.queryToFindStructuresComponents()
                    .with(new StructureComponentsRequestParametizer())
                    .executeQuery()
                    .listResult(AllStructure.class)
                    .toResponseEntity();
        }
        return requestProcessor.queryToFindAllStructuresByDate()
                .with(new StructureComponentsRequestParametizer(null, date, null))
                .executeQuery()
                .listResult(AllStructure.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<ComponentById> getComponentById(String id, Boolean dateMiseAJour) {
        if (Boolean.TRUE.equals(dateMiseAJour)) {
            //voir si la requête ne retourne pas un objet de type ComponentByIdDTO plutôt
            return requestProcessor.queryToFindComponentDateMAJ()
                    .with(new StructureComponentsRequestParametizer(id, true))
                    .executeQuery()
                    .singleResult(ComponentById.class)
                    .toResponseEntity();
        }

        ComponentByIdDTO componentByIdDTO = requestProcessor.queryToFindComponent()
                .with(new StructureComponentsRequestParametizer(id))
                .executeQuery()
                .singleResult(ComponentByIdDTO.class)
                .result();
        ComponentById componentById = structuresComposantsService.transformComponentDTOToComponentById(componentByIdDTO);
        return EndpointsUtils.toResponseEntity(componentById);

//        return requestProcessor.queryToFindComponent()
//                .with(new StructureComponentsRequestParametizer(id))
//                .executeQuery()
//                .singleResult(ComponentById.class)
//                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<StructureSliceKeys>> getSliceKeys(String id) {
        return requestProcessor.queryToFindStructuresSlicesKeys()
                .with(new StructureComponentsRequestParametizer(id))
                .executeQuery()
                .listResult(StructureSliceKeys.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<StructureById> getStructure(String id, Boolean dateMiseAJour) {
        if (Boolean.TRUE.equals(dateMiseAJour)) {
            return requestProcessor.queryToFindStructureDateMAJ()
                    .with(new StructureComponentsRequestParametizer(id, dateMiseAJour))
                    .executeQuery()
                    .singleResult(StructureById.class)
                    .toResponseEntity();
        }

        StructureDTO structureDTO = requestProcessor.queryToFindStructure()
                .with(new StructureComponentsRequestParametizer(id, dateMiseAJour))
                .executeQuery()
                .singleResult(StructureDTO.class)
                .result();
        StructureById structureById = structuresComposantsService.transformStructureDTOToStructureById(structureDTO, null);
        return EndpointsUtils.toResponseEntity(structureById);

        //quand on traitera les composants :

//        StructureDTO structureDTO = requestProcessor.queryToFindStructure()
//                .with(new StructureComponentsRequestParametizer(id, dateMiseAJour))
//                .executeQuery()
//                .singleResult(StructureDTO.class)
//                .result();
//
//        if (structureDTO != null) {
//            List<StructureComponentDTO> componentDTOList = requestProcessor.queryToFindStructureComponents()
//                    .with(new StructureComponentsRequestParametizer(id))
//                    .executeQuery()
//                    .listResult(StructureComponentDTO.class)
//                    .result();
//            StructureById structureById = structuresComposantsService.transformStructureDTOToStructureById(structureDTO, componentDTOList);
//
//            return EndpointsUtils.toResponseEntity(structureById);
//        }
//        return ResponseEntity.notFound().build();

    }
}
