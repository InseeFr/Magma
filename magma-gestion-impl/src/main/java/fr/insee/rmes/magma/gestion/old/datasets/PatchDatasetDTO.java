package fr.insee.rmes.magma.gestion.old.datasets;

import fr.insee.rmes.magma.gestion.old.modelSwagger.dataset.Temporal;
import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;
import org.springframework.http.HttpStatus;


 public record PatchDatasetDTO(
         String issued,
         String modified,
         Temporal temporal,
         Integer numObservations,
         Integer numSeries) {

    public PatchDatasetDTO {
        if (issued == null && modified == null && temporal == null && numObservations == null && numSeries == null){
            try {
                throw new RmesException(HttpStatus.BAD_REQUEST,"All required fields are null","Fill in at least one valid field");
            } catch (RmesException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
