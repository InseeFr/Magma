package fr.insee.rmes.magma.gestion.queries.parameters;

import java.time.LocalDate;

public record StructureComponentsRequestParametizer(String id,
                                                    LocalDate date,
                                                    Boolean dateMiseAJour) implements ParametersForQueryGestion<StructureComponentsRequestParametizer> {

    //for getAllComponents and getAllStructures (no date filter)
    public StructureComponentsRequestParametizer() {
        this(null, null, null);
    }

    //for getAllComponents and getAllStructures (with date filter)
    public StructureComponentsRequestParametizer(LocalDate date) {
        this("none", date, null);
    }

    //for getComponentById and getStructure
    public StructureComponentsRequestParametizer(String id, Boolean dateMiseAJour) {
        this(id, null, dateMiseAJour);
    }

    //for getSliceKeys
    public StructureComponentsRequestParametizer(String id) {
        this(id, null, null);
    }
}
