package com.at.library.exception;

public class BookRentedException extends Exception{

	private static final long serialVersionUID = 1584607620467163491L;
	
	private static Integer error;
	
	private static String msg = "";

	public static Integer getError() {
		return error;
	}

	public static void setError(Integer error) {
		BookRentedException.error = error;
	}

	public static String getMsg() {
		return msg;
	}

	public static void setMsg(String msg) {
		BookRentedException.msg = msg;
	}
}
