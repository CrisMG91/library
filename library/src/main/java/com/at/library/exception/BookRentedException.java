package com.at.library.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="BookRentedException") //404
public class BookRentedException extends Exception{

	private static final long serialVersionUID = 1584607620467163491L;
	
	public BookRentedException(int id){
		super("BookRentedException with id="+id);
	}
}
