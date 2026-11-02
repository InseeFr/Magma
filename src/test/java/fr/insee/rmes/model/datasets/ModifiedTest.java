package fr.insee.rmes.model.datasets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ModifiedTest {

    @ParameterizedTest
    @ValueSource(strings = { "element1", "element2","element3" })
    void shouldReturnStringWhenToString(String string){
        Modified modified  = new Modified(string);
        assertEquals(string,modified.toString());
    }
}