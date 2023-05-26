/*
######################################################
##  Author: Valerio Desiati                         ##
##  File: UsersRepository.java                      ##
##  Last update: 2022 May 13                        ##
######################################################
*/

package com.aesys.valeriodesiati.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aesys.valeriodesiati.mail.model.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
}