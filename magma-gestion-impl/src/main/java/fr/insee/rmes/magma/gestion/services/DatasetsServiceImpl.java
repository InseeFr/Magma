package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.gestion.model.*;
import fr.insee.rmes.magma.gestion.utils.DatasetByIdDTO;
import fr.insee.rmes.magma.gestion.utils.DatasetByIdSummaryDTO;
import fr.insee.rmes.magma.gestion.utils.DatasetDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
                new IdLabelLabelInner().lang("fr").content(dto.titreLg1()),
                new IdLabelLabelInner().lang("en").content(dto.titreLg2())));
        return dataSet;
    }

    @Override
    public DataSet transformDatasetByIdDTOToDataSet(DatasetByIdDTO dto) {
        DataSet dataSet = new DataSet();
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

        dataSet.setTitle(createListLangueContenu(
                new IdLabelLabelInner().lang("fr").content(dto.titleLg1()),
                new IdLabelLabelInner().lang("en").content(dto.titleLg2())));
        dataSet.setSubtitle(createListLangueContenu(
                new IdLabelLabelInner().lang("fr").content(dto.subtitleLg1()),
                new IdLabelLabelInner().lang("en").content(dto.subtitleLg2())));
        dataSet.setAbstract(createListLangueContenu(
                new IdLabelLabelInner().lang("fr").content(dto.abstractLg1()),
                new IdLabelLabelInner().lang("en").content(dto.abstractLg2())));
        dataSet.setDescription(createListLangueContenu(
                new IdLabelLabelInner().lang("fr").content(dto.descriptionLg1()),
                new IdLabelLabelInner().lang("en").content(dto.descriptionLg2())));
        dataSet.setScopeNote(createListLangueContenu(
                new IdLabelLabelInner().lang("fr").content(dto.scopeNoteLg1()),
                new IdLabelLabelInner().lang("en").content(dto.scopeNoteLg2())));

        if (dto.landingPageLg1() != null && !dto.landingPageLg1().isBlank()) {
            dataSet.setLandingPage(createListLangueContenu(
                    new DataSetLandingPageInner().lang("fr").url(dto.landingPageLg1()),
                    new DataSetLandingPageInner().lang("en").url(dto.landingPageLg2())));
        }

        dataSet.setKeyword(buildKeywords(dto.keywordLg1(), dto.keywordLg2()));

        if (dto.idPublisher() != null && !dto.idPublisher().isBlank()) {
            dataSet.setPublisher(new DataSetCreatorInner()
                    .id(dto.idPublisher())
                    .label(createListLangueContenu(
                            new IdLabelLabelInner().lang("fr").content(dto.labelPublisherLg1()),
                            new IdLabelLabelInner().lang("en").content(dto.labelPublisherLg2()))));
        }

        dataSet.setType(createListLangueContenu(
                new IdLabelLabelInner().lang("fr").content(dto.labeltypeLg1()),
                new IdLabelLabelInner().lang("en").content(dto.labeltypeLg2())));
        dataSet.setAccessRights(createListLangueContenu(
                new IdLabelLabelInner().lang("fr").content(dto.labelaccessRightsLg1()),
                new IdLabelLabelInner().lang("en").content(dto.labelaccessRightsLg2())));
        dataSet.setConfidentialityStatus(createListLangueContenu(
                new IdLabelLabelInner().lang("fr").content(dto.labelconfidentialityStatusLg1()),
                new IdLabelLabelInner().lang("en").content(dto.labelconfidentialityStatusLg2())));

        if (dto.spatialId() != null && !dto.spatialId().isBlank()) {
            dataSet.setSpatial(new DataSetCreatorInner()
                    .id(dto.spatialId())
                    .label(createListLangueContenu(
                            new IdLabelLabelInner().lang("fr").content(dto.labelspatialLg1()),
                            new IdLabelLabelInner().lang("en").content(dto.labelspatialLg2()))));
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
                                        new IdLabelLabelInner().lang("fr").content(parts.length > 1 ? parts[1] : null),
                                        new IdLabelLabelInner().lang("en").content(parts.length > 2 ? parts[2] : null)));
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
                        new IdLabelLabelInner().lang("fr").content(dto.derivedDescriptionLg1()),
                        new IdLabelLabelInner().lang("en").content(dto.derivedDescriptionLg2())));
            }
            dataSet.setWasDerivedFrom(wasDerivedFrom);
        }

        return dataSet;
    }

    @Override
    public DataSet transformDatasetByIdSummaryDTOToDataSet(DatasetByIdSummaryDTO dto) {
        DataSet dataSet = new DataSet();
        dataSet.setId(dto.id());
        dataSet.setUri(dto.uri());
        dataSet.setCatalogRecordModified(dto.catalogRecordModified());
        return dataSet;
    }

    private List<IdLabelLabelInner> buildKeywords(String kwLg1, String kwLg2) {
        List<IdLabelLabelInner> keywords = new java.util.ArrayList<>();
        if (kwLg1 != null && !kwLg1.isBlank()) {
            Arrays.stream(kwLg1.split(","))
                    .filter(s -> !s.isBlank())
                    .forEach(kw -> keywords.add(new IdLabelLabelInner().lang("fr").content(kw.trim())));
        }
        if (kwLg2 != null && !kwLg2.isBlank()) {
            Arrays.stream(kwLg2.split(","))
                    .filter(s -> !s.isBlank())
                    .forEach(kw -> keywords.add(new IdLabelLabelInner().lang("en").content(kw.trim())));
        }
        return keywords;
    }
}