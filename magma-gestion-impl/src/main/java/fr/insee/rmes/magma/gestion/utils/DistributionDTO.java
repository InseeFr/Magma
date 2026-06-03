package fr.insee.rmes.magma.gestion.utils;

public record DistributionDTO(
        String identifier,
        String uri,
        String descriptionLg1,
        String descriptionLg2,
        String titleLg1,
        String titleLg2,
        String byteSize,
        String created,
        String modified,
        String downloadURL,
        String format
) {}
