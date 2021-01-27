package com.siemens.mindsphere.exceptionhandler;

public class MindsphereErrorResponse {

	private String errorStatusCode;
	private String errorMessage;
	private String timeStamp;

	public MindsphereErrorResponse(String errorStatusCode, String errorMessage, String timeStamp) {
		super();
		this.errorStatusCode = errorStatusCode;
		this.errorMessage = errorMessage;
		this.timeStamp = timeStamp;
	}

	public String getErrorStatusCode() {
		return errorStatusCode;
	}

	public void setErrorStatusCode(String errorStatusCode) {
		this.errorStatusCode = errorStatusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
