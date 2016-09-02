package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.dto.HistoryRentedDTO;
import com.at.library.model.Rent;

@Repository
public interface RentDao extends CrudRepository<Rent, Integer>{
	
	@Query(value = "SELECT r FROM Rent AS r WHERE r.pk.book.id = :idBook AND r.endDate IS null)")
	public Rent returnBook(@Param(value = "idBook")Integer idBook);
	
	@Query(value = "SELECT new com.at.library.dto.HistoryRentedDTO(r.pk.initDate, r.endDate, r.pk.book.title, r.pk.book.id) FROM Rent AS r")
	public List<HistoryRentedDTO> historyRented();

	@Query(value = "SELECT count(r.pk.book.id) FROM Rent AS r WHERE r.pk.book.id = :idBook")
	public int findBookRent(@Param(value = "idBook")Integer idBook);

}
