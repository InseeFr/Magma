package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UriTest {

    @Test
    void shouldReturnStringWhenToString(){
        Uri uri1  = new Uri("mockedUri");
        assertEquals("mockedUri",uri1.toString());
    }

}