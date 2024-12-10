package com.kjw.ecommerce.controller.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

	@GetMapping("/register")
	public ModelAndView getRegisterPage() {
		return new ModelAndView("/register/register");
	}

}
