package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Libro {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	@NotNull
	@NotEmpty(message="Name can not be empty")
	@Size(min=3, max=40,message="Name must be between 3 and 40 characters")
	private String name;
	
	@Column(name="author")
	@NotNull
	@NotEmpty(message="Author can not be empty")
	@Size(min=3, max=40,message="Author must be between 3 and 40 characters")
	private String author;
	
	@Column(name="pages")
	@NotNull
	@NotEmpty(message="Pages can not be empty")
	private int pages;
	
	@Column(name="synopsis")
	@NotNull
	@NotEmpty(message="Synopsis can not be empty")
	@Lob
	private String synopsis;
	
	@ManyToMany()
	@JoinTable(name="libro_genero")
	List<Genero> genders= new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public List<Genero> getGender() {
		return genders;
	}
	public void setGender(List<Genero> genders) {
		this.genders = genders;
	}
	public Libro() {
		super();
	}
	public Libro(int id,
			@NotNull @NotEmpty(message = "Name can not be empty") @Size(min = 3, max = 40, message = "Name must be between 3 and 40 characters") String name,
			@NotNull @NotEmpty(message = "Author can not be empty") @Size(min = 3, max = 40, message = "Author must be between 3 and 40 characters") String author,
			@NotNull @NotEmpty(message = "Pages can not be empty") int pages,
			@NotNull @NotEmpty(message = "Synopsis can not be empty") @Size(min = 4, max = 255, message = "Synopsis must be between 4 and 255 characters") String synopsis,
			List<Genero> genders) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.pages = pages;
		this.synopsis = synopsis;
		this.genders = genders;
	}
}
