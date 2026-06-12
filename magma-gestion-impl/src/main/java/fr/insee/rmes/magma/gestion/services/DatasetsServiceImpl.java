package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.gestion.model.*;
import fr.insee.rmes.magma.gestion.utils.DatasetByIdDTO;
import fr.insee.rmes.magma.gestion.utils.DatasetDTO;
import fr.insee.rmes.magma.gestion.utils.DistributionDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

import static fr.insee.rmes.magma.gestion.utils.LocalisedLabelUtils.createLangueContenu;
import static fr.insee.rmes.magma.gestion.utils.LocalisedLabelUtils.createListLangueContenu;

@Service
public class DatasetsServiceImpl implements DatasetsService {

    @Override
    public List<Dataset> transformDatasetDTOsToDataSets(List<DatasetDTO> dtos) {
        return dtos.stream().map(this::transformDatasetDTOToDataSet).toList();
    }

    private Dataset transformDatasetDTOToDataSet(DatasetDTO dto) {
        Dataset dataSet = new Dataset();

        dataSet.setLandingPage(null);
        dataSet.setModified(null);
        dataSet.setIssued(null);
        dataSet.setVersion(null);
        dataSet.setSpatialTemporal(null);
        dataSet.setDisseminationStatus(null);
        dataSet.setIdentifier(null);
        dataSet.setCatalogRecordCreated(null);
        dataSet.setCatalogRecordModified(null);
        dataSet.setCatalogRecordCreator(null);
        dataSet.setCatalogRecordContributor(null);
        dataSet.setNumObservations(null);
        dataSet.setNumSeries(null);
        dataSet.setSubtitle(null);
        dataSet.setAbstract(null);
        dataSet.setDescription(null);
        dataSet.setScopeNote(null);
        dataSet.setPublisher(null);
        dataSet.setKeyword(null);
        dataSet.setType(null);
        dataSet.setAccessRights(null);
        dataSet.setConfidentialityStatus(null);
        dataSet.setSpatial(null);
        dataSet.setTemporal(null);
        dataSet.setStructure(null);
        dataSet.setCreator(null);
        dataSet.setWasGeneratedBy(null);
        dataSet.setTheme(null);
        dataSet.setRelations(null);
        dataSet.setArchiveUnit(null);
        dataSet.setWasDerivedFrom(null);
        dataSet.setProcessStep(null);
        dataSet.setAccrualPeriodicity(null);
        dataSet.setTemporalResolution(null);
        dataSet.setSpatialResolution(null);
        dataSet.setStatisticalUnit(null);

        dataSet.setId(dto.id());
        dataSet.setUri(dto.uri());
        dataSet.setValidationState(dto.statutValidation());
        dataSet.setCatalogRecordCreated(dto.dateCreation() != null ? dto.dateCreation().toString() : null);
        dataSet.setCatalogRecordModified(dto.catalogRecordModified() != null ? dto.catalogRecordModified().toString() : null);
        dataSet.setTitle(createListLangueContenu(
                createLangueContenu(dto.titreLg1(),"fr"),
                createLangueContenu(dto.titreLg2(),"en")));
        return dataSet;
    }

