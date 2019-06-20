package com.example.silph.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.silph.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Long> {
	
}