package fr.insee.rmes.dto.datasets;

import fr.insee.rmes.modelSwagger.dataset.Temporal;
import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class PatchDatasetDTOTest {

    @Test
    void shouldThrowAnExceptionWhenPatchDatasetDTO(){
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new PatchDatasetDTO(null,null,null,null,null));
        assertEquals("fr.insee.rmes.utils.exceptions.RmesException: All required fields are null",exception.getMessage());
    }

    @Test
    void shouldNotThrowAnExceptionWhenPatchDatasetDTO(){
        String issued = "mockedIssued";
        String modified = "mockedModified";
        Temporal temporal = new Temporal("beginPeriod","endPeriod");
        int numObservation =100;
        int numSeries =12;
        PatchDatasetDTO patchDatasetDTO = new PatchDatasetDTO(issued,modified,temporal,numObservation,numSeries);

        boolean isIssuedValueKnown = Objects.equals(issued,patchDatasetDTO.issued());
        boolean isModifiedValueKnown = Objects.equals(modified,patchDatasetDTO.modified());
        boolean isTemporalValueKnown = Objects.equals(temporal,patchDatasetDTO.temporal());
        boolean isNumObservationKnown = Objects.equals(numObservation,patchDatasetDTO.numObservations());
        boolean isNumSeriesKnown = Objects.equals(numSeries,patchDatasetDTO.numSeries());

        assertTrue(isIssuedValueKnown &&
                isModifiedValueKnown &&
                isTemporalValueKnown &&
                isNumObservationKnown &&
                isNumSeriesKnown);
    }

}