package fr.insee.rmes.magma.diffusion.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EndpointsUtils {

    private static String typesAutorises;

    @Value("${fr.insee.rmes.magma.api.geographie.types-autorises}")
    public void setTypesAutorises(String typesAutorises) {
        EndpointsUtils.typesAutorises = typesAutorises;
    }

    public static <E> ResponseEntity<List<E>> toResponseEntity(List<E> result) {
        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .body(result);
    }

    public static String defineTerritoriesFilter(String typeValue) {
        return typeValue == null
                ? Arrays.stream(typesAutorises.split(","))
                        .map(t -> "\"" + t.trim() + "\"")
                        .collect(Collectors.joining(", "))
                : "\"" + typeValue + "\"";
    }

    public static <E> ResponseEntity<E> toResponseEntity(E result) {
        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .body(result);
    }



}