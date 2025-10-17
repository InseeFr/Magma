package fr.insee.rmes.metadata.queries.parameters;

import fr.insee.rmes.metadata.model.*;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;
import java.util.function.Function;

@FunctionalInterface
interface ParameterValueDecoder<T> {

    String STRING_CLASS = "java.lang.String";
    String BOOLEAN_CLASS = "boolean";
    String CLASS_CLASS = "java.lang.Class";
    String LOCALE_DATE_CLASS = "java.time.LocalDate";
    String ENUM_DESCENDANTS_AIREDATTRACTIONDESVILLES_CLASS = "fr.insee.rmes.metadata.model.TypeEnumDescendantsAireDAttractionDesVilles";
    String ENUM_DESCENDANTS_ARRONDISSEMENT_CLASS = "fr.insee.rmes.metadata.model.TypeEnumDescendantsArrondissement";
    String ENUM_ASCENDANTS_ARRONDISSEMENT_CLASS = "fr.insee.rmes.metadata.model.TypeEnumAscendantsArrondissement";
    String ENUM_ASCENDANTS_ARRONDISSEMENTMUNICIPAL_CLASS = "fr.insee.rmes.metadata.model.TypeEnumAscendantsArrondissementMunicipal";
    String ENUM_DESCENDANTS_BASSINDEVIE_CLASS = "fr.insee.rmes.metadata.model.TypeEnumDescendantsBassinDeVie";
    String ENUM_ASCENDANTS_CANTON_CLASS = "fr.insee.rmes.metadata.model.TypeEnumAscendantsCanton";
    String ENUM_ASCENDANTS_CANTONOUVILLE_CLASS = "fr.insee.rmes.metadata.model.TypeEnumAscendantsCantonOuVille";
    String ENUM_DESCENDANTS_CANTONOUVILLE_CLASS = "fr.insee.rmes.metadata.model.TypeEnumDescendantsCantonOuVille";
    String ENUM_ASCENDANTS_CIRCONSCRIPTIONTERRITORIALE_CLASS = "fr.insee.rmes.metadata.model.TypeEnumAscendantsCirconscriptionTerritoriale";
    String ENUM_DESCENDANTS_COLLECTIVITEDOUTREMER_CLASS = "fr.insee.rmes.metadata.model.TypeEnumDescendantsCollectiviteDOutreMer";
    String ENUM_ASCENDANTS_COMMUNEASSOCIEE_CLASS = "fr.insee.rmes.metadata.model.TypeEnumAscendantsCommuneAssociee";
    String ENUM_ASCENDANTS_COMMUNEDELEGUEE_CLASS = "fr.insee.rmes.metadata.model.TypeEnumAscendantsCommuneDeleguee";
    String ENUM_ASCENDANTS_DISTRICT_CLASS = "fr.insee.rmes.metadata.model.TypeEnumAscendantsDistrict";
    String ENUM_DESCENDANTS_DEPARTEMENT_CLASS = "fr.insee.rmes.metadata.model.TypeEnumDescendantsDepartement";
    String ENUM_ASCENDANTS_DEPARTEMENT_CLASS = "fr.insee.rmes.metadata.model.TypeEnumAscendantsDepartement";
    String ENUM_DESCENDANTS_COMMUNE_CLASS = "fr.insee.rmes.metadata.model.TypeEnumDescendantsCommune";
    String ENUM_ASCENDANTS_COMMUNE_CLASS = "fr.insee.rmes.metadata.model.TypeEnumAscendantsCommune";
    String ENUM_DESCENDANTS_INTERCOMMUNALITE_CLASS = "fr.insee.rmes.metadata.model.TypeEnumDescendantsIntercommunalite";
    String ENUM_ASCENDANTS_IRIS_CLASS = "fr.insee.rmes.metadata.model.TypeEnumAscendantsIris";
    String ENUM_DESCENDANTS_PAYS_CLASS = "fr.insee.rmes.metadata.model.TypeEnumDescendantsPays";
    String ENUM_DESCENDANTS_REGION_CLASS = "fr.insee.rmes.metadata.model.TypeEnumDescendantsRegion";
    String ENUM_DESCENDANTS_UNITEURBAINE_CLASS = "fr.insee.rmes.metadata.model.TypeEnumDescendantsUniteUrbaine";
    String ENUM_DESCENDANTS_ZONEDEMPLOI_CLASS = "fr.insee.rmes.metadata.model.TypeEnumDescendantsZoneDEmploi";

