package com.at.library.service.bookShelve;

import java.util.List;

import com.at.library.dto.BookShelveDTO;
import com.at.library.model.BookShelve;

public interface BookShelveService {
	
	/**
	 * Lista todas las estanterias
	 * @return
	 */
	List<BookShelveDTO> findAll();

	/**
	 * Encuentra una estanteria segun id
	 * @param id
	 * @return
	 */
	BookShelveDTO findOne(String id);

	/**
	 * Crea una estanteria
	 * @param BookShelveDTO
	 * @return
	 */
	BookShelveDTO create(BookShelveDTO bookShelveDTO);
	
	/**
	 * Transforma un BookShelve en BookShelveDTO
	 * @param BookShelve
	 * @return
	 */
	BookShelveDTO transform(BookShelve bookShelve);

	/**
	 * Transforma un BookShelveDTO en BookShelve
	 * @param BookShelveDTO
	 * @return
	 */
	BookShelve transform(BookShelveDTO bookShelveDTO);

}
