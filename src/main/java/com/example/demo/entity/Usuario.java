package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Usuario {

	public Usuario() {
		super();
	}

	public Usuario(int id,
			@NotNull @NotEmpty(message = "Username can not be empty") @Size(min = 3, max = 40, message = "Username must be between 3 and 40 characters") String username,
			@Email(message = "Not valid format") @NotNull @NotEmpty(message = "Email can not be empty") @Size(min = 5, max = 50, message = "Username must be between 5 and 60 characters") String email,
			boolean enabled,
			@NotNull @NotEmpty(message = "Password can not be empty") @Size(min = 4, max = 255, message = "Password must be between 4 and 255 characters") String password,
			String role) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.enabled = enabled;
		this.password = password;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="username")
	@NotNull
	@NotEmpty(message="Username can not be empty")
	@Size(min=3, max=40,message="Username must be between 3 and 40 characters")
	private String username;
	
	@Column(name="email", length = 50)
	@Email(message="Not valid format")
	@NotNull
	@NotEmpty(message="Email can not be empty")
	@Size(min=5, max=50, message="Username must be between 5 and 60 characters")
	private String email;
	
	private boolean enabled;
	
	@NotNull
	@NotEmpty(message="Password can not be empty")
	@Size(min=4, max=255, message="Password must be between 4 and 255 characters")
	private String password;
	
	private String role;
	
	@ManyToMany()
	@JoinTable(name="favourites")
	List<Libro> favs= new ArrayList<>();
	
	@ManyToMany()
	@JoinTable(name="leidos")
	List<Libro> leidos= new ArrayList<>();
	
	@ManyToMany()
	@JoinTable(name="friends")
	List<Usuario> amigos = new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Libro> getFavoritos() {
		return favs;
	}

	public void setFavoritos(List<Libro> favs) {
		this.favs = favs;
	}

	public List<Libro> getFavs() {
		return favs;
	}

	public void setFavs(List<Libro> favs) {
		this.favs = favs;
	}

	public List<Libro> getLeidos() {
		return leidos;
	}

	public void setLeidos(List<Libro> leidos) {
		this.leidos = leidos;
	}

	public List<Usuario> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Usuario> amigos) {
		this.amigos = amigos;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", email=" + email + ", enabled=" + enabled
				+ ", password=" + password + ", role=" + role + ", favs=" + favs + ", leidos=" + leidos + "]";
	}
	
}
