package br.com.agrego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@GetMapping("/index")
	public ModelAndView helloWorld(Model model) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("title", "index");
		mv.addObject("description", "Thymeleaf tutorial");
		return mv;
	}
}