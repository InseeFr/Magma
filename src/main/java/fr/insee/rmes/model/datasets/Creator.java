package fr.insee.rmes.model.datasets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import java.util.List;

@Getter
@RequiredArgsConstructor(onConstructor_={@JsonCreator})
@EqualsAndHashCode
public class Creator {

    @NonNull
    @JsonValue
    private final List<String> creators;

    @Override
    public String toString() {
        return creators.toString();
    }
}
