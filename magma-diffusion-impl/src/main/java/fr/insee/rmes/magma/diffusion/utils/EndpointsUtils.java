package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.LangueContenu;
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

    public static List<LangueContenu> createListLangueContenu(LangueContenu langueContenu1, LangueContenu langueContenu2) {
        List<LangueContenu> list = new ArrayList<>();
        if (langueContenu1 != null) {
            list.add(langueContenu1);
        }
        if (langueContenu2 != null) {
            list.add(langueContenu2);
        }

        return list;
    }

    public static LangueContenu createLangueContenu(String contenu, String langue) {
        LangueContenu langueContenu = new LangueContenu();
        langueContenu.setContenu(contenu);
        langueContenu.setLangue(langue);
        return langueContenu;
    }
}