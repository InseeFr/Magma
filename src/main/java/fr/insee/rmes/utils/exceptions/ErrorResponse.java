package fr.insee.rmes.utils.exceptions;

public class ErrorResponse {
    private final int status;
    private final String message;
    private final String details;

    public ErrorResponse(int status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
