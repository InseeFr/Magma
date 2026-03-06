package fr.insee.rmes.dto.datasets;

import fr.insee.rmes.modelSwagger.dataset.Temporal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PatchDatasetDTOTest {

    @Test
    void shouldInitializeConstructor(){
        PatchDatasetDTO patchDatasetDTO = new PatchDatasetDTO(
                "issued",
                "modified",
                new Temporal("startPeriod","endPeriod"),
                45,
                78);

        assertNotNull(patchDatasetDTO);}

    @Test
    void shouldNotInitializeConstructor(){
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> new PatchDatasetDTO(null, null, null, null, null));
        assertTrue(exception.getMessage().contains("All required fields are null"));
}


}