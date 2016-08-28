package com.at.library.service.room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.RoomDao;
import com.at.library.dto.RoomDTO;
import com.at.library.model.BookShelve;
import com.at.library.model.Room;
import com.at.library.service.bookShelve.BookShelveService;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private BookShelveService bookShelveService;

	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	public List<RoomDTO> findAll() {
		final Iterable<Room> findAll = roomDao.findAll();
		final Iterator<Room> iterator = findAll.iterator();
		final List<RoomDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Room r = iterator.next();
			final RoomDTO rDTO = transform(r);
			res.add(rDTO);
		}
		return res;
	}

	@Override
	public RoomDTO findOne(String id) {
		final Room r=roomDao.findOne(id);
		return transform(r);
	}

	@Override
	public RoomDTO create(RoomDTO roomDTO) {
		final Iterator<String> iterator = roomDTO.getBookShelve().iterator();
		final List<BookShelve> bookShelves = new ArrayList<>();
		final Room room = new Room();
		
		while (iterator.hasNext()) {
			final String code = iterator.next();
			final BookShelve bookShelve = bookShelveService.transform(bookShelveService.findOne(code));
			bookShelves.add(bookShelve);
		}
		
		room.setCode(roomDTO.getCode());
		room.setBookShelve(bookShelves);
		
		return transform(roomDao.save(room));		
	}

	@Override
	public RoomDTO transform(Room room) {
		return dozer.map(room, RoomDTO.class);
	}

	@Override
	public Room transform(RoomDTO roomDTO) {
		return dozer.map(roomDTO, Room.class);
	}

}
