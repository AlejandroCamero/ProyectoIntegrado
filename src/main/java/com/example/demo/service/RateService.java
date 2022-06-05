package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Libro;
import com.example.demo.entity.Rate;
import com.example.demo.entity.Usuario;

public interface RateService {
	List<Rate> findByIdLibroAndValid(Libro id,boolean valid);
	List<Rate> findByIdUsuario(Usuario id);
	List<Rate> findByValid(boolean valid);
	Rate saveReview(Rate rate);
	Rate validReview(Rate rate);
	Rate findById(int id);
	int removeReview(int id);
}
