package com.at.library.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.at.library.enums.StatusEnum;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 4877910540692673267L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String lastName;
	
	private String dni;	
	
	private boolean Punished;
	
	private StatusEnum status;

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

	public boolean isPunished() {
		return Punished;
	}

	public void setPunished(boolean punished) {
		Punished = punished;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
