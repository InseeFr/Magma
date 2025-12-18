package fr.insee.rmes.magma.gestion.old.modelSwagger.dataset;

import fr.insee.rmes.magma.gestion.old.model.datasets.Id;
import fr.insee.rmes.magma.gestion.old.model.datasets.Uri;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString

public class Distribution {
    private Id id;

    private Uri uri;

}
