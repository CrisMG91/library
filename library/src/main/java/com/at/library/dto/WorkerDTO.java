package com.at.library.dto;

public class WorkerDTO extends DTO{

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

	@Override
	public String toString() {
		return "WorkerDTO [id=" + id + ", name=" + name + ", lastname=" + lastname + "]";
	}
	
}
