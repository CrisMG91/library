package com.at;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.at.library.dao.RentDao;
import com.at.library.dto.RentDTO;
import com.at.library.model.Rent;
import com.at.library.service.rent.RentService;
import com.at.library.service.rent.RentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestRentService {
	
	/*private static final Date INIT = new Date();
	
	private Rent rent = new Rent();
	
	@InjectMocks
	private RentService rentService = new RentServiceImpl();
	
	@Mock 
	private DozerBeanMapper dozer;
	
	@Mock
	private RentDao rentDao;
	
	@Before
	public void init(){
		final RentDTO r = new RentDTO();
		//r.setInitDate(INIT);
		Mockito.when(dozer.map(rent, RentDTO.class)).thenReturn(r);
		Mockito.when(rentDao.findOne(1)).thenReturn(rent);
		//Mockito.when(rentDao.save(rent)).thenReturn(value);
	}
	
	@Test
	@Ignore
	public void create(){
		createRent();
	}
	
	private void createRent(){
		rent.setInitDate(INIT);
	}
	
	@Test
	public void transformRent(){
		final RentService rentService = new RentServiceImpl();
		
		final Rent rent = new Rent();
		rent.setInitDate(INIT);
		final RentDTO rentDTO= rentService.transform(rent);
		Assert.assertEquals("Fecha", rentDTO.getInitDate(), INIT);
	}*/

}
