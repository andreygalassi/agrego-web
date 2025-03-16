package br.com.agrego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/livro")
public class LivroController {

	@GetMapping("/consultar")
	public ModelAndView helloWorld(Model model) {
		ModelAndView mv = new ModelAndView("livro/livro");
		mv.addObject("titulo", "Livro");
		return mv;
	}
}