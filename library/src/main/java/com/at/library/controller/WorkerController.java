package com.at.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.dto.WorkerDTO;
import com.at.library.service.worker.WorkerService;

@RestController
@RequestMapping(value = "/worker")
public class WorkerController {

	@Autowired
	private WorkerService workerService;
	
	@RequestMapping(method = { RequestMethod.GET })
	public List<WorkerDTO> getAll() {
		return workerService.findAll();
	}
	
	@RequestMapping(value="/{id}", method = { RequestMethod.GET })
	public WorkerDTO findOne(@RequestParam("id") Integer id) {
		return workerService.findOne(id);
	}
	
	@RequestMapping(method = { RequestMethod.POST })
	public void create(WorkerDTO worker) {
		workerService.create(worker);
	}
	
	@RequestMapping(value="/{id}", method = { RequestMethod.PUT })
	public void update(@RequestParam("id") Integer id, WorkerDTO worker) {
		workerService.update(id, worker);
	}
	
	@RequestMapping(value="/{id}", method = { RequestMethod.DELETE })
	public void delete(@RequestParam("id") Integer id) {
		workerService.delete(id);
	}
}
