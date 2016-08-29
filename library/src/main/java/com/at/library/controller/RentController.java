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

import com.at.library.dto.RentDTO;
import com.at.library.dto.UserBookRentDTO;
import com.at.library.service.rent.RentService;

@RestController
@RequestMapping(value = "/rent")
public class RentController {
	
	@Autowired
	private RentService rentService;
	
	private static final Logger log = LoggerFactory.getLogger(RentController.class);
	
	/**
	 * Alquila un libro
	 * @param rentDTO
	 * @return
	 */
	@RequestMapping( method = { RequestMethod.POST })
	public boolean rentBook(@RequestBody RentDTO rentDTO) /*throws BookRentedException*/{
		log.debug(String.format("Alquilando un libro:", rentDTO));
		return rentService.rentBook(rentDTO) ;
	}
	
	/**
	 * Devuelve un libro un libro
	 * @param rentDTO
	 * @return
	 */
	@RequestMapping(value="/{id}", method =  { RequestMethod.DELETE})
	public void returnBook(@PathVariable("id")Integer id) {
		log.debug(String.format("Devolviendo el libro:", id));
		rentService.returnBook(id) ;
	}
	
	/**
	 * Devuelve los alquileres
	 * @return
	 */
	@RequestMapping(method =  { RequestMethod.GET})
	public List<UserBookRentDTO> getAll(){
		log.debug(String.format("Historial de alquileres"));
		return rentService.getAll();
	}
}
