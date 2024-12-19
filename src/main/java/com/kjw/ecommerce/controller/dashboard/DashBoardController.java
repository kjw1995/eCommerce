package com.kjw.ecommerce.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.ecommerce.common.url.CommonUrl.CommonURL;

@Controller
public class DashBoardController {

	@GetMapping(CommonURL.PAGE_DASHBOARD)
	public ModelAndView getDashboardPage() {
		return new ModelAndView("/dashboard/dashboard");
	}

}
