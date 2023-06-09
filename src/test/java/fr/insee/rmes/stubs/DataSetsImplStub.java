package fr.insee.rmes.stubs;

import fr.insee.rmes.modelSwagger.dataset.DataSetModelSwagger;
import fr.insee.rmes.services.datasets.DataSetsImpl;

import static fr.insee.rmes.services.datasets.DataSetsImplTest.URI_TEST;

public class DataSetsImplStub extends DataSetsImpl {

    @Override
    protected DataSetModelSwagger findDataSetModelSwagger(String id) {
        return new DataSetModelSwagger(id, URI_TEST, null);
    }
}
