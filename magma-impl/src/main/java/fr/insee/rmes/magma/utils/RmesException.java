package fr.insee.rmes.magma.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class RmesException extends Exception {

	private static final String CODE = "code";

	private static final String DETAILS_STRING = "details";

	private static final String MESSAGE = "message";

	private static final long serialVersionUID = -7959158367542389147L;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private final int status;
	private final String details;
	private String message;

	public RmesException(int status, String message, String details) {
		super();
		this.status = status;
		this.details = createDetails(null, message, details);
	}

	public RmesException(int status, int errorCode, String message, String details) {
		super();
		this.status = status;
		this.details = createDetails(errorCode, message, details);
	}

	public RmesException(int status, int errorCode, String details) {
		super();
		this.status = status;
		this.details = createDetails(errorCode, null, details);
	}

	public RmesException(HttpStatus status, String message, String details) {
		this.message = message;
		this.status = status.value();
		this.details = details;
	}

	public RestMessage toRestMessage(){
		return new RestMessage(this.status, this.getMessage(), this.details);
	}

	public int getStatus() {
		return status;
	}

	public String getDetails() {
		return details;
	}
	public String getMessage() {
		return message;
	}

	public String getMessageAndDetails2() {
		return getMessage() + " " + details;
	}

	private String createDetails(Integer errorCode, String message, String detailsParam) {
		Map<String, Object> det = new LinkedHashMap<>();
		if (errorCode != null) det.put(CODE, errorCode);
		if (message != null) det.put(MESSAGE, message);
		if (detailsParam != null) det.put(DETAILS_STRING, detailsParam);
		try {
			return MAPPER.writeValueAsString(det);
		} catch (JsonProcessingException e) {
			return detailsParam;
		}
	}

}