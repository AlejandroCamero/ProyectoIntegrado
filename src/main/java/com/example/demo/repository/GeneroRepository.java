package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Genero;

public interface GeneroRepository extends JpaRepository<Genero,Integer>{
	public List<Genero> findAllByOrderByName();
	public Genero findByName(String name);
}
