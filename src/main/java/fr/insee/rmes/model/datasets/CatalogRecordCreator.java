package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogRecordCreator {

    private String catalogRecordCreator;

    public CatalogRecordCreator(String catalogRecordCreator) {
        this.catalogRecordCreator = catalogRecordCreator;
    }

    @Override
    public String toString() {
        return catalogRecordCreator ;
    }
}
