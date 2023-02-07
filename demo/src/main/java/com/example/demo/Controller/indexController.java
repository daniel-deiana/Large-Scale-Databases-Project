package com.example.demo.Controller;
import com.example.demo.Utilities.SVariables;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sessionVariables")
public class indexController {
		@GetMapping("/index")
		public String index(Model model){

			SVariables sv = (SVariables) model.getAttribute("sessionVariables");
			if(sv==null){
				model.addAttribute("sessionVariables",new SVariables());
			}
			else{
				sv.myself = null;
			}

			return "index";
		}
}
