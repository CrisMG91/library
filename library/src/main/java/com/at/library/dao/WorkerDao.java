package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Worker;

@Repository
public interface WorkerDao extends CrudRepository<Worker, Integer> {

}
