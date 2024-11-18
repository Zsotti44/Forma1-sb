package com.f1sb.forma1sb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")

public class AdminController {
    @GetMapping({"home","/",""})
    public String home(Model model) {
        model.addAttribute("activePage","admin/home");
        return "admin/home";
    }


}
