package com.rest.webservices.restfulwebservices.exceptions;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustmizedResponceEntityExceptionHandler extends ResponseEntityExceptionHandler {

	//all exception handler for the in this project just Like Globlal Exception Handler
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex,WebRequest request) {


	ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
			ex.getMessage(),request.getDescription(false));

	return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> userNotFoundException(Exception ex,WebRequest request) {


	ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
			ex.getMessage(),request.getDescription(false));

	return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);

	}

	//@Override
	/*
	 * protected ResponseEntity<Object>
	 * handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders
	 * headlers,HttpStatusCode statusCode,WebRequest request) { ErrorDetails
	 * errorDetails = new ErrorDetails(LocalDateTime.now(),
	 * ex.getMessage(),request.getDescription(false));
	 *
	 * return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST); }
	 */
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		//return handleExceptionInternal(ex, null, headers, status, request);

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),request.getDescription(false));

		return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
	}



}
