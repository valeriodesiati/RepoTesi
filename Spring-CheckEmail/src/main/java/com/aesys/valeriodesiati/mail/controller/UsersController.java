/*
######################################################
##  Author: Valerio Desiati                         ##
##  File: UsersController.java                      ##
##  Last update: 2022 May 13                        ##
######################################################
*/

package com.aesys.valeriodesiati.mail.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aesys.valeriodesiati.mail.exception.UserNotFoundException;
import com.aesys.valeriodesiati.mail.model.Users;
import com.aesys.valeriodesiati.mail.repository.UsersRepository;

@RestController
public class UsersController {

	@Autowired
	private UsersRepository repository;

	@Transactional
	@PostMapping("/checkusers")
	@ResponseStatus(HttpStatus.CREATED)
	public Users createUser(@RequestBody Users user) {
		return repository.save(user);
	}

	@Transactional
	@GetMapping("/checkusers/{user}")
	public ResponseEntity<Integer> getID(@PathVariable("user") Users user) {

		try {
			repository.findById(user.getEmail()).orElseThrow(() -> new UserNotFoundException(user));
			return ResponseEntity.status(HttpStatus.OK).body(200);
		} catch (UserNotFoundException | NullPointerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(404);
		}

	}

	@Transactional
	@DeleteMapping("/checkusers/{user}")
	public ResponseEntity<Integer> deleteID(@PathVariable("user") Users user) {
		try {
			repository.deleteById(user.getEmail());
			return ResponseEntity.status(HttpStatus.OK).body(200);
		} catch (EmptyResultDataAccessException | NullPointerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(404);
		}
	}

}