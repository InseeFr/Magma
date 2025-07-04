package fr.insee.rmes.metadata.queries.parameters;

import fr.insee.rmes.metadata.model.*;

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
                                                      TypeEnumDescendantsPays typeEnumDescendantsPays,
                                                      TypeEnumDescendantsRegion typeEnumDescendantsRegion,
                                                      TypeEnumDescendantsUniteUrbaine typeEnumDescendantsUniteUrbaine,
                                                      TypeEnumDescendantsZoneDEmploi typeEnumDescendantsZoneDEmploi,
                                                      String filtreNom,
                                                      Class<?> typeOrigine,
                                                      boolean ascendant) implements ParametersForQuery<AscendantsDescendantsRequestParametizer> {

    //for geo/aireDAttractionDesVilles/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsAireDAttractionDesVilles typeEnumDescendantsAireDAttractionDesVilles,
                                                   Class<?> typeOrigine) {
        this(code, date, typeEnumDescendantsAireDAttractionDesVilles, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeOrigine, false);
    }

    //for geo/arrondissement/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsArrondissement typeEnum,
                                                   Class<?> typeOrigine) {
        this(code, date, null, typeEnum, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeOrigine, false);
    }

    //for geo/arrondissement/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsArrondissement typeEnum,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, typeEnum, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeOrigine, true);
    }

    //for geo/arrondissementMunicipal/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsArrondissementMunicipal typeEnum,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, typeEnum, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeOrigine, true);
    }

    //for geo/bassinDeVie2022/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsBassinDeVie typeEnumDescendantsBassinDeVie,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, typeEnumDescendantsBassinDeVie, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeOrigine, false);
    }

    //for geo/canton/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsCanton typeEnumAscendantsCanton,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, typeEnumAscendantsCanton, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeOrigine, true);
    }

    //for geo/cantonOuVille/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsCantonOuVille typeEnumAscendantsCantonOuVille,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, typeEnumAscendantsCantonOuVille, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeOrigine, true);
    }

    //for geo/cantonOuVille/{code}/decendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsCantonOuVille typeEnumDescendantsCantonOuVille,
                                                   String filtreNom,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, typeEnumDescendantsCantonOuVille, null, null, null, null, null, null, null, null, null, null, null, null, null, null, filtreNom, typeOrigine, false);
    }

    //for geo/circonscriptionTerritoriale/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsCirconscriptionTerritoriale typeEnumAscendantsCirconscriptionTerritoriale,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, typeEnumAscendantsCirconscriptionTerritoriale, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeOrigine, true);
    }

    //for geo/collectiviteDOutreMer/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsCollectiviteDOutreMer typeEnumDescendantsCollectiviteDOutreMer,
                                                   String filtreNom,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, typeEnumDescendantsCollectiviteDOutreMer, null, null, null, null, null, null, null, null, null, null, null, null, filtreNom, typeOrigine, false);
    }

    //for geo/commune/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsCommune typeEnumAscendantsCommune,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, typeEnumAscendantsCommune, null, null, null, null, null, null, null, null, null, null, null, null, typeOrigine, true);
    }

    //for geo/commune/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsCommune typeEnumDescendantsCommune,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, typeEnumDescendantsCommune, null, null, null, null, null, null, null, null, null, null, null, typeOrigine, false);
    }

    //for geo/communeAssociee/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsCommuneAssociee typeEnumAscendantsCommuneAssociee,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, typeEnumAscendantsCommuneAssociee, null, null, null, null, null, null, null, null, null, null, typeOrigine, true);
    }

    //for geo/communeDeleguee/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsCommuneDeleguee typeEnumAscendantsCommuneDeleguee,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, null, typeEnumAscendantsCommuneDeleguee, null, null, null, null, null, null, null, null, null, typeOrigine, true);
    }

    //for geo/departement/{code}/ascendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsDepartement typeEnum,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeEnum, null, null, null, null, null, null, null, null, typeOrigine, true);
    }

    //for geo/departement/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsDepartement typeEnumDescendantsDepartement,
                                                   String filtreNom,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeEnumDescendantsDepartement, null, null, null, null, null, null, filtreNom, typeOrigine, false);
    }


    //for geo/district/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumAscendantsDistrict typeEnumAscendantsDistrict,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeEnumAscendantsDistrict, null, null, null, null, null, null, typeOrigine, true);
    }

    //for geo/intercommunalite/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsIntercommunalite typeEnumDescendantsIntercommunalite,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeEnumDescendantsIntercommunalite, null, null, null, null, null, typeOrigine, false);
    }

    //for geo/pays/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsPays typeEnumDescendantsPays,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeEnumDescendantsPays, null, null, null, null, typeOrigine, false);
    }

    //for geo/region/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsRegion typeEnumDescendantsRegion,
                                                   String filtreNom,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeEnumDescendantsRegion, null, null, filtreNom, typeOrigine, false);
    }

    //for geo/uniteUrbaine2020/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsUniteUrbaine typeEnumDescendantsUniteUrbaine,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeEnumDescendantsUniteUrbaine, null, null, typeOrigine, false);
    }

    //for geo/zoneDEmploi2020/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsZoneDEmploi typeEnumDescendantsZoneDEmploi,
                                                   Class<?> typeOrigine) {
        this(code, date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, typeEnumDescendantsZoneDEmploi, null, typeOrigine, false);
    }

    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        if ("filtreNom".equals(recordComponent.getName())) {
            return new ParameterValueDecoder.DelegaterDecoder<>(stringValue -> stringValue == null ? "*" : stringValue.toString());
        }
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }
}

