package com.at.library.service.book;

import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.model.Book;

public interface BookService {

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
	 * Da de baja un libro
	 * @param id
	 * @throws Exception 
	 */
	void disableBook(Integer id) throws Exception;
	
	/**
	 * Lista de alquileres de un libro
	 * @param idBook
	 * @return
	 */
	List<HistoryRentedDTO> getAllRent(Integer idBook, Integer page, Integer size);
	
	/**
	 * Busca un libro en la api de google
	 * @param title
	 * @return
	 */
	BookDTO searchGoogle(BookDTO bookDto);

	/**
	 * Busca todos los libros / segun su titulo / segun su isbn
	 * @param title
	 * @param isbn
	 * @return
	 */
	List<BookDTO> findBook(String title, String isbn);

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
	 * Realiza la busqueda de todos los libros existentes
	 * 
	 * @return listado de libros
	 */
	List<BookDTO> findAll();
	
	/**
	 * Busca un libro dada su id
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	BookDTO findOne(Integer id) throws Exception;
	
	/**
	 * Elimina un libro dado su id
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * Busca un libro dado un titulo
	 * @param title
	 * @return
	 */
	List<BookDTO> findByTitle(String title);

	/**
	 * Busca un libro dado un autor
	 * @param author
	 * @return
	 */
	List<BookDTO> findByAuthor(String author);

	/**
	 * Busca un libro dado el ISBN
	 * @param isbn
	 * @return
	 */
	List<BookDTO> findByISBN(String isbn);

	

	/**
	 * Dando de alta un libro
	 * @param id
	 * @throws Exception 
	 */
	void enableBook(Integer id) throws Exception;

	/**
	 * Comprueba si un libro esta disponible
	 * @param id
	 * @return
	 */
	boolean availableBook(Integer id);
	
	/**
	 * Devuelve el listado de libros alquilados
	 * @return
	 */
	List<BookDTO> findUnAvailable();

	

	/**
	 * Recorre un List de libros
	 * @return
	 */
	List<BookDTO> collectionOfBooks(List<Book> books);

	/**
	 * Comprueba si un libro esta activo
	 * @param id
	 * @return
	 */
	boolean activeBook(Integer id);

}
