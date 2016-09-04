package com.at.library.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="BookException") //404
public class BookException extends Exception{

	private static final long serialVersionUID = 437585689570129313L;

	public BookException(int id){
		super("BookException with id="+id);
	}
}
