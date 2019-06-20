package com.example.silph.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.silph.model.Executive;
import com.example.silph.repository.ExecutiveRepository;

@Service
public class ExecutiveService {
	
	@Autowired
	private ExecutiveRepository executiveRepository;
	
	@Transactional
	public Executive executiveByUsername(String userName) {
		return this.executiveRepository.findByUserName(userName);
	}

	public Executive add(Executive executive) {
		return this.executiveRepository.save(executive);
	}

}