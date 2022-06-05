package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Mensaje;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.MensajeRepository;
import com.example.demo.service.MensajeService;

@Service
public class MensajeServiceImpl implements MensajeService{

	@Autowired
	@Qualifier("mensajeRepository")
	private MensajeRepository mensajeRepository;
	
	@Override
	public List<Mensaje> findByIdEmisor(Usuario id) {
		// TODO Auto-generated method stub
		return mensajeRepository.findByIdEmisor(id);
	}

	@Override
	public List<Mensaje> findByIdReceptor(Usuario id) {
		// TODO Auto-generated method stub
		return mensajeRepository.findByIdReceptor(id);
	}

	@Override
	public int removeMensaje(int id) {
		mensajeRepository.deleteById(id);
		return 0;
	}

	@Override
	public Mensaje save(Mensaje mensaje) {
		// TODO Auto-generated method stub
		return mensajeRepository.save(mensaje);
	}

}
