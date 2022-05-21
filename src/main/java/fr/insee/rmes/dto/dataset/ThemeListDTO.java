package fr.insee.rmes.dto.dataset;

import java.util.List;

public class ThemeListDTO {
    private List<ThemeListDTO> themeListDTOS;

    public ThemeListDTO() {}

    public ThemeListDTO(List<ThemeListDTO> themeListDTOS) {
        this.themeListDTOS=themeListDTOS;
    }

    public List<ThemeListDTO> getThemeListDTOS () {
        return themeListDTOS;
    }
}
