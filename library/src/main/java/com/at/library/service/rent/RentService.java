package com.at.library.service.rent;

import java.util.List;

import com.at.library.dto.RentDTO;
import com.at.library.dto.UserBookRentDTO;
import com.at.library.model.Rent;

public interface RentService {

	/**
	 * Alquila un libro
	 * @param rentDTO
	 * @return boolean
	 */
	boolean rentBook(RentDTO rentDTO);
	
	/**
	 * Tranforma un Rent en RentDTO 
	 * @param rent
	 * @return
	 */
	RentDTO transform(Rent rent);

	/**
	 * Transforma un RentDTO a Rent
	 * @param rentDTO
	 * @return
	 */
	Rent transform(RentDTO rentDTO);

	/**
	 * Devuelve un libro
	 * @param id
	 * @return
	 */
	void returnBook(Integer id);

	/**
	 * Devuelve todos los alquileres
	 * @return
	 */
	List<UserBookRentDTO> getAll();	
	
}
