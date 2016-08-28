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

import com.at.library.dto.BookShelveDTO;
import com.at.library.service.bookShelve.BookShelveService;

@RestController
@RequestMapping(value = "/room")
public class BookShelveController {
	
	@Autowired
	private BookShelveService bookShelveService;
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	/**
	 * Busca todas las estanterias
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.GET })
	public List<BookShelveDTO> getAll() {
		return bookShelveService.findAll();
	}
	
	/**
	 * Devuelve una estanteria segun su id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}" , method = { RequestMethod.GET })
	public BookShelveDTO findOne(@PathVariable("id")String id){
		log.debug(String.format("Buscando la estanteria con el id %s", id));
		return bookShelveService.findOne(id);
	}
	
	/**
	 * Crea una estanteria
	 * @param RoomDTO
	 * @return
	 */
	@RequestMapping( method = { RequestMethod.POST })
	public BookShelveDTO create(@RequestBody BookShelveDTO bookShelveDTO) {
		log.debug(String.format("Creando la estanteria:", bookShelveDTO));
		return bookShelveService.create(bookShelveDTO) ;
	}

}
