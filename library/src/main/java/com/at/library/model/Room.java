package com.at.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room implements Serializable{
	
	private static final long serialVersionUID = -6197403570500787304L;
	
	@Id	
	private String code;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
	private List<BookShelve> bookShelve = new ArrayList<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<BookShelve> getBookShelve() {
		return bookShelve;
	}

	public void setBookShelve(List<BookShelve> bookShelve) {
		this.bookShelve = bookShelve;
	}
		
}
