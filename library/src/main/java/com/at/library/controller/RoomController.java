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

import com.at.library.dto.RoomDTO;
import com.at.library.service.room.RoomService;

@RestController
//@RequestMapping(value = "/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	/**
	 * Busca todas las salas
	 * @return
	 */
	/*@RequestMapping(method = { RequestMethod.GET })
	public List<RoomDTO> getAll() {
		return roomService.findAll();
	}
	
	/**
	 * Devuelve una sala segun su id
	 * @param id
	 * @return
	 */
	/*@RequestMapping(value="/{id}" , method = { RequestMethod.GET })
	public RoomDTO findOne(@PathVariable("id")String id){
		log.debug(String.format("Buscando la sala con el id %s", id));
		return roomService.findOne(id);
	}
	
	/**
	 * Crea una sala
	 * @param RoomDTO
	 * @return
	 */
	/*@RequestMapping( method = { RequestMethod.POST })
	public RoomDTO create(@RequestBody RoomDTO roomDTO) {
		log.debug(String.format("Creando la sala:", roomDTO));
		return roomService.create(roomDTO) ;
	}*/
}
