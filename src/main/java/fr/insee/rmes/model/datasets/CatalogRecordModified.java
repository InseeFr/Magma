package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogRecordModified {
    private String catalogRecordModified;

    public CatalogRecordModified(String catalogRecordModified) {
        this.catalogRecordModified = catalogRecordModified;
    }
    @Override
    public String toString() {
        return catalogRecordModified ;
    }
}
