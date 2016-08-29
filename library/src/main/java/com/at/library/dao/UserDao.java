package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.enums.StatusEnum;
import com.at.library.model.Rent;
import com.at.library.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer>{
	
	@Query(value = "SELECT r.user.id FROM Rent AS r WHERE r.endDate = CURRENT_DATE AND (r.endDate-r.pk.initDate)>3")
	public List<Integer> punishUser();
	
	@Query(value = "SELECT new Rent(r.pk, r.worker, r.user, r.endDate) FROM Rent AS r WHERE r.user.id = ?1")
	public List<Rent> getAllRent(Integer idUser);
	
	public List<User> findByName (String name);
	
	public User findByDni (String dni);
	
	public List<User> findByStatus (StatusEnum status);
	
	public List<User> findByPunished (Integer punish);
}
