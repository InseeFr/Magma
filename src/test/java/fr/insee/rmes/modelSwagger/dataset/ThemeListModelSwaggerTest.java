package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ThemeListModelSwaggerTest {

    @Test
    void shouldCheckConstructorInitialization(){

        List<ThemeListModelSwagger> themeListModelSwaggerS = List.of(
                new ThemeListModelSwagger(),
                new ThemeListModelSwagger(),
                new ThemeListModelSwagger()
        );

        ThemeListModelSwagger firstThemeListModelSwagger = new ThemeListModelSwagger();
        ThemeListModelSwagger secondThemeListModelSwagger = new ThemeListModelSwagger(themeListModelSwaggerS);

        assertTrue(!firstThemeListModelSwagger.equals(secondThemeListModelSwagger) &&
                secondThemeListModelSwagger.getThemeListModelSwaggerS()==themeListModelSwaggerS);
    }

}