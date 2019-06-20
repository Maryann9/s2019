package com.example.silph.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.silph.services.PhotoCartService;

@Controller
public class PhotoCartController {

	@Autowired
	private PhotoCartService photoCartService;

	@RequestMapping(value = "/removePhoto/{id}", method = RequestMethod.GET)
	public String removePhotoFromCart(@PathVariable("id") Long id, Model model) {
		
		if (this.photoCartService.all().contains(this.photoCartService.findById(id))) {
			this.photoCartService.removePhoto(this.photoCartService.findById(id));
			return "redirect:/goToCart";
		}
		else
			model.addAttribute("photosInCart", this.photoCartService.all());
			return "redirect:/goToCart";
	}
}