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

import com.example.silph.model.Album;
import com.example.silph.model.Photo;
import com.example.silph.model.PhotoCart;
import com.example.silph.services.AlbumService;
import com.example.silph.services.PhotoCartService;
import com.example.silph.services.PhotoService;
import com.example.silph.validator.PhotoValidator;

@Controller
public class PhotoController {

	@Autowired
	PhotoService photoService;
	
	@Autowired
	PhotoCartService photoCartService;
	
	@Autowired
	PhotoValidator photoValidator;
	
	@Autowired
	AlbumService albumService;
	
	
	@RequestMapping(value = "/addphotoNelCarrello/{id}")
	public String addPhotoToCart(@PathVariable("id") Long id, Model model) {
		if(id!=null) {
			Photo f = this.photoService.findById(id);
			PhotoCart fc = new PhotoCart(f.getNome(), f.getDescrizione(), f.getPrezzo(), f.getUrl());
			this.photoCartService.addToCart(fc);
			model.addAttribute("photos", this.photoService.all());
			return "photoGallery";
		}
		else {
			model.addAttribute("photos", this.photoService.all());
			return "photoGallery";
		}

	}
	
	@RequestMapping(value = "/photoPage/{id}")
	public String photoPage(@PathVariable("id") Long id, Model model) {
		if(id!=null) {
			Photo f = this.photoService.findById(id);
			model.addAttribute("photo", f);
			return "photo";
		}
		else {
			model.addAttribute("photos", this.photoService.all());
			return "photoGallery";
		}

	}

	@RequestMapping(value = "/goToCart", method = RequestMethod.GET)
	public String carrello(Model model) {
		if(this.photoCartService.all().isEmpty()) {
			return "emptyCart";
		}
		else {
			model.addAttribute("photosIncart", this.photoCartService.all());
			return "cart";
		}

	}
	
	@RequestMapping("/findPhoto")
	public String cercaAlbum(Model model) {
		model.addAttribute("photo", new Photo());
		return "findPhoto";
		
	}
	
	@RequestMapping(value = "/photoByName", method = RequestMethod.POST)
	public String photoPerNome(@Valid @ModelAttribute Photo photo, 
			Model model, BindingResult bindingResult) {
		List<Photo> photos = new ArrayList<>();

			for (Photo f : this.photoService.all()) {
				if(photo.getNome().equals(f.getNome())) {
					photos.add(f);
				}
			}
			
			if (photos.isEmpty()) {
				model.addAttribute("message", "No photos found "
						);
				return "findPhoto";
			}
			else {
				model.addAttribute("fotografie", photos);
				return "photoGallery";				
			}

		}
	
	@RequestMapping("/selezionaAlbum")
	public String selezionaFotografo(Model model) {
		model.addAttribute("listaAlbum", this.albumService.all());
		return "selezionaAlbum";
	}
	
	@RequestMapping(value = "/addPhoto")
	public String addPhoto(Model model, Album albumSelezionato) {
		Photo photo = new Photo();
		model.addAttribute("photo", photo);
		return "photoForm";
	}
	
	@RequestMapping(value = "/photo", method = RequestMethod.POST)
	public String newPhoto(@Valid@ModelAttribute Photo photo,
			Model model, BindingResult bindingResult) {
		this.photoValidator.validate(photo, bindingResult);
		if(!bindingResult.hasErrors()) {


			this.photoService.add(photo);
			model.addAttribute("photos", this.photoService.all());
			return "photoAdded";
		}
		else {
			return "photoForm";
		}
	}

	@RequestMapping(value = "/selectedAlbum/{id}", method = RequestMethod.GET)
	public String selectAlbum(@PathVariable("id") Long id, Model model, 
			Photo photo) {
		if(id!=null) {
			Album selectedAlbum =  this.albumService.findById(id);
			selectedAlbum.setId(this.albumService.findById(id).getId());
			
			photo.setAlbum(selectedAlbum);
			photo.setPhotographer(selectedAlbum.getPhotographer());
			
			model.addAttribute("selectedAlbum", selectedAlbum);
			return "selectedAlbum";
		}
		else {
			model.addAttribute("albumList", this.albumService.all());
			return "selectAlbum";
		}

	}
}

