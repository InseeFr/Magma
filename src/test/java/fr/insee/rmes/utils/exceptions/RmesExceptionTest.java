package fr.insee.rmes.utils.exceptions;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class RmesExceptionTest {

    int status = 200;
    String message = "mockedMessage";
    String details = "mockedDetails";
    JSONArray jsonArrayDetails = new JSONArray().put("details");
    int errorCode = 0;
    HttpStatus statusBis = HttpStatus.OK;


    RmesException firstRmesException = new RmesException(status,message,details);
    RmesException secondRmesException = new RmesException(status,message,jsonArrayDetails);
    RmesException thirdRmesException = new RmesException(status,errorCode,message,details);
    RmesException sixthRmesException = new RmesException(status,errorCode,message,jsonArrayDetails);
    RmesException seventhRmesException = new RmesException(status,errorCode,message,details);


    @Test
    void shouldReturnGetDetails(){
        RmesException fourthRmesException = new RmesException(status,errorCode,details);
        RmesException fifthRmesException = new RmesException(status,errorCode,jsonArrayDetails);
        RmesException eigththRmesException = new RmesException(statusBis, message, details);

        assertTrue(Objects.equals(fourthRmesException.getMessageAndDetails2(), "null {\"code\":0}") &&
                        Objects.equals(fifthRmesException.getMessageAndDetails2(), "null {\"code\":0}") &&
                        Objects.equals(eigththRmesException.getDetails(), details)
                );
    }


    @Test
    void shouldReturnGetMessage(){
        HashSet<String> setMessages = new HashSet<>();
        setMessages.add(firstRmesException.getMessage());
        setMessages.add(secondRmesException.getMessage());
        setMessages.add(thirdRmesException.getMessage());
        setMessages.add(sixthRmesException.getMessage());
        setMessages.add(seventhRmesException.getMessage());
        assertTrue(setMessages.size()==1 && setMessages.contains(null));
    }

    @ParameterizedTest
    @ValueSource(ints = { 2, 4,400,56,32,5669,8852 })
    void shouldReturnRestMessageWhenToRestMessageAndGetStatus(int mockedStatus){
        List<RmesException> rmesExceptions = List.of(
                new RmesException(mockedStatus,message,details),
                new RmesException(mockedStatus,message,jsonArrayDetails),
                new RmesException(mockedStatus,errorCode,message,details),
                new RmesException(mockedStatus,errorCode,details),
                new RmesException(mockedStatus,errorCode,jsonArrayDetails),
                new RmesException(mockedStatus,errorCode,message,jsonArrayDetails),
                new RmesException(mockedStatus,errorCode,message,details),
                new RmesException(mockedStatus, message, details));

        HashSet<Boolean> setOfRestMessage = new HashSet<>();
        HashSet<Boolean> setOfRmesExceptionGetMessage = new HashSet<>();

        for(RmesException rmesException : rmesExceptions){
            setOfRestMessage.add(rmesException.toRestMessage().getStatus()==mockedStatus);
            setOfRmesExceptionGetMessage.add(rmesException.getStatus()==mockedStatus);
        }

        assertTrue(setOfRestMessage.size()==1 &&
                setOfRestMessage.contains(true) &&
                setOfRmesExceptionGetMessage.size()==1 &&
                setOfRmesExceptionGetMessage.contains(true));
    }
}