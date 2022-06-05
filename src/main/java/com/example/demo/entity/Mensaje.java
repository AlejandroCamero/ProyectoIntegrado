package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Mensaje {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne()
	@JoinColumn(name="id_emisor")
	private Usuario idEmisor;
	
	@ManyToOne()
	@JoinColumn(name="id_receptor")
	private Usuario idReceptor;
	
	@Column(name="message")
	@Lob
	private String message;

	public Mensaje(int id, Usuario idEmisor, Usuario idReceptor, String message) {
		super();
		this.id = id;
		this.idEmisor = idEmisor;
		this.idReceptor = idReceptor;
		this.message = message;
	}

	public Mensaje() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getIdEmisor() {
		return idEmisor;
	}

	public void setIdEmisor(Usuario idEmisor) {
		this.idEmisor = idEmisor;
	}

	public Usuario getIdReceptor() {
		return idReceptor;
	}

	public void setIdReceptor(Usuario idReceptor) {
		this.idReceptor = idReceptor;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", idEmisor=" + idEmisor + ", idReceptor=" + idReceptor + ", mensaje=" + message
				+ "]";
	}
	
	
}
