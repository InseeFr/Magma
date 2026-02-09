package fr.insee.rmes.model.datasets;

import fr.insee.rmes.modelSwagger.dataset.LangContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DistributionsTest {

    String identifier = "mockedIdentifier";
    String byteSize = "mockedByteSize";
    String created= "mockedCreated";
    List<LangContent> description= List.of(LangContent.lg1("lg1"),LangContent.lg2("lg2"));
    List<String> downloadURL= List.of("downloadURL1","downloadURL2");
    String format= "mockedFormat";
    String modified= "mockedModified";
    List<LangContent> title= List.of(LangContent.lg1("lg1"),LangContent.lg2("lg2"));
    String uri= "mockedUri";

    Distributions firstDistributions = new Distributions(identifier,byteSize,created,description,downloadURL,format,modified,title,uri);
    Distributions secondDistributions = new Distributions();
    Distributions thirdDistributions = new Distributions(identifier,uri);

    @ParameterizedTest
    @ValueSource(strings = { "mockedIdentifier1", "mockedIdentifier2", "mockedIdentifier3" })
    void shouldCheckIdentifier(String mockedString){
        firstDistributions.setIdentifier(mockedString);
        assertEquals(mockedString,firstDistributions.getIdentifier());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedByteSize1", "mockedByteSize2", "mockedByteSize3" })
    void shouldCheckByteSize(String mockedString){
        secondDistributions.setByteSize(mockedString);
        assertEquals(mockedString,secondDistributions.getByteSize());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedCreated1", "mockedCreated2", "mockedCreated3" })
    void shouldCheckCreated(String mockedString){
        thirdDistributions.setCreated(mockedString);
        assertEquals(mockedString,thirdDistributions.getCreated());
    }

    @Test
    void shouldCheckDescription(){
        secondDistributions.setDescription(description);
        assertEquals(description,secondDistributions.getDescription());
    }

    @Test
    void shouldCheckDownloadURL(){
        secondDistributions.setDownloadURL(downloadURL);
        assertEquals(downloadURL,secondDistributions.getDownloadURL());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedFormat1", "mockedFormat2", "mockedFormat3" })
    void shouldCheckFormat(String mockedString){
        secondDistributions.setFormat(mockedString);
        assertEquals(mockedString,secondDistributions.getFormat());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedModified1", "mockedModified2", "mockedModified3" })
    void shouldCheckModified(String mockedString){
        secondDistributions.setModified(mockedString);
        assertEquals(mockedString,secondDistributions.getModified());
    }

    @Test
    void shouldCheckTitle(){
        secondDistributions.setTitle(title);
        assertEquals(title,secondDistributions.getTitle());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedUri1", "mockedUri2", "mockedUri3" })
    void shouldCheckUri(String mockedString){
        secondDistributions.setUri(mockedString);
        assertEquals(mockedString,secondDistributions.getUri());
    }

    @Test
    void shouldCheckToString(){
        String actual = firstDistributions.toString();
        assertTrue(actual.contains(identifier) &&
                        actual.contains(byteSize) &&
                        actual.contains(created) &&
                        actual.contains(format) &&
                        actual.contains(modified) &&
                actual.contains(uri));
    }

}