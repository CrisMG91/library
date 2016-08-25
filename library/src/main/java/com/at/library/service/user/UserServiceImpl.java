package com.at.library.service.user;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDao;
import com.at.library.dto.UserDTO;
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
	public UserDTO transform(User user) {
		return dozer.map(user, UserDTO.class);
	}

	@Override
	public User transform(UserDTO user) {
		return dozer.map(user, User.class);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
