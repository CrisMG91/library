package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.enums.UserStatusEnum;
import com.at.library.model.Rent;
import com.at.library.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer>{
	
	@Query(value = "SELECT r FROM Rent AS r WHERE r.endDate = CURRENT_DATE")
	public List<Rent> punishUser();
	
	@Query(value = "SELECT p.user.id FROM Punishment AS p WHERE p.endDay = CURRENT_DATE")
	public List<Integer> forgiveUser();
	
	@Query(value = "SELECT r FROM Rent AS r WHERE r.user.id = ?1")
	public List<Rent> getAllRent(Integer idUser);
	
	public List<User> findByName (String name);
	
	public User findByDni (String dni);
	
	public List<User> findByStatus (UserStatusEnum punished);
	
}
