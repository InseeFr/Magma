package fr.insee.rmes.model.datasets;

import fr.insee.rmes.modelSwagger.dataset.LangContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DistributionsTest {

    Distributions distributions = new Distributions("mockedIdentifier","mockedUri");

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckIdentifier(String mockedString){
        distributions.setIdentifier(mockedString);
        assertEquals(mockedString,distributions.getIdentifier());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckByteSize(String mockedString){
        distributions.setByteSize(mockedString);
        assertEquals(mockedString,distributions.getByteSize());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckCreated(String mockedString){
        distributions.setCreated(mockedString);
        assertEquals(mockedString,distributions.getCreated());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckFormat(String mockedString){
        distributions.setFormat(mockedString);
        assertEquals(mockedString,distributions.getFormat());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckModified(String mockedString){
        distributions.setModified(mockedString);
        assertEquals(mockedString,distributions.getModified());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedSeries1", "mockedSeries2", "mockedSeries3" })
    void shouldCheckUri(String mockedString){
        distributions.setUri(mockedString);
        assertEquals(mockedString,distributions.getUri());
    }

    @Test
    void shouldCheckDescription(){
        List<LangContent> list = List.of (new LangContent(),new LangContent());
        distributions.setDescription(list);
        assertEquals(list,distributions.getDescription());
    }

    @Test
    void shouldCheckDownloadUrl(){
        List<String> list = List.of ("url1","url2");
        distributions.setDownloadURL(list);
        assertEquals(list,distributions.getDownloadURL());
    }

    @Test
    void shouldCheckTitle(){
        List<LangContent> list = List.of (new LangContent(),new LangContent());
        distributions.setTitle(list);
        assertEquals(list,distributions.getTitle());
    }

    @Test
    void shouldCheckToStringContainsSomeAttributesOfDistribution(){
        Distributions distribution = new Distributions("identifier","uri");
        List<LangContent> list = List.of (new LangContent("lang","content"));
        distribution.setTitle(list);
        String actual= distribution.toString();
        assertTrue(actual.contains(distribution.getUri()) &&
                actual.contains(distribution.getIdentifier()) &&
                actual.contains(distribution.getTitle().toString()));
    }

}