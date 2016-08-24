package com.at.library.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 4877910540692673267L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String lastName;
	
	private boolean Punished;
	
	@OneToMany
	private List<Rent> rent;

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

	public boolean isPunished() {
		return Punished;
	}

	public void setPunished(boolean punished) {
		Punished = punished;
	}

}
