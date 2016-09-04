package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.UserDTO;
import com.at.library.service.user.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * Crea un usuario
	 * @param user
	 * @return
	 */
	@RequestMapping( method = { RequestMethod.POST })
	public UserDTO create(@RequestBody UserDTO user) {
		log.debug(String.format("Creando el usuario:", user));
		return userService.create(user) ;
	}
	
	/**
	 * Dar de baja un usuario
	 * @param id
	 */
	@RequestMapping(value="/{id}", method =  { RequestMethod.DELETE})
	public void disable( @PathVariable("id")Integer id) throws Exception{
		log.debug(String.format("Dando de baja el usuario con id %s", id));
		userService.disable(id);
	}
	
	/**
	 * Busca todos los usuarios / por nombre / por dni
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.GET })
	public List<UserDTO> getAll(@RequestParam(value = "name", required=false) String name, @RequestParam(value = "dni", required=false) String dni) {
		return userService.findUsers(name, dni);
	}
	
}
