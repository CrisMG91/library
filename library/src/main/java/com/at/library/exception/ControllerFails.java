package com.at.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.at.library.dto.ApiErrorDTO;

@ControllerAdvice(basePackages = {"com.at.library.controller"})
public class ControllerFails {
	
	@ResponseBody
	//@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO error(Exception e){
		return new ApiErrorDTO(404, e.getMessage());
	}
	
	/*@ResponseBody
	@ExceptionHandler(InvalidDataException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO error(InvalidDataException e){
		return new ApiError(400, e.getMessage());
	}*/
	
	@ResponseBody
	@ExceptionHandler(BookRentedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO error(BookRentedException e){
		return new ApiErrorDTO(400, e.getMessage());
	}
	
}
