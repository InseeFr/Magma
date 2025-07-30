package fr.insee.rmes.model.CodeList;

import fr.insee.rmes.modelSwagger.dataset.Label;
import fr.insee.rmes.modelSwagger.dataset.LangContent;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CodeListTest {

    @Test
    void shouldInitializeWhenCodeList(){

        Label mockedLabel = new Label(List.of(LangContent.lg1("mockedLabel")));
        Code firstMockedCode = new Code("firstMockedCode",mockedLabel,"firstMockedUri");
        Code lastMockedCode = new Code("lastMockedCode",mockedLabel,"lastMockedUri");

        CodeList codeList  = new CodeList("mockedId",List.of(firstMockedCode,lastMockedCode),mockedLabel);

        String id = "id";
        List<Code> codes = List.of( new Code("otherMockedCode",mockedLabel,"otherMockedUri"));
        Label label = new Label(List.of(LangContent.lg1("label")));

        codeList.setId(id);
        codeList.setLabel(label);
        codeList.setCodes(codes);

        boolean isIdExpected = id.equals(codeList.getId());
        boolean isLabelExpected = label.equals(codeList.getLabel());
        boolean isCodesExpected = codes.equals(codeList.getCodes());

        assertTrue( isIdExpected && isLabelExpected && isCodesExpected);

    }


}