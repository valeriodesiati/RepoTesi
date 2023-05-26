/*
######################################################
##  Author: Valerio Desiati                         ##
##  File: Users.java                                ##
##  Last update: 2022 May 13                        ##
######################################################
*/

package com.aesys.valeriodesiati.mail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="users")
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;

	public Users() {}

	public Users(int id, String email, String name, String surname) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", email=" + email + ", name=" + name + ", surname=" + surname + "]";
	}
	
	
	
	

}