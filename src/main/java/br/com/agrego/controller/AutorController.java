package br.com.agrego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/autor")
public class AutorController {

	@GetMapping("/consultar")
	public ModelAndView helloWorld(Model model) {
		ModelAndView mv = new ModelAndView("autor/autor");
		mv.addObject("titulo", "Autor");
		return mv;
	}
}