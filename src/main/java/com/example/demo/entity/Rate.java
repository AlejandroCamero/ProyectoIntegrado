package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Rate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne()
	@JoinColumn(name="usuario_id")
	private Usuario idUsuario;
	
	@ManyToOne()
	@JoinColumn(name="libro_id")
	private Libro idLibro;
	
	@Column(name="stars")
	private int stars;
	
	@Column(name="review")
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String review;
	
	@Column(name="valid")
	private boolean valid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Libro getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Libro idLibro) {
		this.idLibro = idLibro;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
	
	

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Rate(int id, Usuario idUsuario, Libro idLibro, int stars, String review, boolean valid) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idLibro = idLibro;
		this.stars = stars;
		this.review = review;
		this.valid = valid;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Rate() {
		super();
	}

	@Override
	public String toString() {
		return "Rate [id=" + id + ", idUsuario=" + idUsuario + ", idLibro=" + idLibro + ", stars=" + stars + ", review="
				+ review + "]";
	}
	

}
