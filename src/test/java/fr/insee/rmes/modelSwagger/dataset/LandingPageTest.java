package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class LandingPageTest {
    
    LandingPage landingPage= new LandingPage("mockedLang","mockedUrl");

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckLang(String mockedString){
        landingPage.setLang(mockedString);
        assertEquals(mockedString,landingPage.getLang());
    }

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldCheckUrl(String mockedString){
        landingPage.setUrl(mockedString);
        assertEquals(mockedString,landingPage.getUrl());
    }


}