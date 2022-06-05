package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Solicitud {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne()
	@JoinColumn(name="id_emisor")
	private Usuario idEmisor;
	
	@ManyToOne()
	@JoinColumn(name="id_receptor")
	private Usuario idReceptor;

	public Solicitud(int id, Usuario idEmisor, Usuario idReceptor) {
		super();
		this.id = id;
		this.idEmisor = idEmisor;
		this.idReceptor = idReceptor;
	}

	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", idEmisor=" + idEmisor + ", idReceptor=" + idReceptor + "]";
	}

	public Solicitud() {
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
	
	
}
