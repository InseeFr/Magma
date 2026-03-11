package fr.insee.rmes.magma.gestion.queries;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueryPathListGestion {
    public static final String STRUCTURES_COMPONENTS = "structures/getAllStructures.fthl";
    public static final String COMPONENT= "getComponent.ftlh";
    public static final String STRUCTURES_SLICESKEYS = "getStructureSliceKeys.ftlh";
    public static final String STRUCTURE = "getStructure.ftlh";
}
