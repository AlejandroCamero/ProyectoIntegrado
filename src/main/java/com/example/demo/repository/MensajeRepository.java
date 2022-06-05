package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Mensaje;
import com.example.demo.entity.Usuario;

public interface MensajeRepository extends JpaRepository<Mensaje,Integer>{
	
	public List<Mensaje> findByIdEmisor(Usuario id);
	public List<Mensaje> findByIdReceptor(Usuario id);

}
