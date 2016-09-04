package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.dto.BookDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

	@Query(value = "SELECT b FROM Book AS b WHERE b.status != 'DISABLE' ")
	public List<Book> findAllBook();
	
	@Query(value = "SELECT b FROM Book AS b WHERE b.id = :id")
	public Book findId(@Param(value = "id")Integer id);
	
	@Query(value = "SELECT b FROM Book AS b WHERE b.title = :title AND b.status != 'DISABLE' ")
	public List<Book> findByTitle(@Param(value = "title")String title);
	
	@Query(value = "SELECT b FROM Book AS b WHERE b.author = :author AND b.status != 'DISABLE' ")
	public List<Book> findByAuthor(@Param(value = "author")String author);
	
	@Query(value = "SELECT b FROM Book AS b WHERE b.isbn = :isbn AND b.status != 'DISABLE' ")
	public List<Book> findByIsbn(@Param(value = "isbn")String isbn);
	
	@Query(value = "SELECT b FROM Book AS b WHERE b.title = :title AND b.isbn = :isbn AND b.status != 'DISABLE' ")
	public List<Book> findTitleIsbn(@Param(value = "title")String title, @Param(value = "isbn")String isbn);
	
	@Query(value = "SELECT new com.at.library.dto.BookDTO(b.id, b.isbn, b.title, b.author) FROM Book AS b WHERE b.id IN (SELECT r.pk.book.id FROM Rent AS r WHERE r.endDate IS null)")
	public List<BookDTO> findUnAvailable();
	
	@Query(value = "SELECT count(b.id) FROM Rent AS r, Book AS b WHERE r.pk.book.id = :id AND r.pk.book.id = b.id AND r.endDate IS null")
	public Integer availableBook(@Param(value = "id")Integer id);
	
	@Query(value = "SELECT new com.at.library.dto.HistoryRentedDTO(r.pk.initDate, r.endDate, r.pk.book.title, r.pk.book.id) FROM Rent AS r WHERE r.pk.book.id = :idBook")
	public List<HistoryRentedDTO> getAllRent(@Param(value = "idBook")Integer idBook);
	
	@Query(value = "SELECT count(b.id) FROM Book AS b WHERE b.id = :id AND b.status != 'DISABLE'")
	public Integer activeBook(@Param(value = "id")Integer id);
	
}
