package fr.insee.rmes.magma.diffusion.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class EndpointsUtils {

    private final String typesAutorises;

    public EndpointsUtils(@Value("${fr.insee.rmes.magma.api.geographie.types-autorises}") String typesAutorises) {
        this.typesAutorises = typesAutorises;
    }

    public String defineTerritoriesFilter(Enum<?> typeValue) {
        return typeValue == null
                ? Arrays.stream(this.typesAutorises.split(","))
                .map(t -> "\"" + t.trim() + "\"")
                .collect(Collectors.joining(", "))
                : "\"" + typeValue + "\"";
    }
}
