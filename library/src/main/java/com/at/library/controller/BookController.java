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
import com.at.library.dto.UserBookRentDTO;
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
	public void disable( @PathVariable("id")Integer id){
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
	public List<HistoryRentedDTO> getAllRent(@PathVariable("id")Integer idBook) {
		return bookservice.getAllRent(idBook);
	}
	
	/*/**
	 * Devuelve un libro segun su id
	 * @param id
	 * @return
	 */
	/*@RequestMapping(value="/{id}" , method = { RequestMethod.GET })
	public BookDTO findOne(@PathVariable("id")Integer id){
		log.debug(String.format("Buscando el libro con el id %s", id));
		return bookservice.findOne(id);
	}	
	
	/**
	 * Dar de alta un libro
	 * @param id
	 */
	/*@RequestMapping(value="/enable/{id}", method =  { RequestMethod.DELETE})
	public void enable( @PathVariable("id")Integer id){
		log.debug(String.format("Dando de alta el libro con id %s", id));
		bookservice.enableBook(id);
	}
		
	/**
	 * Borrar un libro
	 * @param id
	 */
	/*@RequestMapping(value="/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id")Integer id){
		log.debug(String.format("Borrando el libro con id %s", id));
		bookservice.delete(id);
	}
	
	/**
	 * Buscar un libro mediante su titulo
	 * @param title
	 */
	/*@RequestMapping(value="findbytitle/{title}", method = { RequestMethod.GET })
	public BookDTO findByTitle(@PathVariable("title")String title){
		log.debug(String.format("Buscando el libro con titulo: %s", title));
		return bookservice.findByTitle(title);
	}
	
	/**
	 * Buscar un libro mediante su autor
	 * @param title
	 */
	/*@RequestMapping(value="findbyauthor/{author}", method = { RequestMethod.GET })
	public List<BookDTO> findByAuthor(@PathVariable("author")String author){
		log.debug(String.format("Buscando el libro con autor: %s", author));
		return bookservice.findByAuthor(author);
	}
	
	/**
	 * Buscar un libro mediante su ISBN
	 * @param title
	 */
	/*@RequestMapping(value="findbyisbn/{isbn}", method = { RequestMethod.GET })
	public BookDTO findByISBN(@PathVariable("isbn")String isbn){
		log.debug(String.format("Buscando el libro con ISBN: %s", isbn));
		return bookservice.findByISBN(isbn);
	}
	
	/**
	 * Comprueba si un libro esta disponible
	 * @param book
	 * @return
	 */
	@RequestMapping(value="availablebook/{id}", method =  { RequestMethod.GET})
	public boolean availableBook(@PathVariable("id")Integer id){
		log.debug(String.format("Comprobando la disponibilidad del libro: %s", id));
		return bookservice.availableBook(id);
	}
	
	/**
	 * Devuelve los libros alquilados
	 * @return
	 */
	/*@RequestMapping(value="/unavailablebook", method =  { RequestMethod.GET})
	public List<BookDTO> findUnAvailable(){
		log.debug(String.format("Libros disponibles"));
		return bookservice.findUnAvailable();
	}
	
	/**
	 * Busca un libro en google por su titulo
	 * @param title
	 * @return
	 */
	/*@RequestMapping(value="/getgoogle", method = { RequestMethod.GET }, params={"title"})
	public BookDTO searchGoogle(@RequestParam("title") String title){
		return bookservice.searchGoogle(title);
	}*/

}
