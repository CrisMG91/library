package com.at.library.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookShelve implements Serializable{
	
	private static final long serialVersionUID = -2849296193128440035L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String position;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Room room;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
