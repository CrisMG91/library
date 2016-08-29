package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Punishment;

@Repository
public interface PunishmentDao extends CrudRepository<Punishment, Integer>{

}
