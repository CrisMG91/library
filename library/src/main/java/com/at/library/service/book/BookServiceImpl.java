package com.at.library.service.book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.UserBookRentDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.Book;
import com.at.library.model.Rent;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public List<BookDTO> findAll() {
		final Iterable<Book> findAll = bookDao.findAll();
		final Iterator<Book> iterator = findAll.iterator();
		final List<BookDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			final BookDTO bDTO = transform(b);
			res.add(bDTO);
		}
		return res;
	}

	@Override
	public BookDTO transform(Book book) {
		return dozer.map(book, BookDTO.class);
	}

	@Override
	public Book transform(BookDTO book) {
		return dozer.map(book, Book.class);
	}

	@Override
	public BookDTO findOne(Integer id) {		
		final Book b=bookDao.findOne(id);
		return transform(b);
	}

	@Override
	public BookDTO create(BookDTO bookDTO) {
		final Book book=transform(bookDTO);
		book.setStatus(StatusEnum.ACTIVE);
		return transform(bookDao.save(book));		
	}

	@Override
	public void update(BookDTO book) {
		final BookDTO b=book;
		bookDao.save(transform(b));		
	}

	@Override
	public void delete(Integer id) {
		bookDao.delete(id);		
	}

	@Override
	public BookDTO findByTitle(String title) {
		final Book b=bookDao.findByTitle(title);
		return transform(b);		
	}

	@Override
	public List<BookDTO> findByAuthor(String author) {		
		final Iterable<Book> findByAuthor = bookDao.findByAuthor(author);
		final Iterator<Book> iterator = findByAuthor.iterator();
		final List<BookDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			final BookDTO bDTO = transform(b);
			res.add(bDTO);
		}
		return res;	
	}

	@Override
	public BookDTO findByISBN(String isbn) {
		final Book b=bookDao.findByIsbn(isbn);
		return transform(b);			
	}

	@Override
	public void disableBook(Integer id) {
		Book b = bookDao.findOne(id);
		b.setStatus(StatusEnum.DISABLE);
					
		bookDao.save(b);	
	}

	@Override
	public void enableBook(Integer id) {
		Book b = bookDao.findOne(id);
		b.setStatus(StatusEnum.ACTIVE);
					
		bookDao.save(b);		
	}

	@Override
	public boolean availableBook(Integer id) {
		if(bookDao.availableBook(id) == null)
			return true;
		else return false;
	}

	@Override
	public List<BookDTO> findUnAvailable() {
		return bookDao.findUnAvailable();
	}
	
	@Override
	public List<UserBookRentDTO> getAllRent(Integer idBook){
		List<Rent> rent = bookDao.getAllRent(idBook);
		final Iterator<Rent> iterator = rent.iterator();
		final List<UserBookRentDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Rent r = iterator.next();
			final UserBookRentDTO ubrDTO = new UserBookRentDTO();
			ubrDTO.setRent(r);
			res.add(ubrDTO);
		}
		return res;	
	}	

}
