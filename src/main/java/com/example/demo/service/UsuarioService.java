package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Genero;
import com.example.demo.entity.Usuario;

public interface UsuarioService {
	Usuario register(Usuario usuario);
	Usuario findByEmail(String email);
	List<Usuario> listAll();
	Usuario findById(int id);
	Usuario disableUser(Usuario us);
	Usuario enableUser(Usuario us);
	int removeUser(int id);
	Usuario update(Usuario us);
}
