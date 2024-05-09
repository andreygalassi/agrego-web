package br.com.agrego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Pagina1Controller {

	@GetMapping("/pagina1")
	public ModelAndView helloWorld(Model model) {
		ModelAndView mv = new ModelAndView("pagina1");
		mv.addObject("title", "pagina1");
		mv.addObject("description", "Thymeleaf tutorial");
		return mv;
	}
}