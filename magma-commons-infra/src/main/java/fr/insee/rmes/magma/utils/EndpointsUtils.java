package fr.insee.rmes.magma.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EndpointsUtils {

    private final String typesAutorises;

    public EndpointsUtils(@Value("${fr.insee.rmes.magma.api.geographie.types-autorises}") String typesAutorises) {
        this.typesAutorises = typesAutorises;
    }
    public static <E> ResponseEntity<List<E>> toResponseEntity(List<E> result) {
        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .body(result);
    }

    public String defineTerritoriesFilter(Enum<?> typeValue) {
        return typeValue == null
                ? Arrays.stream(this.typesAutorises.split(","))
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