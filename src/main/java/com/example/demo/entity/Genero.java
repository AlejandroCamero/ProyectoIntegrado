package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Genero {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	@NotNull
	@NotEmpty(message="Name can not be empty")
	@Size(min=3, max=40,message="Name must be between 3 and 40 characters")
	private String name;

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

	@Override
	public String toString() {
		return "Genero [id=" + id + ", name=" + name + "]";
	}

	public Genero(int id,
			@NotNull @NotEmpty(message = "Name can not be empty") @Size(min = 3, max = 40, message = "Name must be between 3 and 40 characters") String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Genero() {
		super();
	}
}
