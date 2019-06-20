package com.example.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.silph.model.PhotoCart;
import com.example.silph.repository.PhotoCartRepository;

@Service
public class PhotoCartService {

	@Autowired
	PhotoCartRepository photoCartRepository;
	
	@Transactional
	public PhotoCart addToCart(PhotoCart photo) {
		return this.photoCartRepository.save(photo);
	}
	
	@Transactional
	public List<PhotoCart> all() {
		return (List <PhotoCart>)this.photoCartRepository.findAll();
	}
	
	@Transactional
	public void removePhoto(PhotoCart photo) {
		this.photoCartRepository.delete(photo);;
	}
	
	@Transactional
	public PhotoCart findById(Long id) {
		return this.photoCartRepository.findById(id).get();
	}
	
}