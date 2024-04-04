package fr.insee.rmes.stubs;

import fr.insee.rmes.model.datasets.Id;
import fr.insee.rmes.model.datasets.Uri;
import fr.insee.rmes.modelSwagger.dataset.DataSetModelSwagger;
import fr.insee.rmes.services.datasets.DataSetsImpl;

import static fr.insee.rmes.services.utils.DataSetsUtilsTest.URI_TEST;

public class DataSetsImplStub extends DataSetsImpl {

    @Override
    protected DataSetModelSwagger findDataSetModelSwagger(String id) {
        Id id1 = new Id(id);
        Uri uri = new Uri(URI_TEST);
        return new DataSetModelSwagger(id1, uri, null);
    }

}
