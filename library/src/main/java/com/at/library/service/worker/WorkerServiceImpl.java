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
		final Worker w = workerDao.findOne(id);
		return transform(w);
	}

	@Override
	public WorkerDTO create(WorkerDTO workerDTO) {
		final Worker worker=transform(workerDTO);
		return transform(workerDao.save(worker));			
	}

	@Override
	public WorkerDTO transform(Worker worker) {
		return dozer.map(worker, WorkerDTO.class);
	}

	@Override
	public Worker transform(WorkerDTO workerDTO) {
		return dozer.map(workerDTO, Worker.class);
	}

}
