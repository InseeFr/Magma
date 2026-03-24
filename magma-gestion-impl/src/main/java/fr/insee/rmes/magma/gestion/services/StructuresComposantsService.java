package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.gestion.model.ComponentById;
import fr.insee.rmes.magma.gestion.model.StructureById;
import fr.insee.rmes.magma.gestion.utils.ComponentByIdDTO;
import fr.insee.rmes.magma.gestion.utils.StructureComponentDTO;
import fr.insee.rmes.magma.gestion.utils.StructureDTO;

import java.util.List;

public interface StructuresComposantsService {
    StructureById transformStructureDTOToStructureById(StructureDTO structureDTO, List<StructureComponentDTO> componentDTOList);

    ComponentById transformComponentDTOToComponentById(ComponentByIdDTO componentByIdDTO);
}
