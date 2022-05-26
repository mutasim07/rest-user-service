package com.perseus.urs.userrestservice.advice;

import com.perseus.urs.userrestservice.exception.BaseException;
import com.perseus.urs.userrestservice.exception.NotFoundException;
import com.perseus.urs.userrestservice.model.response.CommonResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{

	@ExceptionHandler(value = { NotFoundException.class })
	public ResponseEntity<Object> handleConflict(final NotFoundException ex, final WebRequest request)
	{
		addLogs(ex);
		return handleNotFoundException(ex);
	}

	public ResponseEntity<Object> handleNotFoundException(final NotFoundException ex)
	{
		return createResponseModel(404, ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	private void addLogs(Exception ex)
	{
		String message = ex.getMessage();
		log.error(message, ex);
	}

	private ResponseEntity<Object> createResponseModel(int resultCode, String resultDescription, HttpStatus status)
	{
		return new ResponseEntity<>(new CommonResponseModel(resultCode, resultDescription), new HttpHeaders(), status);
	}
}