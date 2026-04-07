package fr.insee.rmes.magma.gestion.utils;

import fr.insee.rmes.magma.gestion.model.LocalisedLabel;

import java.util.ArrayList;
import java.util.List;

public class LocalisedLabelUtils {
    private LocalisedLabelUtils() {
        /* This utility class should not be instantiated */
    }

    public static <T> List<T> createListLangueContenu(T langueContenu1, T langueContenu2) {
        List<T> list = new ArrayList<>();
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
