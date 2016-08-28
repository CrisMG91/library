package com.at.library.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Rent;

@Repository
public interface RentDao extends CrudRepository<Rent, Integer>{
	
	@Query(value = "SELECT new Rent(r.user.id, r.worker.id, r.pk.initDate)  FROM Rent AS r WHERE r.pk.book.id = ?1 AND r.endDate IS null)")
	public Rent returnBook(Integer idBook);
}
