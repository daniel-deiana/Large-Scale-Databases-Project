package com.example.demo.Controller;
import com.example.demo.Model.User;
import com.example.demo.Utilities.SVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class homeController {
		@GetMapping("/home")
		public String home(@SessionAttribute("SVariables") SVariables sv, Model model){
				model.addAttribute("username",sv);
				return "home";
		}
}
