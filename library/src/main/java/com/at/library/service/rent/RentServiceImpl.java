package com.at.library.service.rent;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.RentDao;
import com.at.library.dto.RentDTO;
import com.at.library.model.Rent;

@Service
public class RentServiceImpl implements RentService{

	@Autowired
	private RentDao rentDao;

	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	public RentDTO rentBook(RentDTO rentDTO) {
		final Rent rent = transform(rentDTO);
		return transform(rentDao.save(rent));
	}
	
	@Override
	public RentDTO transform(Rent rent) {
		return dozer.map(rent, RentDTO.class);
	}

	@Override
	public Rent transform(RentDTO rentDTO) {
		return dozer.map(rentDTO, Rent.class);
	}

	
}
