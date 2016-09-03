package com.at.library.service.rent;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.transaction.Transaction;

import org.dozer.DozerBeanMapper;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.at.library.dao.RentDao;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.enums.UserStatusEnum;
import com.at.library.model.Book;
import com.at.library.model.Rent;
import com.at.library.model.RentPK;
import com.at.library.model.User;
import com.at.library.service.book.BookService;
import com.at.library.service.user.UserService;

@Service
public class RentServiceImpl implements RentService{

	@Autowired
	private RentDao rentDao;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	public RentDTO rentBook(RentDTO rentDTO) {	
		final Book b = bookService.transform(bookService.findOne(rentDTO.getBook()));
		final User u = userService.transform(userService.findOne(rentDTO.getUser()));
		Rent rent = new Rent();
		
		if((bookService.availableBook(b.getId()) || this.findBookRent(b.getId()) == 0) && bookService.activeBook(b.getId()) && u.getStatus().equals(UserStatusEnum.ACTIVE)){			
			RentPK pk = new RentPK();
			
			pk.setBook(b);
			pk.setInitDate(new java.util.Date());
			
			rent.setPk(pk);
			rent.setUser(u);
			rent.setEndDate(null);
			
			rentDao.save(rent);
		}
		
		return transform(rent);
	}
	
	@Override
	public int findBookRent(Integer idBook) {	
		return rentDao.findBookRent(idBook);
	}
	
	@Override
	public RentDTO transform(Rent rent) {
		RentDTO renDto = new RentDTO();
		renDto.setBook(rent.getBook().getId());
		renDto.setUser(rent.getUser().getId());
		
		return renDto;
	}

	@Override
	public Rent transform(RentDTO rentDTO) {
		return dozer.map(rentDTO, Rent.class);
	}

	@Override
	public void returnBook(Integer id) {
		Rent r = rentDao.returnBook(id);
		r.setEndDate(new java.util.Date());
				
		rentDao.save(r);
	}

	@Override
	public List<HistoryRentedDTO> getAll(Integer page, Integer size) {
		List<HistoryRentedDTO> historyRentedDTO = rentDao.historyRented();
		if(page != null && size != null){
			List<HistoryRentedDTO> aux = new ArrayList<>();
			for(int i=(page*size)-size; i<(page*size) && i<historyRentedDTO.size(); i++)
				aux.add(historyRentedDTO.get(i));
			historyRentedDTO = aux;
		}		
		
		return historyRentedDTO;
	}

}
