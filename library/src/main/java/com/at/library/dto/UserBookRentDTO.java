package com.at.library.dto;

import com.at.library.model.Rent;

public class UserBookRentDTO extends DTO{
	
	private static final long serialVersionUID = -2476550014013202990L;
	
	private Rent rent;

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
