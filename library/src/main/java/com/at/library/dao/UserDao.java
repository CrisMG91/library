package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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
	
	@Query(value = "SELECT r FROM Rent AS r WHERE r.user.id = :idUser")
	public List<Rent> getAllRent(@Param(value = "idUser")Integer idUser);
	
	@Query(value = "SELECT u FROM User AS u WHERE u.name = :name AND u.status != 'DISABLE' ")
	public List<User> findByName (@Param(value = "name")String name);
	
	@Query(value = "SELECT u FROM User AS u WHERE u.dni = :dni AND u.status != 'DISABLE' ")
	public List<User> findByDni (@Param(value = "dni")String dni);
	
	public List<User> findByStatus (UserStatusEnum punished);
	
	@Query(value = "SELECT u FROM User AS u WHERE u.name = :name AND u.dni = :dni AND u.status != 'DISABLE' ")
	public List<User> findByNameAndDni (@Param(value = "name")String name, @Param(value = "dni")String dni);
	
	@Query(value = "SELECT u FROM User AS u WHERE u.status != 'DISABLE' ")
	public List<User> findAllUser();
	
}
