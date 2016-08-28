package com.at.library.dto;

import java.util.ArrayList;
import java.util.List;

public class RoomDTO extends DTO{
	
	private static final long serialVersionUID = -4185714530986517335L;

	private String code;
	
	private List<String> bookShelve = new ArrayList<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<String> getBookShelve() {
		return bookShelve;
	}

	public void setBookShelve(List<String> bookShelve) {
		this.bookShelve = bookShelve;
	}

	@Override
	public String toString() {
		return "RoomDTO [code=" + code + ", bookShelve=" + bookShelve + "]";
	}
	
}
