package fr.insee.rmes.magma.diffusion.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j

public record RapportQualiteDTO(
    String id,
    String uri,
    String labelLg1,
    String labelLg2,
    String idCible,
    String cible,
    String labelCibleLg1,
    String labelCibleLg2,
    List<RubriqueDTO> rubriqueDTOList
)
{
    public RapportQualiteDTO withId(String id) {
        return new RapportQualiteDTO(id, uri, labelLg1, labelLg2, idCible, cible, labelCibleLg1, labelCibleLg2, rubriqueDTOList);
    }
    public RapportQualiteDTO withUri(String uri) {
        return new RapportQualiteDTO(id, uri, labelLg1, labelLg2, idCible, cible, labelCibleLg1, labelCibleLg2, rubriqueDTOList);
    }
    public RapportQualiteDTO withLabelLg1(String labelLg1) {
        return new RapportQualiteDTO(id, uri, labelLg1, labelLg2, idCible, cible, labelCibleLg1, labelCibleLg2, rubriqueDTOList);
    }
    public RapportQualiteDTO withLabelLg2(String labelLg2) {
        return new RapportQualiteDTO(id, uri, labelLg1, labelLg2, idCible, cible, labelCibleLg1, labelCibleLg2, rubriqueDTOList);
    }
    public RapportQualiteDTO withRubriqueDTOList(List<RubriqueDTO> rubriqueDTOList) {
        return new RapportQualiteDTO(id, uri, labelLg1, labelLg2, idCible, cible, labelCibleLg1, labelCibleLg2, rubriqueDTOList);
    }
}
