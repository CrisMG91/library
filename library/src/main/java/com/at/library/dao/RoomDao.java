package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Room;

@Repository
public interface RoomDao extends CrudRepository<Room, String>{

}
