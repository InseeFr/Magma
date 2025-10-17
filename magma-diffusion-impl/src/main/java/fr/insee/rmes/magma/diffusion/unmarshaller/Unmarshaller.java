package fr.insee.rmes.magma.diffusion.unmarshaller;

import fr.insee.rmes.magma.diffusion.queryexecutor.Csv;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface Unmarshaller {
        <G> Optional<G> unmarshal(@NonNull Csv csv, @NonNull Class<G> targetClass);

        <G> G unmarshalOrNull(@NonNull Csv csv, @NonNull Class<G> targetClass);

        <G> List<G> unmarshalList(@NonNull Csv csv, @NonNull Class<G> targetClass);
}
