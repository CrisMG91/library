package com.at.library.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.at.library.enums.UserStatusEnum;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 4877910540692673267L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String lastName;
	
	private String dni;	
		
	@Enumerated(EnumType.STRING)
	private UserStatusEnum status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public UserStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UserStatusEnum status) {
		this.status = status;
	}

}
