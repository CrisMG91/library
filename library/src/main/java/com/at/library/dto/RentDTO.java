package com.at.library.dto;

public class RentDTO extends DTO{

	private static final long serialVersionUID = 7364756623634860483L;

	private Integer book;
	
	private Integer user;

	public Integer getBook() {
		return book;
	}

	public void setBook(Integer book) {
		this.book = book;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RentDTO [book=" + book + ", user=" + user + "]";
	}	
}
