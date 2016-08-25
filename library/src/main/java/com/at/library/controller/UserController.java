package com.at.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	 * Elimina un usuario
	 * @param id
	 */
	@RequestMapping(value="/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id")Integer id){
		log.debug(String.format("Borrando al usuario con id %s", id));
		userService.delete(id);
	}

}
