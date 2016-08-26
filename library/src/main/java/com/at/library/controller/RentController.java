package com.at.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.RentDTO;
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
	public RentDTO rentBook(@RequestBody RentDTO rentDTO) {
		log.debug(String.format("Alquilando un libro:", rentDTO));
		return rentService.rentBook(rentDTO) ;
	}
}