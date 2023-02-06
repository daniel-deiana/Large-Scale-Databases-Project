package com.example.demo.Controller;
import com.example.demo.Utilities.SVariables;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sessionVariables")
public class homeController {
		@GetMapping("/home")
		public String home(Model model) {
			if(model.getAttribute("sessionVariables") == null)
				model.addAttribute("sessionVariables", new SVariables());
			return "home";
		}
}
