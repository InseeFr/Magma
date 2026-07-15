package fr.insee.rmes.magma.results;

import fr.insee.rmes.magma.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;

import java.util.List;

public record ListResult<E>(List<E> result) {
    public ResponseEntity<List<E>> toResponseEntity() {
        return EndpointsUtils.toResponseEntity(result);
    }
}
