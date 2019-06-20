package com.example.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.silph.model.Photographer;
import com.example.silph.repository.PhotographerRepository;

@Service
public class PhotographerService {
	@Autowired
	private PhotographerRepository photographerRepository;
	
	@Transactional
	public Photographer add(Photographer photographer) {
		return this.photographerRepository.save(photographer);
	}
	
	public List<Photographer> all() {
		return (List<Photographer>) photographerRepository.findAll();
	}

	public Photographer findById(Long id) {
		return photographerRepository.findById(id).get();
	}
}