package com.example.silph.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.silph.model.Photographer;

public interface PhotographerRepository extends CrudRepository<Photographer, Long> {

}