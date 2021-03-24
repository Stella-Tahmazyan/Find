package com.surprise.am.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {

    @GetMapping(value = {"/login","/"})
    public String loginPage() {

        return "login";

    }

    @GetMapping("/home")
    public String page() {
        return "home";
    }

}
