package com.at.library.service.rent;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.RentDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.UserDTO;
import com.at.library.exception.BookRentedException;
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
	public RentDTO rentBook(RentDTO rentDTO) throws Exception{	
		BookDTO bookDto = new BookDTO();
		UserDTO userDto = new UserDTO();
		try{
			bookDto = bookService.findOne(rentDTO.getBook());
			userDto = userService.findOne(rentDTO.getUser());
		}catch(Exception e){}
		
		Rent rent = new Rent();
		RentDTO resp = new RentDTO();
		
		if(bookDto.getId() == null || userDto.getId() == null)
			throw new BookRentedException(3);
		
		final Book b = bookService.transform(bookDto);
		final User u = userService.transform(userDto);		

		if((bookService.availableBook(b.getId()) || this.findBookRent(b.getId()) == 0) && bookService.activeBook(b.getId())){			
			RentPK pk = new RentPK();
			
			pk.setBook(b);
			pk.setInitDate(new java.util.Date());
			
			rent.setPk(pk);
			rent.setUser(u);
			rent.setEndDate(null);
			
			rentDao.save(rent);
			resp = transform(rent);
		}
		
		return resp;
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
	public void returnBook(Integer id) throws Exception{
		Rent r = rentDao.returnBook(id);
		
		if(r == null)
			throw new BookRentedException(2);
		
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
