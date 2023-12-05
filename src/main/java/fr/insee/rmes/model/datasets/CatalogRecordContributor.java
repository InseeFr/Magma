package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogRecordContributor {

    private String catalogRecordContributor;

    public CatalogRecordContributor(String catalogRecordContributor) {
        this.catalogRecordContributor = catalogRecordContributor;
    }

    @Override
    public String toString() {
        return catalogRecordContributor ;
    }
}
