/*
######################################################
##  Author: Valerio Desiati                         ##
##  File: JoinController.java                      ##
##  Last update: 2022 May 13                        ##
######################################################
*/

package com.aesys.valeriodesiati.mail.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {
	
	@Autowired
	EntityManagerFactory emf;
	
	@Transactional
	@GetMapping("/join/checkemail/{mail}")
	public ResponseEntity<Integer> checkEmail(@PathVariable("mail") String mail) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String result = null;
		
		try {
			result = (String) em.createQuery("SELECT u.email FROM Users u, Email e WHERE u.email = e.email AND u.email = :email").setParameter("email", mail).getSingleResult();
			
		}
		catch(NoResultException | NullPointerException e) {
			return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(402);
		}
		
		if(result == null)
			return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(402);
		else
			return ResponseEntity.status(HttpStatus.OK).body(200);
			
	}
}
