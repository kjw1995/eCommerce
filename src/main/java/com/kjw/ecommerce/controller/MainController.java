package com.kjw.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.ecommerce.common.url.CommonUrl.CommonURL;

@Controller
public class MainController {

	@GetMapping("/")
	public String index() {
		return "redirect:" + CommonURL.PAGE_MAIN;
	}

	@GetMapping(CommonURL.PAGE_MAIN)
	public ModelAndView getMainPage() {
		return new ModelAndView("/main");
	}

}
