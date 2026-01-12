package fr.insee.rmes.magma.gestion.api;

import fr.insee.rmes.magma.gestion.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class StructuresComposantsEndpoints implements StructuresComposantsApi {
    private final RequestProcessor requestProcessor;

    @Override
    public ResponseEntity<List<AllComponent>> getAllComponents(String dateMiseAJour) {
        if (date==null) {
            date = LocalDate.now().toString();
        }
        return requestProcessor.queryforFindTerritoire()
                .with(new TerritoireEtoileRequestParametizer(date, Departement.class, "prefecture", true))
                .executeQuery()
                .listResult(TerritoireBaseChefLieu.class)
                .toResponseEntity();
        return StructuresComposantsApi.super.getAllComponents(dateMiseAJour);
    }

    @Override
    public ResponseEntity<List<AllStructure>> getAllStructures(String dateMiseAJour) {
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
