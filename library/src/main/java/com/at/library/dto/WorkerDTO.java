package com.at.library.dto;

import java.io.Serializable;

public class WorkerDTO implements Serializable{

	private static final long serialVersionUID = -318703914751207157L;

	private Integer id;

	private String name;

	private String lastname;

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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}
