/*
######################################################
##  Author: Valerio Desiati                         ##
##  File: EmailRepository.java                      ##
##  Last update: 2022 May 13                        ##
######################################################
*/

package com.aesys.valeriodesiati.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aesys.valeriodesiati.mail.model.Email;

public interface EmailRepository extends JpaRepository<Email, String> {
}