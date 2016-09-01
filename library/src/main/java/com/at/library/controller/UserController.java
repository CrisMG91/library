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

import com.at.library.dto.BookDTO;
import com.at.library.dto.UserBookRentDTO;
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
	public void disable( @PathVariable("id")Integer id){
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
	
	/*/**
	 * Busca un usuarios segun su id
	 * @param idUser
	 * @return
	 */
	/*@RequestMapping(value="/{id}", method = { RequestMethod.GET })
	public UserDTO findOne(@PathVariable("id")Integer idUser) {
		return userService.findOne(idUser);
	}
	
	/**
	 * Busca un usuarios segun su dni
	 * @param idUser
	 * @return
	 */
	/*@RequestMapping(value="/dni/{dni}", method = { RequestMethod.GET })
	public UserDTO findBydni(@PathVariable("dni")String dni) {
		return userService.findByDNI(dni);
	}
	
	/**
	 * Busca un usuarios segun su nombre
	 * @param idUser
	 * @return
	 */
	/*@RequestMapping(value="/name/{name}", method = { RequestMethod.GET })
	public List<UserDTO> findByName(@PathVariable("name")String name) {
		return userService.findByName(name);
	}
	
	/**
	 * Busca los usuarios castigados
	 * @return
	 */
	/*@RequestMapping(value="/punished", method = { RequestMethod.GET })
	public List<UserDTO> findByPunished() {
		return userService.findByPunished();
	}
	
	/**
	 * Encuentra los usuarios activos
	 * @return
	 */
	/*@RequestMapping(value="/status", method = { RequestMethod.GET })
	public List<UserDTO> findByStatus() {
		return userService.findByStatus();
	}*/
	
	/**
	 * Elimina un usuario
	 * @param id
	 */
	/*@RequestMapping(value="/delete/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id")Integer id){
		log.debug(String.format("Borrando al usuario con id %s", id));
		userService.delete(id);
	}	
	
	/**
	 * Dar de alta un usuario
	 * @param id
	 */
	/*@RequestMapping(value="/enable/{id}", method =  { RequestMethod.DELETE})
	public void enable( @PathVariable("id")Integer id){
		log.debug(String.format("Dando de alta el usuario con id %s", id));
		userService.enable(id);
	}	
	
	/**
	 * Alquileres realizado por un usuario
	 */
	/*@RequestMapping(value="/getallrent/{id}", method = { RequestMethod.GET })
	public List<UserBookRentDTO> getAllRent(@PathVariable("id")Integer idUser) {
		return userService.getAllRent(idUser);
	}*/

}