    static <U> ParameterValueDecoder<U> of(Class<U> type) {
        return switch (type.getName()) {
            case BOOLEAN_CLASS -> bool -> (boolean) bool ? "true" : "false";
            case CLASS_CLASS -> clazz -> ((Class<?>) clazz).getSimpleName();
            case STRING_CLASS -> String::valueOf;
            case LOCALE_DATE_CLASS -> localDate -> String.valueOf(localDate == null ? LocalDate.now() : localDate);
            case ENUM_DESCENDANTS_AIREDATTRACTIONDESVILLES_CLASS ->
                    enumAavValue -> enumAavValue == null ? "none" : ((TypeEnumDescendantsAireDAttractionDesVilles) enumAavValue).getValue();
            case ENUM_DESCENDANTS_ARRONDISSEMENT_CLASS ->
                    enumArrValue -> enumArrValue == null ? "none" : ((TypeEnumDescendantsArrondissement) enumArrValue).getValue();
            case ENUM_ASCENDANTS_ARRONDISSEMENT_CLASS ->
                    enumArrValue -> enumArrValue == null ? "none" : ((TypeEnumAscendantsArrondissement) enumArrValue).getValue();
            case ENUM_ASCENDANTS_ARRONDISSEMENTMUNICIPAL_CLASS ->
                    enumArrMuValue -> enumArrMuValue == null ? "none" : ((TypeEnumAscendantsArrondissementMunicipal) enumArrMuValue).getValue();
            case ENUM_DESCENDANTS_BASSINDEVIE_CLASS ->
                    enumBassValue -> enumBassValue == null ? "none" : ((TypeEnumDescendantsBassinDeVie) enumBassValue).getValue();
            case ENUM_ASCENDANTS_CANTON_CLASS ->
                    enumCanValue -> enumCanValue == null ? "none" : ((TypeEnumAscendantsCanton) enumCanValue).getValue();
            case ENUM_ASCENDANTS_CANTONOUVILLE_CLASS ->
                    enumCanOuVilValue -> enumCanOuVilValue == null ? "none" : ((TypeEnumAscendantsCantonOuVille) enumCanOuVilValue).getValue();
            case ENUM_DESCENDANTS_CANTONOUVILLE_CLASS ->
                    enumCanOuVilValue -> enumCanOuVilValue == null ? "none" : ((TypeEnumDescendantsCantonOuVille) enumCanOuVilValue).getValue();
            case ENUM_ASCENDANTS_CIRCONSCRIPTIONTERRITORIALE_CLASS ->
                    enumCirValue -> enumCirValue == null ? "none" : ((TypeEnumAscendantsCirconscriptionTerritoriale) enumCirValue).getValue();
            case ENUM_DESCENDANTS_COLLECTIVITEDOUTREMER_CLASS ->
                    enumComValue -> enumComValue == null ? "none" : ((TypeEnumDescendantsCollectiviteDOutreMer) enumComValue).getValue();
            case ENUM_ASCENDANTS_COMMUNE_CLASS ->
                    enumComValue -> enumComValue == null ? "none" : ((TypeEnumAscendantsCommune) enumComValue).getValue();
            case ENUM_DESCENDANTS_COMMUNE_CLASS ->
                    enumComDesValue -> enumComDesValue == null ? "none" : ((TypeEnumDescendantsCommune) enumComDesValue).getValue();
            case ENUM_ASCENDANTS_COMMUNEASSOCIEE_CLASS ->
                    enumComAValue -> enumComAValue == null ? "none" : ((TypeEnumAscendantsCommuneAssociee) enumComAValue).getValue();
            case ENUM_ASCENDANTS_COMMUNEDELEGUEE_CLASS ->
                    enumComDValue -> enumComDValue == null ? "none" : ((TypeEnumAscendantsCommuneDeleguee) enumComDValue).getValue();
            case ENUM_ASCENDANTS_DISTRICT_CLASS ->
                    enumDisValue -> enumDisValue == null ? "none" : ((TypeEnumAscendantsDistrict) enumDisValue).getValue();
            case ENUM_DESCENDANTS_DEPARTEMENT_CLASS ->
                    enumDepAscValue -> enumDepAscValue == null ? "none" : ((TypeEnumDescendantsDepartement) enumDepAscValue).getValue();
            case ENUM_ASCENDANTS_DEPARTEMENT_CLASS ->
                    enumDepDesValue -> enumDepDesValue == null ? "none" : ((TypeEnumAscendantsDepartement) enumDepDesValue).getValue();
            case ENUM_DESCENDANTS_INTERCOMMUNALITE_CLASS ->
                    enumIntercoValue -> enumIntercoValue == null ? "none" : ((TypeEnumDescendantsIntercommunalite) enumIntercoValue).getValue();
            case ENUM_ASCENDANTS_IRIS_CLASS ->
                    enumIrisValue -> enumIrisValue == null ? "none" : ((TypeEnumAscendantsIris) enumIrisValue).getValue();
            case ENUM_DESCENDANTS_PAYS_CLASS ->
                    enumPaysValue -> enumPaysValue == null ? "none" : ((TypeEnumDescendantsPays) enumPaysValue).getValue();
            case ENUM_DESCENDANTS_REGION_CLASS ->
                    enumRegValue -> enumRegValue == null ? "none" : ((TypeEnumDescendantsRegion) enumRegValue).getValue();
            case ENUM_DESCENDANTS_UNITEURBAINE_CLASS ->
                    enumUuValue -> enumUuValue == null ? "none" : ((TypeEnumDescendantsUniteUrbaine) enumUuValue).getValue();
            case ENUM_DESCENDANTS_ZONEDEMPLOI_CLASS ->
                    enumZeValue -> enumZeValue == null ? "none" : ((TypeEnumDescendantsZoneDEmploi) enumZeValue).getValue();
            case String ignored when Enum.class.isAssignableFrom(type) -> simpleEnum -> ((Enum<?>) simpleEnum).name();
            default -> throw new IllegalArgumentException("Unsupported type: " + type.getName());
        };

    }

