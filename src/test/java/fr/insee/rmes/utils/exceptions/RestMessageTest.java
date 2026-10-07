package fr.insee.rmes.utils.exceptions;

import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestMessageTest {

    @Test
    void shouldInitializeRestMessage(){
        int status =200;
        String message = "mockedMessage";
        String details = "mockedDetails";

        RestMessage restMessage = new RestMessage(100, "message", "details");

        restMessage.setStatus(status);
        restMessage.setMessage(message);
        restMessage.setDetails(details);

        boolean correctValueOfStatus = restMessage.getStatus()== status;
        boolean correctValueOfMessage = Objects.equals(restMessage.getMessage(), message);
        boolean correctValueOfDetails = Objects.equals(restMessage.getDetails(), details);

        assertTrue(correctValueOfStatus && correctValueOfMessage && correctValueOfDetails );


    }

}