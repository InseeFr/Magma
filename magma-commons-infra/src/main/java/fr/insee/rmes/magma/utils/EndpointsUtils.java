package fr.insee.rmes.magma.utils;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class EndpointsUtils {


    public static <E> ResponseEntity<List<E>> toResponseEntity(List<E> result) {
        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .body(result);
    }

    public static <E> ResponseEntity<E> toResponseEntity(E result) {
        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .body(result);
    }



}