package fr.insee.rmes.magma.gestion.queries.parameters;

public record CodesListRequestParametizer(String notation,
                                          String date,
                                          Integer offset,
                                          Integer perPage) implements ParametersForQueryGestion<CodesListRequestParametizer> {

    // for getAllCodesLists (no date filter)
    public CodesListRequestParametizer() {
        this(null, null, null, null);
    }

    // for getCodesList and getCodesListDateMAJ
    public CodesListRequestParametizer(String notation) {
        this(notation, null, null, null);
    }

    // for getCodesListPagination
    public CodesListRequestParametizer(String notation, Integer offset, Integer perPage) {
        this(notation, null, offset, perPage);
    }
}