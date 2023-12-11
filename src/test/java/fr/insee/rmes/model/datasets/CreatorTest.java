package fr.insee.rmes.model.datasets;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreatorTest {

    @Test
    void testToString() {
        var creator=new Creator(List.of("Leonardo", "Michelangelo", "Donatello", "Rafaello"));
        assertEquals("[Leonardo, Michelangelo, Donatello, Rafaello]", creator.toString());
    }
}