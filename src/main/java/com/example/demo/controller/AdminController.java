package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Usuario;
import com.example.demo.service.GeneroService;
import com.example.demo.service.LibroService;
import com.example.demo.service.RateService;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private GeneroService generoService;
	
	@Autowired
	private RateService rateService;
	
	@GetMapping("/users")
	public String usuarios(Model model) {
		model.addAttribute("usuarios",usuarioService.listAll());
		return "admin/usuarios";
	}
	
	@PostMapping("disableUser/{id}")
	public String disable(@PathVariable int id, Model model,RedirectAttributes flash) {
		Usuario us = usuarioService.findById(id);
		model.addAttribute("user", usuarioService.disableUser(us));
		flash.addFlashAttribute("disabled", "User disabled");
		return "redirect:/admin/users";
	}

	@PostMapping("enableUser/{id}")
	public String enable(@PathVariable int id, Model model,RedirectAttributes flash) {
		Usuario us = usuarioService.findById(id);
		model.addAttribute("user", usuarioService.enableUser(us));
		flash.addFlashAttribute("enabled", "User enabled");
		return "redirect:/admin/users";
	}
	
	@PostMapping("user/delete/{id}")
	public String deleteAlumno(@PathVariable(name="id") Integer id, RedirectAttributes flash) {
		usuarioService.removeUser(id);
		flash.addFlashAttribute("enabled", "User deleted");
		return "redirect:/admin/users";
	}
	

}
