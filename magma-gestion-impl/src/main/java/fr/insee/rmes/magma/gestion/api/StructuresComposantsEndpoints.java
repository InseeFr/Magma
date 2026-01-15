package fr.insee.rmes.magma.gestion.api;

import fr.insee.rmes.magma.gestion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.gestion.model.*;
import fr.insee.rmes.magma.gestion.queries.parameters.StructureComponentsRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StructuresComposantsEndpoints implements StructuresComposantsApi {
    private final RequestProcessor requestProcessor;

	public StructuresComposantsEndpoints(RequestProcessor requestProcessor) {
		this.requestProcessor = requestProcessor;
	}

	@Override
    public ResponseEntity<List<AllComponent>> getAllComponents(LocalDate dateMiseAJour) {
        return requestProcessor.queryForFindStructuresComponents()
                .with(new StructureComponentsRequestParametizer(dateMiseAJour, AllComponent.class))
                .executeQuery()
                .listResult(AllComponent.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<AllStructure>> getAllStructures(LocalDate dateMiseAJour) {
        return StructuresComposantsApi.super.getAllStructures(dateMiseAJour);
    }

    @Override
    public ResponseEntity<ComponentById> getComponentById(String id, Boolean dateMiseAJour) {
        return StructuresComposantsApi.super.getComponentById(id, dateMiseAJour);
    }

    @Override
    public ResponseEntity<List<StructureSliceKeys>> getSliceKeys(String id) {
        return StructuresComposantsApi.super.getSliceKeys(id);
    }

    @Override
    public ResponseEntity<StructureById> getStructure(String id, Boolean dateMiseAJour) {
        return StructuresComposantsApi.super.getStructure(id, dateMiseAJour);
    }
}
