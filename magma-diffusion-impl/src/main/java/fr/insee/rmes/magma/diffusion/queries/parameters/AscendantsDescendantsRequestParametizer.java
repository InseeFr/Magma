package fr.insee.rmes.magma.diffusion.queries.parameters;

import fr.insee.rmes.magma.diffusion.model.*;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record AscendantsDescendantsRequestParametizer(String code,
                                                      LocalDate date,
                                                      TypeEnumDescendantsAireDAttractionDesVilles typeEnumDescendantsAireDAttractionDesVilles,
                                                      TypeEnumDescendantsArrondissement typeEnumDescendantsArrondissement,
                                                      TypeEnumAscendantsArrondissement typeEnumAscendantsArrondissement,
                                                      TypeEnumAscendantsArrondissementMunicipal typeEnumAscendantsArrondissementMunicipal,
                                                      TypeEnumDescendantsBassinDeVie typeEnumDescendantsBassinDeVie,
                                                      TypeEnumAscendantsCanton typeEnumAscendantsCanton,
                                                      TypeEnumAscendantsCantonOuVille typeEnumAscendantsCantonOuVille,
                                                      TypeEnumDescendantsCantonOuVille typeEnumDescendantsCantonOuVille,
                                                      TypeEnumAscendantsCirconscriptionTerritoriale typeEnumAscendantsCirconscriptionTerritoriale,
                                                      TypeEnumDescendantsCollectiviteDOutreMer typeEnumDescendantsCollectiviteDOutreMer,
                                                      TypeEnumAscendantsCommune typeEnumAscendantsCommune,
                                                      TypeEnumDescendantsCommune typeEnumDescendantsCommune,
                                                      TypeEnumAscendantsCommuneAssociee typeEnumAscendantsCommuneAssociee,
                                                      TypeEnumAscendantsCommuneDeleguee typeEnumAscendantsCommuneDeleguee,
                                                      TypeEnumAscendantsDepartement typeEnumAscendantsDepartement,
                                                      TypeEnumDescendantsDepartement typeEnumDescendantsDepartement,
                                                      TypeEnumAscendantsDistrict typeEnumAscendantsDistrict,
                                                      TypeEnumDescendantsIntercommunalite typeEnumDescendantsIntercommunalite,
                                                      TypeEnumAscendantsIris typeEnumAscendantsIris,
                                                      TypeEnumDescendantsPays typeEnumDescendantsPays,
                                                      TypeEnumDescendantsRegion typeEnumDescendantsRegion,
                                                      TypeEnumDescendantsUniteUrbaine typeEnumDescendantsUniteUrbaine,
                                                      TypeEnumDescendantsZoneDEmploi typeEnumDescendantsZoneDEmploi,
                                                      String filtreNom,
                                                      String listeTypesGeo,
                                                      Class<?> typeOrigine,
                                                      boolean ascendant) implements ParametersForQuery<AscendantsDescendantsRequestParametizer> {

    //for geo/pays/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsPays typeEnumDescendantsPays,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeEnumDescendantsPays, null, null, null, null, null, typeOrigine, false);
    }

    //for descendants with filtreNom and listeTypesGeo
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   String filtreNom,
                                                   String listeTypesGeo,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, filtreNom, listeTypesGeo, typeOrigine, false);
    }

     //for ascendants/descendants with listeTypesGeo (without filtreNom)
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   String listeTypesGeo,
                                                   Class<?> typeOrigine,
                                                   boolean ascendant) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, listeTypesGeo, typeOrigine, ascendant);
    }

    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        if ("filtreNom".equals(recordComponent.getName())) {
            return new ParameterValueDecoder.DelegaterDecoder<>(stringValue -> stringValue == null ? "*" : stringValue.toString());
        }
        if ("listeTypesGeo".equals(recordComponent.getName())) {
            return new ParameterValueDecoder.DelegaterDecoder<>(value -> (String) value);
        }
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }
}