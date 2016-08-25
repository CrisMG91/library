package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.service.book.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookservice;
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	/**
	 * Busca todos los libros
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.GET })
	public List<BookDTO> getAll() {
		return bookservice.findAll();
	}
	
	/**
	 * Devuelve un libro segun su id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}" , method = { RequestMethod.GET })
	public BookDTO findOne(@PathVariable("id")Integer id){
		log.debug(String.format("Buscando el libro con el id %s", id));
		return bookservice.findOne(id);
	}
			
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
	 * Borrar un libro
	 * @param id
	 */
	@RequestMapping(value="/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id")Integer id){
		log.debug(String.format("Borrando el libro con id %s", id));
		bookservice.delete(id);
	}

}
