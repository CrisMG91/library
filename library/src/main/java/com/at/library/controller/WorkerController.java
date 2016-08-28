package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.WorkerDTO;
import com.at.library.service.worker.WorkerService;

@RestController
@RequestMapping(value = "/worker")
public class WorkerController {

	@Autowired
	private WorkerService workerService;
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	/**
	 * Busca todos los trabajadores
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.GET })
	public List<WorkerDTO> getAll() {
		return workerService.findAll();
	}
	
	/**
	 * Devuelve un trabajador segun su id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}" , method = { RequestMethod.GET })
	public WorkerDTO findOne(@PathVariable("id")Integer id){
		log.debug(String.format("Buscando el trabajador con el id %s", id));
		return workerService.findOne(id);
	}
	
	/**
	 * Crea un trabajador
	 * @param workerDTO
	 * @return
	 */
	@RequestMapping( method = { RequestMethod.POST })
	public WorkerDTO create(@RequestBody WorkerDTO workerDTO) {
		log.debug(String.format("Creando el trabajador:", workerDTO));
		return workerService.create(workerDTO) ;
	}
	
}
