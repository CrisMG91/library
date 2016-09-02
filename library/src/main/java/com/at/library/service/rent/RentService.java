package com.at.library.service.rent;

import java.util.List;

import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.model.Rent;

public interface RentService {

	/**
	 * Alquila un libro
	 * @param rentDTO
	 * @return boolean
	 */
	RentDTO rentBook(RentDTO rentDTO);
	
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
	List<HistoryRentedDTO> getAll();

	/**
	 * Comprueba si el libro existe en el modelo Rent
	 * @param idBook
	 * @return
	 */
	int findBookRent(Integer idBook);
	
}
