package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UriTest {

    @Test
    void shouldReturnUriToString(){
        Uri uri = new Uri("mockedUri");
        assertEquals("mockedUri",uri.toString());
    }
}