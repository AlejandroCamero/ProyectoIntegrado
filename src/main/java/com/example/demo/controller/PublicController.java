package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Libro;
import com.example.demo.entity.Rate;
import com.example.demo.entity.Usuario;
import com.example.demo.service.GeneroService;
import com.example.demo.service.LibroService;
import com.example.demo.service.RateService;
import com.example.demo.service.UsuarioService;

@Controller
public class PublicController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LibroService libroService;
	
	
	@Autowired
	private GeneroService generoService;
	
	@Autowired
	private RateService rateService;
	
	@GetMapping("/")
	public String slash() {
		return "redirect:/public";
	}

	@GetMapping("/public")
	public String slash2(Authentication auth,HttpSession session, 
			@RequestParam(value="logout", required=false) String logout, RedirectAttributes redirect) {
		
		if(logout != null) {
			redirect.addFlashAttribute("mensaje", "Logout successfully");
			return "redirect:/auth/login?logout";
		}
		if(auth == null) {
			return "index";
		}else {
			String username = auth.getName();
			
			if(session.getAttribute("usuario")==null) {
				
				Usuario usuario = usuarioService.findByEmail(username);
				
				if(usuario.isEnabled()==false)
					return "redirect:auth/login?notEnable";
//				else if(usuario.getRole().equals("ROLE_CLIENTE")) {
//					return "redirect:/public";
//				}
				else {
					usuario.setPassword(null);
					session.setAttribute("usuario", usuario);
				}
			}
			return "index";
		}
	}
	
	@GetMapping("/books")
	public String books(Model model) {
		List<Libro> libros = libroService.listAll();
		
		model.addAttribute("books",libros);
		model.addAttribute("genders",generoService.listAll());
		return "books";
	}
	
	@GetMapping("/books/view/{id}")
	public String books(Model model, @PathVariable int id,Authentication auth,HttpSession session) {
		
		if(auth == null) {
			model.addAttribute("book",libroService.findById(id));
			return "book";
		}else {
			String username = auth.getName();
			Usuario usuario = usuarioService.findByEmail(username);
			if(usuario.getFavoritos().contains(libroService.findById(id)))
				model.addAttribute("notfavourite","Remove from favourites");
			else
				model.addAttribute("favourite","Add to favourites");
			
			if(usuario.getLeidos().contains(libroService.findById(id)))
				model.addAttribute("noleido","Remove from 'Books i have read'");
			else
				model.addAttribute("leido","Add to 'Books i have read'");
			
			model.addAttribute("book",libroService.findById(id));
			return "book";
		}
	}
	
	@PostMapping("/books/filter")
	public String filter(@RequestParam("genre") String genre, Model model) {
		
		if(genre!=null) {
			
			List<Libro> libros=libroService.listAll();
			List<Libro> newLibros=new ArrayList<>();
			for (int i=0;i<libros.size();i++) {
				if(libros.get(i).getGender().contains(generoService.findByName(genre))) {
					newLibros.add(libros.get(i));
				}
			}
			
			model.addAttribute("books",newLibros);
			
		}else {
			return "redirect:/books";
		}

		model.addAttribute("genders",generoService.listAll());
		return "books";
	}
	
	@GetMapping("/books/review/{id}")
	public String review(Model model,@PathVariable int id) {
		
		Libro b=libroService.findById(id);
		
		List<Rate> rates=rateService.findByIdLibroAndValid(b,true);
		model.addAttribute("book",b);
		model.addAttribute("reviews",rates);
		return "reviews";
	}
	
}
