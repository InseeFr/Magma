package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogRecordCreated {
    private String catalogRecordCreated;

    public CatalogRecordCreated(String catalogRecordCreated) {
        this.catalogRecordCreated = catalogRecordCreated;
    }
    @Override
    public String toString() {
        return catalogRecordCreated ;
    }
}
