package com.example.silph.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.silph.model.Executive;
import com.example.silph.services.ExecutiveService;
import com.example.silph.validator.ExecutiveValidator;

@Controller
public class LoginController {
	
	@Autowired
	private ExecutiveService executiveService;
	@Autowired
	private ExecutiveValidator executiveValidator;

	@RequestMapping(value = "/loginController", method = RequestMethod.POST)
	public String verifyCredentials(
			@Valid @ModelAttribute("executive") Executive executive, 
			Model model, BindingResult bindingResult) {
		this.executiveValidator.validate(executive, bindingResult);
		if(!bindingResult.hasErrors()) {
			
			Executive executiveDB = this.executiveService.executiveByUsername(executive.getUserName());
			if((executiveDB!=null)&&(executive.checkPassword(executiveDB))) {
				return "executiveHome";
			}
		}
		model.addAttribute("executive", executive);
		model.addAttribute("incorrect datas");
		return "executiveLogin";	
		
	}
}