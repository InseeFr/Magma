package fr.insee.rmes.model.CodeList;

import fr.insee.rmes.modelSwagger.dataset.Label;
import fr.insee.rmes.modelSwagger.dataset.LangContent;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class CodeTest {

    @Test
    void shouldInitializeWhenCode(){

        String mockedCode = "mockedCode";
        String mockedUri = "mockedUri";
        Label mockedLabel = new Label(List.of(LangContent.lg1("mockedLg1")));

        Code code = new Code(mockedCode,mockedLabel,mockedUri);

        code.setCode("newMockedCode");
        code.setUri("newMockedUri");
        Label otherLabel2 = new Label(List.of(LangContent.lg2("mockedLg2")));
        code.setLabel(otherLabel2);

        boolean isCodeExpected = Objects.equals( "newMockedCode",code.getCode());
        boolean isUriExpected = Objects.equals( "newMockedUri",code.getUri());
        boolean isLabelExpected = Objects.equals(otherLabel2,code.getLabel());

        assertTrue(isCodeExpected && isUriExpected && isLabelExpected);

    }



}