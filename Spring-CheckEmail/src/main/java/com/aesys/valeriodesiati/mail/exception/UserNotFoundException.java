/*
######################################################
##  Author: Valerio Desiati                         ##
##  File: UserNotFoundException.java                ##
##  Last update: 2022 May 13                        ##
######################################################
*/

package com.aesys.valeriodesiati.mail.exception;

import com.aesys.valeriodesiati.mail.model.Users;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Users user) {
		super("User not found : " + user.getEmail());
	}

}