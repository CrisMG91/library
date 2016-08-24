package com.at.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.service.book.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookservice;

	@RequestMapping(method = { RequestMethod.GET })
	public List<BookDTO> getAll() {
		return bookservice.findAll();
	}
	
	@RequestMapping(value="/{id}", method = { RequestMethod.GET })
	public BookDTO findOne(@RequestParam("id") Integer id) {
		return bookservice.findOne(id);
	}
	
	@RequestMapping(method = { RequestMethod.POST })
	public void create(BookDTO book) {
		bookservice.create(book);
	}
	
	@RequestMapping(value="/{id}", method = { RequestMethod.PUT })
	public void update(@RequestParam("id") Integer id, BookDTO book) {
		bookservice.update(id, book);
	}
	
	@RequestMapping(value="/{id}", method = { RequestMethod.DELETE })
	public void delete(@RequestParam("id") Integer id) {
		bookservice.delete(id);
	}

}
