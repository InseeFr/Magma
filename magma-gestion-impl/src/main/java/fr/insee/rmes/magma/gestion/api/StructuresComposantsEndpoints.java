package fr.insee.rmes.magma.gestion.api;

import fr.insee.rmes.magma.gestion.api.requestprocessor.RequestProcessorGestion;
import fr.insee.rmes.magma.gestion.model.*;
import fr.insee.rmes.magma.gestion.queries.parameters.StructureComponentsRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StructuresComposantsEndpoints implements StructuresComposantsApi {
    private final RequestProcessorGestion requestProcessor;

	public StructuresComposantsEndpoints(RequestProcessorGestion requestProcessor) {
		this.requestProcessor = requestProcessor;
	}

	@Override
    public ResponseEntity<List<AllComponent>> getAllComponents(LocalDate dateMiseAJour) {
        return requestProcessor.queryToFindStructuresComponents()
                .with(new StructureComponentsRequestParametizer(dateMiseAJour))
                .executeQuery()
                .listResult(AllComponent.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<AllStructure>> getAllStructures(LocalDate dateMiseAJour) {
        return requestProcessor.queryToFindStructuresComponents()
                .with(new StructureComponentsRequestParametizer(dateMiseAJour))
                .executeQuery()
                .listResult(AllStructure.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<ComponentById> getComponentById(String id, Boolean dateMiseAJour) {
        return requestProcessor.queryToFindComponent()
                .with(new StructureComponentsRequestParametizer(id, dateMiseAJour))
                .executeQuery()
                .singleResult(ComponentById.class)
                .toResponseEntity();
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
        return requestProcessor.queryToFindStructure()
                .with(new StructureComponentsRequestParametizer(id, dateMiseAJour))
                .executeQuery()
                .singleResult(StructureById.class)
                .toResponseEntity();
    }
}
