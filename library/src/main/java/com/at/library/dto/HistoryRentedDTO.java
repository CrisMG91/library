package com.at.library.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HistoryRentedDTO extends DTO{

	private static final long serialVersionUID = -3501715433966078367L;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date init;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date end;
	
	private String title;
	
	private Integer idBook;
	
	public HistoryRentedDTO(){}

	public HistoryRentedDTO(Date init, Date end, String title, Integer idBook){
		this.init = init;
		this.end = end;
		this.title = title;
		this.idBook = idBook;
	}
	
	public Date getInit() {
		return init;
	}
	public void setInit(Date init) {
		this.init = init;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getIdBook() {
		return idBook;
	}
	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}
	@Override
	public String toString() {
		return "HistoryRentedDTO [init=" + init + ", end=" + end + ", title=" + title + ", idBook=" + idBook + "]";
	}
	
}
