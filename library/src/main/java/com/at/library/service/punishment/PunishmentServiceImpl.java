package com.at.library.service.punishment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.PunishmentDao;

import com.at.library.model.Punishment;

@Service
public class PunishmentServiceImpl implements PunishmentService{

	@Autowired
	private PunishmentDao punishmentDao;
	
	@Override
	public void create(Punishment p) {		
		punishmentDao.save(p);	
	}	

}
