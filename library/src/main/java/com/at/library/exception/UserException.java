package com.at.library.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="UserException") //404
public class UserException extends Exception{

	private static final long serialVersionUID = -226619216502799005L;

	public UserException(int id){
		super("UserException with id="+id);
	}
}
