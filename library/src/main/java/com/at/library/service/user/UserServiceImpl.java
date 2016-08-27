package com.at.library.service.user;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDao;
import com.at.library.dto.UserDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Autowired
	private DozerBeanMapper dozer;
	
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

}
