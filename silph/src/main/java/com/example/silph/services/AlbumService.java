package com.example.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.silph.model.Album;
import com.example.silph.repository.AlbumRepository;

@Service
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	
	@Transactional
	public Album add(Album album) {
		return this.albumRepository.save(album);
	}

	public List<Album> all() {
		return (List<Album>) albumRepository.findAll();
	}

	public Album findById(Long id) {
		return albumRepository.findById(id).get();
	}
}