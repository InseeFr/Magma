package fr.insee.rmes.modelSwagger.dataset;

import java.util.List;

public class ThemeListModelSwagger {
    private List<ThemeListModelSwagger> themeListModelSwaggerS;

    public ThemeListModelSwagger() {}

    public ThemeListModelSwagger(List<ThemeListModelSwagger> themeListModelSwaggerS) {
        this.themeListModelSwaggerS=themeListModelSwaggerS;
    }

    public List<ThemeListModelSwagger> getThemeListModelSwaggerS () {
        return themeListModelSwaggerS;
    }
}
