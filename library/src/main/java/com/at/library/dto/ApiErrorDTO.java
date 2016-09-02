package com.at.library.dto;

public class ApiErrorDTO extends DTO{

	private static final long serialVersionUID = 6428129973003178787L;

	private Integer error;
	
	private String msg;
	
	public ApiErrorDTO(){}
	
	public ApiErrorDTO(Integer error, String msg){
		this.error = error;
		this.msg = msg;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ApiErrorDTO [error=" + error + ", msg=" + msg + "]";
	}
}
