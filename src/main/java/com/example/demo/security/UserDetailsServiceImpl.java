package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Usuario usuario = usuarioRepository.findByEmail(email);
		UserBuilder builder = null;

		if(usuario != null) {
			builder = User.withUsername(email);
			builder.disabled(false);
			builder.password(usuario.getPassword());
			builder.authorities(usuario.getRole());
		}else {
			throw new UsernameNotFoundException("username not found");
		}
		return builder.build();
	}

}
