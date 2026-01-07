package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.LocalisedLabel;
import fr.insee.rmes.magma.diffusion.model.LocalisedText;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class EndpointsUtils {

    private EndpointsUtils() {}

    public static <E> ResponseEntity<List<E>> toResponseEntity(List<E> result) {
        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        MediaType contentType = MediaType.APPLICATION_JSON;
        if (isXmlRequest()) {
            contentType = MediaType.APPLICATION_XML;
        }

        return ResponseEntity.ok()
                .contentType(contentType)
                .body(result);
    }

    private static boolean isXmlRequest() {
        return false;
    }

    public static <E> ResponseEntity<E> toResponseEntity(E result) {
        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        MediaType contentType = MediaType.APPLICATION_JSON;
        if (isXmlRequest()) {
            contentType = MediaType.APPLICATION_XML;
        }

        return ResponseEntity.ok()
                .contentType(contentType)
                .body(result);
    }

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

    public static List<LocalisedText> createListLangueTexte(LocalisedText langueText1, LocalisedText langueText2) {
        List<LocalisedText> list = new ArrayList<>();
        if (langueText1 != null) {
            list.add(langueText1);
        }
        if (langueText2 != null) {
            list.add(langueText2);
        }

        return list;
    }

    public static LocalisedText createLangueText(String texte, String langue) {
        LocalisedText langueText = new LocalisedText();
        langueText.setTexte(texte);
        langueText.setLangue(langue);
        return langueText;
    }
}