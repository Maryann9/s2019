package com.example.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.silph.model.Customer;
import com.example.silph.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional
	public Customer add(Customer customer) {
		return this.customerRepository.save(customer);
	}
	@Transactional
	public List<Customer> all() {
		return (List <Customer>)this.customerRepository.findAll();
	}
	
	public Customer findById(Long id) {
		return customerRepository.findById(id).get();
	}

}