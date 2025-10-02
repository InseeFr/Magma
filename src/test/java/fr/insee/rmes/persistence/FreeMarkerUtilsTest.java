package fr.insee.rmes.persistence;

import fr.insee.rmes.utils.exceptions.RmesException;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class FreeMarkerUtilsTest {

    @Test
    void shouldReturnRmesExceptionWhenBuildRequest(){
        FreeMarkerUtils freeMarkerUtils = new FreeMarkerUtils();
        RmesException exception = assertThrows(RmesException.class, ()-> freeMarkerUtils.buildRequest("root","kalmar",new HashMap<>()));
        assertNotNull(exception.getDetails());
    }
}