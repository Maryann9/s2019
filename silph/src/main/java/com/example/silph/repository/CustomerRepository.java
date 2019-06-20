package com.example.silph.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.silph.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}