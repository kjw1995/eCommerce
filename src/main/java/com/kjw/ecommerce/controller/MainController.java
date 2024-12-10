package com.kjw.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.ecommerce.controller.common.url.CommonUrl.CommonURL;

@Controller
public class MainController {

	@GetMapping({CommonURL.ROOT_URI, CommonURL.MAIN})
	public ModelAndView getMainPage() {
		return new ModelAndView("/main");
	}

}
