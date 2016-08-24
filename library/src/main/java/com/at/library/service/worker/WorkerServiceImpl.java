package com.at.library.service.worker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.WorkerDao;
import com.at.library.dto.WorkerDTO;
import com.at.library.model.Worker;

@Service
public class WorkerServiceImpl implements WorkerService{
	
	@Autowired
	private WorkerDao workerDao;

	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	public List<WorkerDTO> findAll() {
		final Iterable<Worker> findAll = workerDao.findAll();
		final Iterator<Worker> iterator = findAll.iterator();
		final List<WorkerDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Worker w = iterator.next();
			final WorkerDTO wDTO = transform(w);
			res.add(wDTO);
		}
		return res;
	}

	@Override
	public WorkerDTO findOne(Integer id) {
		return transform(workerDao.findOne(id));
	}

	@Override
	public void create(WorkerDTO worker) {
		workerDao.save(transform(worker));
		
	}

	@Override
	public void update(Integer id, WorkerDTO worker) {
		workerDao.save(transform(worker));		
	}

	@Override
	public void delete(Integer id) {
		workerDao.delete(id);
		
	}

	@Override
	public WorkerDTO transform(Worker worker) {
		return dozer.map(worker, WorkerDTO.class);
	}

	@Override
	public Worker transform(WorkerDTO worker) {
		return dozer.map(worker, Worker.class);
	}

}
