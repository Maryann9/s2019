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

import com.example.silph.model.Album;
import com.example.silph.model.Photographer;
import com.example.silph.services.AlbumService;
import com.example.silph.services.PhotographerService;
import com.example.silph.validator.AlbumValidator;

@Controller
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private AlbumValidator albumValidator;
	
	@Autowired
	private PhotographerService photographerService;
	
	@RequestMapping("/selectPhotographer")
	public String selectPhotographer(Model model) {
		model.addAttribute("photographers", this.photographerService.all());
		return "selectPhotographer";
	}
	
	@RequestMapping("/addAlbum")
	public String addAlbum(Model model) {
		model.addAttribute("album", new Album());
		return "albumForm";
	}
	
	@RequestMapping(value = "/album", method = RequestMethod.POST)
	public String newAlbum(@Valid@ModelAttribute Album album,
			Model model, BindingResult bindingResult) {
		this.albumValidator.validate(album, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.albumService.add(album);
			model.addAttribute("listaAlbum", this.albumService.all());
			return "albumList";
		}
		else {
			model.addAttribute("photographers", this.photographerService.all());
			return "albumForm";
		}
	}
	
	@RequestMapping(value = "/album/{id}", method = RequestMethod.GET)
	public String getAlbum(@PathVariable("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("album", this.albumService.findById(id));
			return "album";
		}
		else {
			model.addAttribute("AlbumList", this.albumService.all());
			return "albumList";
		}
	}
	
	@RequestMapping(value = "/selectedPhotographer/{id}", method = RequestMethod.GET)
	public String selectPhotographer(@PathVariable("id") Long id, Model model) {
		if(id!=null) {
			Photographer selectedPhotographer =  this.photographerService.findById(id);
			selectedPhotographer.setId(this.photographerService.findById(id).getId());
			model.addAttribute("selectedPhotographer", selectedPhotographer);
			return "selectedPhotographer";
		}
		else {
			model.addAttribute("photographers", this.photographerService.all());
			return "selectPhotographer";
		}

	}

}