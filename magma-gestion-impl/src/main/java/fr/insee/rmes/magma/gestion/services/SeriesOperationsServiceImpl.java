package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.gestion.model.*;
import fr.insee.rmes.magma.gestion.utils.IndicateurDTO;
import fr.insee.rmes.magma.gestion.utils.OperationDTO;
import fr.insee.rmes.magma.gestion.utils.SeriesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URI;
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
    public Serie transformSeriesDTOToSerieById(SeriesDTO dto) {
        Serie serieById = new Serie();

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

        if (StringUtils.hasText(dto.type()) ) {
            IdUriLabel type = new IdUriLabel();
            type.setId(dto.typeID());
            type.setUri(URI.create(dto.type()));
            type.setLabel(createListLangueContenu(
                    createLangueContenu(dto.typeLabelLg1(), lg1),
                    createLangueContenu(dto.typeLabelLg2(), lg2)));
            serieById.setType(type);
        }

        if (StringUtils.hasText(dto.periodicity()) ) {
            IdUriLabel frequence = new IdUriLabel();
            frequence.setId(dto.periodicityId());
            frequence.setUri(URI.create(dto.periodicity()));
            frequence.setLabel(createListLangueContenu(
                    createLangueContenu(dto.periodicityLabelLg1(), lg1),
                    createLangueContenu(dto.periodicityLabelLg2(), lg2)));
            serieById.setFrequenceCollecte(frequence);
        }

        if (StringUtils.hasText(dto.families()) ) {
            serieById.setFamille(parseFamille(dto.families()));
        }

        if (StringUtils.hasText(dto.sims()) ) {
            IdUri rapportQualite = new IdUri();
            rapportQualite.setId(dto.simsId());
            rapportQualite.setUri(URI.create(dto.sims()));
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
    public Operation transformOperationDTOToOperation(OperationDTO dto) {
        Operation operation = new Operation();

        operation.setId(dto.operationId());
        operation.setUri(dto.operation());
        operation.setMillesime(dto.temporal());
        operation.setDateCreation(dto.created() != null ? dto.created() : null);
        operation.setDateMiseAJour(dto.modified() != null ? dto.modified() : null);
        operation.setStatutValidation(dto.validationState());

        operation.setLabel(createListLangueContenu(
                createLangueContenu(dto.operationLabelLg1(), lg1),
                createLangueContenu(dto.operationLabelLg2(), lg2)));

        operation.setAltLabel(createListLangueContenu(
                createLangueContenu(dto.operationAltLabelLg1(), lg1),
                createLangueContenu(dto.operationAltLabelLg2(), lg2)));

        if (StringUtils.hasText(dto.series()) ) {
            OperationSerie serie = new OperationSerie();
            serie.setId(dto.seriesId());
            serie.setUri(dto.series());
            serie.setLabel(createListLangueContenu(
                    createLangueContenu(dto.seriesLabelLg1(), lg1),
                    createLangueContenu(dto.seriesLabelLg2(), lg2)));
            operation.setSerie(serie);
        }

        if (StringUtils.hasText(dto.simsId()) ) {
            IdUri rapportQualite = new IdUri();
            rapportQualite.setId(dto.simsId());
            rapportQualite.setUri(URI.create(dto.sims()));
            operation.setRapportQualite(rapportQualite);
        }

        return operation;
    }

    @Override
    public List<Serie> transformSeriesDTOsToSeries(List<SeriesDTO> dtos) {
        return dtos.stream().map(this::transformSeriesDTOToSeriesItem).toList();
    }

    private Serie transformSeriesDTOToSeriesItem(SeriesDTO dto) {
        Serie serieById = new Serie();
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

    private IdUriLabel parseFamille(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }
        String[] parts = raw.split("\\$", -1);
        IdUriLabel famille = new IdUriLabel();
        famille.setId(parts.length > 0 ? parts[0] : null);
        String uriStr = parts.length > 1 ? parts[1] : null;
        famille.setUri(StringUtils.hasText(uriStr) ? URI.create(uriStr) : null);
        famille.setLabel(createListLangueContenu(
                createLangueContenu(parts.length > 2 ? parts[2] : null, lg1),
                createLangueContenu(parts.length > 3 ? parts[3] : null, lg2)));
        return famille;
    }

    private IdUriLabel parseSingleRef(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }
        String[] parts = raw.split("\\$", -1);
        IdUriLabel ref = new IdUriLabel();
        ref.setId(parts.length > 0 ? parts[0] : null);
        String uriStr = parts.length > 1 ? parts[1] : null;
        ref.setUri(StringUtils.hasText(uriStr) ? URI.create(uriStr) : null);
        ref.setLabel(createListLangueContenu(
                createLangueContenu(parts.length > 2 ? parts[2] : null, lg1),
                createLangueContenu(parts.length > 3 ? parts[3] : null, lg2)));
        return ref;
    }

    private List<IdUriLabel> parseRefList(String raw) {
        List<IdUriLabel> list = new ArrayList<>();
        if (raw == null || raw.isBlank()) {
            return list;
        }
        for (String item : raw.split("\\|")) {
            IdUriLabel ref = parseSingleRef(item);
            if (ref != null) {
                list.add(ref);
            }
        }
        return list;
    }

    @Override
    public Indicateur transformIndicateurDTOToIndicateur(IndicateurDTO dto) {
        Indicateur indicateur = new Indicateur();

        indicateur.setAltLabel(null);
        indicateur.setResume(null);
        indicateur.setNoteHistorique(null);
        indicateur.setFrequenceCollecte(null);
        indicateur.setRapportQualite(null);
        indicateur.setSeriesContributrices(null);
        indicateur.setSeriesLiees(null);
        indicateur.setIndicateursLies(null);
        indicateur.setDateCreation(null);
        indicateur.setDateMiseAJour(null);
        indicateur.setProprietaires(null);
        indicateur.setOrganismesResponsables(null);
        indicateur.setPartenaires(null);



        indicateur.setId(dto.indicatorId());
        indicateur.setUri(dto.indicator());
        indicateur.setDateCreation(dto.created() != null ? dto.created().toString() : null);
        indicateur.setDateMiseAJour(dto.modified() != null ? dto.modified().toString() : null);
        indicateur.setStatuValidation(dto.validationState());

        indicateur.setLabel(createListLangueContenu(
                createLangueContenu(dto.indicatorLabelLg1(), lg1),
                createLangueContenu(dto.indicatorLabelLg2(), lg2)));

        indicateur.setAltLabel(createListLangueContenu(
                createLangueContenu(dto.indicatorAltLabelLg1(), lg1),
                createLangueContenu(dto.indicatorAltLabelLg2(), lg2)));

        indicateur.setResume(createListLangueContenu(
                createLangueContenu(dto.indicatorAbstractLg1(), lg1),
                createLangueContenu(dto.indicatorAbstractLg2(), lg2)));

        indicateur.setNoteHistorique(createListLangueContenu(
                createLangueContenu(dto.indicatorHistoryNoteLg1(), lg1),
                createLangueContenu(dto.indicatorHistoryNoteLg2(), lg2)));

        if (dto.periodicity() != null && !dto.periodicity().isBlank()) {
            IdUriLabel frequence = new IdUriLabel(dto.periodicityId());
            frequence.setUri(toUri(dto.periodicity()));
            frequence.setLabel(createListLangueContenu(
                    createLangueContenu(dto.periodicityLabelLg1(), lg1),
                    createLangueContenu(dto.periodicityLabelLg2(), lg2)));
            indicateur.setFrequenceCollecte(frequence);
        }

        if (dto.simsId() != null && !dto.simsId().isBlank()) {
            IdUri rapportQualite = new IdUri();
            rapportQualite.setId(dto.simsId());
            rapportQualite.setUri(URI.create(dto.sims()));
            indicateur.setRapportQualite(rapportQualite);
        }

        indicateur.setSeriesContributrices(parseIdUriLabelList(dto.wasGeneratedBySeries()));
        indicateur.setSeriesLiees(parseIdUriLabelList(dto.seeAlsoSeries()));
        indicateur.setIndicateursLies(parseIdUriLabelList(dto.seeAlsoIndicators()));
        indicateur.setProprietaires(parseIdUriLabelList(dto.creators()));
        indicateur.setOrganismesResponsables(parseIdUriLabelList(dto.publishers()));
        indicateur.setPartenaires(parseIdUriLabelList(dto.contributors()));

        return indicateur;
    }

    private List<IdUriLabel> parseIdUriLabelList(String raw) {
        List<IdUriLabel> list = new ArrayList<>();
        if (raw == null || raw.isBlank()) {
            return null;
        }
        for (String item : raw.split("\\|")) {
            String[] parts = item.split("\\$", -1);
            String id = parts.length > 0 ? parts[0] : null;
            if (id == null || id.isBlank()) {
                continue;
            }
            IdUriLabel ref = new IdUriLabel(id);
            if (parts.length > 1 && !parts[1].isBlank()) {
                ref.setUri(toUri(parts[1]));
            }
            ref.setLabel(createListLangueContenu(
                    createLangueContenu(parts.length > 2 ? parts[2] : null, lg1),
                    createLangueContenu(parts.length > 3 ? parts[3] : null, lg2)));
            list.add(ref);
        }
        return list;
    }

    private URI toUri(String uriString) {
        if (uriString == null || uriString.isBlank()) {
            return null;
        }
        try {
            return URI.create(uriString);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}