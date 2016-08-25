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

}
