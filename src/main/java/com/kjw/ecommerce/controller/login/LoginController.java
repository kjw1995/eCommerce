package com.kjw.ecommerce.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.ecommerce.common.url.CommonUrl.CommonURL;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	@GetMapping(CommonURL.VIEW_LOGIN)
	public ModelAndView getLoginPage() {
		return new ModelAndView("/login/login");
	}

}
