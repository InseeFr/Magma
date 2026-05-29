package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.gestion.model.OperationById;
import fr.insee.rmes.magma.gestion.model.OperationBySerieIdSerie;
import fr.insee.rmes.magma.gestion.model.SerieById;
import fr.insee.rmes.magma.gestion.model.SerieByIdType;
import fr.insee.rmes.magma.gestion.model.StructureByIdAttributsInnerListCode;
import fr.insee.rmes.magma.gestion.utils.OperationDTO;
import fr.insee.rmes.magma.gestion.utils.SeriesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static fr.insee.rmes.magma.gestion.utils.LocalisedLabelUtils.createLangueContenu;
import static fr.insee.rmes.magma.gestion.utils.LocalisedLabelUtils.createListLangueContenu;

@Service
public class SeriesOperationsServiceImpl implements SeriesOperationsService {

    @Value("${fr.insee.rmes.magma.lg1}")
    private String lg1;

    @Value("${fr.insee.rmes.magma.lg2}")
    private String lg2;

    @Override
    public SerieById transformSeriesDTOToSerieById(SeriesDTO dto) {
        SerieById serieById = new SerieById();

        serieById.setSeriesId(dto.seriesId());
        serieById.setUri(dto.series());
        serieById.setDateCreation(dto.created() != null ? dto.created() : null);
        serieById.setDateMiseAJour(dto.modified() != null ? dto.modified() : null);
        serieById.setStatutValidation(dto.validationState());

        serieById.setLabel(createListLangueContenu(
                createLangueContenu(dto.seriesLabelLg1(), lg1),
                createLangueContenu(dto.seriesLabelLg2(), lg2)));

        serieById.setAltLabel(createListLangueContenu(
                createLangueContenu(dto.seriesAltLabelLg1(), lg1),
                createLangueContenu(dto.seriesAltLabelLg2(), lg2)));

        serieById.setResume(createListLangueContenu(
                createLangueContenu(dto.seriesAbstractLg1(), lg1),
                createLangueContenu(dto.seriesAbstractLg2(), lg2)));

        serieById.setNoteHistorique(createListLangueContenu(
                createLangueContenu(dto.seriesHistoryNoteLg1(), lg1),
                createLangueContenu(dto.seriesHistoryNoteLg2(), lg2)));

        if (dto.type() != null && !dto.type().isBlank()) {
            SerieByIdType type = new SerieByIdType();
            type.setId(dto.typeID());
            type.setUri(dto.type());
            type.setLabel(createListLangueContenu(
                    createLangueContenu(dto.typeLabelLg1(), lg1),
                    createLangueContenu(dto.typeLabelLg2(), lg2)));
            serieById.setType(type);
        }

        if (dto.periodicity() != null && !dto.periodicity().isBlank()) {
            SerieByIdType frequence = new SerieByIdType();
            frequence.setId(dto.periodicityId());
            frequence.setUri(dto.periodicity());
            frequence.setLabel(createListLangueContenu(
                    createLangueContenu(dto.periodicityLabelLg1(), lg1),
                    createLangueContenu(dto.periodicityLabelLg2(), lg2)));
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

    @Override
    public OperationById transformOperationDTOToOperationById(OperationDTO dto) {
        OperationById operationById = new OperationById();

        operationById.setId(dto.operationId());
        operationById.setUri(dto.operation());
        operationById.setMillesime(dto.temporal());
        operationById.setDateCreation(dto.created() != null ? dto.created() : null);
        operationById.setDateMiseAJour(dto.modified() != null ? dto.modified() : null);
        operationById.setStatutValidation(dto.validationState());

        operationById.setLabel(createListLangueContenu(
                createLangueContenu(dto.operationLabelLg1(), lg1),
                createLangueContenu(dto.operationLabelLg2(), lg2)));

        operationById.setAltLabel(createListLangueContenu(
                createLangueContenu(dto.operationAltLabelLg1(), lg1),
                createLangueContenu(dto.operationAltLabelLg2(), lg2)));

        if (dto.seriesId() != null && !dto.seriesId().isBlank()) {
            OperationBySerieIdSerie serie = new OperationBySerieIdSerie();
            serie.setId(dto.seriesId());
            serie.setUri(dto.series());
            serie.setLabel(createListLangueContenu(
                    createLangueContenu(dto.seriesLabelLg1(), lg1),
                    createLangueContenu(dto.seriesLabelLg2(), lg2)));
            operationById.setSerie(serie);
        }

        if (dto.simsId() != null && !dto.simsId().isBlank()) {
            StructureByIdAttributsInnerListCode rapportQualite = new StructureByIdAttributsInnerListCode();
            rapportQualite.setId(dto.simsId());
            rapportQualite.setUri(dto.sims());
            operationById.setRapportQualite(rapportQualite);
        }

        return operationById;
    }

    @Override
    public List<SerieById> transformSeriesDTOsToSeries(List<SeriesDTO> dtos) {
        return dtos.stream().map(this::transformSeriesDTOToSeriesItem).toList();
    }

    private SerieById transformSeriesDTOToSeriesItem(SeriesDTO dto) {
        SerieById serieById = new SerieById();
        serieById.setOperations(null);
        serieById.setAltLabel(null);
        serieById.setDateCreation(null);
        serieById.setResume(null);
        serieById.setNoteHistorique(null);
        serieById.setSeriesPrecedentes(null);
        serieById.setSeriesSuivantes(null);
        serieById.setSeriesLiees(null);
        serieById.setIndicateurs(null);
        serieById.setProprietaires(null);
        serieById.setOrganismesResponsables(null);
        serieById.setPartenaires(null);
        serieById.setServicesCollecteurs(null);
        serieById.setSeriesId(dto.seriesId());
        serieById.setUri(dto.series());
        serieById.setLabel(createListLangueContenu(
                createLangueContenu(dto.seriesLabelLg1(), lg1),
                createLangueContenu(dto.seriesLabelLg2(), lg2)));
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
                createLangueContenu(parts.length > 2 ? parts[2] : null, lg1),
                createLangueContenu(parts.length > 3 ? parts[3] : null, lg2)));
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
                createLangueContenu(parts.length > 2 ? parts[2] : null, lg1),
                createLangueContenu(parts.length > 3 ? parts[3] : null, lg2)));
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