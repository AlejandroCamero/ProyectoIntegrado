package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Genero;
import com.example.demo.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	public Usuario findByUsername(String username);
	public Usuario findByEmail(String email);
	public List<Usuario> findByRole(String role);
	public List<Usuario> findAllByRoleOrderByUsername(String role);
	public Usuario findById(int id);
	public Usuario findByEnabled(boolean b);
}
