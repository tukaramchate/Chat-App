// src/main/java/com/tukaram/controller/PageController.java
package com.tukaram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "index"; // Spring will look in templates/index.html
    }
}