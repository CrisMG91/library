package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.BookShelve;

@Repository
public interface BookShelveDao extends CrudRepository<BookShelve, String>{

}
