package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemporalTest {

    Temporal temporal = new Temporal("mockedStartPeriod","mockedEndPeriod");

    @Test
    void shouldCheckStartPeriodMethods(){
        temporal.setStartPeriod("newPeriod");
        assertEquals("newPeriod", temporal.getStartPeriod());
    }

    @Test
    void shouldCheckEndPeriodMethods(){
        temporal.setEndPeriod("newPeriod");
        assertEquals("newPeriod", temporal.getEndPeriod());
    }

}