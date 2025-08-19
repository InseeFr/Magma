package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class LangContentTest {

    LangContent firstLangContent = new LangContent("lang","content");
    LangContent secondLangContent = new LangContent();

    @Test
    void shouldCheckStaticMethodsResults(){
        LangContent lg1 = LangContent.lg1("mockedElementLg1");
        LangContent lg2 = LangContent.lg2("mockedElementLg2");
        assertNotEquals(lg1,lg2);
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLang(String mockedString){
        firstLangContent.setLang(mockedString);
        secondLangContent.withLang(mockedString);
        assertTrue(Objects.equals(firstLangContent.getLang(),secondLangContent.getLang()) &&
                Objects.equals(firstLangContent.getLang(), mockedString));
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckContent(String mockedString){
        firstLangContent.setContent(mockedString);
        secondLangContent.withContent(mockedString);
        assertTrue(Objects.equals(firstLangContent.getContent(),secondLangContent.getContent()) &&
                Objects.equals(firstLangContent.getContent(), mockedString));
    }

}