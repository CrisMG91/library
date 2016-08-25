package com.at.library.service.book;

import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

public interface BookService {

	/**
	 * Realiza la busqueda de todos los libros existentes
	 * 
	 * @return listado de libros
	 */
	List<BookDTO> findAll();

	/**
	 * Transfrma un libro en un libroDTO
	 * 
	 * @param book
	 * @return
	 */
	BookDTO transform(Book book);

	/**
	 * Transforma un libroDTO en un libro
	 * 
	 * @param book
	 * @return
	 */
	Book transform(BookDTO book);
	
	/**
	 * Busca un libro dada su id
	 * @param id
	 * @return
	 */
	BookDTO findOne(Integer id);
	
	/**
	 * Crea un nuevo libro
	 * @param book
	 */
	BookDTO create(BookDTO book);


	/**
	 * Modifica un libro dado su id
	 * @param id
	 * @param book
	 */
	void update(BookDTO book);

	/**
	 * Elimina un libro dado su id
	 * @param id
	 */
	void delete(Integer id);

}
