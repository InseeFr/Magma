package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.gestion.model.*;
import fr.insee.rmes.magma.gestion.utils.DatasetByIdDTO;
import fr.insee.rmes.magma.gestion.utils.DatasetByIdSummaryDTO;
import fr.insee.rmes.magma.gestion.utils.DatasetDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static fr.insee.rmes.magma.gestion.utils.LocalisedLabelUtils.createLangueContenu;
import static fr.insee.rmes.magma.gestion.utils.LocalisedLabelUtils.createListLangueContenu;

@Service
public class DatasetsServiceImpl implements DatasetsService {

    @Override
    public List<DataSet> transformDatasetDTOsToDataSets(List<DatasetDTO> dtos) {
        return dtos.stream().map(this::transformDatasetDTOToDataSet).toList();
    }

    private DataSet transformDatasetDTOToDataSet(DatasetDTO dto) {
        DataSet dataSet = new DataSet();
        dataSet.setId(dto.id());
        dataSet.setUri(dto.uri());
        dataSet.setValidationState(dto.statutValidation());
        dataSet.setCatalogRecordCreated(dto.dateCreation() != null ? dto.dateCreation().toString() : null);
        dataSet.setCatalogRecordModified(dto.dateMiseAJour() != null ? dto.dateMiseAJour().toString() : null);
        dataSet.setTitle(createListLangueContenu(
                createLangueContenu(dto.titreLg1(),"fr"),
                createLangueContenu(dto.titreLg2(),"en")));
        return dataSet;
    }

