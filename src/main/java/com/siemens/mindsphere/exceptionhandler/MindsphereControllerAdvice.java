package com.siemens.mindsphere.exceptionhandler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.siemens.mindsphere.sdk.core.exception.MindsphereClientConfigurationException;
import com.siemens.mindsphere.sdk.core.exception.MindsphereClientException;
import com.siemens.mindsphere.sdk.core.exception.MindsphereException;
import com.siemens.mindsphere.sdk.core.exception.MindsphereForbiddenAccessException;
import com.siemens.mindsphere.sdk.core.exception.MindsphereServiceException;

@RestControllerAdvice(basePackages = "com.siemens.mindsphere.controllers")
public class MindsphereControllerAdvice {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(MindsphereClientConfigurationException.class)
	public ResponseEntity<MindsphereErrorResponse> handleClientConfigurationException(
			final MindsphereClientConfigurationException e) {
		LOGGER.info("MindsphereClientConfigurationExceptionMindsphereClientConfigurationException");
		return populateErrorResponse(e, HttpStatus.PRECONDITION_REQUIRED);
	}

	@ExceptionHandler(MindsphereForbiddenAccessException.class)
	public ResponseEntity<MindsphereErrorResponse> handleForbiddenAccessException(
			final MindsphereForbiddenAccessException e) {
		LOGGER.info("MindsphereForbiddenAccessException");
		return populateErrorResponse(e, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MindsphereClientException.class)
	public ResponseEntity<MindsphereErrorResponse> handleClientException(final MindsphereClientException e) {
		LOGGER.info("MindsphereClientException");
		return populateErrorResponse(e, HttpStatus.PRECONDITION_REQUIRED);
	}

	@ExceptionHandler(MindsphereServiceException.class)
	public ResponseEntity<MindsphereErrorResponse> handleProxyServiceException(final MindsphereServiceException e) {
		LOGGER.info("MindsphereServiceException");
		return populateErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MindsphereErrorResponse> handleProxyServiceException(final Exception e) {
		MindsphereErrorResponse error = new MindsphereErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
		        e.getMessage(), new Date().toString());
		LOGGER.info("EXCEPTION");
		return new ResponseEntity<MindsphereErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<MindsphereErrorResponse> populateErrorResponse(MindsphereException e,
			HttpStatus httpStatus) {
		MindsphereErrorResponse error = new MindsphereErrorResponse(e.getHttpStatus().toString(), e.getErrorMessage(),
				new Date().toString());
		return new ResponseEntity<MindsphereErrorResponse>(error, httpStatus);
	}

}
