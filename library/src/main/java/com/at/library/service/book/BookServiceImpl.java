package com.at.library.service.book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.exception.BookException;
import com.at.library.model.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private DozerBeanMapper dozer;
	
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
	public void disableBook(Integer id) throws Exception{
		Book b = bookDao.findId(id);
		if(b == null)
			throw new BookException(2);
			
		b.setStatus(StatusEnum.DISABLE);			
		bookDao.save(b);	
	}
	
	@Override
	public List<BookDTO> findAll() {
		final Iterable<Book> findAll = bookDao.findAllBook();
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
	public List<HistoryRentedDTO> getAllRent(Integer idBook, Integer page, Integer size){
		List<HistoryRentedDTO> historyRentedDTO = bookDao.getAllRent(idBook);
		if(page != null && size != null){
			List<HistoryRentedDTO> aux = new ArrayList<>();
			for(int i=(page*size)-size; i<(page*size) && i<historyRentedDTO.size(); i++)
				aux.add(historyRentedDTO.get(i));
			historyRentedDTO = aux;
		}		
		
		return historyRentedDTO;
	}

	@Override
	public BookDTO searchGoogle(BookDTO bookDTO) {
		final String uri = "https://www.googleapis.com/books/v1/volumes?q=" + bookDTO.getTitle() + "&maxResults=1";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    
	    JSONObject r = new JSONObject(result);	    
	    JSONObject volumeInfo = r.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo");
	   	    
	    String year = volumeInfo.getString("publishedDate");
	    if(year.length() >= 4)
	    	bookDTO.setYear(Integer.parseInt(year.substring(0,4)));
	    
	    bookDTO.setDescription(volumeInfo.getString("description"));
	    bookDTO.setImage(volumeInfo.getJSONObject("imageLinks").getString("thumbnail"));
	  	    
	    return bookDTO;
	}

	@Override
	public List<BookDTO> findBook(String title, String isbn) {
		List<BookDTO> booksDTO = new ArrayList<>();
		List<Book> books = new ArrayList<>();
		
		if(title == null && isbn == null)
			books = bookDao.findAllBook();
		else if(title != null && isbn == null)
			books = bookDao.findByTitle(title);
		else if (title == null && isbn != null)
			books = bookDao.findByIsbn(isbn);
		else 
			books = bookDao.findTitleIsbn(title, isbn);
		
		booksDTO = this.collectionOfBooks(books);
			
		return booksDTO;
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
	public BookDTO findOne(Integer id) throws Exception{		
		final Book b=bookDao.findId(id);
		BookDTO bDTO = new BookDTO();
		if(b == null)
			throw new BookException(1);
		else
			bDTO =transform(b);
		return bDTO;
	}

	@Override
	public void delete(Integer id) {
		bookDao.delete(id);		
	}

	@Override
	public List<BookDTO> findByTitle(String title) {
		final Iterable<Book> findByTitle = bookDao.findByTitle(title);
		final Iterator<Book> iterator = findByTitle.iterator();
		final List<BookDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			final BookDTO bDTO = transform(b);
			res.add(bDTO);
		}
		return res;			
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
	public List<BookDTO> findByISBN(String isbn) {
		final Iterable<Book> findByIsbn = bookDao.findByIsbn(isbn);
		final Iterator<Book> iterator = findByIsbn.iterator();
		final List<BookDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			final BookDTO bDTO = transform(b);
			res.add(bDTO);
		}
		return res;			
	}

	@Override
	public void enableBook(Integer id) throws Exception{
		Book b = bookDao.findOne(id);
		
		if(b == null)
			throw new BookException(2);
		b.setStatus(StatusEnum.ACTIVE);		
		bookDao.save(b);		
	}

	@Override
	public boolean availableBook(Integer id) {
		boolean resp = false;
		if(bookDao.availableBook(id) == 0)			
			resp = true;
		
		return resp;
	}
	
	@Override
	public boolean activeBook(Integer id) {
		boolean resp = false;
		if(bookDao.activeBook(id) > 0)			
			resp = true;
		
		return resp;
	}

	@Override
	public List<BookDTO> findUnAvailable() {
		return bookDao.findUnAvailable();
	}
	
	@Override
	public List<BookDTO> collectionOfBooks(List<Book> books) {
		final Iterable<Book> findAll = books;
		final Iterator<Book> iterator = findAll.iterator();
		final List<BookDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			BookDTO bDTO = transform(b);
			if(bDTO.getTitle() != null)
				bDTO = this.searchGoogle(bDTO);
			res.add(bDTO);
		}
		return res;
	}	

}
