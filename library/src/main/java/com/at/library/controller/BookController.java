package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.service.book.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookservice;
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	/**
	 * Crear un libro
	 * @param book
	 * @return
	 */
	@RequestMapping( method = { RequestMethod.POST })
	public BookDTO create(@RequestBody BookDTO book) {
		log.debug(String.format("Creando el libro:", book));
		return bookservice.create(book) ;
	}
	
	/**
	 * Modificar un libro
	 * @param id
	 * @param book
	 */
	@RequestMapping(value="/{id}", method =  { RequestMethod.PUT})
	public void update( @PathVariable("id")Integer id, @RequestBody BookDTO book){
		log.debug(String.format("Modificando el libro con id %s", id));
		bookservice.update(book);
	}
	
	/**
	 * Dar de baja un libro
	 * @param id
	 */
	@RequestMapping(value="/{id}", method =  { RequestMethod.DELETE})
	public void disable( @PathVariable("id")Integer id) throws Exception{
		log.debug(String.format("Dando de baja el libro con id %s", id));
		bookservice.disableBook(id);
	}
	
	/**
	 * Busca todos los libros/ por titulo / por isbn
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.GET })
	public List<BookDTO> findBook(@RequestParam(value = "title", required=false) String title, @RequestParam(value = "isbn", required=false) String isbn) {
		return bookservice.findBook(title, isbn);
	}
	
	/**
	 * Lista de alquileres de un libro
	 * @param idBook
	 * @return
	 */
	@RequestMapping(value="{id}/rent", method = { RequestMethod.GET })
	public List<HistoryRentedDTO> getAllRent(@PathVariable("id")Integer idBook, @RequestParam(value = "page", required=false) Integer page, @RequestParam(value = "size", required=false) Integer size) {
		return bookservice.getAllRent(idBook, page, size);
	}

}
