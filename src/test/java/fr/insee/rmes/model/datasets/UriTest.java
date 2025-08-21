package fr.insee.rmes.model.datasets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class UriTest {

    @ParameterizedTest
    @ValueSource(strings = { "element1", "element2","element3" })
    void shouldReturnStringWhenToString(String string){
        Uri uri  = new Uri(string);
        assertEquals(string,uri.toString());
    }

}