    String decode(T value);

    /**
     * Class for custom decoder in {@link ParametersForQuery} records such as {@link AscendantsDescendantsRequestParametizer} which
     * need to customize decoder for a type which has yet a standard decoder in the children of ParameterValueDecoder
     * <p>
     * For example {@link AscendantsDescendantsRequestParametizer} needs a custom treatment for Strings for attribute <code>filtreNom</code>.
     * So it overrides the method {@link  ParametersForQuery#findParameterValueDecoder(RecordComponent)} and for the attribute
     * <code>filtreNom</code>, it returns its custom decoder with this kind of code :
     * <code>
     * if ("filtreNom".equals(recordComponent.getName())){
     * return new ParameterValueDecoder.DelegaterDecoder<String>(value -> value ==null?"*": value);
     * }
     * </code>
     * <p>
     * The DelegaterDecoder is never be returned by
     * { @link fr.insee.rmes.metadata.queries.parameters.ParameterValueDecoder#of(java.lang.Class)} and can only be
     * used when explicitly instanced in a method such as {@link AscendantsDescendantsRequestParametizer#findParameterValueDecoder(RecordComponent)}
     *
     * @param delegatedDecoder : a function applied to decode the value
     * @param <U>              : the type for which the instance will decode
     */
    record DelegaterDecoder<U>(Function<U, String> delegatedDecoder) implements ParameterValueDecoder<U> {
        @Override
        public String decode(U value) {
            return delegatedDecoder.apply(value);
        }
    }

}
