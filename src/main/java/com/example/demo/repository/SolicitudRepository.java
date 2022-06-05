package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Solicitud;
import com.example.demo.entity.Usuario;

public interface SolicitudRepository extends JpaRepository<Solicitud,Integer>{
	public List<Solicitud> findByIdEmisor(Usuario id);
	public List<Solicitud> findByIdReceptor(Usuario id);
	public Solicitud findById(int id);
	public List<Solicitud> findByIdEmisorAndIdReceptor(Usuario id, Usuario id2);
}
