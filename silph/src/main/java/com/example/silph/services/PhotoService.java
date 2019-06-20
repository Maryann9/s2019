package com.example.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.silph.model.Customer;
import com.example.silph.model.Photo;
import com.example.silph.repository.PhotoRepository;

@Service
public class PhotoService {
	@Autowired
	private PhotoRepository photoRepository;
	
	@Transactional
	public Photo add(Photo photo) {
		return this.photoRepository.save(photo);
	}
	
	public Iterable<Photo> findAllById(Iterable<Long> ids) {
		return this.photoRepository.findAllById(ids);
	}
	
	public List<Photo> all() {
		return (List<Photo>) photoRepository.findAll();
	}
	
	public Photo findById(Long id) {
		return photoRepository.findById(id).get();
	}
	
}
