package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer>{
	
	@Query(value = "SELECT r.user.id FROM Rent AS r WHERE r.endDate = CURRENT_DATE AND (r.endDate-r.pk.initDate)>3")
	public List<Integer> punishUser();
}
