package idd.rechercheIndus.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/aboutus")
	public String about(Model model) {
		return "about";
	}
	
	@GetMapping("/work")
	public String work(Model model) {
		return "work";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		return "contact";
	}
}