    @Override
    public Dataset transformDatasetByIdDTOToDataSet(DatasetByIdDTO dto) {
        Dataset dataSet = new Dataset();

        dataSet.setLandingPage(null);
        dataSet.setModified(null);
        dataSet.setIssued(null);
        dataSet.setVersion(null);
        dataSet.setSpatialTemporal(null);
        dataSet.setDisseminationStatus(null);
        dataSet.setIdentifier(null);
        dataSet.setCatalogRecordCreated(null);
        dataSet.setCatalogRecordModified(null);
        dataSet.setCatalogRecordCreator(null);
        dataSet.setCatalogRecordContributor(null);
        dataSet.setNumObservations(null);
        dataSet.setNumSeries(null);
        dataSet.setSubtitle(null);
        dataSet.setAbstract(null);
        dataSet.setDescription(null);
        dataSet.setScopeNote(null);
        dataSet.setPublisher(null);
        dataSet.setKeyword(null);
        dataSet.setType(null);
        dataSet.setAccessRights(null);
        dataSet.setConfidentialityStatus(null);
        dataSet.setSpatial(null);
        dataSet.setTemporal(null);
        dataSet.setStructure(null);
        dataSet.setCreator(null);
        dataSet.setWasGeneratedBy(null);
        dataSet.setTheme(null);
        dataSet.setRelations(null);
        dataSet.setArchiveUnit(null);
        dataSet.setWasDerivedFrom(null);
        dataSet.setProcessStep(null);
        dataSet.setAccrualPeriodicity(null);
        dataSet.setTemporalResolution(null);
        dataSet.setSpatialResolution(null);
        dataSet.setStatisticalUnit(null);

        dataSet.setId(dto.id());
        dataSet.setUri(dto.uri());
        dataSet.setValidationState(dto.statutValidation());
        dataSet.setModified(dto.modified());
        dataSet.setIssued(dto.issued());
        dataSet.setVersion(dto.version());
        dataSet.setSpatialTemporal(dto.spatialTemporal());
        dataSet.setDisseminationStatus(dto.disseminationStatus());
        dataSet.setIdentifier(dto.identifier());
        dataSet.setCatalogRecordCreated(dto.catalogRecordCreated());
        dataSet.setCatalogRecordModified(dto.catalogRecordModified());
        dataSet.setCatalogRecordCreator(dto.catalogRecordCreator());
        dataSet.setCatalogRecordContributor(dto.catalogRecordContributor());

        if (StringUtils.hasText(dto.numObservations()) ) {
            dataSet.setNumObservations(Integer.parseInt(dto.numObservations()));
        }
        if (StringUtils.hasText(dto.numSeries()) ) {
            dataSet.setNumSeries(Integer.parseInt(dto.numSeries()));
        }

        if (StringUtils.hasText(dto.titleLg1()) ) {
            dataSet.setTitle(createListLangueContenu(
                    createLangueContenu(dto.titleLg1(), "fr"),
                    createLangueContenu(dto.titleLg2(), "en")));
        }
        if (StringUtils.hasText(dto.subtitleLg1())) {
            dataSet.setSubtitle(createListLangueContenu(
                    createLangueContenu(dto.subtitleLg1(),"fr"),
                    createLangueContenu(dto.subtitleLg2(),"en")));
        }
        if (StringUtils.hasText(dto.abstractLg1())){
            dataSet.setAbstract(createListLangueContenu(
                    createLangueContenu(dto.abstractLg1(),"fr"),
                    createLangueContenu(dto.abstractLg2(),"en")));
        }
        if (StringUtils.hasText(dto.descriptionLg1())){
            dataSet.setDescription(createListLangueContenu(
                    createLangueContenu(dto.descriptionLg1(),"fr"),
                    createLangueContenu(dto.descriptionLg2(),"en")));
        }
        if (StringUtils.hasText(dto.scopeNoteLg1())) {
            dataSet.setScopeNote(createListLangueContenu(
                    createLangueContenu(dto.scopeNoteLg1(), "fr"),
                    createLangueContenu(dto.scopeNoteLg2(), "en")));
        }
        if (StringUtils.hasText(dto.landingPageLg1())) {
            dataSet.setLandingPage(createListLangueContenu(
                    new DatasetLandingPageInner().lang("fr").url(dto.landingPageLg1()),
                    new DatasetLandingPageInner().lang("en").url(dto.landingPageLg2())));
        }

        if (dto.keywordLg1() != null && dto.keywordLg2() != null){
            dataSet.setKeyword(buildKeywords(dto.keywordLg1(), dto.keywordLg2()));
        }

        if (StringUtils.hasText(dto.idPublisher())) {
            dataSet.setPublisher(new DatasetCreatorInner()
                    .id(dto.idPublisher())
                    .label(createListLangueContenu(
                            createLangueContenu(dto.labelPublisherLg1(),"fr"),
                            createLangueContenu(dto.labelPublisherLg2(),"en"))));
        }

        if (StringUtils.hasText(dto.labeltypeLg1())) {
            dataSet.setType(createListLangueContenu(
                    createLangueContenu(dto.labeltypeLg1(),"fr"),
                    createLangueContenu(dto.labeltypeLg2(),"en")));
        }
        if (StringUtils.hasText(dto.labelaccessRightsLg1())) {
            dataSet.setAccessRights(createListLangueContenu(
                    createLangueContenu(dto.labelaccessRightsLg1(),"fr"),
                    createLangueContenu(dto.labelaccessRightsLg2(),"en")));
        }
        if (StringUtils.hasText(dto.labelconfidentialityStatusLg1())) {
            dataSet.setConfidentialityStatus(createListLangueContenu(
                    createLangueContenu(dto.labelconfidentialityStatusLg1(),"fr"),
                    createLangueContenu(dto.labelconfidentialityStatusLg2(),"en")));
        }


        if (StringUtils.hasText(dto.spatialId())) {
            dataSet.setSpatial(new DatasetCreatorInner()
                    .id(dto.spatialId())
                    .label(createListLangueContenu(
                            createLangueContenu(dto.labelspatialLg1(),"fr"),
                            createLangueContenu(dto.labelspatialLg2(),"en"))));
        }

        if (StringUtils.hasText(dto.startPeriod())) {
            dataSet.setTemporal(new DatasetTemporal()
                    .startPeriod(dto.startPeriod())
                    .endPeriod(dto.endPeriod()));
        }

        if (StringUtils.hasText(dto.structureUri())) {
            dataSet.setStructure(new DatasetStructure()
                    .uri(dto.structureUri())
                    .id(dto.structureId())
                    .dsd(dto.dsd()));
        }

        if (StringUtils.hasText(dto.creators())) {
            dataSet.setCreator(Arrays.stream(dto.creators().split("\\|"))
                    .filter(s -> !s.isBlank())
                    .map(raw -> {
                        String[] parts = raw.split("\\$", -1);
                        return new DatasetCreatorInner()
                                .id(parts.length > 0 ? parts[0] : null)
                                .label(createListLangueContenu(
                                        createLangueContenu(parts.length > 1 ? parts[1] : null, "fr"),
                                        createLangueContenu(parts.length > 2 ? parts[2] : null, "en")));
                    })
                    .toList());
        }

        if (StringUtils.hasText(dto.operationStat())) {
            dataSet.setWasGeneratedBy(Arrays.stream(dto.operationStat().split(","))
                    .filter(s -> !s.isBlank())
                    .map(uri -> new DatasetCreatorInner().id(uri))
                    .toList());
        }

        if (StringUtils.hasText(dto.names())) {
            dataSet.setTheme(Arrays.stream(dto.names().split(","))
                    .filter(s -> !s.isBlank())
                    .map(uri -> new DatasetThemeInner().uri(uri))
                    .toList());
        }

        if (StringUtils.hasText(dto.relations())) {
            dataSet.setRelations(Arrays.stream(dto.relations().split(","))
                    .filter(s -> !s.isBlank())
                    .toList());
        }

        if (StringUtils.hasText(dto.archiveUnits())) {
            dataSet.setArchiveUnit(Arrays.stream(dto.archiveUnits().split(","))
                    .filter(s -> !s.isBlank())
                    .map(uri -> new DatasetCreatorInner().id(uri))
                    .toList());
        }

        if (StringUtils.hasText(dto.wasDerivedFromS())) {
            DatasetWasDerivedFrom wasDerivedFrom = new DatasetWasDerivedFrom()
                    .datasets(Arrays.stream(dto.wasDerivedFromS().split(","))
                            .filter(s -> !s.isBlank())
                            .toList());
            if (StringUtils.hasText(dto.derivedDescriptionLg1())) {
                wasDerivedFrom.setDescription(createListLangueContenu(
                        createLangueContenu(dto.derivedDescriptionLg1(),"fr"),
                        createLangueContenu(dto.derivedDescriptionLg2(), "en")));
            }
            if (dto.derivedDescriptionLg1() != null && dto.derivedDescriptionLg1().isBlank()) {
                wasDerivedFrom.setDescription(createListLangueContenu(
                        createLangueContenu(dto.derivedDescriptionLg1(),"fr"),
                        createLangueContenu("", "en")));
            }
            dataSet.setWasDerivedFrom(wasDerivedFrom);
        }

        return dataSet;
    }

