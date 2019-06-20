package com.example.silph.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.silph.model.Executive;

public interface ExecutiveRepository extends CrudRepository<Executive, Long>{
	
	public Executive findByUserName(String userName);
}