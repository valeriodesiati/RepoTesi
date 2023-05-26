/*
######################################################
##  Author: Valerio Desiati                         ##
##  File: GlobalExceptionHandler.java               ##
##  Last update: 2022 May 13                        ##
######################################################
*/

package com.aesys.valeriodesiati.mail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmailNotFoundException.class)
	public void springHandleNotFound(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value());
	}

	@ExceptionHandler(EmailUnSupportedFieldPatchException.class)
	public void springUnSupportedFieldPatch(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value());
	}

}