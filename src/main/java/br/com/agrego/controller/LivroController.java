package br.com.agrego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/livro")
@RolesAllowed("LIVRO")
public class LivroController {

	@RolesAllowed("LIVRO_VISUALIZAR")
	@GetMapping("/consultar")
	public ModelAndView helloWorld(Model model) {
		ModelAndView mv = new ModelAndView("livro/index");
		mv.addObject("titulo", "Livro");
		return mv;
	}
}