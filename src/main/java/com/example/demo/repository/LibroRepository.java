package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Integer>{
	public Libro findByName(String name);
	public List<Libro> findByAuthor(String author);
	public Libro findById(int id);
}
