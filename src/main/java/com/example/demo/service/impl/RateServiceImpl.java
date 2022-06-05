package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Libro;
import com.example.demo.entity.Rate;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.RateRepository;
import com.example.demo.service.RateService;

@Service
public class RateServiceImpl implements RateService{

	@Autowired
	@Qualifier("rateRepository")
	private RateRepository rateRepository;
	
	

	@Override
	public Rate saveReview(Rate rate) {
		// TODO Auto-generated method stub
		return rateRepository.save(rate);
	}



	@Override
	public List<Rate> findByIdLibroAndValid(Libro id,boolean valid) {
		// TODO Auto-generated method stub
		return rateRepository.findByIdLibroAndValid(id,valid);
	}



	@Override
	public List<Rate> findByIdUsuario(Usuario id) {
		// TODO Auto-generated method stub
		return rateRepository.findByIdUsuario(id);
	}



	@Override
	public List<Rate> findByValid(boolean valid) {
		// TODO Auto-generated method stub
		return rateRepository.findByValid(valid);
	}



	@Override
	public Rate validReview(Rate rate) {
		rate.setValid(true);
		return rateRepository.save(rate);
	}



	@Override
	public Rate findById(int id) {
		// TODO Auto-generated method stub
		return rateRepository.findById(id);
	}



	@Override
	public int removeReview(int id) {
		rateRepository.deleteById(id);
		return 0;
	}
}