    @Override
    public DataSet transformDatasetByIdDTOToDataSet(DatasetByIdDTO dto) {
        DataSet dataSet = new DataSet();

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

        if (dto.numObservations() != null && !dto.numObservations().isBlank()) {
            dataSet.setNumObservations(Integer.parseInt(dto.numObservations()));
        }
        if (dto.numSeries() != null && !dto.numSeries().isBlank()) {
            dataSet.setNumSeries(Integer.parseInt(dto.numSeries()));
        }

        if (dto.titleLg1() != null && !dto.titleLg1().isBlank()) {
            dataSet.setTitle(createListLangueContenu(
                    createLangueContenu(dto.titleLg1(), "fr"),
                    createLangueContenu(dto.titleLg2(), "en")));
        }
        if (dto.subtitleLg1() != null && !dto.subtitleLg1().isBlank()) {
            dataSet.setSubtitle(createListLangueContenu(
                    createLangueContenu(dto.subtitleLg1(),"fr"),
                    createLangueContenu(dto.subtitleLg2(),"en")));
        }
        if (dto.abstractLg1() != null && !dto.abstractLg1().isBlank()){
            dataSet.setAbstract(createListLangueContenu(
                    createLangueContenu(dto.abstractLg1(),"fr"),
                    createLangueContenu(dto.abstractLg2(),"en")));
        }
        if (dto.descriptionLg1() != null && !dto.descriptionLg1().isBlank()){
            dataSet.setDescription(createListLangueContenu(
                    createLangueContenu(dto.descriptionLg1(),"fr"),
                    createLangueContenu(dto.descriptionLg2(),"en")));
        }
        if (dto.scopeNoteLg1() != null && !dto.scopeNoteLg1().isBlank()) {
            dataSet.setScopeNote(createListLangueContenu(
                    createLangueContenu(dto.scopeNoteLg1(), "fr"),
                    createLangueContenu(dto.scopeNoteLg2(), "en")));
        }
        if (dto.landingPageLg1() != null && !dto.landingPageLg1().isBlank()) {
            dataSet.setLandingPage(createListLangueContenu(
                    new DataSetLandingPageInner().lang("fr").url(dto.landingPageLg1()),
                    new DataSetLandingPageInner().lang("en").url(dto.landingPageLg2())));
        }

        if (dto.keywordLg1() != null && dto.keywordLg2() != null){
            dataSet.setKeyword(buildKeywords(dto.keywordLg1(), dto.keywordLg2()));
        }

        if (dto.idPublisher() != null && !dto.idPublisher().isBlank()) {
            dataSet.setPublisher(new DataSetCreatorInner()
                    .id(dto.idPublisher())
                    .label(createListLangueContenu(
                            createLangueContenu(dto.labelPublisherLg1(),"fr"),
                            createLangueContenu(dto.labelPublisherLg2(),"en"))));
        }

        if (dto.labeltypeLg1() != null && !dto.labeltypeLg1().isBlank()) {
            dataSet.setType(createListLangueContenu(
                    createLangueContenu(dto.labeltypeLg1(),"fr"),
                    createLangueContenu(dto.labeltypeLg2(),"en")));
        }
        if (dto.labelaccessRightsLg1() != null && !dto.labelaccessRightsLg1().isBlank()) {
            dataSet.setAccessRights(createListLangueContenu(
                    createLangueContenu(dto.labelaccessRightsLg1(),"fr"),
                    createLangueContenu(dto.labelaccessRightsLg2(),"en")));
        }
        if (dto.labelconfidentialityStatusLg1() != null && !dto.labelconfidentialityStatusLg1().isBlank()) {
            dataSet.setConfidentialityStatus(createListLangueContenu(
                    createLangueContenu(dto.labelconfidentialityStatusLg1(),"fr"),
                    createLangueContenu(dto.labelconfidentialityStatusLg2(),"en")));
        }


        if (dto.spatialId() != null && !dto.spatialId().isBlank()) {
            dataSet.setSpatial(new DataSetCreatorInner()
                    .id(dto.spatialId())
                    .label(createListLangueContenu(
                            createLangueContenu(dto.labelspatialLg1(),"fr"),
                            createLangueContenu(dto.labelspatialLg2(),"en"))));
        }

        if (dto.startPeriod() != null && !dto.startPeriod().isBlank()) {
            dataSet.setTemporal(new DataSetTemporal()
                    .startPeriod(dto.startPeriod())
                    .endPeriod(dto.endPeriod()));
        }

        if (dto.structureUri() != null && !dto.structureUri().isBlank()) {
            dataSet.setStructure(new DataSetStructure()
                    .uri(dto.structureUri())
                    .id(dto.structureId())
                    .dsd(dto.dsd()));
        }

        if (dto.creators() != null && !dto.creators().isBlank()) {
            dataSet.setCreator(Arrays.stream(dto.creators().split("\\|"))
                    .filter(s -> !s.isBlank())
                    .map(raw -> {
                        String[] parts = raw.split("\\$", -1);
                        return new DataSetCreatorInner()
                                .id(parts.length > 0 ? parts[0] : null)
                                .label(createListLangueContenu(
                                        createLangueContenu(parts.length > 1 ? parts[1] : null, "fr"),
                                        createLangueContenu(parts.length > 2 ? parts[2] : null, "en")));
                    })
                    .toList());
        }

        if (dto.operationStat() != null && !dto.operationStat().isBlank()) {
            dataSet.setWasGeneratedBy(Arrays.stream(dto.operationStat().split(","))
                    .filter(s -> !s.isBlank())
                    .map(uri -> new DataSetCreatorInner().id(uri))
                    .toList());
        }

        if (dto.names() != null && !dto.names().isBlank()) {
            dataSet.setTheme(Arrays.stream(dto.names().split(","))
                    .filter(s -> !s.isBlank())
                    .map(uri -> new DataSetThemeInner().uri(uri))
                    .toList());
        }

        if (dto.relations() != null && !dto.relations().isBlank()) {
            dataSet.setRelations(Arrays.stream(dto.relations().split(","))
                    .filter(s -> !s.isBlank())
                    .toList());
        }

        if (dto.archiveUnits() != null && !dto.archiveUnits().isBlank()) {
            dataSet.setArchiveUnit(Arrays.stream(dto.archiveUnits().split(","))
                    .filter(s -> !s.isBlank())
                    .map(uri -> new DataSetCreatorInner().id(uri))
                    .toList());
        }

        if (dto.wasDerivedFromS() != null && !dto.wasDerivedFromS().isBlank()) {
            DataSetWasDerivedFrom wasDerivedFrom = new DataSetWasDerivedFrom()
                    .datasets(Arrays.stream(dto.wasDerivedFromS().split(","))
                            .filter(s -> !s.isBlank())
                            .toList());
            if (dto.derivedDescriptionLg1() != null && !dto.derivedDescriptionLg1().isBlank()) {
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
    public DataSet transformDatasetByIdSummaryDTOToDataSet(DatasetByIdSummaryDTO dto) {
        DataSet dataSet = new DataSet();
        dataSet.setCreator(null);
        dataSet.setTitle(null);
        dataSet.setSubtitle(null);
        dataSet.setDescription(null);
        dataSet.setScopeNote(null);
        dataSet.setWasGeneratedBy(null);
        dataSet.setType(null);
        dataSet.setArchiveUnit(null);
        dataSet.setAccessRights(null);
        dataSet.setConfidentialityStatus(null);
        dataSet.setTheme(null);
        dataSet.setLandingPage(null);
        dataSet.setTemporalResolution(null);
        dataSet.setSpatialResolution(null);
        dataSet.setStatisticalUnit(null);
        dataSet.setRelations(null);
        dataSet.setKeyword(null);
        dataSet.setAbstract(null);

        dataSet.setId(dto.id());
        dataSet.setUri(dto.uri());
        dataSet.setCatalogRecordModified(dto.catalogRecordModified());
        return dataSet;
    }

    private List<LocalisedLabel> buildKeywords(String kwLg1, String kwLg2) {
        List<LocalisedLabel> keywords = new java.util.ArrayList<>();
        if (kwLg1 != null && !kwLg1.isBlank()) {
            Arrays.stream(kwLg1.split(","))
                    .filter(s -> !s.isBlank())
                    .forEach(kw -> keywords.add(new LocalisedLabel().langue("fr").contenu(kw.trim())));
        }
        if (kwLg2 != null && !kwLg2.isBlank()) {
            Arrays.stream(kwLg2.split(","))
                    .filter(s -> !s.isBlank())
                    .forEach(kw -> keywords.add(new LocalisedLabel().langue("en").contenu(kw.trim())));
        }
        return keywords;
    }
}