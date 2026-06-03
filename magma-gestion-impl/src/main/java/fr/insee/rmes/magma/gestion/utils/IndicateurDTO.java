package fr.insee.rmes.magma.gestion.utils;

import java.time.LocalDate;

public record IndicateurDTO(
        String indicatorId,
        String indicator,
        String indicatorLabelLg1,
        String indicatorLabelLg2,
        String indicatorAltLabelLg1,
        String indicatorAltLabelLg2,
        String indicatorAbstractLg1,
        String indicatorAbstractLg2,
        String indicatorHistoryNoteLg1,
        String indicatorHistoryNoteLg2,
        String periodicity,
        String periodicityId,
        String periodicityLabelLg1,
        String periodicityLabelLg2,
        String wasGeneratedBySeries,
        String seeAlsoSeries,
        String seeAlsoIndicators,
        String sims,
        String simsId,
        LocalDate created,
        LocalDate modified,
        String creators,
        String publishers,
        String contributors,
        String validationState
) {
}