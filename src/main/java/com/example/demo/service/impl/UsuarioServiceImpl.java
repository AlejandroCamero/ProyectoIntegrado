package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;
	

	public Usuario register(Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}


	@Override
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}


	@Override
	public List<Usuario> listAll() {
		return usuarioRepository.findAllByRoleOrderByUsername("ROLE_CLIENTE");
	}


	@Override
	public Usuario findById(int id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}


	@Override
	public Usuario enableUser(Usuario usuario) {
		usuario.setEnabled(true);
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario disableUser(Usuario usuario) {
		usuario.setEnabled(false);
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public int removeUser(int id) {
		usuarioRepository.deleteById(id);
		return 0;
	}


	@Override
	public Usuario update(Usuario us) {
		return usuarioRepository.save(us);
	}

}
