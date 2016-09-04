package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDao;
import com.at.library.dto.UserBookRentDTO;
import com.at.library.dto.UserDTO;
import com.at.library.enums.UserStatusEnum;
import com.at.library.exception.UserException;
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
	public void penalize() throws Exception{		
		log.debug("Comienza el proceso de sancion");
		Iterator<Rent> punish = userDao.punishUser().iterator();
		while (punish.hasNext()) {
			Rent r = punish.next();
			DateTime initDate = new DateTime(r.getInitDate());
			DateTime endDate = new DateTime(r.getEndDate());
			Integer days = Days.daysBetween(initDate.toLocalDate(), endDate.toLocalDate()).getDays();
			
			if( days > 3){
				Integer idUser = r.getUser().getId();
				this.changePunishment(idUser, 0);
				Punishment p = new Punishment();
				p.setId(idUser);
				p.setEndDay(endDate.plusDays(3*days).toDate());
				punishmentService.create(p);
			}
		}
	}
	
	@Override
	@Scheduled(cron = "0 0/1 * * * ?" )
	public void forgive() throws Exception{
		log.debug("Comienza el proceso de comprobaciones de sanciones");
		Iterator<Integer> forgive = userDao.forgiveUser().iterator();
		while (forgive.hasNext()) {
			UserDTO uDTO = this.findOne(forgive.next());
			User u = this.transform(uDTO);
			if(u.getStatus() == UserStatusEnum.PUNISHED)
				this.changePunishment(u.getId(), 1);
		}
	}
	
	@Override
	public UserDTO create(UserDTO userDTO) {
		final User user = transform(userDTO);
		user.setStatus(UserStatusEnum.ACTIVE);
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
	public void disable(Integer id) throws Exception{
		User u = userDao.findId(id);
		
		if(u == null)
			throw new UserException(3);
			
		u.setStatus(UserStatusEnum.DISABLE);
		userDao.save(u);					
	}

	@Override
	public void enable(Integer id) throws Exception{
		User u = userDao.findOne(id);
		
		if(u == null)
			throw new UserException(5);
		u.setStatus(UserStatusEnum.ACTIVE);
					
		userDao.save(u);
	}

	@Override
	public UserDTO findOne(Integer idUser) throws Exception{
		final User u = userDao.findId(idUser);
		UserDTO uDTO = new UserDTO();		
		if(u == null)
			throw new UserException(2);
		
		uDTO = transform(u);
		return uDTO;
	}

	@Override
	public void changePunishment(Integer id, Integer option) throws Exception{
		User u = userDao.findOne(id);
		
		if(u == null)
			throw new UserException(1);
		if(option == 1)
			u.setStatus(UserStatusEnum.ACTIVE);
		else
			u.setStatus(UserStatusEnum.PUNISHED);
					
		userDao.save(u);		
	}

	@Override
	public List<UserDTO> findAll() {
		final Iterable<User> findAll = userDao.findAllUser();
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
	public List<UserDTO> findByDNI(String dni) {
		final Iterable<User> findUser = userDao.findByDni(dni);
		final Iterator<User> iterator = findUser.iterator();
		final List<UserDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final User u = iterator.next();
			final UserDTO uDTO = transform(u);
			res.add(uDTO);
		}
		return res;
	}

	@Override
	public List<UserDTO> findByStatus() {
		final Iterable<User> findAll = userDao.findByStatus(UserStatusEnum.ACTIVE);
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
		final Iterable<User> findAll = userDao.findByStatus(UserStatusEnum.PUNISHED);
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

	@Override
	public List<UserDTO> findUsers(String name, String dni) {
		List<UserDTO> usersDTO = new ArrayList<>();
		
		if(name == null && dni == null)
			usersDTO = this.findAll();
		else if(name != null && dni == null)
			usersDTO = this.findByName(name);
		else if (name == null && dni != null)
			usersDTO = this.findByDNI(dni);
		else 
			usersDTO = this.findByNameDni(name, dni);
			
		return usersDTO;
	}

	@Override
	public List<UserDTO> findByNameDni(String name, String dni) {
		final Iterable<User> findAll = userDao.findByNameAndDni(name, dni);
		final Iterator<User> iterator = findAll.iterator();
		final List<UserDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final User u = iterator.next();
			final UserDTO uDTO = transform(u);
			res.add(uDTO);
		}
		return res;	
	}

}
