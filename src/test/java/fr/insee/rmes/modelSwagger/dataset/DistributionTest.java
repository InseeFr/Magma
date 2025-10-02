package fr.insee.rmes.modelSwagger.dataset;

import fr.insee.rmes.model.datasets.Id;
import fr.insee.rmes.model.datasets.Uri;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DistributionTest {

    @Test
    void shouldCheckAttributeValue(){
        Id id = new Id("mockedId");
        Uri uri = new Uri("mockedUri");
        Distribution distribution = new Distribution(id,uri);
        assertTrue(distribution.getId()==id && distribution.getUri()==uri);
    }

}