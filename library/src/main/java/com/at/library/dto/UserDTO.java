package com.at.library.dto;

public class UserDTO extends DTO{

	private static final long serialVersionUID = 8251722573505260615L;

	private Integer id;
	
	private String name;
	
	private String lastName;
	
	private String dni;	
	
	private boolean Punished;

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

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", lastName=" + lastName + ", dni=" + dni + ", Punished="
				+ Punished + "]";
	}
	
}
