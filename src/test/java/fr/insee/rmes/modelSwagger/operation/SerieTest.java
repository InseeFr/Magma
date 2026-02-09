package fr.insee.rmes.modelSwagger.operation;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SerieTest {

    List<Label> labelSerie = List.of(new Label(),new Label());
    Frequence frequence = new Frequence();

    Serie firstSerie = new Serie();
    Serie secondSerie= new Serie("mockedSerieId",labelSerie,frequence,"mockedSeries");
    Serie thirdSerie= new Serie("mockedSerieId",labelSerie,"mockedSeries");

    @Test
    void shouldCheckSerieConstructors(){
        firstSerie.setFrequence(frequence);
        thirdSerie.withFrequence(frequence);

        assertTrue(firstSerie.getFrequence()== thirdSerie.getFrequence() &&
                firstSerie.getFrequence()==secondSerie.getFrequence() &&
                firstSerie.getFrequence()==frequence);
    }

    @Test
    void shouldCheckLabelSerie(){
        assertTrue(secondSerie.getLabelSerie()==thirdSerie.getLabelSerie() &&
                firstSerie.getLabelSerie()==null && secondSerie.getLabelSerie()==labelSerie);
    }


}