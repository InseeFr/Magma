package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.gestion.model.SerieById;
import fr.insee.rmes.magma.gestion.model.SerieByIdType;
import fr.insee.rmes.magma.gestion.model.StructureByIdAttributsInnerListCode;
import fr.insee.rmes.magma.gestion.utils.SeriesDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static fr.insee.rmes.magma.gestion.utils.LocalisedLabelUtils.createLangueContenu;
import static fr.insee.rmes.magma.gestion.utils.LocalisedLabelUtils.createListLangueContenu;

@Service
public class SeriesOperationsServiceImpl implements SeriesOperationsService {

    @Override
    public SerieById transformSeriesDTOToSerieById(SeriesDTO dto) {
        SerieById serieById = new SerieById();

        serieById.setSeriesId(dto.seriesId());
        serieById.setUri(dto.series());
        serieById.setDateCreation(dto.created());
        serieById.setDateMiseAJour(dto.modified());
        serieById.setStatutValidation(dto.validationState());

        serieById.setLabel(createListLangueContenu(
                createLangueContenu(dto.seriesLabelLg1(), "fr"),
                createLangueContenu(dto.seriesLabelLg2(), "en")));

        serieById.setAltLabel(createListLangueContenu(
                createLangueContenu(dto.seriesAltLabelLg1(), "fr"),
                createLangueContenu(dto.seriesAltLabelLg2(), "en")));

        serieById.setResume(createListLangueContenu(
                createLangueContenu(dto.seriesAbstractLg1(), "fr"),
                createLangueContenu(dto.seriesAbstractLg2(), "en")));

        serieById.setNoteHistorique(createListLangueContenu(
                createLangueContenu(dto.seriesHistoryNoteLg1(), "fr"),
                createLangueContenu(dto.seriesHistoryNoteLg2(), "en")));

        if (dto.type() != null && !dto.type().isBlank()) {
            SerieByIdType type = new SerieByIdType();
            type.setId(dto.typeID());
            type.setUri(dto.type());
            type.setLabel(createListLangueContenu(
                    createLangueContenu(dto.typeLabelLg1(), "fr"),
                    createLangueContenu(dto.typeLabelLg2(), "en")));
            serieById.setType(type);
        }

        if (dto.periodicity() != null && !dto.periodicity().isBlank()) {
            SerieByIdType frequence = new SerieByIdType();
            frequence.setId(dto.periodicityId());
            frequence.setUri(dto.periodicity());
            frequence.setLabel(createListLangueContenu(
                    createLangueContenu(dto.periodicityLabelLg1(), "fr"),
                    createLangueContenu(dto.periodicityLabelLg2(), "en")));
            serieById.setFrequenceCollecte(frequence);
        }

        if (dto.families() != null && !dto.families().isBlank()) {
            serieById.setFamille(parseFamille(dto.families()));
        }

        if (dto.simsId() != null && !dto.simsId().isBlank()) {
            StructureByIdAttributsInnerListCode rapportQualite = new StructureByIdAttributsInnerListCode();
            rapportQualite.setId(dto.simsId());
            rapportQualite.setUri(dto.sims());
            serieById.setRapportQualite(rapportQualite);
        }

        serieById.setSeriesPrecedentes(parseRefList(dto.previousSeries()));
        serieById.setSeriesSuivantes(parseRefList(dto.nextSeries()));
        serieById.setSeriesLiees(parseRefList(dto.seeAlsoSeries()));
        serieById.setOperations(parseRefList(dto.operations()));
        serieById.setIndicateurs(parseRefList(dto.indicators()));
        serieById.setProprietaires(parseRefList(dto.creators()));
        serieById.setOrganismesResponsables(parseRefList(dto.publishers()));
        serieById.setPartenaires(parseRefList(dto.contributors()));
        serieById.setServicesCollecteurs(parseRefList(dto.dataCollectors()));

        return serieById;
    }

    private SerieByIdType parseFamille(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }
        String[] parts = raw.split("\\$", -1);
        SerieByIdType famille = new SerieByIdType();
        famille.setId(parts.length > 0 ? parts[0] : null);
        famille.setUri(parts.length > 1 ? parts[1] : null);
        famille.setLabel(createListLangueContenu(
                createLangueContenu(parts.length > 2 ? parts[2] : null, "fr"),
                createLangueContenu(parts.length > 3 ? parts[3] : null, "en")));
        return famille;
    }

    private SerieByIdType parseSingleRef(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }
        String[] parts = raw.split("\\$", -1);
        SerieByIdType ref = new SerieByIdType();
        ref.setId(parts.length > 0 ? parts[0] : null);
        ref.setUri(parts.length > 1 ? parts[1] : null);
        ref.setLabel(createListLangueContenu(
                createLangueContenu(parts.length > 2 ? parts[2] : null, "fr"),
                createLangueContenu(parts.length > 3 ? parts[3] : null, "en")));
        return ref;
    }

    private List<SerieByIdType> parseRefList(String raw) {
        List<SerieByIdType> list = new ArrayList<>();
        if (raw == null || raw.isBlank()) {
            return list;
        }
        for (String item : raw.split("\\|")) {
            SerieByIdType ref = parseSingleRef(item);
            if (ref != null) {
                list.add(ref);
            }
        }
        return list;
    }
}