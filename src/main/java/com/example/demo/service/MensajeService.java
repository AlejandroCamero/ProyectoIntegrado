package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Mensaje;
import com.example.demo.entity.Usuario;

public interface MensajeService {
	List<Mensaje> findByIdEmisor(Usuario id);
	List<Mensaje> findByIdReceptor(Usuario id);
	int removeMensaje(int id);
	Mensaje save(Mensaje mensaje);
}
