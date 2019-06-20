package com.example.silph.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.silph.model.Photographer;
import com.example.silph.services.PhotographerService;
import com.example.silph.validator.PhotographerValidator;

@Controller
public class PhotographerController {

	@Autowired
	PhotographerService photographerService;
	
	@Autowired
	PhotographerValidator photographerValidator;
	
	@RequestMapping("/addPhotographer")
	public String addPhotographer(Model model) {
		model.addAttribute("photographer", new Photographer());
		return "photographerForm";
		
	}
	
	@RequestMapping(value = "/photographer", method = RequestMethod.POST)
	public String newPhotographer(@Valid @ModelAttribute Photographer photographer, 
			Model model, BindingResult bindingResult) {
		this.photographerValidator.validate(photographer, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.photographerService.add(photographer);
			model.addAttribute("photographers", this.photographerService.all());
			return "photographers";
		}
		else {
			return "photographerForm";
		}
	}
	

	
	@RequestMapping(value = "/photographer/{id}", method = RequestMethod.GET)
	public String getPhotographer(@PathVariable("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("photographer", this.photographerService.findById(id));
			return "photographer.html";
		}
		else {
			model.addAttribute("photographers", this.photographerService.all());
			return "photographers.html";
		}
	}
	
	@RequestMapping("/findPhotographer")
	public String findPhotographer(Model model) {
		model.addAttribute("photographer", new Photographer());
		return "findPhotographerByName";
		
	}
	
	@RequestMapping(value = "/photographerPerNome", method = RequestMethod.POST)
	public String photographerByName(@Valid @ModelAttribute Photographer photographer, 
			Model model, BindingResult bindingResult) {
		this.photographerValidator.validate(photographer, bindingResult);
		List<Photographer> photographers = new ArrayList<>();

			for (Photographer f : this.photographerService.all()) {
				if(photographer.getNome().equals(f.getNome())) {
					photographers.add(f);
				}
			}
			
			if (photographers.isEmpty()) {
				model.addAttribute("message", "No such photographers");
				return "findPhotographersById";
			}
			else {
				model.addAttribute("photographers", photographers);
				return "photographers";				
			}

		}

	
	
}