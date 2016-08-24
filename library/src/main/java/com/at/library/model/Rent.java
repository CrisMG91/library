package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rent implements Serializable{
	
	private static final long serialVersionUID = -5023900050593970404L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer idUser;
	
	private Integer idWorker;
	
	private Integer idBook;
	
	@Temporal(TemporalType.DATE)
	private Date startRent;
	
	@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="id")
	private Worker worker;
	
	@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="id")
	private Book book;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdWorker() {
		return idWorker;
	}

	public void setIdWorker(Integer idWorker) {
		this.idWorker = idWorker;
	}

	public Integer getIdBook() {
		return idBook;
	}

	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}

	public Date getStartRent() {
		return startRent;
	}

	public void setStartRent(Date startRent) {
		this.startRent = startRent;
	}
	
}
