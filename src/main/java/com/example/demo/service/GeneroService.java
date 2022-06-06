package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Genero;
import com.example.demo.entity.Libro;

public interface GeneroService {
	List<Genero> listAll();
	Genero findByName(String name);
	Genero save(Genero genero);
	int removeGender(int id);
}
