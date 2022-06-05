package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Genero;
import com.example.demo.entity.Libro;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService{

	@Autowired
	@Qualifier("libroRepository")
	private LibroRepository libroRepository;

	@Override
	public Libro findByName(String name) {
		return libroRepository.findByName(name);
	}

	@Override
	public List<Libro> findByAuthor(String author) {
		return libroRepository.findByAuthor(author);
	}


	@Override
	public List<Libro> listAll() {
		// TODO Auto-generated method stub
		return libroRepository.findAll();
	}

	@Override
	public Libro findById(int id) {
		// TODO Auto-generated method stub
		return libroRepository.findById(id);
	}
}
