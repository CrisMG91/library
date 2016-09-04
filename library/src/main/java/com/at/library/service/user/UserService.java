package com.at.library.service.user;

import java.util.List;

import com.at.library.dto.UserBookRentDTO;
import com.at.library.dto.UserDTO;
import com.at.library.model.User;

public interface UserService {

	/**
	 * Crea un usuario
	 * @param userDto
	 * @return
	 */
	UserDTO create(UserDTO userDto);
	
	/**
	 * Borra un usuario
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * Transforma un User en UserDTO
	 * @param user
	 * @return
	 */
	UserDTO transform(User user);

	/**
	 * Transforma un UserDTO en User
	 * @param user
	 * @return
	 */
	User transform(UserDTO user);

	/**
	 * Da de baja un usuario
	 * @param id
	 * @throws Exception 
	 */
	void disable(Integer id) throws Exception;

	/**
	 * Da de alta un usuario
	 * @param id
	 * @throws Exception 
	 */
	void enable(Integer id) throws Exception;

	/**
	 * Busca todos los usuarios
	 * @return
	 */
	List<UserDTO> findAll();
	
	/**
	 * Busca un usuario segun su id
	 * @param idUser
	 * @return 
	 * @throws Exception 
	 */
	UserDTO findOne(Integer idUser) throws Exception;
	
	/**
	 * Busca los usuarios llamados como name
	 * @param name
	 * @return
	 */
	List<UserDTO> findByName(String name);
	
	/**
	 * Busca un usuario segun su dni
	 * @param dni
	 * @return
	 */
	List<UserDTO> findByDNI(String dni);
	
	/**
	 * Usuarios Activos
	 * @param status
	 * @return
	 */
	List<UserDTO> findByStatus ();
	
	/**
	 * Usuarios castigados
	 * @param punish
	 * @return
	 */
	List<UserDTO> findByPunished ();

	/**
	 * Cambia el estado de castigo de un usuario
	 * @param id
	 * @throws Exception 
	 */
	void changePunishment(Integer id, Integer option) throws Exception;

	/**
	 * Comprueba los usuarios a sancionar y los penaliza
	 * @throws Exception 
	 */
	void penalize() throws Exception;

	/**
	 * Comprueba los usuarios que han cumplido la sancion y se la quita
	 * @throws Exception 
	 */
	void forgive() throws Exception;
	
	/**
	 * Deuvle todos los alquileres de un usuario
	 * @param idUser
	 * @return
	 */
	List<UserBookRentDTO> getAllRent(Integer idUser);

	/**
	 * Busca todos los usuarios / segun su nombre / segun su dni
	 * @param name
	 * @param dni
	 * @return
	 */
	List<UserDTO> findUsers(String name, String dni);
	
	/**
	 * Busca un usuario por su nombre y dni
	 * @param name, dni
	 * @return
	 */
	List<UserDTO> findByNameDni(String name, String dni);

}
