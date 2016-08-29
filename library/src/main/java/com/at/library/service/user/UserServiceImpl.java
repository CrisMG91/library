package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDao;
import com.at.library.dto.UserBookRentDTO;
import com.at.library.dto.UserDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.Punishment;
import com.at.library.model.Rent;
import com.at.library.model.User;
import com.at.library.service.punishment.PunishmentService;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PunishmentService punishmentService;

	@Autowired
	private DozerBeanMapper dozer;
	
	public Date getDate(Integer days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date());		
		calendar.add(Calendar.DAY_OF_YEAR, days); 
		return calendar.getTime();
	}
	
	@Override
	@Scheduled(cron = "0 0/1 * * * ?" )
	public void penalize(){		
		log.debug("Comienza el proceso de sancion");
		Iterator<Integer> punish = userDao.punishUser().iterator();
		while (punish.hasNext()) {
			Integer idUser = punish.next();
			this.changePunishment(idUser);
			
			Punishment p = new Punishment();
			p.setId(idUser);
			p.setEndDay(this.getDate(3));
			punishmentService.create(p);
		}
	}
	
	@Override
	@Scheduled(cron = "0 0/1 * * * ?" )
	public void forgive(){
		log.debug("Comienza el proceso de comprobaciones de sanciones");
	}
	
	@Override
	public UserDTO create(UserDTO userDTO) {
		final User user = transform(userDTO);
		return transform(userDao.save(user));
	}
	
	@Override
	public void delete(Integer id) {
		userDao.delete(id);	
	}
	
	@Override
	public UserDTO transform(User user) {
		return dozer.map(user, UserDTO.class);
	}

	@Override
	public User transform(UserDTO user) {
		return dozer.map(user, User.class);
	}

	@Override
	public void disable(Integer id) {
		User u = userDao.findOne(id);
		u.setStatus(StatusEnum.DISABLE);
					
		userDao.save(u);			
	}

	@Override
	public void enable(Integer id) {
		User u = userDao.findOne(id);
		u.setStatus(StatusEnum.ACTIVE);
					
		userDao.save(u);
	}

	@Override
	public UserDTO findOne(Integer idUser) {
		final User u = userDao.findOne(idUser);
		return transform(u);
	}

	@Override
	public void changePunishment(Integer id) {
		User u = userDao.findOne(id);
		if(u.getPunished())
			u.setPunished(false);
		else
			u.setPunished(true);
					
		userDao.save(u);		
	}

	@Override
	public List<UserDTO> findAll() {
		final Iterable<User> findAll = userDao.findAll();
		final Iterator<User> iterator = findAll.iterator();
		final List<UserDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final User u = iterator.next();
			final UserDTO uDTO = transform(u);
			res.add(uDTO);
		}
		return res;
	}

	@Override
	public List<UserDTO> findByName(String name) {
		final Iterable<User> findAll = userDao.findByName(name);
		final Iterator<User> iterator = findAll.iterator();
		final List<UserDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final User u = iterator.next();
			final UserDTO uDTO = transform(u);
			res.add(uDTO);
		}
		return res;		
	}

	@Override
	public UserDTO findByDNI(String dni) {
		final User u = userDao.findByDni(dni);
		return transform(u);
	}

	@Override
	public List<UserDTO> findByStatus() {
		final Iterable<User> findAll = userDao.findByStatus(StatusEnum.ACTIVE);
		final Iterator<User> iterator = findAll.iterator();
		final List<UserDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final User u = iterator.next();
			final UserDTO uDTO = transform(u);
			res.add(uDTO);
		}
		return res;		
	}

	@Override
	public List<UserDTO> findByPunished() {
		final Iterable<User> findAll = userDao.findByPunished(1);
		final Iterator<User> iterator = findAll.iterator();
		final List<UserDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final User u = iterator.next();
			final UserDTO uDTO = transform(u);
			res.add(uDTO);
		}
		return res;	
	}

	@Override
	public List<UserBookRentDTO> getAllRent(Integer idUser) {
		List<Rent> rent = userDao.getAllRent(idUser);
		final Iterator<Rent> iterator = rent.iterator();
		final List<UserBookRentDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Rent r = iterator.next();
			final UserBookRentDTO ubrDTO = new UserBookRentDTO();
			ubrDTO.setRent(r);
			res.add(ubrDTO);
		}
		return res;	
	}

}
