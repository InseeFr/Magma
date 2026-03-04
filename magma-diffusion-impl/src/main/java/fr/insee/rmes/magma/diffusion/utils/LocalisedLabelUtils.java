package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.LocalisedLabel;

import java.util.ArrayList;
import java.util.List;

public class LocalisedLabelUtils {

    public static List<LocalisedLabel> createListLangueContenu(LocalisedLabel langueContenu1, LocalisedLabel langueContenu2) {
        List<LocalisedLabel> list = new ArrayList<>();
        if (langueContenu1 != null) {
            list.add(langueContenu1);
        }
        if (langueContenu2 != null) {
            list.add(langueContenu2);
        }

        return list;
    }

    public static LocalisedLabel createLangueContenu(String contenu, String langue) {
        LocalisedLabel langueContenu = new LocalisedLabel();
        langueContenu.setContenu(contenu);
        langueContenu.setLangue(langue);
        return langueContenu;
    }
}
