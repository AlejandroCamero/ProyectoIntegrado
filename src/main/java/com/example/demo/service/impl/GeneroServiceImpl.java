package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Genero;
import com.example.demo.repository.GeneroRepository;
import com.example.demo.service.GeneroService;

@Service
public class GeneroServiceImpl implements GeneroService{

	@Autowired
	@Qualifier("generoRepository")
	private GeneroRepository generoRepository;

	@Override
	public List<Genero> listAll() {
		// TODO Auto-generated method stub
		return generoRepository.findAllByOrderByName();
	}

	@Override
	public Genero findByName(String name) {
		// TODO Auto-generated method stub
		return generoRepository.findByName(name);
	}
}
