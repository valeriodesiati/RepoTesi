/*
######################################################
##  Author: Valerio Desiati                         ##
##  File: EmailNotFoundException.java               ##
##  Last update: 2022 May 13                        ##
######################################################
*/

package com.aesys.valeriodesiati.mail.exception;

public class EmailNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailNotFoundException(String mail) {
		super("Email not found : " + mail);
	}

}