    @Override
    public List<Distribution> transformDistributionDTOsToDistributions(List<DistributionDTO> dtos) {
        Map<String, List<DistributionDTO>> grouped = new LinkedHashMap<>();
        for (DistributionDTO dto : dtos) {
            grouped.computeIfAbsent(dto.identifier(), k -> new ArrayList<>()).add(dto);
        }
        return grouped.values().stream().map(rows -> {
            DistributionDTO first = rows.get(0);
            Distribution d = new Distribution();
            d.setIdentifier(first.identifier());
            d.setUri(first.uri());
            d.setByteSize(first.byteSize());
            d.setCreated(first.created());
            d.setModified(first.modified());
            d.setFormat(first.format());
            d.setDownloadURL(rows.stream()
                    .map(DistributionDTO::downloadURL)
                    .filter(StringUtils::hasText)
                    .distinct()
                    .toList());
            if (StringUtils.hasText(first.titleLg1())) {
                d.setTitle(createListLangueContenu(
                        createLangueContenu(first.titleLg1(), "fr"),
                        createLangueContenu(first.titleLg2(), "en")));
            }
            if (StringUtils.hasText(first.descriptionLg1())) {
                d.setDescription(createListLangueContenu(
                        createLangueContenu(first.descriptionLg1(), "fr"),
                        createLangueContenu(first.descriptionLg2(), "en")));
            }
            return d;
        }).toList();
    }

    private List<LocalisedLabel> buildKeywords(String kwLg1, String kwLg2) {
        List<LocalisedLabel> keywords = new java.util.ArrayList<>();
        if (StringUtils.hasText(kwLg1)) {
            Arrays.stream(kwLg1.split(","))
                    .filter(s -> !s.isBlank())
                    .forEach(kw -> keywords.add(new LocalisedLabel().langue("fr").contenu(kw.trim())));
        }
        if (StringUtils.hasText(kwLg2)) {
            Arrays.stream(kwLg2.split(","))
                    .filter(s -> !s.isBlank())
                    .forEach(kw -> keywords.add(new LocalisedLabel().langue("en").contenu(kw.trim())));
        }
        return keywords;
    }
}