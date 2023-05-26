/*
######################################################
##  Author: Valerio Desiati                         ##
##  File: UserUnSupportedFieldPatchException.java   ##
##  Last update: 2022 May 13                        ##
######################################################
*/

package com.aesys.valeriodesiati.mail.exception;

import java.util.Set;

public class UserUnSupportedFieldPatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserUnSupportedFieldPatchException(Set<String> keys) {
		super("Field " + keys.toString() + " update is not allow.");
	}

}