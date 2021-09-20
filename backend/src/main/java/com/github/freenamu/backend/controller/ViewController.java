package com.github.freenamu.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/")
    public String root() {
        return "redirect:/view/";
    }

    @GetMapping("/view/**")
    public String index() {
        return "/index.html";
    }
}
