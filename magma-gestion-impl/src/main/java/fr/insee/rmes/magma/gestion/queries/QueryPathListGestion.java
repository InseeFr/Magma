package fr.insee.rmes.magma.gestion.queries;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueryPathListGestion {
    public static final String STRUCTURES_COMPONENTS = "structures/getAllStructures.ftlh";
    public static final String ALL_STRUCTURES_BY_DATE ="structures/getAllStructuresFilterByDate.ftlh";
    public static final String COMPONENT= "components/getComponent.ftlh";
    public static final String COMPONENT_DATE_MAJ = "components/getComponentDateMAJ.ftlh";
    public static final String STRUCTURES_SLICESKEYS = "structures/getStructureSliceKeys.ftlh";
    public static final String STRUCTURE = "structures/getStructure.ftlh";
    public static final String STRUCTURE_DATE_MAJ = "structures/getStructureDateMAJ.ftlh";
    public static final String STRUCTURE_COMPONENTS = "structures/getStructureComponents.ftlh";

    public static final String ALL_CODES_LISTS = "codeLists/getAllCodesLists.ftlh";
    public static final String ALL_CODES_LISTS_BY_DATE = "codeLists/getAllCodesListsByDate.ftlh";
    public static final String CODES_LIST = "codeLists/getCodesList.ftlh";
    public static final String CODES_LIST_DATE_MAJ = "codeLists/getCodesListDateMAJ.ftlh";
    public static final String CODES_LIST_PAGINATION = "codeLists/getCodesListPagination.ftlh";
}
