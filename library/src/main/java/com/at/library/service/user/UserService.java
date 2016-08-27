package com.at.library.service.user;

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
	 */
	void disable(Integer id);

	/**
	 * Da de alta un usuario
	 * @param id
	 */
	void enable(Integer id);

	/**
	 * Busca un usuario segun su id
	 * @param idUser
	 * @return 
	 */
	UserDTO findOne(Integer idUser);

	/**
	 * Cambia el estado de castigo de un usuario
	 * @param id
	 */
	void changePunishment(Integer id);

}
