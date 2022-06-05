package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Solicitud;
import com.example.demo.entity.Usuario;

public interface SolicitudService {
	public List<Solicitud> findByIdEmisor(Usuario id);
	public List<Solicitud> findByIdReceptor(Usuario id);
	Solicitud findById(int id);
	int removeSolicitud(int id);
	Solicitud save(Solicitud solicitud);
	List<Solicitud> findByIdEmisorAndIdReceptor(Usuario id, Usuario id2);
}
