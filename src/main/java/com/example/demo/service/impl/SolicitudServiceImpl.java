package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Solicitud;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.SolicitudRepository;
import com.example.demo.service.SolicitudService;

@Service
public class SolicitudServiceImpl implements SolicitudService{
	
	@Autowired
	@Qualifier("solicitudRepository")
	private SolicitudRepository solicitudRepository;

	@Override
	public List<Solicitud> findByIdEmisor(Usuario id) {
		// TODO Auto-generated method stub
		return solicitudRepository.findByIdEmisor(id);
	}

	@Override
	public List<Solicitud> findByIdReceptor(Usuario id) {
		// TODO Auto-generated method stub
		return solicitudRepository.findByIdReceptor(id);
	}

	@Override
	public int removeSolicitud(int id) {
		solicitudRepository.deleteById(id);
		return 0;
	}

	@Override
	public Solicitud findById(int id) {
		// TODO Auto-generated method stub
		return solicitudRepository.findById(id);
	}

	@Override
	public Solicitud save(Solicitud solicitud) {
		// TODO Auto-generated method stub
		return solicitudRepository.save(solicitud);
	}

	@Override
	public List<Solicitud> findByIdEmisorAndIdReceptor(Usuario id, Usuario id2) {
		// TODO Auto-generated method stub
		return solicitudRepository.findByIdEmisorAndIdReceptor(id, id2);
	}

}
