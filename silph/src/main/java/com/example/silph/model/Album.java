package com.example.silph.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Album {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
	private List<Photo> photos;
	
	@ManyToOne
	private Photographer photographer;

	public Album(String nome) {
		this.nome = nome;
	}
	
	public Album() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photographer getPhotographer() {
		return photographer;
	}

	public void setPhotographer(Photographer photographer) {
		this.photographer = photographer;
	}
}