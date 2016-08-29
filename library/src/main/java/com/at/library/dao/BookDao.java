package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.dto.BookDTO;
import com.at.library.model.Book;
import com.at.library.model.Rent;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

	public Book findByTitle(String title);
	
	public List<Book> findByAuthor(String author);
	
	public Book findByIsbn(String isbn);
	
	@Query(value = "SELECT new com.at.library.dto.BookDTO(b.id, b.isbn, b.title, b.author) FROM Book AS b WHERE b.id IN (SELECT r.pk.book.id FROM Rent AS r WHERE r.endDate IS null)")
	public List<BookDTO> findUnAvailable();
	
	@Query(value = "SELECT r.pk.book.id FROM Rent AS r, Book AS b WHERE (r.endDate IS null AND r.pk.book.id = ?1) OR (b.id = ?1 AND b.status = 'DISABLE')")
	public Integer availableBook(Integer id);
	
	@Query(value = "SELECT new Rent(r.pk, r.worker, r.user, r.endDate) FROM Rent AS r WHERE r.pk.book.id = ?1")
	public List<Rent> getAllRent(Integer idBook);
}
