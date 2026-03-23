package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.TerritoireBase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EndpointsUtils {

    public static <E> ResponseEntity<List<E>> toResponseEntity(List<E> result) {
        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .body(result);
    }

    public String defineTerritoriesFilter(String typeValue) {
        return typeValue == null
                ? Arrays.stream(TerritoireBase.TypeEnum.values())
                        .map(t -> "\"" + t.getValue() + "\"")
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