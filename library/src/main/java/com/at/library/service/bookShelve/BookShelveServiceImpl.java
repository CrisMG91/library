package com.at.library.service.bookShelve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.BookShelveDao;
import com.at.library.dto.BookShelveDTO;
import com.at.library.model.Book;
import com.at.library.model.BookShelve;
import com.at.library.model.Room;
import com.at.library.service.book.BookService;
import com.at.library.service.room.RoomService;

@Service
public class BookShelveServiceImpl implements BookShelveService{

	@Autowired
	private BookShelveDao bookShelveDao;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private RoomService roomService;

	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	public List<BookShelveDTO> findAll() {
		final Iterable<BookShelve> findAll = bookShelveDao.findAll();
		final Iterator<BookShelve> iterator = findAll.iterator();
		final List<BookShelveDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final BookShelve bs = iterator.next();
			final BookShelveDTO bsDTO = transform(bs);
			res.add(bsDTO);
		}
		return res;
	}

	@Override
	public BookShelveDTO findOne(String id) {
		final BookShelve bs=bookShelveDao.findOne(id);
		return transform(bs);
	}

	@Override
	public BookShelveDTO create(BookShelveDTO bookShelveDTO) throws Exception {
		final Iterator<Integer> iterator = bookShelveDTO.getBooks().iterator();
		final List<Book> books = new ArrayList<>();
		final BookShelve bookShelve = new BookShelve();
		
		while (iterator.hasNext()) {
			final Integer idBook = iterator.next();
			final Book book = bookService.transform(bookService.findOne(idBook));
			books.add(book);
		}
		
		Room room = roomService.transform(roomService.findOne(bookShelveDTO.getRoom()));
		
		bookShelve.setCode(bookShelveDTO.getCode());
		bookShelve.setBooks(books);
		bookShelve.setRoom(room);
		
		return transform(bookShelveDao.save(bookShelve));	
	}

	@Override
	public BookShelveDTO transform(BookShelve bookShelve) {
		return dozer.map(bookShelve, BookShelveDTO.class);
	}

	@Override
	public BookShelve transform(BookShelveDTO bookShelveDTO) {
		return dozer.map(bookShelveDTO, BookShelve.class);
	}

	
}
