package fr.insee.rmes.magma.diffusion.utils;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
}