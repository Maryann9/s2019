package com.example.silph.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Photographer {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	
	@OneToMany(mappedBy = "photographer", cascade = CascadeType.ALL)
	private List<Photo> photos;
	
	@OneToMany(mappedBy = "photographer", cascade = CascadeType.ALL)
	private List<Album> album;

	public Photographer(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public List<Album> getAlbum() {
		return album;
	}

	public void setAlbum(List<Album> album) {
		this.album = album;
	}

	public Photographer() {
		
	}
}