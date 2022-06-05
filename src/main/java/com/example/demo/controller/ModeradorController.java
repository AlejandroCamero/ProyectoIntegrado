package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Libro;
import com.example.demo.entity.Mensaje;
import com.example.demo.entity.Usuario;
import com.example.demo.service.LibroService;
import com.example.demo.service.MensajeService;
import com.example.demo.service.RateService;
import com.example.demo.service.SolicitudService;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/moderador/")
public class ModeradorController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private RateService rateService;
	
	@Autowired
	private MensajeService mensajeService;
	
	@Autowired
	private SolicitudService solicitudService;
	
	@GetMapping("/profile")
	public String profile(Authentication auth,HttpSession session, Model model) {
		String username = auth.getName();
		Usuario usuario = usuarioService.findByEmail(username);
		model.addAttribute("user",usuario);
		return "cliente/profile";
	}
	
	@GetMapping("/updatepassword")
	public String updatepassword(Authentication auth,HttpSession session, Model model) {
		String username = auth.getName();
		Usuario usuario = usuarioService.findByEmail(username);
		model.addAttribute("user",usuario);
		return "cliente/updatepassword";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Usuario usuario, Authentication auth,HttpSession session,Model model,RedirectAttributes flash) {
		usuario.setId(usuarioService.findByEmail(usuario.getEmail()).getId());
		usuario.setRole("ROLE_MOD");
		usuario.setEnabled(true);
		usuario.setPassword(usuarioService.findByEmail(usuario.getEmail()).getPassword());
		usuarioService.update(usuario);
		flash.addFlashAttribute("mensaje","Profile updated");
		return "redirect:/logout";
	}
	
	@PostMapping("/updatepassword")
	public String updatepassword(@ModelAttribute Usuario usuario, Authentication auth,HttpSession session,Model model,RedirectAttributes flash) {
		String username = auth.getName();
		Usuario user = usuarioService.findByEmail(username);
		
		usuario.setId(user.getId());
		usuario.setRole("ROLE_MOD");
		usuario.setEnabled(true);
		usuario.setEmail(user.getEmail());
		usuario.setUsername(user.getUsername());
		usuarioService.register(usuario);
		flash.addFlashAttribute("mensaje","Password updated");
		return "redirect:/logout";
	}
	
	@PostMapping("/favourite/{id}")
	public String favourite(@PathVariable int id,Authentication auth) {
		Libro libro = libroService.findById(id);
		String username = auth.getName();
		Usuario user = usuarioService.findByEmail(username);
		
		List<Libro> favs=user.getFavoritos();
		favs.add(libro);
		
		user.setFavoritos(favs);
		
		usuarioService.update(user);
		
		return "redirect:/books";
	}
	
	@PostMapping("/unfavourite/{id}")
	public String unfavourite(@PathVariable int id,Authentication auth) {
		Libro libro = libroService.findById(id);
		String username = auth.getName();
		Usuario user = usuarioService.findByEmail(username);
		
		List<Libro> favs=user.getFavoritos();
		favs.remove(libro);
		
		user.setFavoritos(favs);
		
		usuarioService.update(user);
		
		return "redirect:/books";
	}
	
	@PostMapping("/read/{id}")
	public String read(@PathVariable int id,Authentication auth) {
		Libro libro = libroService.findById(id);
		String username = auth.getName();
		Usuario user = usuarioService.findByEmail(username);
		
		List<Libro> leidos=user.getLeidos();
		leidos.add(libro);
		
		user.setLeidos(leidos);
		
		usuarioService.update(user);
		
		return "redirect:/books";
	}
	
	@PostMapping("/unread/{id}")
	public String unread(@PathVariable int id,Authentication auth) {
		Libro libro = libroService.findById(id);
		String username = auth.getName();
		Usuario user = usuarioService.findByEmail(username);
		
		List<Libro> leidos=user.getLeidos();
		leidos.remove(libro);
		
		user.setLeidos(leidos);
		
		usuarioService.update(user);
		
		return "redirect:/books";
	}
	
	@GetMapping("/favourites")
	public String favourite(Model model,Authentication auth) {
		String username = auth.getName();
		Usuario user = usuarioService.findByEmail(username);
		
		model.addAttribute("books",user.getFavoritos());
		
		return "favourites";
	}
	
	@GetMapping("/read")
	public String read(Model model,Authentication auth) {
		String username = auth.getName();
		Usuario user = usuarioService.findByEmail(username);
		
		model.addAttribute("books",user.getLeidos());
		
		return "read";
	}
	
	@GetMapping("/reviews")
	public String reviews(Model model) {
		model.addAttribute("reviews",rateService.findByValid(false));
		return "moderador/reviews";
	}
	
	@PostMapping("validReview/{id}")
	public String valid(@PathVariable int id, Model model,RedirectAttributes flash) {
		model.addAttribute("review", rateService.validReview(rateService.findById(id)));
		flash.addFlashAttribute("enabled", "Review valid");
		return "redirect:/moderador/reviews";
	}
	
	@PostMapping("review/delete/{id}")
	public String deleteReview(@PathVariable(name="id") Integer id, RedirectAttributes flash) {
		rateService.removeReview(id);
		flash.addFlashAttribute("enabled", "Review deleted");
		return "redirect:/moderador/reviews";
	}
	
	@GetMapping("/inbox")
	public String inbox(Model model,Authentication auth) {
		String username = auth.getName();
		Usuario user = usuarioService.findByEmail(username);
		
		List<Mensaje> mensajes= mensajeService.findByIdReceptor(user);
		model.addAttribute("messages",mensajes);
		model.addAttribute("emisor",0);
		return "moderador/mensajes";
		
	}
	
	@GetMapping("/sendMessage/{id}")
	public String sendMessage(@PathVariable int id,Model model,Authentication auth) {
		

		model.addAttribute("mensaje",new Mensaje());
		model.addAttribute("id",id);

		return "moderador/enviarMensaje";
	}
	
	@PostMapping("/sendMessage")
	public String PostsendMessage(@ModelAttribute Mensaje mensaje,Model model,Authentication auth) {
		String username = auth.getName();
		Usuario user = usuarioService.findByEmail(username);
		
		mensaje.setIdEmisor(user);
		model.addAttribute(mensajeService.save(mensaje));
		
		return "redirect:/moderador/inbox";
	}
	
	@GetMapping("/viewSentMessages")
	public String viewSentMessages(Model model,Authentication auth) {
		String username = auth.getName();
		Usuario user = usuarioService.findByEmail(username);
		
		List<Mensaje> mensajes= mensajeService.findByIdEmisor(user);
		model.addAttribute("messages",mensajes);
		model.addAttribute("emisor",1);
		return "moderador/mensajes";
		
	}
	
	@PostMapping("deleteMessage/{id}")
	public String deleteMessage(@PathVariable(name="id") Integer id, RedirectAttributes flash) {
		mensajeService.removeMensaje(id);
		flash.addFlashAttribute("enabled", "Message deleted");
		return "redirect:/moderador/inbox";
	}
}
