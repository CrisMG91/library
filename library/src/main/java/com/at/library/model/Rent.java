package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Rent implements Serializable{
	
	private static final long serialVersionUID = -5023900050593970404L;
	
	@EmbeddedId
	private RentPK pk;

	/* Id utilizadas en la versión anterior
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	private Date iniDate;	
	
	@Id
	@OneToOne
	private Book book;*/
	
	@ManyToOne
	private Worker worker;
	
	@ManyToOne
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	public RentPK getPk() {
		return pk;
	}

	public void setPk(RentPK pk) {
		this.pk = pk;
	}

	@Transient
	public Book getBook(){
		return pk.getBook();
	}
	
	@Transient 
	public void setBook(Book book){
		pk.setBook(book);
	}
	
	@Transient
	public Date getInitDate(){
		return pk.getInitDate();
	}
	
	@Transient 
	public void setInitDate(Date initDate){
		pk.setInitDate(initDate);
	}

	/* Get y set de la versión anterior de Book e InitDate
	 * public Date getIniDate() {
		return iniDate;
	}

	public void setIniDate(Date iniDate) {
		this.iniDate = iniDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}*/

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
	
}
