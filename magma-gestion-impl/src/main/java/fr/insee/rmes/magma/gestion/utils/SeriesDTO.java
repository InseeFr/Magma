package fr.insee.rmes.magma.gestion.utils;

import java.time.LocalDate;

public record SeriesDTO(
        String seriesId,
        String series,
        String seriesLabelLg1,
        String seriesLabelLg2,
        String seriesAltLabelLg1,
        String seriesAltLabelLg2,
        String seriesAbstractLg1,
        String seriesAbstractLg2,
        String seriesHistoryNoteLg1,
        String seriesHistoryNoteLg2,
        String type,
        String typeID,
        String typeLabelLg1,
        String typeLabelLg2,
        String periodicity,
        String periodicityId,
        String periodicityLabelLg1,
        String periodicityLabelLg2,
        String families,
        String seeAlsoSeries,
        String previousSeries,
        String nextSeries,
        String operations,
        String indicators,
        String sims,
        String simsId,
        LocalDate created,
        LocalDate modified,
        String creators,
        String publishers,
        String contributors,
        String dataCollectors,
        String validationState
) {
}