package com.at.library.service.room;

import java.util.List;

import com.at.library.dto.RoomDTO;
import com.at.library.model.Room;

public interface RoomService {

	/**
	 * Lista todas las salas
	 * @return
	 */
	List<RoomDTO> findAll();

	/**
	 * Encuentra una sala segun su id
	 * @param id
	 * @return
	 */
	RoomDTO findOne(String id);

	/**
	 * Crea una sala
	 * @param roomDTO
	 * @return
	 */
	RoomDTO create(RoomDTO roomDTO);
	
	/**
	 * Transforma un Room en RoomDTO
	 * @param room
	 * @return
	 */
	RoomDTO transform(Room room);

	/**
	 * Transforma un RoomDTO en Room
	 * @param roomDTO
	 * @return
	 */
	Room transform(RoomDTO roomDTO);

}
