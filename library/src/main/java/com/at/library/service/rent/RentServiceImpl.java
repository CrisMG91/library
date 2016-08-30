package com.at.library.service.rent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.RentDao;
import com.at.library.dto.RentDTO;
import com.at.library.dto.UserBookRentDTO;
import com.at.library.enums.UserStatusEnum;
import com.at.library.model.Book;
import com.at.library.model.Rent;
import com.at.library.model.RentPK;
import com.at.library.model.User;
import com.at.library.model.Worker;
import com.at.library.service.book.BookService;
import com.at.library.service.user.UserService;
import com.at.library.service.worker.WorkerService;

@Service
public class RentServiceImpl implements RentService{

	@Autowired
	private RentDao rentDao;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private WorkerService workerService;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	public boolean rentBook(RentDTO rentDTO) {	
		boolean resp = false;
		final Book b = bookService.transform(bookService.findOne(rentDTO.getIdLibro()));
		final User u = userService.transform(userService.findOne(rentDTO.getIdUser()));
		final Worker w = workerService.transform(workerService.findOne(rentDTO.getIdWorker()));
		
		if(bookService.availableBook(b.getId()) && u.getStatus().equals(UserStatusEnum.ACTIVE)){			
			Rent rent = new Rent();
			RentPK pk = new RentPK();
			
			pk.setBook(b);
			pk.setInitDate(new java.util.Date());
			
			rent.setPk(pk);
			rent.setUser(u);
			rent.setWorker(w);
			rent.setEndDate(null);
			
			transform(rentDao.save(rent));
			resp = true;
		}
		
		return resp;
	}
	
	@Override
	public RentDTO transform(Rent rent) {
		return dozer.map(rent, RentDTO.class);
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
	public List<UserBookRentDTO> getAll() {
		final Iterable<Rent> findAll = rentDao.findAll();
		final Iterator<Rent> iterator = findAll.iterator();
		final List<UserBookRentDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Rent r = iterator.next();
			final UserBookRentDTO rDTO = new UserBookRentDTO();
			rDTO.setRent(r);
			res.add(rDTO);
		}
		return res;
	}
	
}
