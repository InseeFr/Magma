package fr.insee.rmes.magma.diffusion.utils;

public record DocumentDTO(
        String url,
        String labelLg1,
        String labelLg2,
        String dateMiseAJour,
        String langue
) {

}