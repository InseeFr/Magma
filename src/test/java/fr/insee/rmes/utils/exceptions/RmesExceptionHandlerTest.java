package fr.insee.rmes.utils.exceptions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.ResponseEntity;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class RmesExceptionHandlerTest {

    @ParameterizedTest
    @ValueSource(ints = { 200, 301,302,401,403,404,500,502,503,504 })
    void shouldCheckHandleRmesException(int code){
        RmesExceptionHandler rmesExceptionHandler = new RmesExceptionHandler();
        ResponseEntity<RestMessage> response = rmesExceptionHandler.handleRmesException(new RmesException(code,"mockedMessage","mockedStatus"));
        assertTrue(response.toString().contains(valueOf(code)));
    }

    @ParameterizedTest
    @ValueSource(ints = { 499,1001,874,2156,741 })
    void shouldReturnIllegalArgumentExceptionWhenHandleRmesException(int fakeCode){
        RmesExceptionHandler rmesExceptionHandler = new RmesExceptionHandler();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->rmesExceptionHandler.handleRmesException(new RmesException(fakeCode,"mockedMessage","mockedStatus")));
        String expected = "No matching constant for ["+fakeCode+"]";
        assertEquals(expected,exception.getMessage());
    }

}