package fr.insee.rmes.magma.diffusion.queries;

public final class QueryPathListDiffusion {
    public static final String ASCENDANTS_OR_DESCENDANTS = "geographie/getAscendantsOrDescendantsByCodeTypeDate.ftlh";
    public static final String CONCEPT = "concepts/getConceptByCode.ftlh";
    public static final String CONCEPTS = "concepts/getConceptsByLabel.ftlh";
    public static final String NEARBY_CONCEPTS = "concepts/getLinkedConceptsQuery.ftlh";
    public static final String INTITULES_ALTERNATIFS = "concepts/getConceptIntitulesAlternatifs.ftlh";
    public static final String TERRITOIRE = "geographie/getTerritoireByCodeDateNomCommune.ftlh";
    public static final String COMMUNE_CANTONS = "geographie/getCommuneCantonsByCodeDate.ftlh";
    public static final String CANTON_COMMUNES = "geographie/getCantonCommunesByCodeDate.ftlh";
    public static final String PRECEDENTS = "geographie/getPreviousOrNextByCodeTypeDate.ftlh";
    public static final String PROJETES = "geographie/getProjectionByCodeTypeDate.ftlh";
    public static final String IRIS_FAUX_IRIS = "geographie/getIrisByCodeDate.ftlh";
    public static final String IRIS_LIST = "geographie/getIrisList.ftlh";
    public static final String ASCENDANTS_FAUX_IRIS = "geographie/getAscendantsFauxIrisByCodeTypeDate.ftlh";
    public static final String LIEN_COMMUNE_IRIS = "geographie/hasIrisDescendant.ftlh";
    public static final String LIEN_PAYS = "geographie/getPays.ftlh";
    public static final String DESCENDANTS_PAYS = "geographie/getPaysDescendants.ftlh";
    public static final String PAYS_PRECEDENTS = "geographie/getPaysPrecedents.ftlh";
    public static final String PAYS_SUIVANTS = "geographie/getPaysSuivants.ftlh";
    public static final String TERRITOIRES_LIES = "geographie/getTerritoiresLies.ftlh";
    public static final String NOMENCLATURE = "classifications/getClassificationByCode.ftlh";


}
