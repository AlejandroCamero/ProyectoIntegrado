package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;

@Controller
public class AuthController {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/auth/login")
	public String login(@RequestParam(value="error",required=false) String error,@RequestParam(value="logout",required=false) String logout,
			@RequestParam(value="notEnable",required=false) String enable,Model model,RedirectAttributes redirAttrs) {
		
		if(error!=null) {
			redirAttrs.addFlashAttribute("fallo", "Email or password wrong");
			return "redirect:/auth/login";
		}if(logout!=null){
			redirAttrs.addFlashAttribute("fallo", "Logout successfully");
			return "redirect:/auth/login";
		}if(enable!=null){
			redirAttrs.addFlashAttribute("fallo", "User not activated, please wait to be activated by the administrator");
			return "redirect:/auth/login";
		}else {
			model.addAttribute("usuario",new Usuario());
			return "auth/login";
		}
	}
	
	@GetMapping("/auth/register")
	public String register(Model model) {
		model.addAttribute("user",new Usuario());
		return "auth/register";
	}
	
	@PostMapping("/auth/register")
	public String register(@Valid @ModelAttribute Usuario usuario, BindingResult bindingResult, RedirectAttributes flash) {
		if(bindingResult.hasErrors()) {
			flash.addFlashAttribute("fallo",bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/auth/register";
		}
		
		if(usuarioService.findByEmail(usuario.getEmail())==null) {
			usuario.setEnabled(false);
			usuario.setRole("ROLE_CLIENTE");
			usuarioService.register(usuario);
			flash.addFlashAttribute("mensaje","Registered successfully, please wait to be activated by the administrator");
			return "redirect:/public";
		}else {
			flash.addFlashAttribute("fallo","This email already exists");
			return "redirect:/auth/register";
		}

		
	}
	
}
