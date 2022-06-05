package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Genero;
import com.example.demo.entity.Libro;
import com.example.demo.entity.Usuario;

public interface LibroService {

	Libro findByName(String name);
	List<Libro> findByAuthor(String author);
	List<Libro> listAll();
	Libro findById(int id);
}
