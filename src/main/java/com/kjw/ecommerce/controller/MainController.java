package com.kjw.ecommerce.controller;

import com.kjw.ecommerce.controller.common.url.CommonUrl.CommonUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping({CommonUrl.ROOT_URI, CommonUrl.MAIN})
    public ModelAndView getMainPage() {
        return new ModelAndView("/main");
    }

}
