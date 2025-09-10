package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class LabelDataSetTest {

    LabelDataSet firstLabelDataSet = new LabelDataSet();
    LabelDataSet secondLabelDataSet= new LabelDataSet("mockedLang","mockedContent");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLang(String mockedString){
        firstLabelDataSet.setLang(mockedString);
        secondLabelDataSet.withLang(mockedString);
        assertTrue(Objects.equals(firstLabelDataSet.getLang(),secondLabelDataSet.getLang()) &&
                Objects.equals(firstLabelDataSet.getLang(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckContent(String mockedString){
        firstLabelDataSet.setContent(mockedString);
        secondLabelDataSet.withContent(mockedString);
        assertTrue(Objects.equals(firstLabelDataSet.getContent(),secondLabelDataSet.getContent()) &&
                Objects.equals(firstLabelDataSet.getContent(), mockedString));
    }

}