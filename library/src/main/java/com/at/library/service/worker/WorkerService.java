package com.at.library.service.worker;

import java.util.List;

import com.at.library.dto.WorkerDTO;
import com.at.library.model.Worker;

public interface WorkerService {

	/**
	 * Busca todos los trabajadores
	 * @param 
	 * @return List<WorkerDTO>
	 */
	List<WorkerDTO> findAll();

	/**
	 * Busca un trabajador
	 * @param id
	 * @return BookDTO
	 */
	WorkerDTO findOne(Integer id);
	
	/**
	 * Crea un trabajador
	 * @param worker
	 * @return 
	 */
	void create(WorkerDTO worker);

	/**
	 * Modifica un trabajador
	 * @param id
	 * @param worker
	 */
	void update(Integer id, WorkerDTO worker);

	/**
	 * Borra un trabajador
	 * @param id
	 */
	void delete(Integer id);
	
	
	/**
	 * Transforma un worker en un workerDTO
	 * 
	 * @param worker
	 * @return
	 */
	WorkerDTO transform(Worker worker);

	/**
	 * Transforma un workerDTO en un worker
	 * 
	 * @param worker
	 * @return
	 */
	Worker transform(WorkerDTO worker);	

}
