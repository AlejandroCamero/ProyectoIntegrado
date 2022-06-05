package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Libro;
import com.example.demo.entity.Rate;
import com.example.demo.entity.Usuario;

public interface RateRepository extends JpaRepository<Rate,Integer>{
	public List<Rate> findByIdLibroAndValid(Libro libro,boolean valid);
	public List<Rate> findByIdUsuario(Usuario usuario);
	public List<Rate> findByValid(boolean valid);
	public Rate findById(int id);
}
