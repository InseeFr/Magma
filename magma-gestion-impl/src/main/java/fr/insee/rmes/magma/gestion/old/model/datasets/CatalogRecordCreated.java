package fr.insee.rmes.magma.gestion.old.model.datasets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(onConstructor_={@JsonCreator})
public class CatalogRecordCreated{
    @JsonValue
    private final String value;
}