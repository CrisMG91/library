package com.at.library.dto;

import java.util.ArrayList;
import java.util.List;

public class BookShelveDTO extends DTO{

	private static final long serialVersionUID = 7235415387639020538L;

	private String code;
	
	private List<Integer> books = new ArrayList<>();
	
	private String room;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Integer> getBooks() {
		return books;
	}

	public void setBooks(List<Integer> books) {
		this.books = books;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "BookShelveDTO [code=" + code + ", books=" + books + ", room=" + room + "]";
	}

}
