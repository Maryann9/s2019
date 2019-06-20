package com.example.silph.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.silph.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Long> {

}