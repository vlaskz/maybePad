package com.vlaskz.maybepad.Home;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public Resource home() {
        return new ClassPathResource("static/home.html");
    }

    @GetMapping("/{path:[^.]*}")
    @ResponseBody
    public Resource dynamicRouteHandler(@PathVariable String path) {
        return new ClassPathResource("static/defaultPage.html");
    }
}