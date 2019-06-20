package com.example.silph.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.silph.model.Customer;
import com.example.silph.services.CustomerService;
import com.example.silph.services.PhotoCartService;
import com.example.silph.validator.CustomerValidator;

@Controller

public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	PhotoCartService photoCartService;
	
	@Autowired
	CustomerValidator customerValidator;
	
	@RequestMapping("/addRequest")
	public String addCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "requestForm";
		
	}
	
	@RequestMapping(value = "/request", method = RequestMethod.POST)
	public String newCustomer(@Valid @ModelAttribute Customer customer, 
			Model model, BindingResult bindingResult) {
		this.customerValidator.validate(customer, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.customerService.add(customer);
			model.addAttribute("customer", customer);
			model.addAttribute("photosInCart", this.photoCartService.all());
			return "request";
		}
		else {
			return "requestForm";
		}
	}
	
	@RequestMapping(value = "/receivedRequests", method = RequestMethod.GET)
	public String richiesteRicevute(Model model) {
		if(this.customerService.all().isEmpty()) {
			model.addAttribute("message", "There are no requests");
			return "requests";
		}
		else {
			model.addAttribute("customers", this.customerService.all());
			return "requests";
		}

	}
	
	
	@RequestMapping(value = "/customer/{id}")
	public String photoPage(@PathVariable("id") Long id, Model model) {
		if(id!=null) {
			Customer customer = this.customerService.findById(id);
			model.addAttribute("photosInCart", this.photoCartService.all());
			model.addAttribute("customer", customer);
			return "request";
		}
		else {
			model.addAttribute("customers", this.customerService.all());
			return "requests";
		}

	}
	
	
}