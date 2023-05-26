/*
######################################################
##  Author: Valerio Desiati                         ##
##  File: EmailController.java                      ##
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

import com.aesys.valeriodesiati.mail.exception.EmailNotFoundException;
import com.aesys.valeriodesiati.mail.model.Email;
import com.aesys.valeriodesiati.mail.repository.EmailRepository;

@RestController
public class EmailController {

	@Autowired
	private EmailRepository repository;

	@Transactional
	@PostMapping("/checkemail")
	@ResponseStatus(HttpStatus.CREATED)
	public Email createEmail(@RequestBody Email mail) {
		return repository.save(mail);
	}

	@Transactional
	@GetMapping("/checkemail/{mail}")
	public ResponseEntity<Integer> getID(@PathVariable("mail") String mail) {
		
		try {
			repository.findById(mail).orElseThrow(() -> new EmailNotFoundException(mail));
			return ResponseEntity.status(HttpStatus.OK).body(200);
		}
		catch(EmailNotFoundException | NullPointerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(404);
		}
		
	}

	@Transactional
	@DeleteMapping("/checkemail/{mail}")
	public ResponseEntity<Integer> deleteID(@PathVariable("mail") String mail) {
		try {
			repository.deleteById(mail);
			return ResponseEntity.status(HttpStatus.OK).body(200);
		}
		catch(EmptyResultDataAccessException | NullPointerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(404);
		}
	}

}