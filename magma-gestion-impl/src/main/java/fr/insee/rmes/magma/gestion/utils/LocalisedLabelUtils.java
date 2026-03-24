package fr.insee.rmes.magma.gestion.utils;

import fr.insee.rmes.magma.gestion.model.StructureByIdMesuresInnerLabelInner;

import java.util.ArrayList;
import java.util.List;

public class LocalisedLabelUtils {
    private LocalisedLabelUtils() {
        /* This utility class should not be instantiated */
    }


    public static List<StructureByIdMesuresInnerLabelInner> createListLangueContenu(StructureByIdMesuresInnerLabelInner langueContenu1, StructureByIdMesuresInnerLabelInner langueContenu2) {
        List<StructureByIdMesuresInnerLabelInner> list = new ArrayList<>();
        if (langueContenu1 != null) {
            list.add(langueContenu1);
        }
        if (langueContenu2 != null) {
            list.add(langueContenu2);
        }

        return list;
    }

    public static StructureByIdMesuresInnerLabelInner createLangueContenu(String contenu, String langue) {
        StructureByIdMesuresInnerLabelInner langueContenu = new StructureByIdMesuresInnerLabelInner();
        langueContenu.setContenu(contenu);
        langueContenu.setLangue(langue);
        return langueContenu;
    }
}
