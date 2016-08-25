package com.at.library.dto;

import java.io.Serializable;
import java.util.Date;

import com.at.library.model.Book;
import com.at.library.model.RentPK;
import com.at.library.model.User;
import com.at.library.model.Worker;

public class RentDTO implements Serializable{

	private static final long serialVersionUID = 7364756623634860483L;

	private RentPK pk;
	
	private Worker worker;
	
	private User user;
	
	private Date endDate;
	
	public RentPK getPk() {
		return pk;
	}

	public void setPk(RentPK pk) {
		this.pk = pk;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Book getBook(){
		return pk.getBook();
	}
	
	public void setBook(Book book){
		pk.setBook(book);
	}
	
	public Date getInitDate(){
		return pk.getInitDate();
	}
	
	public void setInitDate(Date initDate){
		pk.setInitDate(initDate);
	}
	
	

}
