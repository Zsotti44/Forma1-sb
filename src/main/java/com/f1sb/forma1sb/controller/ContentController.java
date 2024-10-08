package com.f1sb.forma1sb.controller;

import com.f1sb.forma1sb.model.repositories.PilotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {
    @Autowired private  GPController gpController;
    @Autowired private  PilotaController pilotaController;

    public ContentController(GPController gpController, PilotaController pilotaController) {
        this.pilotaController = pilotaController;
        this.gpController = gpController;
    }

    @GetMapping({"","/"})
    public String home(Model model) {
        model.addAttribute("countPilots",pilotaController.countPilots());
        model.addAttribute("countGP",gpController.countGPs());
        return "home";
    }
    @GetMapping("/sign-in")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }
}
