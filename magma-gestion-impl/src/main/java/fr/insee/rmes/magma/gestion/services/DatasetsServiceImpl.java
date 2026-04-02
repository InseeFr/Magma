package fr.insee.rmes.magma.gestion.services;

import fr.insee.rmes.magma.gestion.model.DataSet;
import fr.insee.rmes.magma.gestion.model.IdLabelLabelInner;
import fr.insee.rmes.magma.gestion.utils.DatasetDTO;
import org.springframework.stereotype.Service;

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
}