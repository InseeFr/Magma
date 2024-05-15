package fr.insee.rmes.mapper.dataset;

import fr.insee.rmes.dto.datasets.PatchDatasetDTO;
import fr.insee.rmes.model.datasets.PatchDataset;

public class PatchDatasetMapper {

    public static PatchDataset mapToPatchDataset(PatchDatasetDTO patchDatasetDTO){
        PatchDataset patchDataset = PatchDataset.builder()
                .numObservations(patchDatasetDTO.getNumObservations())
                .numSeries(patchDatasetDTO.getNumSeries())
                .issued(patchDatasetDTO.getIssued())
                .modified(patchDatasetDTO.getModified())
                .temporal(patchDatasetDTO.getTemporal())
                .build();
        return patchDataset;
    }

}
