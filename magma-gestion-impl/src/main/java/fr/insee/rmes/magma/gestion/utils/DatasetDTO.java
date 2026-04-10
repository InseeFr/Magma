package fr.insee.rmes.magma.gestion.utils;

import java.time.LocalDate;

public record DatasetDTO(
        String id,
        String uri,
        String titreLg1,
        String titreLg2,
        LocalDate dateMiseAJour,
        String statutValidation,
        LocalDate dateCreation
) {
}