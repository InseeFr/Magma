package fr.insee.rmes.magma.utils;

import fr.insee.rmes.magma.model.LocalisedContenu;
import fr.insee.rmes.magma.model.LocalisedUrl;

import java.util.ArrayList;
import java.util.List;

public class LocalisedLabelUtils {
    private LocalisedLabelUtils() {
        /* This utility class should not be instantiated */
    }

    public static  List<LocalisedContenu> createListLangueContenu(LocalisedContenu langueContenu1, LocalisedContenu langueContenu2) {
        List<LocalisedContenu> list = new ArrayList<>();
        if (langueContenu1 != null) {
            list.add(langueContenu1);
        }
        if (langueContenu2 != null) {
            list.add(langueContenu2);
        }
        return list;
    }

    public static  List<LocalisedUrl> createListLangueUrl(LocalisedUrl langueUrl1, LocalisedUrl langueUrl2) {
        List<LocalisedUrl> list = new ArrayList<>();
        if (langueUrl1 != null) {
            list.add(langueUrl1);
        }
        if (langueUrl2 != null) {
            list.add(langueUrl2);
        }
        return list;
    }

    public static LocalisedContenu createLangueContenu(String contenu, String langue) {
        LocalisedContenu langueContenu = new LocalisedContenu();
        langueContenu.setContenu(contenu);
        langueContenu.setLangue(langue);
        return langueContenu;
    }
}
