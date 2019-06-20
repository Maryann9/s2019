package com.example.silph.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.silph.model.Executive;
import com.example.silph.model.PhotoCart;
import com.example.silph.services.AlbumService;
import com.example.silph.services.PhotoService;
import com.example.silph.services.PhotographerService;

@Controller
public class MainController {
	
	@Autowired
	PhotoService photoService;

	@Autowired
	PhotographerService photographerService;
	
	@Autowired
	AlbumService albumService;
		
	
		
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getIndex(Model model) {
		return "index.html"; 
	}
	
	@RequestMapping(value="/getGallery")
	public String getGallery(Model model) {
		model.addAttribute("photos", this.photoService.all());
		model.addAttribute("cart", new PhotoCart());
		return "photoGallery";
	}
	
	@RequestMapping(value="/findPhotographers")
	public String getPhotographers(Model model) {
		model.addAttribute("photographers", this.photographerService.all());
		return "photographers";
	}
	
	@RequestMapping(value="/getAlbum")
	public String getAlbum (Model model) {
		model.addAttribute("albumList", this.albumService.all());
		return "albumList";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		Executive executive = new Executive();
		model.addAttribute("executive", executive);
		return "executiveLogin";
	}

}

