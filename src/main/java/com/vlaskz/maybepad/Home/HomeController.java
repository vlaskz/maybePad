package com.vlaskz.maybepad.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/{path:[^.]*}")
    public String forward(@PathVariable String path){
        return "forward:/";
    }
}
